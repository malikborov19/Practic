package com.malikborov.servicemonitor.repository;

import com.malikborov.servicemonitor.model.AvailabilityLog;
import com.malikborov.servicemonitor.model.MonitoredService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface AvailabilityLogRepository extends JpaRepository<AvailabilityLog, Long> {

    List<AvailabilityLog> findByServiceAndCheckedAtBetween(
            MonitoredService service,
            LocalDateTime from,
            LocalDateTime to
    );
}
