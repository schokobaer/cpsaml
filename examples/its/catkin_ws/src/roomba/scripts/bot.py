
from threading import Thread, Event

class Roomba:
    def __init__(self):
        self.speedLeft = 0
        self.speedRight = 0
        self.bumper = False
        self.lifted = False
        self.energy = 1.0
        self.docked = False

    def connect(self):
        #self.thread = Thread(target=self.navigate_task_2, name='NavigationWorkerThread')
        pass

    def drive(self, left, right):
        self.speedLeft = left
        self.speedRight = right

    def stop(self):
        self.speedLeft = 0
        self.speedRight = 0

    def spin(self):
        pass