package jawnathan.data;

import jawnathan.models.Gig;
import jawnathan.models.Group;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class GroupJdbcTemplateRepositoryTest {
    @Autowired
    GroupJdbcTemplateRepository repository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup(){
        knownGoodState.set();
    }

    @Test
    void findAll() {
        List<Group> groups = repository.findAll();
        assertNotNull(groups);
    }

    @Test
    void findById() {
        Group actual = repository.findById(2);
        assertNotNull(actual);
        assertEquals(2, actual.getGroupId());
    }

    @Test
    void add() {
        Group group = new Group();
        group.setGroupName("New Group");
        group.setGenre("Punk Rock");
        group.setPhotoUrl(null);
        group.setWebsiteUrl(null);
        Group actual = repository.add(group);
        assertNotNull(actual);
        assertEquals(4, actual.getGroupId());
    }

    @Test
    void update() {
        Group group = new Group();
        group.setGroupId(3);
        group.setGroupName("New Group");
        group.setGenre("Punk Rock");
        group.setPhotoUrl(null);
        group.setWebsiteUrl(null);
        assertTrue(repository.update(group));
    }

    @Test
    void deleteById() {
        assertTrue(repository.deleteById(1));
        assertFalse(repository.deleteById(3000));
    }
}