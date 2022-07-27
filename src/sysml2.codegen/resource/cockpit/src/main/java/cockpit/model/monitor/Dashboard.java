package «pkg».cockpit.model.monitor;

import java.util.List;


public class Dashboard {
    private String id;
    private String name;
    private Cps cps;
    private String instance;
    private NotificationType notificationType;
    private List<Action> actions;
    private List<Monitor> monitors;
    private List<EventSpecification> eventSpecifications;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Cps getCps() {
        return cps;
    }

    public void setCps(Cps cps) {
        this.cps = cps;
    }

    public String getInstance() {
        return instance;
    }

    public void setInstance(String instance) {
        this.instance = instance;
    }

    public NotificationType getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(NotificationType notificationType) {
        this.notificationType = notificationType;
    }

    public List<Action> getActions() {
        return actions;
    }

    public void setActions(List<Action> actions) {
        this.actions = actions;
    }

    public List<Monitor> getMonitors() {
        return monitors;
    }

    public void setMonitors(List<Monitor> monitors) {
        this.monitors = monitors;
    }

    public List<EventSpecification> getEventSpecifications() {
        return eventSpecifications;
    }

    public void setEventSpecifications(List<EventSpecification> eventSpecifications) {
        this.eventSpecifications = eventSpecifications;
    }
}
