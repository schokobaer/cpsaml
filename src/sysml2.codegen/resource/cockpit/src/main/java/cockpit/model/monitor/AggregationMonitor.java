package «pkg».cockpit.model.monitor;

import «pkg».cockpit.model.monitor.logic.LogicalExpression;

public class AggregationMonitor extends PropertyMonitor {
    private Aggregation aggregation;
    private LogicalExpression filter;

    public Aggregation getAggregation() {
        return aggregation;
    }

    public void setAggregation(Aggregation aggregation) {
        this.aggregation = aggregation;
    }

    public LogicalExpression getFilter() {
        return filter;
    }

    public void setFilter(LogicalExpression filter) {
        this.filter = filter;
    }
}
