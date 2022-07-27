FROM ros:kinetic-robot

# installs
RUN /bin/bash -c 'apt-get update -y; apt-get install python2.7 -y; apt-get install python-pika -y; apt-get install python-enum -y'

# create catkin workspace
RUN ["mkdir", "-p", "/catkin_ws/src"]
WORKDIR /catkin_ws

# add package
ADD ./src/roomba /catkin_ws/src
ADD ./roomba.sh /catkin_ws/roomba.sh

# build workspace
RUN /bin/bash -c '. /opt/ros/kinetic/setup.bash; cd /catkin_ws; catkin_make'
	
# set env variables
ENV rabbit_host 'localhost'
ENV roomba_id 'roomba0'

# start launcher	
ENTRYPOINT /bin/bash -c '. /catkin_ws/roomba.sh ${rabbit_host} ${roomba_id}'
