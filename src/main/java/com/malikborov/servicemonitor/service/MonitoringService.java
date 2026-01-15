package com.malikborov.servicemonitor.service;

import com.malikborov.servicemonitor.model.*;
import com.malikborov.servicemonitor.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class MonitoringService {

    private final MonitoredServiceRepository serviceRepo;
    private final AvailabilityLogRepository logRepo;
    private final RestTemplate restTemplate = new RestTemplate();

    public MonitoringService(MonitoredServiceRepository serviceRepo,
                             AvailabilityLogRepository logRepo) {
        this.serviceRepo = serviceRepo;
        this.logRepo = logRepo;
    }

    public void checkAllServices() {
        List<MonitoredService> services = serviceRepo.findAll();

        for (MonitoredService s : services) {
            boolean up = false;

            try {
                Map<?, ?> response = restTemplate.getForObject(s.getUrl(), Map.class);
                if (response != null && "UP".equals(response.get("status"))) {
                    up = true;
                }
            } catch (Exception e) {
                up = false;
            }

            AvailabilityLog log = new AvailabilityLog();
            log.setService(s);
            log.setUp(up);
            log.setCheckedAt(LocalDateTime.now());
            logRepo.save(log);
        }
    }
}
