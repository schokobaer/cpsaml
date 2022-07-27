#!/usr/bin/env python
# license removed for brevity

import rospy
from component import BaseBattery

class Battery(BaseBattery):
    def __init__(self):
        BaseBattery.__init__(self)
    
    def fetch(self):
        # TODO: IMPLEMENT
        pass
     

	
if __name__ == '__main__':
    try:
        battery = Battery()
        battery.init_ros()
        battery.spin()
    except rospy.ROSInterruptException:
        pass
