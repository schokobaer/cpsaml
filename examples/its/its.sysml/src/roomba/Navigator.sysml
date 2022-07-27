package Navigator {
	
	private import Cps::RosNodeAnnotation;
	private import ScalarValues::*;
	private import RoombaUnits::*;
	private import Wheels::Wheels;
	private import Speaker::Speaker;
	
	action def Navigate(in target: String);
	action def Stop();
	
	part def Navigator {
		@RosNodeAnnotation;
	
		attribute target: String;
		attribute orientating: Boolean;
		attribute position: String;	
		
		ref part wheels: Wheels;
		ref part speaker: Speaker;
		
		perform action navigate: Navigate;
		perform action stop: Stop;
	}
}