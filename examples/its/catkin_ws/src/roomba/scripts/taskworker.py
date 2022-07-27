
from collections import namedtuple
from threading import Thread, Event
from wheels import turn_time
import rospy

Task = namedtuple('Task', 'speed time angle')

class TaskWorker:

    def __init__(self, dt):
        self.dt = dt
        self.event = None
        

    def stack(self, speed, time, angle):
        self.dt.tasks.append(Task(angle=angle, time=time, speed=speed))
        
    def start(self):
        if self.event == None:
            thread = Thread(target=self.bg_worker)
            thread.daemon = True
            thread.start()

    def stop(self):
        if self.event != None:
            self.event.set()
            self.event = None

    def clear(self):
        self.dt.tasks = []

    def bg_worker(self):
        while(len(self.dt.tasks) > 0):
            self.event = Event()
            task = self.dt.tasks.pop(0)
            self.dt.wheels.drive(task.speed)
            if self.event.wait(timeout=task.time) > 0:
                self.event = None
                return
            self.dt.wheels.stop()
            self.event = Event()
            sec = turn_time(task.angle)
            self.dt.wheels.turn(task.angle)
            if self.event.wait(timeout=sec) > 0:
                self.event = None
                return
            self.event = None

            