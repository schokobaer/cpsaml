/**
 */
package cpsaml;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Cps</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link cpsaml.Cps#getHardware <em>Hardware</em>}</li>
 * </ul>
 *
 * @see cpsaml.CpsamlPackage#getCps()
 * @model abstract="true"
 * @generated
 */
public interface Cps extends DescribedNamedElement, ActingElement, ProducingElement {
	/**
	 * Returns the value of the '<em><b>Hardware</b></em>' containment reference list.
	 * The list contents are of type {@link cpsaml.Hardware}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Hardware</em>' containment reference list.
	 * @see cpsaml.CpsamlPackage#getCps_Hardware()
	 * @model containment="true" keys="name"
	 * @generated
	 */
	EList<Hardware> getHardware();

} // Cps
