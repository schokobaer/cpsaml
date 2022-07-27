#!/usr/bin/env python
# license removed for brevity

from roomba.scripts.component.roomba_dt_base import RoombaState
import rospy
from component import BaseRoomba
from taskworker import TaskWorker

class Roomba(BaseRoomba):
    def __init__(self):
        BaseRoomba.__init__(self)
        self.taskworker = TaskWorker(self)
        self.battery.onDockedChanged = self.on_battery_docked_changed
        self.wheels.onLiftedChanged = self.on_wheels_lifted_changed
    
    def on_battery_docked_changed(self, docked):
        if docked:
            self.state = RoombaState.DOCKED

    def on_wheels_lifted_changed(self, lifted):
        if lifted:
            self.state = RoombaState.IDLE

    def handle_drive(self, speed):
        if self.state == RoombaState.SEEKING:
            return
        if self.state == RoombaState.TASK:
            self.taskworker.stop()
    	if self.state == RoombaState.NAVIGATING:
            self.navigator.stop()

        self.wheels.drive(speed)
        self.state = RoombaState.MANUAL
    	
    def handle_turn(self, angle):
    	if self.state == RoombaState.SEEKING:
            return
        if self.state == RoombaState.TASK:
            self.taskworker.stop()
    	if self.state == RoombaState.NAVIGATING:
            self.navigator.stop()

        self.wheels.turn(angle)
        self.state = RoombaState.MANUAL
    	
    def handle_stop(self):
        if self.state == RoombaState.MANUAL or self.state == RoombaState.SEEKING:
    	    self.wheels.stop()
        if self.state == RoombaState.NAVIGATING:
            self.navigator.stop()
        if self.state == RoombaState.TASK:
            self.taskworker.stop()

        self.state = RoombaState.IDLE
    	
    def handle_stack(self, speed, time, angle):
    	self.taskworker.stack(speed, time, angle)
    	
    def handle_start(self):
    	self.taskworker.start()
        self.state = RoombaState.TASK
    	
    def handle_clear(self):
    	self.taskworker.clear()
    	
    def handle_dock(self):
        self.state = RoombaState.SEEKING
    	pass
    	
    def handle_navigate(self, target):
    	self.navigator.navigate(target)
        self.state = RoombaState.NAVIGATING
    	
	
if __name__ == '__main__':
    try:
        roomba = Roomba()
        roomba.init_ros()
        roomba.rabbit.init_rabbit()
        roomba.spin()
    except rospy.ROSInterruptException:
        pass
