#!/usr/bin/env python
# license removed for brevity

import rospy
from std_msgs.msg import Bool

class BaseBumper:
    def __init__(self):
    	self.pushed = False
        
        self.pushedPub = None

    def fetch(self):
        pass
	
	

    def init_ros(self):
        rospy.loginfo("init ros ...")
        self.pushedPub = rospy.Publisher('pushed', Bool, queue_size=10)
        
        rospy.init_node('bumper')
        
        
        rospy.loginfo("init ros finished")


    def spin(self):
        rate = rospy.Rate(10) # 10hz
        while not rospy.is_shutdown():
            # Read bot data
            self.fetch()
            
            # publish
            self.pushedPub.publish(self.pushed)
            rospy.loginfo("pushed: {}".format(self.pushed))

            rate.sleep()

