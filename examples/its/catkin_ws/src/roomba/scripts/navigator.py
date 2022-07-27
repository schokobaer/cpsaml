#!/usr/bin/env python
# license removed for brevity

import rospy
from component import BaseNavigator

class Navigator(BaseNavigator):
    def __init__(self):
        BaseNavigator.__init__(self)
        self.position = None
        self.target = None
    
    def handle_navigate(self, req):
    	self.target = req.data
        return BaseNavigator.handle_navigate(self, req)
    	
    def handle_stop(self, req):
    	self.target = None
        return BaseNavigator.handle_stop(self, req)
    	
	
if __name__ == '__main__':
    try:
        navigator = Navigator()
        navigator.init_ros()
        navigator.spin()
    except rospy.ROSInterruptException:
        pass
