package jawnathan.data;

import jawnathan.models.Person;
import jawnathan.models.Song;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class SongJdbcTemplateRepositoryTest {

    @Autowired
    SongJdbcTemplateRepository repository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup(){
        knownGoodState.set();
    }

    @Test
    void findAll() {
        List<Song> songs = repository.findAll();
        assertNotNull(songs);
    }

    @Test
    void findById() {
        Song actual = repository.findById(2);
        assertNotNull(actual);
        assertEquals(2, actual.getSongId());
    }

    @Test
    void add() {
        Song song = new Song();
        song.setName("Hello World");
        song.setPlaySongUrl("something.com");
        song.setPhotoUrl("something.com");
        song.setBuySongUrl("something.com");
        song.setAlbumId(1);
        song.setGroupId(1);
        Song actual = repository.add(song);
        assertNotNull(actual);
        assertEquals(4, actual.getSongId());
    }

    @Test
    void update() {
        Song song = new Song();
        song.setSongId(1);
        song.setName("Hello World");
        song.setPlaySongUrl("something.com");
        song.setPhotoUrl("something.com");
        song.setBuySongUrl("something.com");
        song.setAlbumId(1);
        song.setGroupId(1);
        assertTrue(repository.update(song));
    }

    @Test
    void deleteById() {
        assertTrue(repository.deleteById(1));
        assertFalse(repository.deleteById(3000));
    }
}