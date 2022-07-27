package «pkg».cockpit.model.monitor;

public class Severity {
    private Threshold green;
    private Threshold orange;

    public Severity() { }

    public Severity(Threshold green, Threshold orange) {
        this.green = green;
        this.orange = orange;
    }

    public Threshold getGreen() {
        return green;
    }

    public void setGreen(Threshold green) {
        this.green = green;
    }

    public Threshold getOrange() {
        return orange;
    }

    public void setOrange(Threshold orange) {
        this.orange = orange;
    }
}
