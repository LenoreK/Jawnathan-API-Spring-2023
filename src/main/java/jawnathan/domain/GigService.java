package jawnathan.domain;

import jawnathan.data.GigRepository;
import jawnathan.models.Gig;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class GigService {
    private final GigRepository repository;

    public GigService(GigRepository repository) {
        this.repository = repository;
    }

    public List<Gig> findAll() {
        return repository.findAll();
    }

    public Gig findById(int gigId) {
        return repository.findById(gigId);
    }

    public Result<Gig> add(Gig gig) {
        Result<Gig> result = validate(gig);
        if (!result.isSuccess()) {
            return result;
        }

        if (gig.getGigId() != 0) {
            result.addMessage("gigId cannot be set for `add` operation", ResultType.INVALID);
            return result;
        }

        gig = repository.add(gig);
        result.setPayload(gig);
        return result;
    }

    public Result<Gig> update(Gig gig) {
        Result<Gig> result = validate(gig);
        if (!result.isSuccess()) {
            return result;
        }

        if (gig.getGigId() <= 0) {
            result.addMessage("gigId must be set for `update` operation", ResultType.INVALID);
            return result;
        }

        if (!repository.update(gig)) {
            String msg = String.format("gigId: %s, not found", gig.getGigId());
            result.addMessage(msg, ResultType.NOT_FOUND);
        }

        return result;
    }

    public boolean deleteById(int gigId) {
        return repository.deleteById(gigId);
    }

    private Result<Gig> validate(Gig gig){
        Result<Gig> result = new Result<>();
        if (gig == null){
            result.addMessage("gig cannot be null", ResultType.INVALID);
        }
        if (Validations.isNullOrBlank(String.valueOf(gig.getDate()))){
            result.addMessage("gig is required", ResultType.INVALID);
        }
        if (gig.getDate().isBefore(LocalDate.now())){
            result.addMessage("gig can not be in the past", ResultType.INVALID);
        }
        if (gig.getEndTime().isBefore(gig.getStartTime())){
            result.addMessage("end time cannot be before start time", ResultType.INVALID);
        }
        if (Validations.isNullOrBlank(String.valueOf(gig.getVenueId()))){
            result.addMessage("venueId cannot be null", ResultType.INVALID);
        }
        if (Validations.isNullOrBlank(gig.getDetails())){
            result.addMessage("gigId cannot be null", ResultType.INVALID);
        }
        return result;
    }
}
