import rospy
from std_msgs.msg import Bool, String
from std_srvs.srv import Trigger
from roomba.srv import StringSrv

class NavigatorClient:
    def __init__(self, name = "navigator", onOrientatingChanged = None, onPositionChanged = None, onTargetChanged = None):
        self._name = name
        
        # properties
        self.orientating = False
        self.position = ""
        self.target = ""
        
        # event handler
        self.onOrientatingChanged = onOrientatingChanged
        self.onPositionChanged = onPositionChanged
        self.onTargetChanged = onTargetChanged

        # services:
        self.navigate = None
        self.stop = None
    
    def init_ros(self):
        # services
        rospy.wait_for_service(self._name + '/navigate')
        self.navigate = rospy.ServiceProxy(self._name + '/navigate', StringSrv)
        rospy.wait_for_service(self._name + '/stop')
        self.stop = rospy.ServiceProxy(self._name + '/stop', Trigger)

        # topics
        rospy.Subscriber(self._name + '/orientating', Bool, self.handle_orientating)
        rospy.Subscriber(self._name + '/position', String, self.handle_position)
        rospy.Subscriber(self._name + '/target', String, self.handle_target)

    def handle_orientating(self, data):
        if self.orientating != data.data:
            self.orientating = data.data
            if self.onOrientatingChanged != None:
                self.onOrientatingChanged(self.orientating)
    
    def handle_position(self, data):
        if self.position != data.data:
            self.position = data.data
            if self.onPositionChanged != None:
                self.onPositionChanged(self.position)
    
    def handle_target(self, data):
        if self.target != data.data:
            self.target = data.data
            if self.onTargetChanged != None:
                self.onTargetChanged(self.target)
    

        
    def to_map(self):
        return {
            'orientating': self.orientating,
            'position': self.position,
            'target': self.target
        }
