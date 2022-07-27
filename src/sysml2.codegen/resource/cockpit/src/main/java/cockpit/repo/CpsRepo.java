package «pkg».cockpit.repo;

import «pkg».cockpit.model.monitor.Cps;
import «pkg».cockpit.util.ReflectionUtils;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Component
public class CpsRepo {

    private Map<Cps, Map<String, Object>> repo = new HashMap<>();

    public CpsRepo() {
        for (Cps cps: Cps.values()) {
            repo.put(cps, new HashMap<>());
        }
    }

    public void put(Cps cps, Object instance) {
        Map<String, Object> map = repo.get(cps);
        String id = ReflectionUtils.resolveId(instance);
        map.put(id, instance);
    }

    public <T> T get(T old) {
        Cps cps = ReflectionUtils.resolveCps(old);
        String id = ReflectionUtils.resolveId(old);
        return (T) getAll(cps).stream()
                .filter(r -> id.equals(ReflectionUtils.resolveId(r)))
                .findAny()
                .orElse(null);
    }

    public <T> Collection<T> getAll(Cps cps) {
        return (Collection<T>) repo.get(cps).values();
    }

    public void remove(Cps cps, Object instance) {
        Map<String, Object> map = repo.get(cps);
        String id = ReflectionUtils.resolveId(instance);
        if (map.containsKey(id)) {
            map.remove(id);
        }
    }
}
