package jawnathan.models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Gig {
    private int gigId;
    private LocalDate date;
    private LocalDate endDate;
    private String details;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private List<Venue> venues = new ArrayList<>();

    public Gig(int gigId, LocalDate date, LocalDate endDate, String details, LocalDateTime startTime, LocalDateTime endTime) {
        this.gigId = gigId;
        this.date = date;
        this.endDate = endDate;
        this.details = details;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Gig() {
    }

    public int getGigId() {
        return gigId;
    }

    public void setGigId(int gigId) {
        this.gigId = gigId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public List<Venue> getVenues() {
        return venues;
    }

    public void setVenues(List<Venue> venues) {
        this.venues = venues;
    }
}