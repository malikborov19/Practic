package com.malikborov.servicemonitor.controller;

import com.malikborov.servicemonitor.model.MonitoredService;
import com.malikborov.servicemonitor.repository.MonitoredServiceRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/services")
public class AdminController {

    private final MonitoredServiceRepository repo;

    public AdminController(MonitoredServiceRepository repo) {
        this.repo = repo;
    }

    @PostMapping
    public MonitoredService add(@RequestBody MonitoredService s) {
        return repo.save(s);
    }
}
