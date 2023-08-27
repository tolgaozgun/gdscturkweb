package com.tolgaozgun.gdscturkweb.entity;

import com.tolgaozgun.gdscturkweb.entity.user.LeadEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name = "attendance_status")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AttendanceStatusEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "attendance_status_id", nullable = false)
    private Long attendanceStatusId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "attendance_id", nullable = false)
    private AttendanceEntity attendanceEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lead_id", nullable = false)
    private LeadEntity lead;

    @Column(name = "attendance_status", nullable = false)
    private Boolean attendanceStatus;

}