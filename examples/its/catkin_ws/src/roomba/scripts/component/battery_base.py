#!/usr/bin/env python
# license removed for brevity

import rospy
from std_msgs.msg import Float32, Bool

class BaseBattery:
    def __init__(self):
    	self.docked = False
    	self.energy = 0.0
        
        self.dockedPub = None
        self.energyPub = None

    def fetch(self):
        pass
	
	

    def init_ros(self):
        rospy.loginfo("init ros ...")
        self.dockedPub = rospy.Publisher('docked', Bool, queue_size=10)
        self.energyPub = rospy.Publisher('energy', Float32, queue_size=10)
        
        rospy.init_node('battery')
        
        
        rospy.loginfo("init ros finished")


    def spin(self):
        rate = rospy.Rate(10) # 10hz
        while not rospy.is_shutdown():
            # Read bot data
            self.fetch()
            
            # publish
            self.dockedPub.publish(self.docked)
            self.energyPub.publish(self.energy)
            rospy.loginfo("docked: {} / energy: {}".format(self.docked, self.energy))

            rate.sleep()

