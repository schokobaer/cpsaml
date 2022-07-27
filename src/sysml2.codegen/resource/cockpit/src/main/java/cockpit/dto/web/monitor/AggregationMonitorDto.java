package «pkg».cockpit.dto.web.monitor;

import «pkg».cockpit.dto.web.monitor.logic.LogicalExpressionDto;

public class AggregationMonitorDto extends PropertyMonitorDto {

    private String aggregation;
    private LogicalExpressionDto filter;

    public String getAggregation() {
        return aggregation;
    }

    public void setAggregation(String aggregation) {
        this.aggregation = aggregation;
    }

    public LogicalExpressionDto getFilter() {
        return filter;
    }

    public void setFilter(LogicalExpressionDto filter) {
        this.filter = filter;
    }
}
