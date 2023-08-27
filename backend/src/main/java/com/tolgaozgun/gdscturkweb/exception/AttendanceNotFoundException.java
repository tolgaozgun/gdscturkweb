package com.tolgaozgun.gdscturkweb.exception;

public class AttendanceNotFoundException extends RuntimeException {
    public AttendanceNotFoundException() {
        super("Attendance not found!");
    }

    public AttendanceNotFoundException(Long attendanceId) {
        super("Attendance with ID " + attendanceId + "not found!");
    }
}
