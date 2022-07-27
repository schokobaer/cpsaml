#!/usr/bin/env python
# license removed for brevity

import rospy
from component import BaseBumper

class Bumper(BaseBumper):
    def __init__(self):
        BaseBumper.__init__(self)
    
    def fetch(self):
        # TODO: IMPLEMENT
        pass
     

	
if __name__ == '__main__':
    try:
        bumper = Bumper()
        bumper.init_ros()
        bumper.spin()
    except rospy.ROSInterruptException:
        pass
