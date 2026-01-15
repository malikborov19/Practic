package com.malikborov.servicemonitor.repository;

import com.malikborov.servicemonitor.model.MonitoredService;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MonitoredServiceRepository extends JpaRepository<MonitoredService, Long> {
}
