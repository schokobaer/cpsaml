package TaskManager {
	
	private import Cps::RosNodeAnnotation;
	private import ScalarValues::*;
	private import RoombaUnits::*;
	
	attribute def Task {
		attribute speed: RPM;
		attribute time: Second;
		attribute angle: Degree;
	}
	
	action def AddTask(in speed: RPM, in time: Second, in angle: Degree);
	action def Start();
	action def Stop();
	action def Clear();
	
	part def TaskManager {
		@RosNodeAnnotation;
		
		attribute tasks: Task[*];
		attribute active: Boolean;
		
		perform action add: AddTask;
		perform action start: Start;
		perform action stop: Stop;
		perform action clear: Clear;
	}
}