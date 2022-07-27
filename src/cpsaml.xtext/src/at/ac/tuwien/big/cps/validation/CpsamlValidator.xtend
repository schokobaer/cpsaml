package at.ac.tuwien.big.cps.validation

import at.ac.tuwien.big.cps.validation.AbstractCpsamlValidator
import cpsaml.CpsSystem
import org.eclipse.xtext.validation.Check
import cpsaml.SingletonCps

import static extension at.ac.tuwien.big.cps.util.NameUtil.*
import cpsaml.MultiCps
import cpsaml.CpsamlPackage

class CpsamlValidator extends AbstractCpsamlValidator {
	
	
	public static final String INVALID_NAME = "invalidName";
	
	@Check
	def distinctCpsIdentifiers(MultiCps cps) {
		if (cps.instances.empty) {
			return
		}
		
		(cps.eContainer as CpsSystem).cps.filter(SingletonCps).filter[sc|cps.instances.exists[i|i.toIdentifier.equals(sc.name.toIdentifier)]].forEach[
			error("Names must be unique", CpsamlPackage.Literals.DESCRIBED_NAMED_ELEMENT__NAME, INVALID_NAME)
		]
		
		(cps.eContainer as CpsSystem).cps.filter(MultiCps).filter[it!==cps].filter[mc|mc.instances.map[it.toIdentifier].exists[cps.instances.map[it.toIdentifier].contains(it)]].forEach[
			error("Names must be unique", CpsamlPackage.Literals.SPECIFIC_INSTANCES_ELEMENT__INSTANCES, INVALID_NAME)
		]
	}

}