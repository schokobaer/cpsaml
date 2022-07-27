package «pkg».cockpit.model.monitor.logic;

public enum Comparator {
    EQ("="),
    NEQ("!="),
    SMT("<"),
    SME("<="),
    GRT(">"),
    GRE(">=")
    ;


    private String symbol;

    Comparator(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }
}
