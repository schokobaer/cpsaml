package RoombaUnits {
	private import ScalarValues::*;
	private import Cps::*;
	
	attribute def Percent :> Real{ @BoundsAnnotation{ min = 0.0; max = 1.0; } }
	
	attribute def RPM :> Integer { @BoundsAnnotation{ min = -200; max = 200; } }
	
	attribute def Second :> Real { @BoundsAnnotation{ min = 0; max = 255; } }
	
	attribute def Degree :> Real { @BoundsAnnotation{ min = -360; max = 360; } }
}