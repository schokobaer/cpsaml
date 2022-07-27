/**
 */
package cpsaml.impl;

import cpsaml.ActingElement;
import cpsaml.Actuator;
import cpsaml.Command;
import cpsaml.Cps;
import cpsaml.CpsSystem;
import cpsaml.CpsamlFactory;
import cpsaml.CpsamlPackage;
import cpsaml.DescribedNamedElement;
import cpsaml.Hardware;
import cpsaml.MultiCps;
import cpsaml.ProducingElement;
import cpsaml.Resource;
import cpsaml.Sensor;
import cpsaml.SingletonCps;
import cpsaml.SpecificInstancesElement;

import cpsaml.util.CpsamlValidator;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EValidator;

import org.eclipse.emf.ecore.impl.EPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class CpsamlPackageImpl extends EPackageImpl implements CpsamlPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass describedNamedElementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass actingElementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass commandEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass cpsSystemEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass cpsEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass resourceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass specificInstancesElementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass hardwareEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass producingElementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass actuatorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass sensorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass singletonCpsEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass multiCpsEClass = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see cpsaml.CpsamlPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private CpsamlPackageImpl() {
		super(eNS_URI, CpsamlFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 *
	 * <p>This method is used to initialize {@link CpsamlPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static CpsamlPackage init() {
		if (isInited) return (CpsamlPackage)EPackage.Registry.INSTANCE.getEPackage(CpsamlPackage.eNS_URI);

		// Obtain or create and register package
		Object registeredCpsamlPackage = EPackage.Registry.INSTANCE.get(eNS_URI);
		CpsamlPackageImpl theCpsamlPackage = registeredCpsamlPackage instanceof CpsamlPackageImpl ? (CpsamlPackageImpl)registeredCpsamlPackage : new CpsamlPackageImpl();

		isInited = true;

		// Create package meta-data objects
		theCpsamlPackage.createPackageContents();

		// Initialize created meta-data
		theCpsamlPackage.initializePackageContents();

		// Register package validator
		EValidator.Registry.INSTANCE.put
			(theCpsamlPackage,
			 new EValidator.Descriptor() {
				 public EValidator getEValidator() {
					 return CpsamlValidator.INSTANCE;
				 }
			 });

		// Mark meta-data to indicate it can't be changed
		theCpsamlPackage.freeze();

		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(CpsamlPackage.eNS_URI, theCpsamlPackage);
		return theCpsamlPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDescribedNamedElement() {
		return describedNamedElementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDescribedNamedElement_Name() {
		return (EAttribute)describedNamedElementEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDescribedNamedElement_Description() {
		return (EAttribute)describedNamedElementEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getActingElement() {
		return actingElementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getActingElement_Commands() {
		return (EReference)actingElementEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getCommand() {
		return commandEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getCpsSystem() {
		return cpsSystemEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCpsSystem_Cps() {
		return (EReference)cpsSystemEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getCps() {
		return cpsEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCps_Hardware() {
		return (EReference)cpsEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getResource() {
		return resourceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSpecificInstancesElement() {
		return specificInstancesElementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSpecificInstancesElement_Instances() {
		return (EAttribute)specificInstancesElementEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getHardware() {
		return hardwareEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getProducingElement() {
		return producingElementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getProducingElement_Resources() {
		return (EReference)producingElementEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getActuator() {
		return actuatorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSensor() {
		return sensorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSingletonCps() {
		return singletonCpsEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getMultiCps() {
		return multiCpsEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CpsamlFactory getCpsamlFactory() {
		return (CpsamlFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		describedNamedElementEClass = createEClass(DESCRIBED_NAMED_ELEMENT);
		createEAttribute(describedNamedElementEClass, DESCRIBED_NAMED_ELEMENT__NAME);
		createEAttribute(describedNamedElementEClass, DESCRIBED_NAMED_ELEMENT__DESCRIPTION);

		actingElementEClass = createEClass(ACTING_ELEMENT);
		createEReference(actingElementEClass, ACTING_ELEMENT__COMMANDS);

		commandEClass = createEClass(COMMAND);

		cpsSystemEClass = createEClass(CPS_SYSTEM);
		createEReference(cpsSystemEClass, CPS_SYSTEM__CPS);

		cpsEClass = createEClass(CPS);
		createEReference(cpsEClass, CPS__HARDWARE);

		resourceEClass = createEClass(RESOURCE);

		specificInstancesElementEClass = createEClass(SPECIFIC_INSTANCES_ELEMENT);
		createEAttribute(specificInstancesElementEClass, SPECIFIC_INSTANCES_ELEMENT__INSTANCES);

		hardwareEClass = createEClass(HARDWARE);

		producingElementEClass = createEClass(PRODUCING_ELEMENT);
		createEReference(producingElementEClass, PRODUCING_ELEMENT__RESOURCES);

		actuatorEClass = createEClass(ACTUATOR);

		sensorEClass = createEClass(SENSOR);

		singletonCpsEClass = createEClass(SINGLETON_CPS);

		multiCpsEClass = createEClass(MULTI_CPS);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		commandEClass.getESuperTypes().add(this.getDescribedNamedElement());
		cpsSystemEClass.getESuperTypes().add(this.getActingElement());
		cpsSystemEClass.getESuperTypes().add(this.getDescribedNamedElement());
		cpsEClass.getESuperTypes().add(this.getDescribedNamedElement());
		cpsEClass.getESuperTypes().add(this.getActingElement());
		cpsEClass.getESuperTypes().add(this.getProducingElement());
		resourceEClass.getESuperTypes().add(this.getDescribedNamedElement());
		hardwareEClass.getESuperTypes().add(this.getSpecificInstancesElement());
		hardwareEClass.getESuperTypes().add(this.getDescribedNamedElement());
		actuatorEClass.getESuperTypes().add(this.getHardware());
		actuatorEClass.getESuperTypes().add(this.getActingElement());
		sensorEClass.getESuperTypes().add(this.getHardware());
		sensorEClass.getESuperTypes().add(this.getProducingElement());
		singletonCpsEClass.getESuperTypes().add(this.getCps());
		multiCpsEClass.getESuperTypes().add(this.getCps());
		multiCpsEClass.getESuperTypes().add(this.getSpecificInstancesElement());

		// Initialize classes, features, and operations; add parameters
		initEClass(describedNamedElementEClass, DescribedNamedElement.class, "DescribedNamedElement", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getDescribedNamedElement_Name(), ecorePackage.getEString(), "name", null, 1, 1, DescribedNamedElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDescribedNamedElement_Description(), ecorePackage.getEString(), "description", null, 0, 1, DescribedNamedElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(actingElementEClass, ActingElement.class, "ActingElement", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getActingElement_Commands(), this.getCommand(), null, "commands", null, 0, -1, ActingElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		getActingElement_Commands().getEKeys().add(this.getDescribedNamedElement_Name());

		initEClass(commandEClass, Command.class, "Command", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(cpsSystemEClass, CpsSystem.class, "CpsSystem", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getCpsSystem_Cps(), this.getCps(), null, "cps", null, 0, -1, CpsSystem.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		getCpsSystem_Cps().getEKeys().add(this.getDescribedNamedElement_Name());

		initEClass(cpsEClass, Cps.class, "Cps", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getCps_Hardware(), this.getHardware(), null, "hardware", null, 0, -1, Cps.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		getCps_Hardware().getEKeys().add(this.getDescribedNamedElement_Name());

		initEClass(resourceEClass, Resource.class, "Resource", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(specificInstancesElementEClass, SpecificInstancesElement.class, "SpecificInstancesElement", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getSpecificInstancesElement_Instances(), ecorePackage.getEString(), "instances", null, 0, -1, SpecificInstancesElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(hardwareEClass, Hardware.class, "Hardware", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(producingElementEClass, ProducingElement.class, "ProducingElement", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getProducingElement_Resources(), this.getResource(), null, "resources", null, 0, -1, ProducingElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		getProducingElement_Resources().getEKeys().add(this.getDescribedNamedElement_Name());

		initEClass(actuatorEClass, Actuator.class, "Actuator", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(sensorEClass, Sensor.class, "Sensor", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(singletonCpsEClass, SingletonCps.class, "SingletonCps", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(multiCpsEClass, MultiCps.class, "MultiCps", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		// Create resource
		createResource(eNS_URI);

		// Create annotations
		// http://www.eclipse.org/OCL/Import
		createImportAnnotations();
		// http://www.eclipse.org/emf/2002/Ecore
		createEcoreAnnotations();
		// http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot
		createPivotAnnotations();
	}

	/**
	 * Initializes the annotations for <b>http://www.eclipse.org/OCL/Import</b>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void createImportAnnotations() {
		String source = "http://www.eclipse.org/OCL/Import";
		addAnnotation
		  (this,
		   source,
		   new String[] {
			   "ecore", "http://www.eclipse.org/emf/2002/Ecore"
		   });
	}

	/**
	 * Initializes the annotations for <b>http://www.eclipse.org/emf/2002/Ecore</b>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void createEcoreAnnotations() {
		String source = "http://www.eclipse.org/emf/2002/Ecore";
		addAnnotation
		  (this,
		   source,
		   new String[] {
			   "invocationDelegates", "http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot",
			   "settingDelegates", "http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot",
			   "validationDelegates", "http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot"
		   });
		addAnnotation
		  (describedNamedElementEClass,
		   source,
		   new String[] {
			   "constraints", "notEmpty"
		   });
		addAnnotation
		  (cpsSystemEClass,
		   source,
		   new String[] {
			   "constraints", "distinctCpsNames"
		   });
		addAnnotation
		  (specificInstancesElementEClass,
		   source,
		   new String[] {
			   "constraints", "notEmpty"
		   });
	}

	/**
	 * Initializes the annotations for <b>http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot</b>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void createPivotAnnotations() {
		String source = "http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot";
		addAnnotation
		  (describedNamedElementEClass,
		   source,
		   new String[] {
			   "notEmpty", "name <> null and name <> \'\' and not name->exists(c|c=\'\\n\' or c=\'\\r\')"
		   });
		addAnnotation
		  (cpsSystemEClass,
		   source,
		   new String[] {
			   "distinctCpsNames", "cps->forAll(c1, c2| c1<>c2 and c1.oclIsKindOf(MultiCps) implies c1.oclAsType(MultiCps).instances->forAll(i1|i1 <> c2.name and c2.oclIsKindOf(MultiCps) implies c2.oclAsType(MultiCps).instances->forAll(i2|i1<>i2)))"
		   });
		addAnnotation
		  (specificInstancesElementEClass,
		   source,
		   new String[] {
			   "notEmpty", "instances->forAll(i | i <> null and i <> \'\' and not i->exists(c|c=\'\\n\' or c=\'\\r\'))"
		   });
	}

} //CpsamlPackageImpl
