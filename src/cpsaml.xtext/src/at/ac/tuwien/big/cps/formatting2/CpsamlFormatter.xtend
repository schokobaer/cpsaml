/*
 * generated by Xtext 2.22.0
 */
package at.ac.tuwien.big.cps.formatting2

import at.ac.tuwien.big.cps.services.CpsamlGrammarAccess
import com.google.inject.Inject
import cpsaml.CpsSystem
import cpsaml.SingletonCps
import org.eclipse.xtext.formatting2.AbstractFormatter2
import org.eclipse.xtext.formatting2.IFormattableDocument

class CpsamlFormatter extends AbstractFormatter2 {
	
	@Inject extension CpsamlGrammarAccess

	def dispatch void format(CpsSystem cpsSystem, extension IFormattableDocument document) {
		// TODO: format HiddenRegions around keywords, attributes, cross references, etc. 
		for (cps : cpsSystem.cps) {
			cps.format
		}
		for (command : cpsSystem.commands) {
			command.format
		}
	}

	def dispatch void format(SingletonCps singletonCps, extension IFormattableDocument document) {
		// TODO: format HiddenRegions around keywords, attributes, cross references, etc. 
		for (command : singletonCps.commands) {
			command.format
		}
		for (resource : singletonCps.resources) {
			resource.format
		}
		for (hardware : singletonCps.hardware) {
			hardware.format
		}
	}
	
	// TODO: implement for MultiCps, Actuator, Sensor
}
