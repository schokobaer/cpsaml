package Roomba {
	
	private import Cps::CpsAnnotation;
	private import Battery::Battery;
	private import Bumper::Bumper;
	private import Wheels::Wheels;
	private import Speaker::Speaker;
	private import Navigator::Navigator;
	private import ScalarValues::*;
	private import RoombaUnits::*;
	private import RoombaState::RoombaState;
	
	attribute def Task {
		attribute speed: RPM;
		attribute time: Second;
		attribute angle: Degree;
	}
	
    action def Drive(in speed: RPM);
    
    action def Turn(in angle: Degree);
    
    /**
     * stops all current activites
     */
    action def Stop();
    
    /**
     * Adds a task to execute on a stack
     */
    action def Stack(in speed: RPM, in time: Second, in angle: Degree);
    
    /**
     * Starts executing the stack
     */
    action def Start();
    
    /**
     * Clears the Stack
     */
    action def Clear();
    
    /**
     * Drives the roomba to its docking station
     * for loading the battery.
     */
    action def Dock();
    
    action def Navigate(in target: String);
    
	
	part def Roomba {
		@CpsAnnotation { mobile = true; }
		
		state 'state': RoombaState;
		attribute tasks: Task[*];

		part battery: Battery;
		part bumper: Bumper;
		part wheels: Wheels;
		part speaker: Speaker;
		part navigator: Navigator;
		
		perform action drive: Drive;
		perform action turn: Turn;
		perform action stop: Stop;
		perform action stack: Stack;
		perform action start: Start;
		perform action clear: Clear;
		perform action dock: Dock;
		perform action navigate: Navigate;
	}
}
