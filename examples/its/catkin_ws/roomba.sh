#!/bin/bash
/opt/ros/kinetic/setup.bash
source devel/setup.bash
echo "setup finished"
roscore > /dev/null &
sleep 3
echo "roscore started"
rosparam set /rabbit_host "$1"
echo "rabbit_host param set to $1"
roslaunch roomba roomba.launch roomba_id:=$2
