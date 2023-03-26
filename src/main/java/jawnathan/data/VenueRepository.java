package jawnathan.data;

import jawnathan.models.Venue;

import java.util.List;

public interface VenueRepository {
    List<Venue> findAll();

    Venue findById(int venueId);

    Venue add(Venue venue);

    boolean update(Venue venue);

    boolean deleteById(int venueId);
}
