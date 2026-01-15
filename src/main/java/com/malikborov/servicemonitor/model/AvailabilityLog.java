package com.malikborov.servicemonitor.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "availability_logs")
public class AvailabilityLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "service_id")
    private MonitoredService service;

    private boolean up;
    private LocalDateTime checkedAt;

    public Long getId() { return id; }

    public MonitoredService getService() { return service; }
    public void setService(MonitoredService service) { this.service = service; }

    public boolean isUp() { return up; }
    public void setUp(boolean up) { this.up = up; }

    public LocalDateTime getCheckedAt() { return checkedAt; }
    public void setCheckedAt(LocalDateTime checkedAt) { this.checkedAt = checkedAt; }
}
