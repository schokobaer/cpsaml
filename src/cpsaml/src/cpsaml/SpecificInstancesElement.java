/**
 */
package cpsaml;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Specific Instances Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link cpsaml.SpecificInstancesElement#getInstances <em>Instances</em>}</li>
 * </ul>
 *
 * @see cpsaml.CpsamlPackage#getSpecificInstancesElement()
 * @model abstract="true"
 *        annotation="http://www.eclipse.org/emf/2002/Ecore constraints='notEmpty'"
 *        annotation="http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot notEmpty='instances-&gt;forAll(i | i &lt;&gt; null and i &lt;&gt; \'\' and not i-&gt;exists(c|c=\'\\n\' or c=\'\\r\'))'"
 * @generated
 */
public interface SpecificInstancesElement extends EObject {
	/**
	 * Returns the value of the '<em><b>Instances</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Instances</em>' attribute list.
	 * @see cpsaml.CpsamlPackage#getSpecificInstancesElement_Instances()
	 * @model
	 * @generated
	 */
	EList<String> getInstances();

} // SpecificInstancesElement
