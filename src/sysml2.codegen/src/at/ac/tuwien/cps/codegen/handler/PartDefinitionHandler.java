package at.ac.tuwien.cps.codegen.handler;

import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.omg.sysml.lang.sysml.ActionUsage;
import org.omg.sysml.lang.sysml.AnnotatingFeature;
import org.omg.sysml.lang.sysml.BindingConnector;
import org.omg.sysml.lang.sysml.DataType;
import org.omg.sysml.lang.sysml.Element;
import org.omg.sysml.lang.sysml.FeatureTyping;
import org.omg.sysml.lang.sysml.LiteralBoolean;
import org.omg.sysml.lang.sysml.MetadataFeature;
import org.omg.sysml.lang.sysml.MultiplicityRange;
import org.omg.sysml.lang.sysml.Namespace;
import org.omg.sysml.lang.sysml.Package;
import org.omg.sysml.lang.sysml.PartDefinition;
import org.omg.sysml.lang.sysml.PerformActionUsage;
import org.omg.sysml.lang.sysml.ReferenceUsage;
import org.omg.sysml.lang.sysml.StateUsage;
import org.omg.sysml.util.SysMLUtil;
import org.omg.sysml.xtext.services.SysMLGrammarAccess.PerformedActionUsageElements;

import at.ac.tuwien.cps.codegen.mapper.SysMLMapper;
import at.ac.tuwien.cps.codegen.model.Action;
import at.ac.tuwien.cps.codegen.model.Part;
import at.ac.tuwien.cps.codegen.model.PartField;
import at.ac.tuwien.cps.codegen.util.CpsUtil;
import at.ac.tuwien.cps.codegen.util.SysMLReflectionUtil;

public class PartDefinitionHandler implements ElementHandler {
	
	private BaseHandler base;
	private SysMLMapper mapper = new SysMLMapper();
	
	public PartDefinitionHandler(BaseHandler base) {
		this.base = base;
	}
	
	@Override
	public boolean responsible(Object o) {
		return o instanceof PartDefinition;
	}
	
