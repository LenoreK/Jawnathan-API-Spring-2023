package jawnathan.controllers;

import jawnathan.domain.AlbumService;
import jawnathan.domain.Result;
import jawnathan.models.Album;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/album")
public class AlbumController {
    private final AlbumService albumService;

    public AlbumController(AlbumService albumService) {
        this.albumService = albumService;
    }
    @GetMapping
    public List<Album> findAll() {
        return albumService.findAll();
    }

    @GetMapping("/{albumId}")
    public ResponseEntity<Album> findById(@PathVariable int albumId) {
        Album album = albumService.findById(albumId);
        if (album == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(album);
    }

    @PostMapping
    public ResponseEntity<Object> add(@RequestBody Album album) {
        Result<Album> result = albumService.add(album);
        if (result.isSuccess()) {
            return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);
        }
        return ErrorResponse.build(result);
    }

    @PutMapping("/{albumId}")
    public ResponseEntity<Object> update(@PathVariable int albumId, @RequestBody Album album) {
        if (albumId != album.getAlbumId()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        Result<Album> result = albumService.update(album);
        if (result.isSuccess()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ErrorResponse.build(result);
    }

    @DeleteMapping("/{albumId}")
    public ResponseEntity<Void> deleteById(@PathVariable int albumId) {
        if (albumService.deleteById(albumId)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
