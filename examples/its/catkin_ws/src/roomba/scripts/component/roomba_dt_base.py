#!/usr/bin/env python
# license removed for brevity

from collections import namedtuple
import sys
import random
import pika	
import rospy
from threading import Thread
import json
from client import WheelsClient, SpeakerClient, NavigatorClient, BatteryClient, BumperClient

Task = namedtuple('Task', 'speed time angle')

class RoombaState:
    DOCKED = 'DOCKED'
    NAVIGATING = 'NAVIGATING'
    TASK = 'TASK'
    IDLE = 'IDLE'
    SEEKING = 'SEEKING'
    MANUAL = 'MANUAL'

    @staticmethod
    def values():
        return [
            RoombaState.DOCKED,
            RoombaState.NAVIGATING,
            RoombaState.TASK,
            RoombaState.IDLE,
            RoombaState.SEEKING,
            RoombaState.MANUAL
        ]



class BaseRoomba:
    def __init__(self):
    	self.tasks = [Task(angle=45, time=3.3, speed=200)]
        self.state = RoombaState.IDLE
        self.rabbit = RabbitClient(self)
        self.wheels = WheelsClient("wheels")
        self.speaker = SpeakerClient("speaker")
        self.navigator = NavigatorClient("navigator")
        self.battery = BatteryClient("battery")
        self.bumper = BumperClient("bumper")
        
    def handle_drive(self, speed):
        pass
    
    def handle_turn(self, angle):
        pass
    
    def handle_stop(self):
        pass
    
    def handle_stack(self, speed, time, angle):
        pass
    
    def handle_start(self):
        pass
    
    def handle_clear(self):
        pass
    
    def handle_dock(self):
        pass
    
    def handle_navigate(self, target):
        pass
    

    def to_map(self):
    	return {
    	    'tasks': map(lambda t: {
    	        'angle': t.angle,
    	        'time': t.time,
    	        'speed': t.speed
    	    }, self.tasks),
    	    'state': self.state,
    	    'wheels': self.wheels.to_map(),
    	    'speaker': self.speaker.to_map(),
    	    'navigator': self.navigator.to_map(),
    	    'battery': self.battery.to_map(),
    	    'bumper': self.bumper.to_map()
    	}
	
    def init_ros(self):
        rospy.init_node('roomba_dt')
		
        if 1 > 0:
            # wheels
            rospy.loginfo("init wheels ...")
            self.wheels.init_ros()
            rospy.loginfo("init wheels finished")
            
            # speaker
            rospy.loginfo("init speaker ...")
            self.speaker.init_ros()
            rospy.loginfo("init speaker finished")
            
            # navigator
            rospy.loginfo("init navigator ...")
            self.navigator.init_ros()
            rospy.loginfo("init navigator finished")
            
            # battery
            rospy.loginfo("init battery ...")
            self.battery.init_ros()
            rospy.loginfo("init battery finished")
            
            # bumper
            rospy.loginfo("init bumper ...")
            self.bumper.init_ros()
            rospy.loginfo("init bumper finished")
        
        	
        rospy.loginfo("init ros finished")


    def spin(self):
        rate = rospy.Rate(10) # 10hz
        while not rospy.is_shutdown():

            # publish the Roomba to rabbit
            roomba_msg = self.to_map()
            msg_json = json.dumps(roomba_msg)

            self.rabbit.publish(msg_json)
            rospy.loginfo("{}".format(msg_json))

            rate.sleep()

class RabbitClient:
    def __init__(self, roomba):
        self.roomba = roomba
        self.rabbit_channel_out = None
        self.data_exchanger = 'data'
        self.data_routingkey = 'data.roomba'
        self.roomba_id = "roomba-{}".format(random.randint(10000, 99999))
        if len(sys.argv) > 1:
            self.roomba_id = sys.argv[1]
        self.action_exchanger = "action"
        self.action_routingkey = "action.{}".format(self.roomba_id)

    def init_rabbit(self):
        rospy.loginfo("init rabbit ...")

        rabbit_host = rospy.get_param('/rabbit_host', 'localhost')
        rospy.loginfo("rabbit_host={}".format(rabbit_host))
        connection1 = pika.BlockingConnection(pika.ConnectionParameters(host=rabbit_host))
        connection2 = pika.BlockingConnection(pika.ConnectionParameters(host=rabbit_host))

        # data channel data
        data_channel = connection1.channel()
        data_channel.exchange_declare(exchange=self.data_exchanger, durable=False, exchange_type='topic')

        # action channel action
        action_channel = connection2.channel()
        action_channel.exchange_declare(exchange=self.action_exchanger, exchange_type='topic')
        action_queue = action_channel.queue_declare(self.roomba_id, exclusive=True)
        action_queue_name = action_queue.method.queue
        action_channel.queue_bind(exchange=self.action_exchanger, queue=action_queue_name, routing_key=self.action_routingkey)
        # action_channel.basic_consume(queue=action_queue_name, on_message_callback=self.handle_rabbit_msg, auto_ack=True)
        action_channel.basic_consume(self.handle_rabbit_msg, action_queue_name, True)

        rabbitworker = Thread(target=self.rabbit_bg_task, name='RabbitMsgHandler', args=(action_channel,))
        rabbitworker.daemon = True
        rabbitworker.start()

        rospy.loginfo("init rabbit finished")
        self.data_channel = data_channel

    def publish(self, msg_json):
        self.data_channel.basic_publish(
            exchange=self.data_exchanger,
            routing_key=self.data_routingkey,
            properties=pika.BasicProperties(
                headers={'roomba_id': self.roomba_id}
            ),
            body=msg_json)

    def handle_rabbit_msg(self, ch, method, properties, body):
        rospy.loginfo("received a rabbit message: {}".format(body))
        action = properties.headers['action']
        args = json.loads(json.loads(body))
        if action == 'drive':
            speed = args["speed"]
            self.roomba.handle_drive(speed)
        if action == 'turn':
            angle = args["angle"]
            self.roomba.handle_turn(angle)
        if action == 'stop':
            self.roomba.handle_stop()
        if action == 'stack':
            speed = args["speed"]
            time = args["time"]
            angle = args["angle"]
            self.roomba.handle_stack(speed, time, angle)
        if action == 'start':
            self.roomba.handle_start()
        if action == 'clear':
            self.roomba.handle_clear()
        if action == 'dock':
            self.roomba.handle_dock()
        if action == 'navigate':
            target = args["target"]
            self.roomba.handle_navigate(target)

    def rabbit_bg_task(self, channel):
        channel.start_consuming()
