package jawnathan.controllers;

import jawnathan.domain.Result;
import jawnathan.domain.VenueService;
import jawnathan.models.Venue;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/venue")
public class VenueController {
    private final VenueService venueService;

    public VenueController(VenueService venueService) {
        this.venueService = venueService;
    }
    @GetMapping
    public List<Venue> findAll() {
        return venueService.findAll();
    }

    @GetMapping("/{venueId}")
    public ResponseEntity<Venue> findById(@PathVariable int venueId) {
        Venue venue = venueService.findById(venueId);
        if (venue == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(venue);
    }

    @PostMapping
    public ResponseEntity<Object> add(@RequestBody Venue venue) {
        Result<Venue> result = venueService.add(venue);
        if (result.isSuccess()) {
            return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);
        }
        return ErrorResponse.build(result);
    }

    @PutMapping("/{venueId}")
    public ResponseEntity<Object> update(@PathVariable int venueId, @RequestBody Venue venue) {
        if (venueId != venue.getVenueId()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        Result<Venue> result = venueService.update(venue);
        if (result.isSuccess()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ErrorResponse.build(result);
    }

    @DeleteMapping("/{venueId}")
    public ResponseEntity<Void> deleteById(@PathVariable int venueId) {
        if (venueService.deleteById(venueId)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
