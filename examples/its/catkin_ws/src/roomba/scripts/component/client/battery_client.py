import rospy
from std_msgs.msg import Float32, Bool

class BatteryClient:
    def __init__(self, name = "battery", onDockedChanged = None, onEnergyChanged = None):
        self._name = name
        
        # properties
        self.docked = False
        self.energy = 0.0
        
        # event handler
        self.onDockedChanged = onDockedChanged
        self.onEnergyChanged = onEnergyChanged

        # services:
    
    def init_ros(self):
        # services

        # topics
        rospy.Subscriber(self._name + '/docked', Bool, self.handle_docked)
        rospy.Subscriber(self._name + '/energy', Float32, self.handle_energy)

    def handle_docked(self, data):
        if self.docked != data.data:
            self.docked = data.data
            if self.onDockedChanged != None:
                self.onDockedChanged(self.docked)
    
    def handle_energy(self, data):
        if self.energy != data.data:
            self.energy = data.data
            if self.onEnergyChanged != None:
                self.onEnergyChanged(self.energy)
    

        
    def to_map(self):
        return {
            'docked': self.docked,
            'energy': self.energy
        }
