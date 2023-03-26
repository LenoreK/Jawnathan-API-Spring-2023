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
}