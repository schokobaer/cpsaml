package Wheels {
		
	private import Cps::RosNodeAnnotation;
	private import RoombaUnits::*;
	private import ScalarValues::Boolean;
	
	attribute def Speed {
		attribute left: RPM;
		attribute right: RPM;
	}
	
    action def Drive(in speed: RPM);
    action def Stop();
    action def Turn(in angle: Degree);
    
    
	part def Wheels {
		@RosNodeAnnotation;
		
		attribute speed: Speed;
		attribute lifted: Boolean;
		
		perform action drive: Drive;
		perform action stop: Stop;
		perform action turn: Turn;
	}
}
