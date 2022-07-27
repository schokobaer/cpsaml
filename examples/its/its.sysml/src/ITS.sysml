package ITS {
    
    private import Cps::SystemAnnotation;
    private import Roomba::Roomba;
    private import ScalarValues::String;
    
    /**
     * To request an arbitrary idle roomba to
     * a specific place.
     */
    action def Request(in target: String);
    

	part def ITS {
		@SystemAnnotation;
		
		part roombas: Roomba[*];
		
		
		perform action request: Request;
	}
	
	
	part its: ITS;
}
