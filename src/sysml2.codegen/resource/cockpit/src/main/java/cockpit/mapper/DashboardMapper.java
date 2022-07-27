package «pkg».cockpit.mapper;

import «pkg».cockpit.dto.web.PredefinedPropertyMonitorSpecDto;
import «pkg».cockpit.dto.web.monitor.*;
import «pkg».cockpit.dto.web.monitor.logic.ComparisonDto;
import «pkg».cockpit.dto.web.monitor.logic.LogicalDto;
import «pkg».cockpit.dto.web.monitor.logic.LogicalExpressionDto;
import «pkg».cockpit.dto.web.monitor.logic.ThresholdDto;
import «pkg».cockpit.model.monitor.*;
import «pkg».cockpit.model.monitor.predefined.PredefinedValueMonitorSpec;
import «pkg».cockpit.model.monitor.logic.*;
import «pkg».cockpit.service.PredefinedMonitorProviderService;
import «pkg».cockpit.util.Variant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static «pkg».cockpit.util.Utils.runCatching;

@Component
public class DashboardMapper {

    private PredefinedMonitorProviderService predefinedMonitorProviderService;

    @Autowired
    public void setObjectiveProviderService(PredefinedMonitorProviderService predefinedMonitorProviderService) {
        this.predefinedMonitorProviderService = predefinedMonitorProviderService;
    }

    public DashboardDto map(Dashboard dashboard) {
        DashboardDto dto = new DashboardDto();
        dto.setId(dashboard.getId());
        dto.setName(dashboard.getName());
        dto.setCps(dashboard.getCps() != null ? dashboard.getCps().name() : null);
        dto.setInstance(dashboard.getInstance());
        dto.setNotificationType(dashboard.getNotificationType().name());
        dto.setActions(dashboard.getActions().stream().map(a -> {
            ActionDto ac = new ActionDto();
            ac.setName(a.getName());
            ac.setService(a.getService());
            ac.setParams(a.getParams().stream().map(p -> {
                if (p instanceof InputParameterMapping) {
                    InputParameterMappingDto ip = new InputParameterMappingDto();
                    ip.setParameter(p.getParameter());
                    ip.setType(((InputParameterMapping)p).getType().name());
                    return ip;
                } else {
                    FixedValueParameterMappingDto fp = new FixedValueParameterMappingDto();
                    fp.setParameter(p.getParameter());
                    fp.setValue(((FixedValueParameterMapping)p).getValue().toString());
                    return fp;
                }
            }).collect(Collectors.toList()));
            return ac;
        }).collect(Collectors.toList()));
        dto.setMonitors(dashboard.getMonitors().stream().map(o -> {
            if (o instanceof PropertyMonitor) {
                PropertyMonitor po = (PropertyMonitor) o;
                PropertyMonitorDto pod = new PropertyMonitorDto();
                if (o instanceof AggregationMonitor) {
                    AggregationMonitor ag = (AggregationMonitor) o;
                    pod = new AggregationMonitorDto();
                    ((AggregationMonitorDto) pod).setAggregation(ag.getAggregation().name());
                    ((AggregationMonitorDto) pod).setFilter((LogicalExpressionDto) map(ag.getFilter()));
                }
                pod.setName(po.getName());
                if (po.getSource() != null) {
                    if (po.getSource().isA()) {
                        pod.setPath(pathToDto(po.getSource().getA()));
                    } else {
                        PredefinedValueMonitorSpec spec = po.getSource().getB();
                        pod.setCustom(new PredefinedPropertyMonitorSpecDto(spec.getName(), spec.getRepresentation().name(), spec.getReturnType(), spec.getBounds()));
                    }
                }
                pod.setRepresentation(po.getRepresentation().name());
                pod.setAlarmOnFalseValue(po.getAlarmOnFalseValue());
                if (po.getSeverity() != null) {
                    if (po.getSeverity().isA()) {
                        pod.setSeverity(new SeverityDto());
                        pod.getSeverity().setGreen(new ThresholdDto());
                        pod.getSeverity().getGreen().setComparator(po.getSeverity().getA().getGreen().getComparator().name());
                        pod.getSeverity().getGreen().setValue(po.getSeverity().getA().getGreen().getValue());
                        if (po.getSeverity().getA().getOrange() != null) {
                            pod.getSeverity().setOrange(new ThresholdDto());
                            pod.getSeverity().getOrange().setComparator(po.getSeverity().getA().getOrange().getComparator().name());
                            pod.getSeverity().getOrange().setValue(po.getSeverity().getA().getOrange().getValue());
                        }
                    } else {
                        pod.setCustomSeverity(po.getSeverity().getB());
                    }
                }
                return pod;
            } /*else if (o instanceof AggregationObjectiveOld) {
                AggregationObjectiveOld ao = (AggregationObjectiveOld) o;
                AggregationObjectiveDtoOld aod = new AggregationObjectiveDtoOld();
                aod.setName(ao.getName());
                aod.setCps(ao.getCps() != null ? ao.getCps().name() : null);
                aod.setPath(pathToDto(ao.getPath()));
                aod.setAggregation(ao.getAggregation().name());
                aod.setFilter((LogicalExpressionDto) map(ao.getFilter()));
                return aod;
            }*/ else if (o instanceof CriticalMonitor) {
                CriticalMonitor co = (CriticalMonitor) o;
                CriticalMonitorDto cod = new CriticalMonitorDto();
                cod.setName(co.getName());
                cod.setOnEvent(co.getOnEvent());
                if (co.getExpression().isA()) {
                    cod.setExpression((LogicalExpressionDto) map(co.getExpression().getA()));
                } else {
                    cod.setCustom(co.getExpression().getB());
                }
                return cod;
            }
            return null;
        }).collect(Collectors.toList()));
        dto.setEventSpecifications(dashboard.getEventSpecifications().stream()
                .map(er -> er.getExpression().map(
                        tuple -> new EventSpecificationDto(
                                er.getName(),
                                (LogicalExpressionDto) map(tuple.getFirst()),
                                (LogicalExpressionDto) map(tuple.getSecond())
                                ),
                        predefined -> new EventSpecificationDto(er.getName(), predefined)
                        ))
                .collect(Collectors.toList()));
        return dto;
    }

