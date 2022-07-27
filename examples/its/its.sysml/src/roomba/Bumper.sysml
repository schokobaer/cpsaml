package Bumper {
		
	private import Cps::RosNodeAnnotation;
	private import ScalarValues::*;
	
	part def Bumper {
		@RosNodeAnnotation;
		
        attribute pushed: Boolean;
	}
}
