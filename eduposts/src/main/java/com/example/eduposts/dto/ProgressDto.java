package com.example.eduposts.dto;
import java.util.Date;

public class ProgressDto {
    private String status;
    private int completed;
    private String notes;
    private Date reminder;

    // Constructors
    public ProgressDto() {}

    public ProgressDto(String status, int completed, String notes, Date reminder) {
        this.status = status;
        this.completed = completed;
        this.notes = notes;
        this.reminder = reminder;
    }

    // Getters and setters
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getCompleted() {
        return completed;
    }

    public void setCompleted(int completed) {
        this.completed = completed;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Date getReminder() {
        return reminder;
    }

    public void setReminder(Date reminder) {
        this.reminder = reminder;
    }
}