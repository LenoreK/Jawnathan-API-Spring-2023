package jawnathan.controllers;

import jawnathan.domain.Result;
import jawnathan.domain.VenueService;
import jawnathan.domain.VideoService;
import jawnathan.models.Venue;
import jawnathan.models.Video;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/video")
public class VideoController {
    private final VideoService videoService;

    public VideoController(VideoService videoService) {
        this.videoService = videoService;
    }
    @GetMapping
    public List<Video> findAll() {
        return videoService.findAll();
    }

    @GetMapping("/{videoId}")
    public ResponseEntity<Video> findById(@PathVariable int videoId) {
        Video video = videoService.findById(videoId);
        if (video == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(video);
    }

    @PostMapping
    public ResponseEntity<Object> add(@RequestBody Video video) {
        Result<Video> result = videoService.add(video);
        if (result.isSuccess()) {
            return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);
        }
        return ErrorResponse.build(result);
    }

    @PutMapping("/{videoId}")
    public ResponseEntity<Object> update(@PathVariable int videoId, @RequestBody Video video) {
        if (videoId != video.getVideoId()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        Result<Video> result = videoService.update(video);
        if (result.isSuccess()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ErrorResponse.build(result);
    }

    @DeleteMapping("/{videoId}")
    public ResponseEntity<Void> deleteById(@PathVariable int videoId) {
        if (videoService.deleteById(videoId)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
