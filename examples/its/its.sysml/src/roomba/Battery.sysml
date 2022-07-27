package Battery {
		
	private import Cps::RosNodeAnnotation;
	private import ScalarValues::*;
	private import RoombaUnits::Percent;
		
	part def Battery {
		@RosNodeAnnotation;
		
        attribute energy: Percent;
        
        /* 
         * flag if the roomba is in its docking station and the battery gets loaded
         */
        attribute docked: Boolean;
	}
}