	@Override
	public void handle(Object o) {
		PartDefinition pd = (PartDefinition) o;
		
		if (pd.isAbstract()) return;
		
		//p.getMember().forEach(base::handle);
		//System.out.println("Part Definition: " + pd.getName() + "");
		//System.out.println("CPS:" + CpsUtil.isCps(pd));
		//System.out.println("Mobile:" + CpsUtil.isMobileCps(pd));
		
		
		if (CpsUtil.isSystem(pd)) {
			Part sys = mapper.map(pd);
			base.getSystem().setSystemwideDt(sys);
						
			base.getSystem().setCps(
			sys.getParts().stream()
				.filter(pf -> CpsUtil.isCps(pf.getType()))
				.filter(distinctByKey(pf -> pf.getType().getQualifiedName()))
				.map(pf -> mapper.map(pf.getType()))
				.collect(Collectors.toList())
				);
			
			return;
			
		} //else if (true) return;
		
		//pd.getOwnedAction().forEach(base::handle);
		
		
		
		pd.getOwnedMember().stream()
			.filter(AnnotatingFeature.class::isInstance)
			.map(AnnotatingFeature.class::cast)
			.forEach(af -> {
				System.out.println("Annotation " + af.getMetadataType().getQualifiedName());
				System.out.println("Features: " + af.getOwnedFeature().size());
				af.getOwnedFeature().stream()
				.filter(MetadataFeature.class::isInstance)
				.map(MetadataFeature.class::cast)
				.forEach(f -> {
					
					System.out.println("Redefinitions: " + f.getOwnedRedefinition().size());
					f.getOwnedRedefinition().stream().map(rdf -> rdf.getRedefinedFeature().getName()).findAny().orElse("");
					f.getOwnedRedefinition().forEach(rdf -> {
						System.out.println("RedefQName: " + rdf.getRedefinedFeature().getName());
					});
					
					System.out.println("Value: " + SysMLReflectionUtil.stringify(f.getMetadataFeatureValue().getValue()));
				});
				
				System.out.println("AnnoValuesWithFun");
				SysMLReflectionUtil.getAnnotationValueMap(af).forEach((k,v) -> System.out.println(k + ": " + v));
		});
		
		
		System.out.println("=== Fields ===");
		SysMLReflectionUtil.getFields(pd).forEach((name, field) -> {
			System.out.println(
					name + ": " + 
					field.getType().getQualifiedName() + 
					(field.isList() ? " (list)" : "")
			);
			
			field.getType().getOwnedMember().stream()
				.filter(AnnotatingFeature.class::isInstance)
				.map(AnnotatingFeature.class::cast)
				.forEach(af -> {
					System.out.println("Annotation " + af.getMetadataType().getQualifiedName());
					System.out.println("Features: " + af.getOwnedFeature().size());
					af.getOwnedFeature().stream()
					.filter(MetadataFeature.class::isInstance)
					.map(MetadataFeature.class::cast)
					.forEach(f -> {
						
						System.out.println("Redefinitions: " + f.getOwnedRedefinition().size());
						f.getOwnedRedefinition().stream().map(rdf -> rdf.getRedefinedFeature().getName()).findAny().orElse("");
						f.getOwnedRedefinition().forEach(rdf -> {
							System.out.println("RedefQName: " + rdf.getRedefinedFeature().getName());
						});
						
						System.out.println("Value: " + SysMLReflectionUtil.stringify(f.getMetadataFeatureValue().getValue()));
					});
					
					System.out.println("AnnoValuesWithFun");
					SysMLReflectionUtil.getAnnotationValueMap(af).forEach((k,v) -> System.out.println(k + ": " + v));
			});
		});
		System.out.println("=== States ===");
		SysMLReflectionUtil.getStateFields(pd).forEach((name, field) -> {
			System.out.println(
					name + ": " + 
					field.getType().getQualifiedName() + 
					(field.isList() ? " (list) " : " ") + 
					SysMLReflectionUtil.getStateValues(field.getUsage())
			);
		});
		System.out.println("=== Parts ===");
		SysMLReflectionUtil.getPartFields(pd).forEach((name, field) -> {
			System.out.println(
					name + ": " + 
					field.getType().getQualifiedName() + 
					(field.isList() ? " (list) " : " ")
			);
		});
		
		
		/*
		pd.getOwnedAttribute().forEach(at -> {
			System.out.println("Name: " + at.getName());
			
			at.getOwnedRelationship().stream()
				.filter(FeatureTyping.class::isInstance)
				.map(FeatureTyping.class::cast)
				.forEach(ft -> {
					//System.out.println("FT.TypeQN: " + ft.getType().getQualifiedName());
				});
			
			// Both only work with local definitions -> No ScalarValues
			at.getAttributeDefinition().forEach(ad -> {
				System.out.println("AD.Type: " + ad.getQualifiedName());
			});
			at.getType().forEach(t -> {
				System.out.println("Type: " + t.getQualifiedName());
			});
			
		});
		*/
		
		/**
		 * STATES
		 */
		/*
		pd.getOwnedState().forEach(su -> {
			System.out.println("State variable " + su.getName() + ": " + su.getType().get(0).getQualifiedName());
			su.getType().get(0).getOwnedFeature().forEach(f -> {
				if (f instanceof StateUsage) 
					System.out.println(f.getName() + ": " + f.getQualifiedName());
			});
		});
		*/
		
		
		System.out.println("===MEMBERS BITCHES ====" + pd.getName());
		pd.getOwnedAction().forEach(acHome -> {
		pd.getMember().stream()
			.filter(BindingConnector.class::isInstance)
			.map(BindingConnector.class::cast)
			.filter(b -> b.getSourceFeature() == acHome || b.getTargetFeature().contains(acHome))
			.forEach(m -> {
			System.out.println(m.getName() + ": " + m.getClass().getSimpleName());
			System.out.println("Source : " + m.getSourceFeature().getName() + " - " + m.getSourceFeature().getClass().getSimpleName());
			System.out.println("Target : " + m.getTargetFeature().get(0).getName() + " - " +m.getTargetFeature().get(0).getClass().getSimpleName());
			//System.out.println("Source QName: " + m.getSourceFeature().getQualifiedName());
			m.getSource().forEach(s -> {
				System.out.println(s.getOwner().getName() + " - " + s.getOwner().getClass().getSimpleName());
			});
		});
		});
		
		
		/*
		pd.getOwnedAction().forEach(a -> {
			System.out.println("Checking " + a.getName());
			getBinding(a);
		});
		*/
		
		//System.out.println();
	}
	
	private static void crawlFields(DataType dt) {
		
	}
	
	public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
	    Set<Object> seen = ConcurrentHashMap.newKeySet();
	    return t -> seen.add(keyExtractor.apply(t));
	}
	
	private ActionUsage getBinding(ActionUsage acHome) {
		System.out.println("Owner: " + acHome.getOwner().getName() + " - " + acHome.getOwner().getClass().getSimpleName());
		
		Namespace owner = (Namespace) acHome.getOwner();
		
		return owner.getMember().stream()
			.filter(BindingConnector.class::isInstance)
			.map(BindingConnector.class::cast)
			.filter(b -> b.getSourceFeature() == acHome || b.getTargetFeature().contains(acHome))
			.map(b -> {
				if (b.getSourceFeature() == acHome) {
					return b.getTargetFeature().get(0);
				} else {
					return b.getSourceFeature();
				}
			})
			.filter(ActionUsage.class::isInstance)
			.map(ActionUsage.class::cast)
			.findAny()
			.orElse(null);
	}
	
	
}
