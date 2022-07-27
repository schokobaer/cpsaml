package com.example.its.its.cockpit.service;

import com.example.its.its.cockpit.model.monitor.Cps;
import com.example.its.its.cockpit.model.monitor.Dashboard;
import com.example.its.its.cockpit.repo.DashboardRepo;
import com.example.its.its.cockpit.util.ReflectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class DashboardService {

    @Autowired
    private DashboardRepo repo;

    public List<Dashboard> getAll() {
        return repo.getAll();
    }

    public List<Dashboard> getAll(Object instance) {
        Cps cps = ReflectionUtils.resolveCps(instance);
        String id = ReflectionUtils.resolveId(instance);
        return getAll().stream()
                .filter(m -> m.getCps().equals(cps))
                .filter(m -> m.getInstance() == null || m.getInstance().equals(id))
                .collect(Collectors.toList());
    }

    public Dashboard get(String id) {
        return repo.get(id);
    }

    public String create(Dashboard dashboard) {
        dashboard.setId(UUID.randomUUID().toString());
        repo.add(dashboard);
        return dashboard.getId();
    }

    public void update(Dashboard dashboard) {
        repo.update(dashboard);
    }

    public void delete(String id) {
        repo.delete(id);
    }
}
