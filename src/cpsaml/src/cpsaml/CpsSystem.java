/**
 */
package cpsaml;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Cps System</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link cpsaml.CpsSystem#getCps <em>Cps</em>}</li>
 * </ul>
 *
 * @see cpsaml.CpsamlPackage#getCpsSystem()
 * @model annotation="http://www.eclipse.org/emf/2002/Ecore constraints='distinctCpsNames'"
 *        annotation="http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot distinctCpsNames='cps-&gt;forAll(c1, c2| c1&lt;&gt;c2 and c1.oclIsKindOf(MultiCps) implies c1.oclAsType(MultiCps).instances-&gt;forAll(i1|i1 &lt;&gt; c2.name and c2.oclIsKindOf(MultiCps) implies c2.oclAsType(MultiCps).instances-&gt;forAll(i2|i1&lt;&gt;i2)))'"
 * @generated
 */
public interface CpsSystem extends ActingElement, DescribedNamedElement {
	/**
	 * Returns the value of the '<em><b>Cps</b></em>' containment reference list.
	 * The list contents are of type {@link cpsaml.Cps}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Cps</em>' containment reference list.
	 * @see cpsaml.CpsamlPackage#getCpsSystem_Cps()
	 * @model containment="true" keys="name"
	 * @generated
	 */
	EList<Cps> getCps();

} // CpsSystem
