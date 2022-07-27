import rospy
from std_msgs.msg import Bool

class BumperClient:
    def __init__(self, name = "bumper", onPushedChanged = None):
        self._name = name
        
        # properties
        self.pushed = False
        
        # event handler
        self.onPushedChanged = onPushedChanged

        # services:
    
    def init_ros(self):
        # services

        # topics
        rospy.Subscriber(self._name + '/pushed', Bool, self.handle_pushed)

    def handle_pushed(self, data):
        if self.pushed != data.data:
            self.pushed = data.data
            if self.onPushedChanged != None:
                self.onPushedChanged(self.pushed)
    

        
    def to_map(self):
        return {
            'pushed': self.pushed
        }
