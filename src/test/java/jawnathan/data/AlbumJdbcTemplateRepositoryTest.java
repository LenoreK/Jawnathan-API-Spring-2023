package jawnathan.data;

import jawnathan.models.Album;
import jawnathan.models.Gig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class AlbumJdbcTemplateRepositoryTest {

    @Autowired
    AlbumJdbcTemplateRepository repository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup(){
        knownGoodState.set();
    }

    @Test
    void findAll() {
        List<Album> albums = repository.findAll();
        assertNotNull(albums);
    }

    @Test
    void findById() {
        Album actual = repository.findById(1);
        assertNotNull(actual);
        assertEquals(1, actual.getAlbumId());
    }

    @Test
    void add() {
        Album album = new Album();
        album.setName("Hello World");
        album.setReleaseYear(2023);
        album.setPhotoUrl(null);
        album.setAlbumUrl(null);
        Album actual = repository.add(album);
        assertNotNull(actual);
        assertEquals(2, actual.getAlbumId());
    }

    @Test
    void update() {
        Album album = new Album();
        album.setAlbumId(1);
        album.setName("Hello World");
        album.setReleaseYear(2023);
        album.setPhotoUrl(null);
        album.setAlbumUrl(null);
        assertTrue(repository.update(album));
    }

    @Test
    void deleteById() {
        assertTrue(repository.deleteById(1));
        assertFalse(repository.deleteById(3000));
    }
}