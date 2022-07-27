#!/usr/bin/env python
# license removed for brevity

import rospy
from component import BaseSpeaker

class Speaker(BaseSpeaker):
    def __init__(self):
        BaseSpeaker.__init__(self)
    
    def fetch(self):
        # TODO: IMPLEMENT
        pass
     

    def handle_playSong(self, req):
    	# TODO: IMPLEMENT
    	pass
    	
	
if __name__ == '__main__':
    try:
        speaker = Speaker()
        speaker.init_ros()
        speaker.spin()
    except rospy.ROSInterruptException:
        pass
