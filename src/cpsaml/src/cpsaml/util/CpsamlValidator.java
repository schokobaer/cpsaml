/**
 */
package cpsaml.util;

import cpsaml.*;

import java.util.Map;

import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.common.util.ResourceLocator;

import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.util.EObjectValidator;

/**
 * <!-- begin-user-doc -->
 * The <b>Validator</b> for the model.
 * <!-- end-user-doc -->
 * @see cpsaml.CpsamlPackage
 * @generated
 */
public class CpsamlValidator extends EObjectValidator {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final CpsamlValidator INSTANCE = new CpsamlValidator();

	/**
	 * A constant for the {@link org.eclipse.emf.common.util.Diagnostic#getSource() source} of diagnostic {@link org.eclipse.emf.common.util.Diagnostic#getCode() codes} from this package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.common.util.Diagnostic#getSource()
	 * @see org.eclipse.emf.common.util.Diagnostic#getCode()
	 * @generated
	 */
	public static final String DIAGNOSTIC_SOURCE = "cpsaml";

	/**
	 * A constant with a fixed name that can be used as the base value for additional hand written constants.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final int GENERATED_DIAGNOSTIC_CODE_COUNT = 0;

	/**
	 * A constant with a fixed name that can be used as the base value for additional hand written constants in a derived class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static final int DIAGNOSTIC_CODE_COUNT = GENERATED_DIAGNOSTIC_CODE_COUNT;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CpsamlValidator() {
		super();
	}

	/**
	 * Returns the package of this validator switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EPackage getEPackage() {
	  return CpsamlPackage.eINSTANCE;
	}

	/**
	 * Calls <code>validateXXX</code> for the corresponding classifier of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected boolean validate(int classifierID, Object value, DiagnosticChain diagnostics, Map<Object, Object> context) {
		switch (classifierID) {
			case CpsamlPackage.DESCRIBED_NAMED_ELEMENT:
				return validateDescribedNamedElement((DescribedNamedElement)value, diagnostics, context);
			case CpsamlPackage.ACTING_ELEMENT:
				return validateActingElement((ActingElement)value, diagnostics, context);
			case CpsamlPackage.COMMAND:
				return validateCommand((Command)value, diagnostics, context);
			case CpsamlPackage.CPS_SYSTEM:
				return validateCpsSystem((CpsSystem)value, diagnostics, context);
			case CpsamlPackage.CPS:
				return validateCps((Cps)value, diagnostics, context);
			case CpsamlPackage.RESOURCE:
				return validateResource((Resource)value, diagnostics, context);
			case CpsamlPackage.SPECIFIC_INSTANCES_ELEMENT:
				return validateSpecificInstancesElement((SpecificInstancesElement)value, diagnostics, context);
			case CpsamlPackage.HARDWARE:
				return validateHardware((Hardware)value, diagnostics, context);
			case CpsamlPackage.PRODUCING_ELEMENT:
				return validateProducingElement((ProducingElement)value, diagnostics, context);
			case CpsamlPackage.ACTUATOR:
				return validateActuator((Actuator)value, diagnostics, context);
			case CpsamlPackage.SENSOR:
				return validateSensor((Sensor)value, diagnostics, context);
			case CpsamlPackage.SINGLETON_CPS:
				return validateSingletonCps((SingletonCps)value, diagnostics, context);
			case CpsamlPackage.MULTI_CPS:
				return validateMultiCps((MultiCps)value, diagnostics, context);
			default:
				return true;
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateDescribedNamedElement(DescribedNamedElement describedNamedElement, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(describedNamedElement, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(describedNamedElement, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(describedNamedElement, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(describedNamedElement, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(describedNamedElement, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(describedNamedElement, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(describedNamedElement, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(describedNamedElement, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(describedNamedElement, diagnostics, context);
		if (result || diagnostics != null) result &= validateDescribedNamedElement_notEmpty(describedNamedElement, diagnostics, context);
		return result;
	}

	/**
	 * The cached validation expression for the notEmpty constraint of '<em>Described Named Element</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static final String DESCRIBED_NAMED_ELEMENT__NOT_EMPTY__EEXPRESSION = "name <> null and name <> '' and not name->exists(c|c='\\n' or c='\\r')";

	/**
	 * Validates the notEmpty constraint of '<em>Described Named Element</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateDescribedNamedElement_notEmpty(DescribedNamedElement describedNamedElement, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return
			validate
				(CpsamlPackage.Literals.DESCRIBED_NAMED_ELEMENT,
				 describedNamedElement,
				 diagnostics,
				 context,
				 "http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot",
				 "notEmpty",
				 DESCRIBED_NAMED_ELEMENT__NOT_EMPTY__EEXPRESSION,
				 Diagnostic.ERROR,
				 DIAGNOSTIC_SOURCE,
				 0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateActingElement(ActingElement actingElement, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(actingElement, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateCommand(Command command, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(command, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(command, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(command, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(command, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(command, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(command, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(command, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(command, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(command, diagnostics, context);
		if (result || diagnostics != null) result &= validateDescribedNamedElement_notEmpty(command, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateCpsSystem(CpsSystem cpsSystem, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(cpsSystem, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(cpsSystem, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(cpsSystem, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(cpsSystem, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(cpsSystem, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(cpsSystem, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(cpsSystem, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(cpsSystem, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(cpsSystem, diagnostics, context);
		if (result || diagnostics != null) result &= validateDescribedNamedElement_notEmpty(cpsSystem, diagnostics, context);
		if (result || diagnostics != null) result &= validateCpsSystem_distinctCpsNames(cpsSystem, diagnostics, context);
		return result;
	}

	/**
	 * The cached validation expression for the distinctCpsNames constraint of '<em>Cps System</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static final String CPS_SYSTEM__DISTINCT_CPS_NAMES__EEXPRESSION = "cps->forAll(c1, c2| c1<>c2 and c1.oclIsKindOf(MultiCps) implies c1.oclAsType(MultiCps).instances->forAll(i1|i1 <> c2.name and c2.oclIsKindOf(MultiCps) implies c2.oclAsType(MultiCps).instances->forAll(i2|i1<>i2)))";

	/**
	 * Validates the distinctCpsNames constraint of '<em>Cps System</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateCpsSystem_distinctCpsNames(CpsSystem cpsSystem, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return
			validate
				(CpsamlPackage.Literals.CPS_SYSTEM,
				 cpsSystem,
				 diagnostics,
				 context,
				 "http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot",
				 "distinctCpsNames",
				 CPS_SYSTEM__DISTINCT_CPS_NAMES__EEXPRESSION,
				 Diagnostic.ERROR,
				 DIAGNOSTIC_SOURCE,
				 0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateCps(Cps cps, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(cps, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(cps, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(cps, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(cps, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(cps, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(cps, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(cps, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(cps, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(cps, diagnostics, context);
		if (result || diagnostics != null) result &= validateDescribedNamedElement_notEmpty(cps, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateResource(Resource resource, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(resource, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(resource, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(resource, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(resource, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(resource, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(resource, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(resource, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(resource, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(resource, diagnostics, context);
		if (result || diagnostics != null) result &= validateDescribedNamedElement_notEmpty(resource, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateSpecificInstancesElement(SpecificInstancesElement specificInstancesElement, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(specificInstancesElement, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(specificInstancesElement, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(specificInstancesElement, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(specificInstancesElement, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(specificInstancesElement, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(specificInstancesElement, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(specificInstancesElement, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(specificInstancesElement, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(specificInstancesElement, diagnostics, context);
		if (result || diagnostics != null) result &= validateSpecificInstancesElement_notEmpty(specificInstancesElement, diagnostics, context);
		return result;
	}

	/**
	 * The cached validation expression for the notEmpty constraint of '<em>Specific Instances Element</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static final String SPECIFIC_INSTANCES_ELEMENT__NOT_EMPTY__EEXPRESSION = "instances->forAll(i | i <> null and i <> '' and not i->exists(c|c='\\n' or c='\\r'))";

	/**
	 * Validates the notEmpty constraint of '<em>Specific Instances Element</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateSpecificInstancesElement_notEmpty(SpecificInstancesElement specificInstancesElement, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return
			validate
				(CpsamlPackage.Literals.SPECIFIC_INSTANCES_ELEMENT,
				 specificInstancesElement,
				 diagnostics,
				 context,
				 "http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot",
				 "notEmpty",
				 SPECIFIC_INSTANCES_ELEMENT__NOT_EMPTY__EEXPRESSION,
				 Diagnostic.ERROR,
				 DIAGNOSTIC_SOURCE,
				 0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateHardware(Hardware hardware, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(hardware, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(hardware, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(hardware, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(hardware, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(hardware, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(hardware, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(hardware, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(hardware, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(hardware, diagnostics, context);
		if (result || diagnostics != null) result &= validateSpecificInstancesElement_notEmpty(hardware, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateProducingElement(ProducingElement producingElement, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(producingElement, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateActuator(Actuator actuator, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(actuator, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(actuator, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(actuator, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(actuator, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(actuator, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(actuator, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(actuator, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(actuator, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(actuator, diagnostics, context);
		if (result || diagnostics != null) result &= validateSpecificInstancesElement_notEmpty(actuator, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateSensor(Sensor sensor, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(sensor, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(sensor, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(sensor, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(sensor, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(sensor, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(sensor, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(sensor, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(sensor, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(sensor, diagnostics, context);
		if (result || diagnostics != null) result &= validateSpecificInstancesElement_notEmpty(sensor, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateSingletonCps(SingletonCps singletonCps, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(singletonCps, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(singletonCps, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(singletonCps, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(singletonCps, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(singletonCps, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(singletonCps, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(singletonCps, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(singletonCps, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(singletonCps, diagnostics, context);
		if (result || diagnostics != null) result &= validateDescribedNamedElement_notEmpty(singletonCps, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateMultiCps(MultiCps multiCps, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(multiCps, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(multiCps, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(multiCps, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(multiCps, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(multiCps, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(multiCps, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(multiCps, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(multiCps, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(multiCps, diagnostics, context);
		if (result || diagnostics != null) result &= validateDescribedNamedElement_notEmpty(multiCps, diagnostics, context);
		return result;
	}

	/**
	 * Returns the resource locator that will be used to fetch messages for this validator's diagnostics.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ResourceLocator getResourceLocator() {
		// TODO
		// Specialize this to return a resource locator for messages specific to this validator.
		// Ensure that you remove @generated or mark it @generated NOT
		return super.getResourceLocator();
	}

} //CpsamlValidator
