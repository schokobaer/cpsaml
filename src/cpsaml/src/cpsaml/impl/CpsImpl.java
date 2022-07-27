/**
 */
package cpsaml.impl;

import cpsaml.ActingElement;
import cpsaml.Command;
import cpsaml.Cps;
import cpsaml.CpsamlPackage;
import cpsaml.Hardware;
import cpsaml.ProducingElement;
import cpsaml.Resource;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Cps</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link cpsaml.impl.CpsImpl#getCommands <em>Commands</em>}</li>
 *   <li>{@link cpsaml.impl.CpsImpl#getResources <em>Resources</em>}</li>
 *   <li>{@link cpsaml.impl.CpsImpl#getHardware <em>Hardware</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class CpsImpl extends DescribedNamedElementImpl implements Cps {
	/**
	 * The cached value of the '{@link #getCommands() <em>Commands</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCommands()
	 * @generated
	 * @ordered
	 */
	protected EList<Command> commands;

	/**
	 * The cached value of the '{@link #getResources() <em>Resources</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getResources()
	 * @generated
	 * @ordered
	 */
	protected EList<Resource> resources;

	/**
	 * The cached value of the '{@link #getHardware() <em>Hardware</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHardware()
	 * @generated
	 * @ordered
	 */
	protected EList<Hardware> hardware;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CpsImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CpsamlPackage.Literals.CPS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Command> getCommands() {
		if (commands == null) {
			commands = new EObjectContainmentEList<Command>(Command.class, this, CpsamlPackage.CPS__COMMANDS);
		}
		return commands;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Resource> getResources() {
		if (resources == null) {
			resources = new EObjectContainmentEList<Resource>(Resource.class, this, CpsamlPackage.CPS__RESOURCES);
		}
		return resources;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Hardware> getHardware() {
		if (hardware == null) {
			hardware = new EObjectContainmentEList<Hardware>(Hardware.class, this, CpsamlPackage.CPS__HARDWARE);
		}
		return hardware;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case CpsamlPackage.CPS__COMMANDS:
				return ((InternalEList<?>)getCommands()).basicRemove(otherEnd, msgs);
			case CpsamlPackage.CPS__RESOURCES:
				return ((InternalEList<?>)getResources()).basicRemove(otherEnd, msgs);
			case CpsamlPackage.CPS__HARDWARE:
				return ((InternalEList<?>)getHardware()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case CpsamlPackage.CPS__COMMANDS:
				return getCommands();
			case CpsamlPackage.CPS__RESOURCES:
				return getResources();
			case CpsamlPackage.CPS__HARDWARE:
				return getHardware();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case CpsamlPackage.CPS__COMMANDS:
				getCommands().clear();
				getCommands().addAll((Collection<? extends Command>)newValue);
				return;
			case CpsamlPackage.CPS__RESOURCES:
				getResources().clear();
				getResources().addAll((Collection<? extends Resource>)newValue);
				return;
			case CpsamlPackage.CPS__HARDWARE:
				getHardware().clear();
				getHardware().addAll((Collection<? extends Hardware>)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case CpsamlPackage.CPS__COMMANDS:
				getCommands().clear();
				return;
			case CpsamlPackage.CPS__RESOURCES:
				getResources().clear();
				return;
			case CpsamlPackage.CPS__HARDWARE:
				getHardware().clear();
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case CpsamlPackage.CPS__COMMANDS:
				return commands != null && !commands.isEmpty();
			case CpsamlPackage.CPS__RESOURCES:
				return resources != null && !resources.isEmpty();
			case CpsamlPackage.CPS__HARDWARE:
				return hardware != null && !hardware.isEmpty();
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
		if (baseClass == ActingElement.class) {
			switch (derivedFeatureID) {
				case CpsamlPackage.CPS__COMMANDS: return CpsamlPackage.ACTING_ELEMENT__COMMANDS;
				default: return -1;
			}
		}
		if (baseClass == ProducingElement.class) {
			switch (derivedFeatureID) {
				case CpsamlPackage.CPS__RESOURCES: return CpsamlPackage.PRODUCING_ELEMENT__RESOURCES;
				default: return -1;
			}
		}
		return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass) {
		if (baseClass == ActingElement.class) {
			switch (baseFeatureID) {
				case CpsamlPackage.ACTING_ELEMENT__COMMANDS: return CpsamlPackage.CPS__COMMANDS;
				default: return -1;
			}
		}
		if (baseClass == ProducingElement.class) {
			switch (baseFeatureID) {
				case CpsamlPackage.PRODUCING_ELEMENT__RESOURCES: return CpsamlPackage.CPS__RESOURCES;
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
	}

} //CpsImpl
