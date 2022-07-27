package at.ac.tuwien.big.cps.util

import cpsaml.Cps
import cpsaml.MultiCps

class NameUtil {
	
	
	static def String toIdentifier(String name) {
		return name.replace(' ', '').toLowerCase
	}
	
	static def boolean hasSpecificInstances(Cps cps) {
		return cps instanceof MultiCps && !(cps as MultiCps).instances.empty
	}
}