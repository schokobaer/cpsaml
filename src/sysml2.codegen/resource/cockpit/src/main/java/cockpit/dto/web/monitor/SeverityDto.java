package «pkg».cockpit.dto.web.monitor;

import «pkg».cockpit.dto.web.monitor.logic.ThresholdDto;

public class SeverityDto {
    private ThresholdDto green;
    private ThresholdDto orange;

    public ThresholdDto getGreen() {
        return green;
    }

    public void setGreen(ThresholdDto green) {
        this.green = green;
    }

    public ThresholdDto getOrange() {
        return orange;
    }

    public void setOrange(ThresholdDto orange) {
        this.orange = orange;
    }
}
