package Cps {
	
	private import ScalarValues::*;
	
	/*
	constraint def RangeConstraint (min: NumericalValue, max: NumericalValue) {
		min < max
	}
	
	constraint def InRangeConstraint (val: NumericalValue, min: NumericalValue, max: NumericalValue) {
		min < val && val < max
	}
	*/
	
	/**
	 * Defines Bounds for Integer attributes.
	 */
	attribute def BoundsAnnotation {		
		attribute min: NumericalValue;
		attribute max: NumericalValue;
		
		assert constraint { min <= max }
	}
	
		
	/**
	 * Defines that a ros node will be generated from the annotated part.
	 */
	attribute def RosNodeAnnotation;
	
	
	/**
	 * Defines that the annotated part is a CPS.
	 */
	attribute def CpsAnnotation :> RosNodeAnnotation {		
		attribute mobile : Boolean;
	}
	
	
	/**
	 * Defines that the annotated part is the Root system.
	 */
	attribute def SystemAnnotation;
	
}