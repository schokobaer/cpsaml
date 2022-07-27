import rospy
from std_msgs.msg import Bool
from roomba.srv import IntSrv

class SpeakerClient:
    def __init__(self, name = "speaker", onPlayingChanged = None):
        self._name = name
        
        # properties
        self.playing = False
        
        # event handler
        self.onPlayingChanged = onPlayingChanged

        # services:
        self.playSong = None
    
    def init_ros(self):
        # services
        rospy.wait_for_service(self._name + '/playsong')
        self.playSong = rospy.ServiceProxy(self._name + '/playsong', IntSrv)

        # topics
        rospy.Subscriber(self._name + '/playing', Bool, self.handle_playing)

    def handle_playing(self, data):
        if self.playing != data.data:
            self.playing = data.data
            if self.onPlayingChanged != None:
                self.onPlayingChanged(self.playing)
    

        
    def to_map(self):
        return {
            'playing': self.playing
        }