    public Dashboard map(DashboardDto dto) {
        Dashboard m = new Dashboard();
        m.setId(dto.getId());
        m.setName(dto.getName());
        m.setCps(dto.getCps() != null ? Cps.valueOf(dto.getCps()) : null);
        m.setInstance(dto.getInstance());
        m.setNotificationType(dto.getNotificationType() != null ? NotificationType.valueOf(dto.getNotificationType()) : null);
        m.setActions(dto.getActions().stream().map(a -> {
            Action ac = new Action();
            ac.setName(a.getName());
            ac.setService(a.getService());
            ac.setParams(a.getParams().stream().map(p -> {
                if (p instanceof InputParameterMappingDto) {
                    InputParameterMapping ip = new InputParameterMapping();
                    ip.setParameter(p.getParameter());
                    ip.setType(InputType.valueOf(((InputParameterMappingDto)p).getType()));
                    return ip;
                } else {
                    FixedValueParameterMapping fp = new FixedValueParameterMapping();
                    fp.setParameter(p.getParameter());
                    fp.setValue(((FixedValueParameterMappingDto)p).getValue());
                    return fp;
                }
            }).collect(Collectors.toList()));
            return ac;
        }).collect(Collectors.toList()));
        m.setMonitors(dto.getMonitors().stream().map(o -> {
            if (o instanceof PropertyMonitorDto) {
                PropertyMonitorDto pod = (PropertyMonitorDto) o;
                PropertyMonitor po = new PropertyMonitor();
                if (o instanceof AggregationMonitorDto) {
                    AggregationMonitorDto agd = (AggregationMonitorDto) o;
                    po = new AggregationMonitor();
                    ((AggregationMonitor) po).setAggregation(Aggregation.valueOf(agd.getAggregation()));
                    ((AggregationMonitor) po).setFilter((LogicalExpression) map(agd.getFilter()));
                }
                po.setName(pod.getName());
                if (pod.getPath() != null) {
                    po.setSource(Variant.of(pathFromDto(pod.getPath()), null));
                } else if (pod.getCustom() != null) {
                    PredefinedValueMonitorSpec spec = predefinedMonitorProviderService.getPropertyMonitors(m.getCps()).get(pod.getCustom().getName());
                    po.setSource(Variant.of(null, spec));
                }

                if (pod.getSeverity() != null) {
                    po.setSeverity(Variant.of(new Severity(), null));
                    po.getSeverity().getA().setGreen(new Threshold());
                    po.getSeverity().getA().getGreen().setComparator(Comparator.valueOf(pod.getSeverity().getGreen().getComparator()));
                    po.getSeverity().getA().getGreen().setValue(pod.getSeverity().getGreen().getValue());
                    if (pod.getSeverity().getOrange() != null) {
                        po.getSeverity().getA().setOrange(new Threshold());
                        po.getSeverity().getA().getOrange().setComparator(Comparator.valueOf(pod.getSeverity().getOrange().getComparator()));
                        po.getSeverity().getA().getOrange().setValue(pod.getSeverity().getOrange().getValue());
                    }
                } else if (pod.getCustomSeverity() != null) {
                    po.setSeverity(Variant.of(null, pod.getCustomSeverity()));
                }
                po.setRepresentation(Representation.valueOf(pod.getRepresentation()));
                po.setAlarmOnFalseValue(pod.getAlarmOnFalseValue());
                return po;
            } /*else if (o instanceof AggregationObjectiveDtoOld) {
                AggregationObjectiveDtoOld aod = (AggregationObjectiveDtoOld) o;
                AggregationObjectiveOld ao = new AggregationObjectiveOld();
                ao.setName(aod.getName());
                ao.setAggregation(Aggregation.valueOf(aod.getAggregation()));
                ao.setCps(aod.getCps() != null ? Cps.valueOf(aod.getCps()) : null);
                ao.setPath(pathFromDto(aod.getPath()));
                ao.setFilter((LogicalExpression) map(aod.getFilter()));
                return ao;
            } */else if (o instanceof CriticalMonitorDto) {
                CriticalMonitorDto cod = (CriticalMonitorDto) o;
                CriticalMonitor co = new CriticalMonitor();
                co.setName(cod.getName());
                co.setOnEvent(cod.getOnEvent());
                if (cod.getExpression() != null) {
                    co.setExpression(Variant.of((LogicalExpression) map(cod.getExpression()), null));
                } else {
                    co.setExpression(Variant.of(null, cod.getCustom()));
                }
                return co;
            }
            return null;
        }).collect(Collectors.toList()));
        m.setEventSpecifications(dto.getEventSpecifications().stream()
        .map(erd -> erd.getCustom() == null ?
                new EventSpecification(
                erd.getName(),
                (LogicalExpression) map(erd.getBefore()),
                (LogicalExpression) map(erd.getAfter())
        ) : new EventSpecification(erd.getName(), erd.getCustom())
                ).collect(Collectors.toList()));
        return m;
    }

