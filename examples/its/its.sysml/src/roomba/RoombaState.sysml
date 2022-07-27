package RoombaState {
	
	attribute def drive;
	attribute def stop;
	attribute def start;
	attribute def finished;
	attribute def dock;
	attribute def navigate;  
	
	state def RoombaState {
		entry; then IDLE;
		
		state IDLE;
			accept drive then MANUAL;
			accept start then TASK;
			accept dock then SEEKING;
			accept navigate then NAVIGATING;
		
		state MANUAL;
			accept stop then IDLE;
			accept start then TASK;
			accept dock then SEEKING;
			accept navigate then NAVIGATING;
			
		state TASK;
			accept stop then IDLE;
			accept dock then SEEKING;
			accept navigate then NAVIGATING;
		
		state SEEKING;
			accept finished then DOCKED;
			accept stop then IDLE;
		
		state DOCKED;
			accept drive then MANUAL;
			accept navigate then NAVIGATING;
		
		state NAVIGATING;
			accept drive then MANUAL;
			accept stop then IDLE;
			accept start then TASK;
			accept dock then SEEKING;
			accept finished then IDLE;
		
		
	}
}