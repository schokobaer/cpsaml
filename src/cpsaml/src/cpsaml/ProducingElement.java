/**
 */
package cpsaml;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Producing Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link cpsaml.ProducingElement#getResources <em>Resources</em>}</li>
 * </ul>
 *
 * @see cpsaml.CpsamlPackage#getProducingElement()
 * @model abstract="true"
 * @generated
 */
public interface ProducingElement extends EObject {
	/**
	 * Returns the value of the '<em><b>Resources</b></em>' containment reference list.
	 * The list contents are of type {@link cpsaml.Resource}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Resources</em>' containment reference list.
	 * @see cpsaml.CpsamlPackage#getProducingElement_Resources()
	 * @model containment="true" keys="name"
	 * @generated
	 */
	EList<Resource> getResources();

} // ProducingElement
