#!/usr/bin/env python
# license removed for brevity

import rospy
from std_msgs.msg import Bool
from roomba.msg import Speed
from std_srvs.srv import Trigger, TriggerResponse
from roomba.srv import IntSrv, IntSrvResponse, FloatSrv, FloatSrvResponse

class BaseWheels:
    def __init__(self):
    	self.lifted = False
    	self.speed = Speed(0, 0)
        
        self.liftedPub = None
        self.speedPub = None

    def fetch(self):
        pass
	
	
    def handle_drive(self, req):
    	return IntSrvResponse(True)
    
    def handle_stop(self, req):
    	return TriggerResponse(True, "")
    
    def handle_turn(self, req):
    	return FloatSrvResponse(True)
    

    def init_ros(self):
        rospy.loginfo("init ros ...")
        self.liftedPub = rospy.Publisher('lifted', Bool, queue_size=10)
        self.speedPub = rospy.Publisher('speed', Speed, queue_size=10)
        
        rospy.init_node('wheels')
        
        rospy.Service('drive', IntSrv, self.handle_drive)
        rospy.Service('stop', Trigger, self.handle_stop)
        rospy.Service('turn', FloatSrv, self.handle_turn)
        
        rospy.loginfo("init ros finished")


    def spin(self):
        rate = rospy.Rate(10) # 10hz
        while not rospy.is_shutdown():
            # Read bot data
            self.fetch()
            
            # publish
            self.liftedPub.publish(self.lifted)
            self.speedPub.publish(self.speed)
            rospy.loginfo("lifted: {} / speed: {}".format(self.lifted, self.speed))

            rate.sleep()

