import rospy
from std_msgs.msg import Bool
from roomba.msg import Speed
from std_srvs.srv import Trigger
from roomba.srv import IntSrv, FloatSrv

class WheelsClient:
    def __init__(self, name = "wheels", onLiftedChanged = None, onSpeedChanged = None):
        self._name = name
        
        # properties
        self.lifted = False
        self.speed = Speed(0, 0)
        
        # event handler
        self.onLiftedChanged = onLiftedChanged
        self.onSpeedChanged = onSpeedChanged

        # services:
        self.drive = None
        self.stop = None
        self.turn = None
    
    def init_ros(self):
        # services
        rospy.wait_for_service(self._name + '/drive')
        self.drive = rospy.ServiceProxy(self._name + '/drive', IntSrv)
        rospy.wait_for_service(self._name + '/stop')
        self.stop = rospy.ServiceProxy(self._name + '/stop', Trigger)
        rospy.wait_for_service(self._name + '/turn')
        self.turn = rospy.ServiceProxy(self._name + '/turn', FloatSrv)

        # topics
        rospy.Subscriber(self._name + '/lifted', Bool, self.handle_lifted)
        rospy.Subscriber(self._name + '/speed', Speed, self.handle_speed)

    def handle_lifted(self, data):
        if self.lifted != data.data:
            self.lifted = data.data
            if self.onLiftedChanged != None:
                self.onLiftedChanged(self.lifted)
    
    def handle_speed(self, data):
        if self.speed.left != data.left or self.speed.right != data.right:
            self.speed = data
            if self.onSpeedChanged != None:
                self.onSpeedChanged(self.speed)
    

        
    def to_map(self):
        return {
            'lifted': self.lifted,
            'speed': {
                'left': self.speed.left,
                'right': self.speed.right
            }
        }
