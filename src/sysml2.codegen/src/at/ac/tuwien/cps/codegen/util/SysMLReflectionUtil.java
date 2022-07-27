package at.ac.tuwien.cps.codegen.util;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.omg.sysml.lang.sysml.AnnotatingFeature;
import org.omg.sysml.lang.sysml.AttributeDefinition;
import org.omg.sysml.lang.sysml.DataType;
import org.omg.sysml.lang.sysml.Definition;
import org.omg.sysml.lang.sysml.Expression;
import org.omg.sysml.lang.sysml.FeatureTyping;
import org.omg.sysml.lang.sysml.LiteralBoolean;
import org.omg.sysml.lang.sysml.LiteralInteger;
import org.omg.sysml.lang.sysml.LiteralReal;
import org.omg.sysml.lang.sysml.LiteralString;
import org.omg.sysml.lang.sysml.MetadataFeature;
import org.omg.sysml.lang.sysml.MultiplicityRange;
import org.omg.sysml.lang.sysml.Namespace;
import org.omg.sysml.lang.sysml.OperatorExpression;
import org.omg.sysml.lang.sysml.PartUsage;
import org.omg.sysml.lang.sysml.StateUsage;
import org.omg.sysml.lang.sysml.Type;
import org.omg.sysml.lang.sysml.Usage;

import at.ac.tuwien.cps.codegen.model.Field;
import at.ac.tuwien.cps.codegen.model.PartField;
import at.ac.tuwien.cps.codegen.model.StateField;

public class SysMLReflectionUtil {

	
	public static boolean isAnnotationPresent(Namespace elem, String annotationQualifiedName) {
		return elem.getOwnedMember().stream()
			.filter(AnnotatingFeature.class::isInstance)
			.map(AnnotatingFeature.class::cast)
			.anyMatch(af -> annotationQualifiedName.equals(af.getMetadataType().getQualifiedName()));
	}
	
	public static AnnotatingFeature getAnnotationFeature(Namespace elem, String annotationQualifiedName) {
		return elem.getOwnedMember().stream()
			.filter(AnnotatingFeature.class::isInstance)
			.map(AnnotatingFeature.class::cast)
			.filter(af -> annotationQualifiedName.equals(af.getMetadataType().getQualifiedName()))
			.findAny()
			.orElse(null);
	}
	
	public static Map<String, String> getAnnotationValueMap(AnnotatingFeature af) {
		return af.getOwnedFeature().stream()
			.filter(MetadataFeature.class::isInstance)
			.map(MetadataFeature.class::cast)
			.collect(Collectors.toMap(
					mf -> mf.getOwnedRedefinition().stream().map(rdf -> rdf.getRedefinedFeature().getName()).findAny().orElse(""),
					mf -> stringify(mf.getMetadataFeatureValue().getValue())
			));
	}
	
	public static boolean isSubTypeOf(Type elem, String superTypeQualifiedName) {
		return elem.allSupertypes().stream()
				.anyMatch(t -> superTypeQualifiedName.equals(t.getQualifiedName()));
				
	}
	
	
	public static Map<String, DataType> getAttributes(Definition def) {
		return def.getOwnedAttribute().stream()
			.filter(at -> !at.getAttributeDefinition().isEmpty())
			.collect(Collectors.toMap(
					at -> at.getName(),
					at -> at.getAttributeDefinition().get(0)
					));
	}
	
	public static Map<String, Field> getFields(Definition def) {
		return def.getOwnedAttribute().stream()
				.filter(at -> !at.getAttributeDefinition().isEmpty())
				.collect(Collectors.toMap(
						at -> at.getName(),
						at -> new Field(at.getName(), isList(at), at.getAttributeDefinition().get(0), at)
						));
	}
	
	public static Map<String, PartField> getPartFields(Definition def) {
		return def.getOwnedPart().stream()
				.filter(pu -> !pu.getPartDefinition().isEmpty() && pu.getName() != null)
				.collect(Collectors.toMap(
						pu -> pu.getName(),
						pu -> new PartField(pu.getName(), isList(pu), pu.getPartDefinition().get(0), pu)
				));
	}
	
	public static Map<String, StateField> getStateFields(Definition def) {
		return def.getOwnedState().stream()
				.filter(s -> !s.getStateDefinition().isEmpty())
				.collect(Collectors.toMap(
						s -> s.getName(),
						s -> new StateField(s.getName(), isList(s), s.getStateDefinition().get(0), s)
						));
	}
	
	public static Set<String> getStateValues(StateUsage su) {
		return su.getType().get(0).getOwnedFeature().stream()
			.filter(StateUsage.class::isInstance)
			.map(x -> x.getName())
			.collect(Collectors.toSet());		
	}
	
	public static String getDefaultStateValue(StateUsage su) {
		return su.getType().get(0).getOwnedFeature().stream()
			.filter(StateUsage.class::isInstance)
			.map(x -> x.getName())
			.findFirst().orElse(null);		
	}
	
	public static boolean isList(Type t) {
		if (t.getMultiplicity() == null || !(t.getMultiplicity() instanceof MultiplicityRange)) {
			return false;
		}
		MultiplicityRange multiplicity = (MultiplicityRange) t.getMultiplicity();
		if ("1".equals(stringify(multiplicity.getUpperBound()))) {
			return false;
		}
		return true;
	}
	
	
	public static String stringify(Expression exp) { // TODO: Operator Expression
		if (exp instanceof LiteralBoolean) {
			return ((LiteralBoolean) exp).isValue() + "";
		} else if (exp instanceof LiteralString) {
			return ((LiteralString) exp).getValue();
		} else if (exp instanceof LiteralInteger) {
			return ((LiteralInteger) exp).getValue() + "";
		} else if (exp instanceof LiteralReal) {
			return ((LiteralReal) exp).getValue() + "";
		} else if (exp instanceof OperatorExpression) {
			OperatorExpression oe = (OperatorExpression) exp;
			return oe.getOperator() + stringify(oe.getOperand().get(0));
		}
		return null;
	}
	
}
