/**
 */
package cpsaml;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each operation of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see cpsaml.CpsamlFactory
 * @model kind="package"
 *        annotation="http://www.eclipse.org/OCL/Import ecore='http://www.eclipse.org/emf/2002/Ecore'"
 *        annotation="http://www.eclipse.org/emf/2002/Ecore invocationDelegates='http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot' settingDelegates='http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot' validationDelegates='http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot'"
 * @generated
 */
public interface CpsamlPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "cpsaml";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://big.tuwien.ac.at/cps/cpsaml";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "cpsaml";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	CpsamlPackage eINSTANCE = cpsaml.impl.CpsamlPackageImpl.init();

	/**
	 * The meta object id for the '{@link cpsaml.impl.DescribedNamedElementImpl <em>Described Named Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see cpsaml.impl.DescribedNamedElementImpl
	 * @see cpsaml.impl.CpsamlPackageImpl#getDescribedNamedElement()
	 * @generated
	 */
	int DESCRIBED_NAMED_ELEMENT = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DESCRIBED_NAMED_ELEMENT__NAME = 0;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DESCRIBED_NAMED_ELEMENT__DESCRIPTION = 1;

	/**
	 * The number of structural features of the '<em>Described Named Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DESCRIBED_NAMED_ELEMENT_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Described Named Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DESCRIBED_NAMED_ELEMENT_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link cpsaml.impl.ActingElementImpl <em>Acting Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see cpsaml.impl.ActingElementImpl
	 * @see cpsaml.impl.CpsamlPackageImpl#getActingElement()
	 * @generated
	 */
	int ACTING_ELEMENT = 1;

	/**
	 * The feature id for the '<em><b>Commands</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTING_ELEMENT__COMMANDS = 0;

	/**
	 * The number of structural features of the '<em>Acting Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTING_ELEMENT_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Acting Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTING_ELEMENT_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link cpsaml.impl.CommandImpl <em>Command</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see cpsaml.impl.CommandImpl
	 * @see cpsaml.impl.CpsamlPackageImpl#getCommand()
	 * @generated
	 */
	int COMMAND = 2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMMAND__NAME = DESCRIBED_NAMED_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMMAND__DESCRIPTION = DESCRIBED_NAMED_ELEMENT__DESCRIPTION;

	/**
	 * The number of structural features of the '<em>Command</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMMAND_FEATURE_COUNT = DESCRIBED_NAMED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Command</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMMAND_OPERATION_COUNT = DESCRIBED_NAMED_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link cpsaml.impl.CpsSystemImpl <em>Cps System</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see cpsaml.impl.CpsSystemImpl
	 * @see cpsaml.impl.CpsamlPackageImpl#getCpsSystem()
	 * @generated
	 */
	int CPS_SYSTEM = 3;

	/**
	 * The feature id for the '<em><b>Commands</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CPS_SYSTEM__COMMANDS = ACTING_ELEMENT__COMMANDS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CPS_SYSTEM__NAME = ACTING_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CPS_SYSTEM__DESCRIPTION = ACTING_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Cps</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CPS_SYSTEM__CPS = ACTING_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Cps System</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CPS_SYSTEM_FEATURE_COUNT = ACTING_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The number of operations of the '<em>Cps System</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CPS_SYSTEM_OPERATION_COUNT = ACTING_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link cpsaml.impl.CpsImpl <em>Cps</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see cpsaml.impl.CpsImpl
	 * @see cpsaml.impl.CpsamlPackageImpl#getCps()
	 * @generated
	 */
	int CPS = 4;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CPS__NAME = DESCRIBED_NAMED_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CPS__DESCRIPTION = DESCRIBED_NAMED_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Commands</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CPS__COMMANDS = DESCRIBED_NAMED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Resources</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CPS__RESOURCES = DESCRIBED_NAMED_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Hardware</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CPS__HARDWARE = DESCRIBED_NAMED_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Cps</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CPS_FEATURE_COUNT = DESCRIBED_NAMED_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The number of operations of the '<em>Cps</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CPS_OPERATION_COUNT = DESCRIBED_NAMED_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link cpsaml.impl.ResourceImpl <em>Resource</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see cpsaml.impl.ResourceImpl
	 * @see cpsaml.impl.CpsamlPackageImpl#getResource()
	 * @generated
	 */
	int RESOURCE = 5;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE__NAME = DESCRIBED_NAMED_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE__DESCRIPTION = DESCRIBED_NAMED_ELEMENT__DESCRIPTION;

	/**
	 * The number of structural features of the '<em>Resource</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_FEATURE_COUNT = DESCRIBED_NAMED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Resource</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_OPERATION_COUNT = DESCRIBED_NAMED_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link cpsaml.impl.SpecificInstancesElementImpl <em>Specific Instances Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see cpsaml.impl.SpecificInstancesElementImpl
	 * @see cpsaml.impl.CpsamlPackageImpl#getSpecificInstancesElement()
	 * @generated
	 */
	int SPECIFIC_INSTANCES_ELEMENT = 6;

	/**
	 * The feature id for the '<em><b>Instances</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPECIFIC_INSTANCES_ELEMENT__INSTANCES = 0;

	/**
	 * The number of structural features of the '<em>Specific Instances Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPECIFIC_INSTANCES_ELEMENT_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Specific Instances Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPECIFIC_INSTANCES_ELEMENT_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link cpsaml.impl.HardwareImpl <em>Hardware</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see cpsaml.impl.HardwareImpl
	 * @see cpsaml.impl.CpsamlPackageImpl#getHardware()
	 * @generated
	 */
	int HARDWARE = 7;

	/**
	 * The feature id for the '<em><b>Instances</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HARDWARE__INSTANCES = SPECIFIC_INSTANCES_ELEMENT__INSTANCES;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HARDWARE__NAME = SPECIFIC_INSTANCES_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HARDWARE__DESCRIPTION = SPECIFIC_INSTANCES_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Hardware</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HARDWARE_FEATURE_COUNT = SPECIFIC_INSTANCES_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Hardware</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HARDWARE_OPERATION_COUNT = SPECIFIC_INSTANCES_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link cpsaml.impl.ProducingElementImpl <em>Producing Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see cpsaml.impl.ProducingElementImpl
	 * @see cpsaml.impl.CpsamlPackageImpl#getProducingElement()
	 * @generated
	 */
	int PRODUCING_ELEMENT = 8;

	/**
	 * The feature id for the '<em><b>Resources</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRODUCING_ELEMENT__RESOURCES = 0;

	/**
	 * The number of structural features of the '<em>Producing Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRODUCING_ELEMENT_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Producing Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRODUCING_ELEMENT_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link cpsaml.impl.ActuatorImpl <em>Actuator</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see cpsaml.impl.ActuatorImpl
	 * @see cpsaml.impl.CpsamlPackageImpl#getActuator()
	 * @generated
	 */
	int ACTUATOR = 9;

	/**
	 * The feature id for the '<em><b>Instances</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTUATOR__INSTANCES = HARDWARE__INSTANCES;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTUATOR__NAME = HARDWARE__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTUATOR__DESCRIPTION = HARDWARE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Commands</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTUATOR__COMMANDS = HARDWARE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Actuator</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTUATOR_FEATURE_COUNT = HARDWARE_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Actuator</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTUATOR_OPERATION_COUNT = HARDWARE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link cpsaml.impl.SensorImpl <em>Sensor</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see cpsaml.impl.SensorImpl
	 * @see cpsaml.impl.CpsamlPackageImpl#getSensor()
	 * @generated
	 */
	int SENSOR = 10;

	/**
	 * The feature id for the '<em><b>Instances</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SENSOR__INSTANCES = HARDWARE__INSTANCES;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SENSOR__NAME = HARDWARE__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SENSOR__DESCRIPTION = HARDWARE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Resources</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SENSOR__RESOURCES = HARDWARE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Sensor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SENSOR_FEATURE_COUNT = HARDWARE_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Sensor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SENSOR_OPERATION_COUNT = HARDWARE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link cpsaml.impl.SingletonCpsImpl <em>Singleton Cps</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see cpsaml.impl.SingletonCpsImpl
	 * @see cpsaml.impl.CpsamlPackageImpl#getSingletonCps()
	 * @generated
	 */
	int SINGLETON_CPS = 11;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SINGLETON_CPS__NAME = CPS__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SINGLETON_CPS__DESCRIPTION = CPS__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Commands</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SINGLETON_CPS__COMMANDS = CPS__COMMANDS;

	/**
	 * The feature id for the '<em><b>Resources</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SINGLETON_CPS__RESOURCES = CPS__RESOURCES;

	/**
	 * The feature id for the '<em><b>Hardware</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SINGLETON_CPS__HARDWARE = CPS__HARDWARE;

	/**
	 * The number of structural features of the '<em>Singleton Cps</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SINGLETON_CPS_FEATURE_COUNT = CPS_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Singleton Cps</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SINGLETON_CPS_OPERATION_COUNT = CPS_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link cpsaml.impl.MultiCpsImpl <em>Multi Cps</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see cpsaml.impl.MultiCpsImpl
	 * @see cpsaml.impl.CpsamlPackageImpl#getMultiCps()
	 * @generated
	 */
	int MULTI_CPS = 12;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MULTI_CPS__NAME = CPS__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MULTI_CPS__DESCRIPTION = CPS__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Commands</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MULTI_CPS__COMMANDS = CPS__COMMANDS;

	/**
	 * The feature id for the '<em><b>Resources</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MULTI_CPS__RESOURCES = CPS__RESOURCES;

	/**
	 * The feature id for the '<em><b>Hardware</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MULTI_CPS__HARDWARE = CPS__HARDWARE;

	/**
	 * The feature id for the '<em><b>Instances</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MULTI_CPS__INSTANCES = CPS_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Multi Cps</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MULTI_CPS_FEATURE_COUNT = CPS_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Multi Cps</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MULTI_CPS_OPERATION_COUNT = CPS_OPERATION_COUNT + 0;


	/**
	 * Returns the meta object for class '{@link cpsaml.DescribedNamedElement <em>Described Named Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Described Named Element</em>'.
	 * @see cpsaml.DescribedNamedElement
	 * @generated
	 */
	EClass getDescribedNamedElement();

	/**
	 * Returns the meta object for the attribute '{@link cpsaml.DescribedNamedElement#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see cpsaml.DescribedNamedElement#getName()
	 * @see #getDescribedNamedElement()
	 * @generated
	 */
	EAttribute getDescribedNamedElement_Name();

	/**
	 * Returns the meta object for the attribute '{@link cpsaml.DescribedNamedElement#getDescription <em>Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Description</em>'.
	 * @see cpsaml.DescribedNamedElement#getDescription()
	 * @see #getDescribedNamedElement()
	 * @generated
	 */
	EAttribute getDescribedNamedElement_Description();

	/**
	 * Returns the meta object for class '{@link cpsaml.ActingElement <em>Acting Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Acting Element</em>'.
	 * @see cpsaml.ActingElement
	 * @generated
	 */
	EClass getActingElement();

	/**
	 * Returns the meta object for the containment reference list '{@link cpsaml.ActingElement#getCommands <em>Commands</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Commands</em>'.
	 * @see cpsaml.ActingElement#getCommands()
	 * @see #getActingElement()
	 * @generated
	 */
	EReference getActingElement_Commands();

	/**
	 * Returns the meta object for class '{@link cpsaml.Command <em>Command</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Command</em>'.
	 * @see cpsaml.Command
	 * @generated
	 */
	EClass getCommand();

	/**
	 * Returns the meta object for class '{@link cpsaml.CpsSystem <em>Cps System</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Cps System</em>'.
	 * @see cpsaml.CpsSystem
	 * @generated
	 */
	EClass getCpsSystem();

	/**
	 * Returns the meta object for the containment reference list '{@link cpsaml.CpsSystem#getCps <em>Cps</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Cps</em>'.
	 * @see cpsaml.CpsSystem#getCps()
	 * @see #getCpsSystem()
	 * @generated
	 */
	EReference getCpsSystem_Cps();

	/**
	 * Returns the meta object for class '{@link cpsaml.Cps <em>Cps</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Cps</em>'.
	 * @see cpsaml.Cps
	 * @generated
	 */
	EClass getCps();

	/**
	 * Returns the meta object for the containment reference list '{@link cpsaml.Cps#getHardware <em>Hardware</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Hardware</em>'.
	 * @see cpsaml.Cps#getHardware()
	 * @see #getCps()
	 * @generated
	 */
	EReference getCps_Hardware();

	/**
	 * Returns the meta object for class '{@link cpsaml.Resource <em>Resource</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Resource</em>'.
	 * @see cpsaml.Resource
	 * @generated
	 */
	EClass getResource();

	/**
	 * Returns the meta object for class '{@link cpsaml.SpecificInstancesElement <em>Specific Instances Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Specific Instances Element</em>'.
	 * @see cpsaml.SpecificInstancesElement
	 * @generated
	 */
	EClass getSpecificInstancesElement();

	/**
	 * Returns the meta object for the attribute list '{@link cpsaml.SpecificInstancesElement#getInstances <em>Instances</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Instances</em>'.
	 * @see cpsaml.SpecificInstancesElement#getInstances()
	 * @see #getSpecificInstancesElement()
	 * @generated
	 */
	EAttribute getSpecificInstancesElement_Instances();

	/**
	 * Returns the meta object for class '{@link cpsaml.Hardware <em>Hardware</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Hardware</em>'.
	 * @see cpsaml.Hardware
	 * @generated
	 */
	EClass getHardware();

	/**
	 * Returns the meta object for class '{@link cpsaml.ProducingElement <em>Producing Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Producing Element</em>'.
	 * @see cpsaml.ProducingElement
	 * @generated
	 */
	EClass getProducingElement();

	/**
	 * Returns the meta object for the containment reference list '{@link cpsaml.ProducingElement#getResources <em>Resources</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Resources</em>'.
	 * @see cpsaml.ProducingElement#getResources()
	 * @see #getProducingElement()
	 * @generated
	 */
	EReference getProducingElement_Resources();

	/**
	 * Returns the meta object for class '{@link cpsaml.Actuator <em>Actuator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Actuator</em>'.
	 * @see cpsaml.Actuator
	 * @generated
	 */
	EClass getActuator();

	/**
	 * Returns the meta object for class '{@link cpsaml.Sensor <em>Sensor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Sensor</em>'.
	 * @see cpsaml.Sensor
	 * @generated
	 */
	EClass getSensor();

	/**
	 * Returns the meta object for class '{@link cpsaml.SingletonCps <em>Singleton Cps</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Singleton Cps</em>'.
	 * @see cpsaml.SingletonCps
	 * @generated
	 */
	EClass getSingletonCps();

	/**
	 * Returns the meta object for class '{@link cpsaml.MultiCps <em>Multi Cps</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Multi Cps</em>'.
	 * @see cpsaml.MultiCps
	 * @generated
	 */
	EClass getMultiCps();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	CpsamlFactory getCpsamlFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each operation of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link cpsaml.impl.DescribedNamedElementImpl <em>Described Named Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see cpsaml.impl.DescribedNamedElementImpl
		 * @see cpsaml.impl.CpsamlPackageImpl#getDescribedNamedElement()
		 * @generated
		 */
		EClass DESCRIBED_NAMED_ELEMENT = eINSTANCE.getDescribedNamedElement();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DESCRIBED_NAMED_ELEMENT__NAME = eINSTANCE.getDescribedNamedElement_Name();

		/**
		 * The meta object literal for the '<em><b>Description</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DESCRIBED_NAMED_ELEMENT__DESCRIPTION = eINSTANCE.getDescribedNamedElement_Description();

		/**
		 * The meta object literal for the '{@link cpsaml.impl.ActingElementImpl <em>Acting Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see cpsaml.impl.ActingElementImpl
		 * @see cpsaml.impl.CpsamlPackageImpl#getActingElement()
		 * @generated
		 */
		EClass ACTING_ELEMENT = eINSTANCE.getActingElement();

		/**
		 * The meta object literal for the '<em><b>Commands</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ACTING_ELEMENT__COMMANDS = eINSTANCE.getActingElement_Commands();

		/**
		 * The meta object literal for the '{@link cpsaml.impl.CommandImpl <em>Command</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see cpsaml.impl.CommandImpl
		 * @see cpsaml.impl.CpsamlPackageImpl#getCommand()
		 * @generated
		 */
		EClass COMMAND = eINSTANCE.getCommand();

		/**
		 * The meta object literal for the '{@link cpsaml.impl.CpsSystemImpl <em>Cps System</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see cpsaml.impl.CpsSystemImpl
		 * @see cpsaml.impl.CpsamlPackageImpl#getCpsSystem()
		 * @generated
		 */
		EClass CPS_SYSTEM = eINSTANCE.getCpsSystem();

		/**
		 * The meta object literal for the '<em><b>Cps</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CPS_SYSTEM__CPS = eINSTANCE.getCpsSystem_Cps();

		/**
		 * The meta object literal for the '{@link cpsaml.impl.CpsImpl <em>Cps</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see cpsaml.impl.CpsImpl
		 * @see cpsaml.impl.CpsamlPackageImpl#getCps()
		 * @generated
		 */
		EClass CPS = eINSTANCE.getCps();

		/**
		 * The meta object literal for the '<em><b>Hardware</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CPS__HARDWARE = eINSTANCE.getCps_Hardware();

		/**
		 * The meta object literal for the '{@link cpsaml.impl.ResourceImpl <em>Resource</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see cpsaml.impl.ResourceImpl
		 * @see cpsaml.impl.CpsamlPackageImpl#getResource()
		 * @generated
		 */
		EClass RESOURCE = eINSTANCE.getResource();

		/**
		 * The meta object literal for the '{@link cpsaml.impl.SpecificInstancesElementImpl <em>Specific Instances Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see cpsaml.impl.SpecificInstancesElementImpl
		 * @see cpsaml.impl.CpsamlPackageImpl#getSpecificInstancesElement()
		 * @generated
		 */
		EClass SPECIFIC_INSTANCES_ELEMENT = eINSTANCE.getSpecificInstancesElement();

		/**
		 * The meta object literal for the '<em><b>Instances</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SPECIFIC_INSTANCES_ELEMENT__INSTANCES = eINSTANCE.getSpecificInstancesElement_Instances();

		/**
		 * The meta object literal for the '{@link cpsaml.impl.HardwareImpl <em>Hardware</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see cpsaml.impl.HardwareImpl
		 * @see cpsaml.impl.CpsamlPackageImpl#getHardware()
		 * @generated
		 */
		EClass HARDWARE = eINSTANCE.getHardware();

		/**
		 * The meta object literal for the '{@link cpsaml.impl.ProducingElementImpl <em>Producing Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see cpsaml.impl.ProducingElementImpl
		 * @see cpsaml.impl.CpsamlPackageImpl#getProducingElement()
		 * @generated
		 */
		EClass PRODUCING_ELEMENT = eINSTANCE.getProducingElement();

		/**
		 * The meta object literal for the '<em><b>Resources</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PRODUCING_ELEMENT__RESOURCES = eINSTANCE.getProducingElement_Resources();

		/**
		 * The meta object literal for the '{@link cpsaml.impl.ActuatorImpl <em>Actuator</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see cpsaml.impl.ActuatorImpl
		 * @see cpsaml.impl.CpsamlPackageImpl#getActuator()
		 * @generated
		 */
		EClass ACTUATOR = eINSTANCE.getActuator();

		/**
		 * The meta object literal for the '{@link cpsaml.impl.SensorImpl <em>Sensor</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see cpsaml.impl.SensorImpl
		 * @see cpsaml.impl.CpsamlPackageImpl#getSensor()
		 * @generated
		 */
		EClass SENSOR = eINSTANCE.getSensor();

		/**
		 * The meta object literal for the '{@link cpsaml.impl.SingletonCpsImpl <em>Singleton Cps</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see cpsaml.impl.SingletonCpsImpl
		 * @see cpsaml.impl.CpsamlPackageImpl#getSingletonCps()
		 * @generated
		 */
		EClass SINGLETON_CPS = eINSTANCE.getSingletonCps();

		/**
		 * The meta object literal for the '{@link cpsaml.impl.MultiCpsImpl <em>Multi Cps</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see cpsaml.impl.MultiCpsImpl
		 * @see cpsaml.impl.CpsamlPackageImpl#getMultiCps()
		 * @generated
		 */
		EClass MULTI_CPS = eINSTANCE.getMultiCps();

	}

} //CpsamlPackage
