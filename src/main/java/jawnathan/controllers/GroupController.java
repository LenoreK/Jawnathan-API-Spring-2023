package jawnathan.controllers;

import jawnathan.domain.AlbumService;
import jawnathan.domain.GroupService;
import jawnathan.domain.Result;
import jawnathan.models.Album;
import jawnathan.models.Group;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/group")
public class GroupController {
    private final GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }
    @GetMapping
    public List<Group> findAll() {
        return groupService.findAll();
    }

    @GetMapping("/{groupId}")
    public ResponseEntity<Group> findById(@PathVariable int groupId) {
        Group group = groupService.findById(groupId);
        if (group == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(group);
    }

    @PostMapping
    public ResponseEntity<Object> add(@RequestBody Group group) {
        Result<Group> result = groupService.add(group);
        if (result.isSuccess()) {
            return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);
        }
        return ErrorResponse.build(result);
    }

    @PutMapping("/{groupId}")
    public ResponseEntity<Object> update(@PathVariable int groupId, @RequestBody Group group) {
        if (groupId != group.getGroupId()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        Result<Group> result = groupService.update(group);
        if (result.isSuccess()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ErrorResponse.build(result);
    }

    @DeleteMapping("/{groupId}")
    public ResponseEntity<Void> deleteById(@PathVariable int groupId) {
        if (groupService.deleteById(groupId)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
