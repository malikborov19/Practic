package com.malikborov.servicemonitor.scheduler;

import com.malikborov.servicemonitor.service.MonitoringService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class HealthCheckScheduler {

    private final MonitoringService monitoringService;

    public HealthCheckScheduler(MonitoringService monitoringService) {
        this.monitoringService = monitoringService;
    }

    @Scheduled(cron = "0 */1 * * * *") // каждую минуту
    public void runChecks() {
        monitoringService.checkAllServices();
    }
}