    /**
     * Maps to Dto
     * @param path
     * @return
     */
    private List<Object> pathToDto(List<PathFieldMapping> path) {
        return path.stream().map(p ->
                p.getFunction() == null ? p.getField() :
                        new PathFieldMappingDto(
                                p.getField(),
                                p.getFunction().map(sf -> sf.name(), vf -> vf.name())
                        )).collect(Collectors.toList());
    }

    /**
     * Maps from Dto
     * @param path
     * @return
     */
    private List<PathFieldMapping> pathFromDto(List<Object> path) {
        return path.stream().map(p ->
                p instanceof String ? new PathFieldMapping((String) p) :
                        p instanceof Map ? new PathFieldMapping(
                                ((Map<String, String>) p).get("field"),
                                Variant.of(
                                        runCatching(() -> ScalarFunction.valueOf(((Map<String, String>) p).get("function"))),
                                        runCatching(() -> VectorFunction.valueOf(((Map<String, String>) p).get("function")))
                                )
                        ) : null).collect(Collectors.toList());
    }



    private Logical map(LogicalDto dto) {
        if (dto instanceof LogicalExpressionDto) {
            LogicalExpressionDto expd = (LogicalExpressionDto) dto;
            LogicalExpression exp = new LogicalExpression();
            exp.setOperator(expd.getOperator() != null ? LogicalOperator.valueOf(expd.getOperator()) : null);
            exp.setExpressions(expd.getExpressions().stream().map(l -> map(l)).collect(Collectors.toList()));
            exp.setNot(expd.isNot());
            return exp;
        } else {
            ComparisonDto cd = (ComparisonDto) dto;
            Comparison c = new Comparison();
            c.setPath(pathFromDto(cd.getPath()));
            c.setComparator(cd.getComparator() != null ? Comparator.valueOf(cd.getComparator()): null);
            c.setValue(cd.getValue());
            return c;
        }
    }

    private LogicalDto map(Logical logical) {
        if (logical instanceof LogicalExpression) {
            LogicalExpression exp = (LogicalExpression) logical;
            LogicalExpressionDto dto = new LogicalExpressionDto();
            dto.setOperator(exp.getOperator() != null ? exp.getOperator().name() : null);
            dto.setExpressions(exp.getExpressions().stream().map(l -> map(l)).collect(Collectors.toList()));
            dto.setNot(exp.isNot());
            return dto;
        } else {
            Comparison c = (Comparison) logical;
            ComparisonDto dto = new ComparisonDto();
            dto.setPath(pathToDto(c.getPath()));
            dto.setComparator(c.getComparator() != null ? c.getComparator().name() : null);
            dto.setValue(c.getValue());
            return dto;
        }
    }
}
