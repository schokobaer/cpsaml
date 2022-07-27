#!/usr/bin/env python
# license removed for brevity

import rospy
from std_msgs.msg import Bool
from roomba.srv import IntSrv, IntSrvResponse

class BaseSpeaker:
    def __init__(self):
    	self.playing = False
        
        self.playingPub = None

    def fetch(self):
        pass
	
	
    def handle_playSong(self, req):
    	return IntSrvResponse(True)
    

    def init_ros(self):
        rospy.loginfo("init ros ...")
        self.playingPub = rospy.Publisher('playing', Bool, queue_size=10)
        
        rospy.init_node('speaker')
        
        rospy.Service('playsong', IntSrv, self.handle_playSong)
        
        rospy.loginfo("init ros finished")


    def spin(self):
        rate = rospy.Rate(10) # 10hz
        while not rospy.is_shutdown():
            # Read bot data
            self.fetch()
            
            # publish
            self.playingPub.publish(self.playing)
            rospy.loginfo("playing: {}".format(self.playing))

            rate.sleep()

