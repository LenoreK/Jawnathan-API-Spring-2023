package jawnathan.data;

import jawnathan.models.Group;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface GroupRepository {
    List<Group> findAll();

    Group findById(int groupId);

    Group add(Group group);

    boolean update(Group group);

    @Transactional
    boolean deleteById(int groupId);
}
