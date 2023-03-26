package jawnathan.data;

import jawnathan.models.Gig;
import jawnathan.models.Venue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class VenueJdbcTemplateRepositoryTest {

    @Autowired
    VenueJdbcTemplateRepository repository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup(){
        knownGoodState.set();
    }

    @Test
    void findAll(){
        List<Venue> venues = repository.findAll();
        assertNotNull(venues);
    }

    @Test
    void findById() {
        Venue actual = repository.findById(1);
        assertNotNull(actual);
        assertEquals(1, actual.getVenueId());
        assertEquals("NJ", actual.getState());
        assertEquals("https://weedstock.org/home", actual.getUrl());
    }

    @Test
    void shouldAdd(){
        Venue venue = makeVenue();
        Venue actual = repository.add(venue);
        assertNotNull(venue);
    }

    @Test
    void shouldUpdate(){
        Venue venue = makeVenue();
        venue.setVenueId(3);
        assertTrue(repository.update(venue));
        venue.setVenueId(16);
        assertFalse(repository.update(venue));
    }

    @Test
    void shouldDeleteById(){
        assertTrue(repository.deleteById(1));
        assertFalse(repository.deleteById(5));
    }

    Venue makeVenue(){
        Venue venue = new Venue();
        venue.setName("Venue");
        venue.setUrl("something.com");
        venue.setAddress("100 Park Blvd.");
        venue.setCity("Cherry Hill");
        venue.setState("NJ");
        venue.setZipcode("08043");
        return venue;
    }

}