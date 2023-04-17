package jawnathan.domain;

import jawnathan.data.VenueRepository;
import jawnathan.data.VideoRepository;
import jawnathan.models.Venue;
import jawnathan.models.Video;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VenueService {
    private final VenueRepository repository;

    public VenueService(VenueRepository repository) {
        this.repository = repository;
    }

    public List<Venue> findAll() {
        return repository.findAll();
    }

    public Venue findById(int venueId) {
        return repository.findById(venueId);
    }

    public Result<Venue> add(Venue venue) {
        Result<Venue> result = validate(venue);
        if (!result.isSuccess()) {
            return result;
        }

        if (venue.getVenueId() != 0) {
            result.addMessage("venueId cannot be set for `add` operation", ResultType.INVALID);
            return result;
        }

        venue = repository.add(venue);
        result.setPayload(venue);
        return result;
    }

    public Result<Venue> update(Venue venue) {
        Result<Venue> result = validate(venue);
        if (!result.isSuccess()) {
            return result;
        }

        if (venue.getVenueId() <= 0) {
            result.addMessage("venueId must be set for `update` operation", ResultType.INVALID);
            return result;
        }

        if (!repository.update(venue)) {
            String msg = String.format("venueId: %s, not found", venue.getVenueId());
            result.addMessage(msg, ResultType.NOT_FOUND);
        }

        return result;
    }

    public boolean deleteById(int venueId) {
        return repository.deleteById(venueId);
    }

    private Result<Venue> validate(Venue venue){
        Result<Venue> result = new Result<>();
        if(venue == null){
            result.addMessage("venue cannot be null", ResultType.INVALID);
            return result;
        }
        if (Validations.isNullOrBlank(venue.getName())){
            result.addMessage("Venue name is required", ResultType.INVALID);
        }
        if (Validations.isNullOrBlank(venue.getAddress())){
            result.addMessage("Venue address is required", ResultType.INVALID);
        }
        if (Validations.isNullOrBlank(venue.getCity())){
            result.addMessage("Venue city is required", ResultType.INVALID);
        }
        if (Validations.isNullOrBlank(venue.getState())){
            result.addMessage("Venue city is required", ResultType.INVALID);
        }
        if (Validations.isNullOrBlank(venue.getZipcode())){
            result.addMessage("Venue zipcode is required", ResultType.INVALID);
        }
        return result;
    }
}
