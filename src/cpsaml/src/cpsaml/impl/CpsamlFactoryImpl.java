/**
 */
package cpsaml.impl;

import cpsaml.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class CpsamlFactoryImpl extends EFactoryImpl implements CpsamlFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static CpsamlFactory init() {
		try {
			CpsamlFactory theCpsamlFactory = (CpsamlFactory)EPackage.Registry.INSTANCE.getEFactory(CpsamlPackage.eNS_URI);
			if (theCpsamlFactory != null) {
				return theCpsamlFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new CpsamlFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CpsamlFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case CpsamlPackage.COMMAND: return createCommand();
			case CpsamlPackage.CPS_SYSTEM: return createCpsSystem();
			case CpsamlPackage.RESOURCE: return createResource();
			case CpsamlPackage.ACTUATOR: return createActuator();
			case CpsamlPackage.SENSOR: return createSensor();
			case CpsamlPackage.SINGLETON_CPS: return createSingletonCps();
			case CpsamlPackage.MULTI_CPS: return createMultiCps();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Command createCommand() {
		CommandImpl command = new CommandImpl();
		return command;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CpsSystem createCpsSystem() {
		CpsSystemImpl cpsSystem = new CpsSystemImpl();
		return cpsSystem;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Resource createResource() {
		ResourceImpl resource = new ResourceImpl();
		return resource;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Actuator createActuator() {
		ActuatorImpl actuator = new ActuatorImpl();
		return actuator;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Sensor createSensor() {
		SensorImpl sensor = new SensorImpl();
		return sensor;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SingletonCps createSingletonCps() {
		SingletonCpsImpl singletonCps = new SingletonCpsImpl();
		return singletonCps;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MultiCps createMultiCps() {
		MultiCpsImpl multiCps = new MultiCpsImpl();
		return multiCps;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CpsamlPackage getCpsamlPackage() {
		return (CpsamlPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static CpsamlPackage getPackage() {
		return CpsamlPackage.eINSTANCE;
	}

} //CpsamlFactoryImpl
