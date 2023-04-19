package jawnathan.controllers;

import jawnathan.domain.AlbumService;
import jawnathan.domain.GigService;
import jawnathan.domain.Result;
import jawnathan.models.Album;
import jawnathan.models.Gig;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/gig")
public class GigController {
    private final GigService gigService;

    public GigController(GigService gigService) {
        this.gigService = gigService;
    }
    @GetMapping
    public List<Gig> findAll() {
        return gigService.findAll();
    }

    @GetMapping("/{gigId}")
    public ResponseEntity<Gig> findById(@PathVariable int gigId) {
        Gig gig = gigService.findById(gigId);
        if (gig == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(gig);
    }

    @PostMapping
    public ResponseEntity<Object> add(@RequestBody Gig gig) {
        Result<Gig> result = gigService.add(gig);
        if (result.isSuccess()) {
            return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);
        }
        return ErrorResponse.build(result);
    }

    @PutMapping("/{gigId}")
    public ResponseEntity<Object> update(@PathVariable int gigId, @RequestBody Gig gig) {
        if (gigId != gig.getGigId()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        Result<Gig> result = gigService.update(gig);
        if (result.isSuccess()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ErrorResponse.build(result);
    }

    @DeleteMapping("/{gigId}")
    public ResponseEntity<Void> deleteById(@PathVariable int gigId) {
        if (gigService.deleteById(gigId)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
