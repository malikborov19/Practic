package com.malikborov.servicemonitor.controller;

import com.malikborov.servicemonitor.model.*;
import com.malikborov.servicemonitor.repository.*;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/stats")
public class StatsController {

    private final MonitoredServiceRepository serviceRepo;
    private final AvailabilityLogRepository logRepo;

    public StatsController(MonitoredServiceRepository serviceRepo,
                           AvailabilityLogRepository logRepo) {
        this.serviceRepo = serviceRepo;
        this.logRepo = logRepo;
    }

    @GetMapping("/availability")
    public Map<String, Object> availability(
            @RequestParam Long serviceId,
            @RequestParam String from,
            @RequestParam String to
    ) {
        MonitoredService service = serviceRepo.findById(serviceId).orElseThrow();

        LocalDateTime f = LocalDateTime.parse(from);
        LocalDateTime t = LocalDateTime.parse(to);

        List<AvailabilityLog> logs =
                logRepo.findByServiceAndCheckedAtBetween(service, f, t);

        long total = logs.size();
        long up = logs.stream().filter(AvailabilityLog::isUp).count();

        double percent = total == 0 ? 0 : (up * 100.0 / total);

        Map<String, Object> res = new HashMap<>();
        res.put("totalChecks", total);
        res.put("upChecks", up);
        res.put("availabilityPercent", percent);

        return res;
    }
}
