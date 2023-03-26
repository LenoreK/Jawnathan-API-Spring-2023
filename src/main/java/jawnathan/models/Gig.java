package jawnathan.models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Gig {
    private int gigId;
    private LocalDate date;
    private String details;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    private int venueId;
    private List<PersonGigRole> personGigRoles = new ArrayList<>();
    private List<Venue> venues = new ArrayList<>();

    private List<GroupGig> groupGigs = new ArrayList<>();

    public Gig(int gigId, LocalDate date, LocalDate endDate, String details, LocalDateTime startTime, LocalDateTime endTime, int venueId) {
        this.gigId = gigId;
        this.date = date;
        this.details = details;
        this.startTime = startTime;
        this.endTime = endTime;
        this.venueId = venueId;
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

    public List<PersonGigRole> getPersonGigRoles() {
        return personGigRoles;
    }

    public void setPersonGigRoles(List<PersonGigRole> personGigRoles) {
        this.personGigRoles = personGigRoles;
    }

    public List<GroupGig> getGroupGigs() {
        return groupGigs;
    }

    public void setGroupGigs(List<GroupGig> groupGigs) {
        this.groupGigs = groupGigs;
    }

    public List<Venue> getVenues() {
        return venues;
    }

    public void setVenues(List<Venue> venues) {
        this.venues = venues;
    }

    public int getVenueId() {
        return venueId;
    }

    public void setVenueId(int venueId) {
        this.venueId = venueId;
    }
}