package jawnathan.domain;

import jawnathan.data.AlbumRepository;
import jawnathan.models.Album;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static jawnathan.TestHelper.makeAlbum;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class AlbumServiceTest {

    @Autowired
    AlbumService service;

    @MockBean
    AlbumRepository repository;

    @Test
    void findAll() {
    }

    @Test
    void findById() {
        Album expected = makeAlbum();
        when(repository.findById(1)).thenReturn(expected);
        Album actual = service.findById(1);
        assertEquals(expected, actual);
    }

    @Test
    void add() {
    }

    @Test
    void update() {
    }

    @Test
    void deleteById() {
    }
}