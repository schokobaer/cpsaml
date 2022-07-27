package Speaker {
		
	private import Cps::RosNodeAnnotation;
	private import ScalarValues::*;
	
    action def 'Play Song'(in songNumber: Integer);
    
    
	part def Speaker {
		@RosNodeAnnotation;
		
		attribute playing: Boolean;
				        
		perform action playSong: 'Play Song';
	}
}
