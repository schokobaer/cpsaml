#!/usr/bin/env python
# license removed for brevity

import rospy
from std_msgs.msg import Bool, String
from std_srvs.srv import Trigger, TriggerResponse
from roomba.srv import StringSrv, StringSrvResponse
from client import WheelsClient, SpeakerClient

class BaseNavigator:
    def __init__(self):
    	self.orientating = False
    	self.position = ""
    	self.target = ""
        self.wheels = WheelsClient("wheels", onLiftedChanged=self.handle_wheels_lifted, onSpeedChanged=self.handle_wheels_speed)
        self.speaker = SpeakerClient("speaker", onPlayingChanged=self.handle_speaker_playing)
        
        self.orientatingPub = None
        self.positionPub = None
        self.targetPub = None

    def fetch(self):
        pass
	
    def handle_wheels_lifted(self, lifted):
        pass
    
    def handle_wheels_speed(self, speed):
        pass
    
    def handle_speaker_playing(self, playing):
        pass
    
	
    def handle_navigate(self, req):
    	return StringSrvResponse(True)
    
    def handle_stop(self, req):
    	return TriggerResponse(True, "")
    

    def init_ros(self):
        rospy.loginfo("init ros ...")
        self.orientatingPub = rospy.Publisher('orientating', Bool, queue_size=10)
        self.positionPub = rospy.Publisher('position', String, queue_size=10)
        self.targetPub = rospy.Publisher('target', String, queue_size=10)
        
        rospy.init_node('navigator')
        
        rospy.Service('navigate', StringSrv, self.handle_navigate)
        rospy.Service('stop', Trigger, self.handle_stop)
        
        rospy.loginfo("init ros finished")


    def spin(self):
        rate = rospy.Rate(10) # 10hz
        while not rospy.is_shutdown():
            # Read bot data
            self.fetch()
            
            # publish
            self.orientatingPub.publish(self.orientating)
            self.positionPub.publish(self.position)
            self.targetPub.publish(self.target)
            rospy.loginfo("orientating: {} / position: {} / target: {}".format(self.orientating, self.position, self.target))

            rate.sleep()

