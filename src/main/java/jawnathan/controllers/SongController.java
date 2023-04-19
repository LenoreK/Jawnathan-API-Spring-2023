package jawnathan.controllers;

import jawnathan.domain.PersonService;
import jawnathan.domain.Result;
import jawnathan.domain.SongService;
import jawnathan.models.Person;
import jawnathan.models.Song;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/song")
public class SongController {
    private final SongService songService;

    public SongController(SongService songService) {
        this.songService = songService;
    }
    @GetMapping
    public List<Song> findAll() {
        return songService.findAll();
    }

    @GetMapping("/{songId}")
    public ResponseEntity<Song> findById(@PathVariable int songId) {
        Song song = songService.findById(songId);
        if (song == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(song);
    }

    @PostMapping
    public ResponseEntity<Object> add(@RequestBody Song song) {
        Result<Song> result = songService.add(song);
        if (result.isSuccess()) {
            return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);
        }
        return ErrorResponse.build(result);
    }

    @PutMapping("/{songId}")
    public ResponseEntity<Object> update(@PathVariable int songId, @RequestBody Song song) {
        if (songId != song.getSongId()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        Result<Song> result = songService.update(song);
        if (result.isSuccess()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ErrorResponse.build(result);
    }

    @DeleteMapping("/{songId}")
    public ResponseEntity<Void> deleteById(@PathVariable int songId) {
        if (songService.deleteById(songId)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
