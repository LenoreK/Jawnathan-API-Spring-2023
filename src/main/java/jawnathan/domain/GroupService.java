package jawnathan.domain;

import jawnathan.data.GroupRepository;
import jawnathan.models.Group;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupService {
    private final GroupRepository repository;

    public GroupService(GroupRepository repository) {
        this.repository = repository;
    }

    public List<Group> findAll() {
        return repository.findAll();
    }

    public Group findById(int groupId) {
        return repository.findById(groupId);
    }

    public Result<Group> add(Group group) {
        Result<Group> result = validate(group);
        if (!result.isSuccess()) {
            return result;
        }

        if (group.getGroupId() != 0) {
            result.addMessage("groupId cannot be set for `add` operation", ResultType.INVALID);
            return result;
        }

        group = repository.add(group);
        result.setPayload(group);
        return result;
    }

    public Result<Group> update(Group group) {
        Result<Group> result = validate(group);
        if (!result.isSuccess()) {
            return result;
        }

        if (group.getGroupId() <= 0) {
            result.addMessage("groupId must be set for `update` operation", ResultType.INVALID);
            return result;
        }

        if (!repository.update(group)){
            String msg = String.format("groupId: %s, not found", group.getGroupId());
            result.addMessage(msg, ResultType.NOT_FOUND);
        }

        return result;
    }

    public boolean deleteById(int groupId) {
        return repository.deleteById(groupId);
    }

    private Result<Group> validate(Group group){
        Result<Group> result = new Result<>();
        if (group == null){
            result.addMessage("group cannot be null", ResultType.INVALID);
            return result;
        }
        if (Validations.isNullOrBlank(group.getGroupName())){
            result.addMessage("group name is required", ResultType.INVALID);
        }
        if (Validations.isNullOrBlank(group.getGenre())){
            result.addMessage("genre is required", ResultType.INVALID);
        }
        return result;
    }
}
