package jawnathan.data;

import jawnathan.models.Gig;
import jawnathan.models.Venue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class GigJdbcTemplateRepositoryTest {

    @Autowired
    GigJdbcTemplateRepository repository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup(){
        knownGoodState.set();
    }

    @Test
    void findAll(){
        List<Gig> gigs = repository.findAll();
        assertNotNull(gigs);
    }

    @Test
    void findById() {
        Gig actual = repository.findById(2);
        assertNotNull(actual);
        assertEquals(2, actual.getGigId());
    }

    @Test
    void shouldAddGig(){
        Gig gig = new Gig();
        gig.setDate(LocalDate.ofEpochDay(2023-07-21));
        gig.setDetails("I'm playing with cheesy and the crackers at Laguna");
        gig.setStartTime(LocalTime.parse("18:30"));
        gig.setEndTime(LocalTime.parse("21:30"));
        gig.setVenueId(2);
        Gig actual = repository.add(gig);
        assertNotNull(actual);
        assertEquals(4, actual.getGigId());
    }

    @Test
    void shouldUpdate(){
        Gig gig = new Gig();
        gig.setGigId(3);
        gig.setDate(LocalDate.ofEpochDay(2023-07-21));
        gig.setDetails("I'm playing with cheesy and the crackers at Laguna");
        gig.setStartTime(LocalTime.parse("18:30"));
        gig.setEndTime(LocalTime.parse("21:30"));
        gig.setVenueId(2);
        assertTrue(repository.update(gig));
    }

    @Test
    void shouldDelete(){
        assertTrue(repository.deleteById(1));
        assertFalse(repository.deleteById(3000));
    }
}