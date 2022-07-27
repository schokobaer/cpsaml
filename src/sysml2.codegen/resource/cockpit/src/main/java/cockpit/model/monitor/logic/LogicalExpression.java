package «pkg».cockpit.model.monitor.logic;

import java.util.List;

public class LogicalExpression implements Logical {
    private List<Logical> expressions;
    private LogicalOperator operator;
    private boolean not;

    public LogicalExpression() { }

    public LogicalExpression(List<Logical> expressions, LogicalOperator operator) {
        this.expressions = expressions;
        this.operator = operator;
    }

    public List<Logical> getExpressions() {
        return expressions;
    }

    public void setExpressions(List<Logical> expressions) {
        this.expressions = expressions;
    }

    public LogicalOperator getOperator() {
        return operator;
    }

    public void setOperator(LogicalOperator operator) {
        this.operator = operator;
    }

    public boolean isNot() {
        return not;
    }

    public void setNot(boolean not) {
        this.not = not;
    }
}
