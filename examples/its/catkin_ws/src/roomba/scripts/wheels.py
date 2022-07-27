#!/usr/bin/env python
# license removed for brevity

import rospy
from component import BaseWheels
from bot import Roomba
from threading import Thread, Event

class Wheels(BaseWheels):
    def __init__(self):
        BaseWheels.__init__(self)
        self.roomba = Roomba()
        self.roomba.connect()
        self.event = None
    
    def fetch(self):
        self.speed.left = self.roomba.speedLeft
        self.speed.right = self.roomba.speedRight
        self.lifted = self.roomba.lifted
     

    def handle_drive(self, req):
    	self.roomba.drive(req.data, req.data)
        return BaseWheels.handle_drive(self, req)
    	
    def handle_stop(self, req):
    	self.roomba.stop()
        return BaseWheels.handle_stop(self, req)
    	
    def handle_turn(self, req):
    	thread = Thread(target=self.turn_callback, name='WheelTurnThread', args=(req.data,))
        thread.daemon = True
        if self.event != None:
            self.event.set()
            self.event = None
        thread.start()
        return BaseWheels.handle_turn(self, req)

    def turn_callback(self, angle):
        self.event = Event()
        clockwise = 1
        if angle < 0:
            clockwise = -1
            angle = angle * (-1)
        
        # 6.45sec for 180
        angle = angle % 360
        sec = (float(angle) / 360.0) * 12.9
        self.roomba.drive((-50) * clockwise, 50 * clockwise)
        self.event.wait(timeout=sec)
        self.roomba.stop()


def turn_time(angle):
    if angle < 0:
        angle = angle * (-1)
    
    # 6.45sec for 180
    angle = angle % 360
    sec = (float(angle) / 360.0) * 12.9
    return sec
	
if __name__ == '__main__':
    try:
        wheels = Wheels()
        wheels.init_ros()
        wheels.spin()
    except rospy.ROSInterruptException:
        pass
