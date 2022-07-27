/**
 */
package cpsaml;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Acting Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link cpsaml.ActingElement#getCommands <em>Commands</em>}</li>
 * </ul>
 *
 * @see cpsaml.CpsamlPackage#getActingElement()
 * @model abstract="true"
 * @generated
 */
public interface ActingElement extends EObject {
	/**
	 * Returns the value of the '<em><b>Commands</b></em>' containment reference list.
	 * The list contents are of type {@link cpsaml.Command}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Commands</em>' containment reference list.
	 * @see cpsaml.CpsamlPackage#getActingElement_Commands()
	 * @model containment="true" keys="name"
	 * @generated
	 */
	EList<Command> getCommands();

} // ActingElement
