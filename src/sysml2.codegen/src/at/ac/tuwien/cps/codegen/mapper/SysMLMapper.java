package at.ac.tuwien.cps.codegen.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.omg.sysml.lang.sysml.AttributeDefinition;
import org.omg.sysml.lang.sysml.PartDefinition;
import org.omg.sysml.lang.sysml.PerformActionUsage;
import org.omg.sysml.lang.sysml.ReferenceUsage;

import at.ac.tuwien.cps.codegen.model.Action;
import at.ac.tuwien.cps.codegen.model.Part;
import at.ac.tuwien.cps.codegen.util.CpsUtil;
import at.ac.tuwien.cps.codegen.util.SysMLReflectionUtil;

public class SysMLMapper {

	public Part map(PartDefinition pd) {

		Part part = new Part();
		part.setName(pd.getName());
		part.setMobile(CpsUtil.isMobileCps(pd));
		part.setFields(SysMLReflectionUtil.getFields(pd).entrySet().stream().map(k -> k.getValue())
				.collect(Collectors.toList()));
		part.setStates(SysMLReflectionUtil.getStateFields(pd).entrySet().stream().map(k -> k.getValue())
				.collect(Collectors.toList()));
		part.setActions(pd.getOwnedAction().stream().filter(PerformActionUsage.class::isInstance)
				.map(PerformActionUsage.class::cast)
				.map(ao -> new Action(ao.getName(), ao,
						ao.getParameter().stream().map(ReferenceUsage.class::cast).collect(Collectors.toList())))
				.collect(Collectors.toList()));
		part.setParts(SysMLReflectionUtil.getPartFields(pd).entrySet().stream().map(k -> k.getValue())
				.collect(Collectors.toList()));

		return part;
	}

	public Part mapAttr(AttributeDefinition ad) {

		Part part = new Part();
		part.setName(ad.getName());
		part.setMobile(false);
		part.setFields(SysMLReflectionUtil.getFields(ad).entrySet().stream().map(k -> k.getValue())
				.collect(Collectors.toList()));
		part.setStates(SysMLReflectionUtil.getStateFields(ad).entrySet().stream().map(k -> k.getValue())
				.collect(Collectors.toList()));
		part.setActions(List.of());
		part.setParts(List.of());
		return part;
	}

}
