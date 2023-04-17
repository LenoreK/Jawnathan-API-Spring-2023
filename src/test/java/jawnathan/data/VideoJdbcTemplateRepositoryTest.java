package jawnathan.data;

import jawnathan.models.Person;
import jawnathan.models.Video;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class VideoJdbcTemplateRepositoryTest {

    @Autowired
    VideoJdbcTemplateRepository repository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup(){
        knownGoodState.set();
    }

    @Test
    void findAll() {
        List<Video> videos = repository.findAll();
        assertNotNull(videos);
    }

    @Test
    void findById() {
        Video actual = repository.findById(2);
        assertNotNull(actual);
        assertEquals(2, actual.getVideoId());
    }

    @Test
    void add() {
        Video video = new Video();
        video.setUrl("something.com");
        video.setName("Hello World");
        Video actual = repository.add(video);
        assertNotNull(actual);
        assertEquals(4, actual.getVideoId());
    }

    @Test
    void update() {
        Video video = new Video();
        video.setVideoId(2);
        video.setUrl("something.com");
        video.setName("Hello World");
        assertTrue(repository.update(video));
    }

    @Test
    void deleteById() {
        assertTrue(repository.deleteById(1));
        assertFalse(repository.deleteById(3000));
    }
}