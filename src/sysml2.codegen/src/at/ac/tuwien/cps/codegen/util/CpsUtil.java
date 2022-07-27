package at.ac.tuwien.cps.codegen.util;

import java.util.AbstractMap;
import java.util.Map;

import org.omg.sysml.lang.sysml.AnnotatingFeature;
import org.omg.sysml.lang.sysml.AttributeDefinition;
import org.omg.sysml.lang.sysml.AttributeUsage;
import org.omg.sysml.lang.sysml.DataType;
import org.omg.sysml.lang.sysml.PartDefinition;
import org.omg.sysml.lang.sysml.Type;
import org.omg.sysml.lang.sysml.Usage;

import at.ac.tuwien.cps.codegen.model.CpsSystem;
import at.ac.tuwien.cps.codegen.model.Part;

public class CpsUtil {

	public static final String ABSTRACT_SYSTEM = "Cps::AbstractSystem";
	public static final String ABSTRACT_ROSNODE = "Cps::AbstractRosNode";
	public static final String ABSTRACT_MOBILE_CPS = "Cps::AbstractMobileCps";
	public static final String ABSTRACT_FIXED_CPS = "Cps::AbstractFixedCps";
	public static final String ABSTRACT_CPS = "Cps::AbstractCps";
	
	public static final String ANNO_CPS = "Cps::CpsAnnotation";
	public static final String ANNO_SYSTEM = "Cps::SystemAnnotation";
	public static final String ANNO_ROSNODE= "Cps::RosNodeAnnotation";
	public static final String ANNO_BOUNDS= "Cps::BoundsAnnotation";
	
	public static final String BOUNDS_MIN = "min";
	public static final String BOUNDS_MAX = "max";
	public static final String CPS_MOBILE = "mobile";
	
	public static final String PRIMITIVE_INT= "ScalarValues::Integer";
	public static final String PRIMITIVE_REAL= "ScalarValues::Real";
	public static final String PRIMITIVE_BOOL= "ScalarValues::Boolean";
	public static final String PRIMITIVE_STRING = "ScalarValues::String";
	
	public static boolean isSystem(PartDefinition pd) {
		return !pd.isAbstract() && (SysMLReflectionUtil.isSubTypeOf(pd, ABSTRACT_SYSTEM) || SysMLReflectionUtil.isAnnotationPresent(pd, ANNO_SYSTEM));
	}
	
	public static boolean isCps(PartDefinition pd) {
		return !pd.isAbstract() && (SysMLReflectionUtil.isSubTypeOf(pd, ABSTRACT_CPS) || SysMLReflectionUtil.isAnnotationPresent(pd, ANNO_CPS));
	}
	
	public static boolean isMobileCps(PartDefinition pd) {
		if (!isCps(pd)) {
			return false;
		}
		AnnotatingFeature anno = SysMLReflectionUtil.getAnnotationFeature(pd, ANNO_CPS);
		if (anno == null) {
			return SysMLReflectionUtil.isSubTypeOf(pd, ABSTRACT_MOBILE_CPS);
		}
		
		Map<String, String> vals = SysMLReflectionUtil.getAnnotationValueMap(anno);
		return Boolean.parseBoolean(vals.getOrDefault(CPS_MOBILE, "false"));
	}
	
	public static boolean isRosNode(PartDefinition pd) {
		return !pd.isAbstract() && (SysMLReflectionUtil.isSubTypeOf(pd, ABSTRACT_ROSNODE) || SysMLReflectionUtil.isAnnotationPresent(pd, ANNO_ROSNODE));
	}
	
	public static Map.Entry<Double, Double> getBounds(DataType pd) {		
		if (!SysMLReflectionUtil.isAnnotationPresent(pd, ANNO_BOUNDS)) {
			return null;
		}
		
		Map<String, String> map = SysMLReflectionUtil.getAnnotationValueMap(SysMLReflectionUtil.getAnnotationFeature(pd, ANNO_BOUNDS));
		double min = Double.MIN_VALUE;
		double max = Double.MAX_VALUE;
		
		if (map.containsKey(BOUNDS_MIN)) {
			min = Double.parseDouble(map.get(BOUNDS_MIN));
		}
		if (map.containsKey(BOUNDS_MAX)) {
			max = Double.parseDouble(map.get(BOUNDS_MAX));
		}
		
		return new AbstractMap.SimpleEntry(min, max);
	}
	
	public static String dataType(Usage usg) {
		{
			Type t = usg.getType().get(0);
			if (PRIMITIVE_INT.equals(t.getQualifiedName())) {
				return "int";
			} else if (PRIMITIVE_REAL.equals(t.getQualifiedName())) {
				return "double";
			} else if (PRIMITIVE_BOOL.equals(t.getQualifiedName())) {
				return "boolean";
			} else if (PRIMITIVE_STRING.equals(t.getQualifiedName())) {
				return "String";
			}
		}
		
		return usg.getType().get(0).allSupertypes().stream()
			.map(t -> {
				if (PRIMITIVE_INT.equals(t.getQualifiedName())) {
					return "int";
				} else if (PRIMITIVE_REAL.equals(t.getQualifiedName())) {
					return "double";
				} else if (PRIMITIVE_BOOL.equals(t.getQualifiedName())) {
					return "boolean";
				} else if (PRIMITIVE_STRING.equals(t.getQualifiedName())) {
					return "String";
				}
				return null;
			})
			.filter(s -> s != null)
			.findFirst()
			.orElse(null);
	}
	
	public static String dataTypeWrapper(Usage usg) {
		String dt = dataType(usg);
		if ("int".equals(dt)) {
			return "Integer";
		} else if ("double".equals(dt)) {
			return "Double";
		} if ("boolean".equals(dt)) {
			return "Boolean";
		}
		return dt;
	}
	
	public static boolean isSingletonCps(CpsSystem sys, Part part) {
		/*
		 * var singleton = false
			if (system.systemwideDt.parts.filter[pf | pf.type.name === it.name].size === 1) {
				singleton = !system.systemwideDt.parts.findFirst[pf | pf.type.name === it.name].list
			}
		 */
		if (sys.getSystemwideDt().getParts().stream().filter(pf -> pf.getType().getName().equals(part.getName())).count() == 1) {
			return !sys.getSystemwideDt().getParts().stream()
					.filter(pf -> pf.getType().getName().equals(part.getName()))
					.findFirst()
					.get()
					.isList();
		}
		return false;
	}
	
}
