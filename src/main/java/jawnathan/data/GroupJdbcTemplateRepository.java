package jawnathan.data;

import jawnathan.data.mappers.GroupMapper;
import jawnathan.models.Group;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class GroupJdbcTemplateRepository implements GroupRepository {
    private final JdbcTemplate jdbcTemplate;

    public GroupJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Group> findAll() {
        final String sql = "select group_id, group_name, genre, group_photo_url, group_website_url from musical_group;";
        return jdbcTemplate.query(sql, new GroupMapper());
    }

    @Override
    public Group findById(int groupId){
        final String sql = "select group_id, group_name, genre, group_photo_url, group_website_url "
                + "from musical_group "
                + "where group_id = ?;";
        return jdbcTemplate.query(sql, new GroupMapper(), groupId).stream()
                .findFirst()
                .orElse(null);
    }

    @Override
    public Group add(Group group) {
        final String sql = "insert into musical_group (group_name, genre, group_photo_url, group_website_url) values (?,?,?,?);";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, group.getGroupName());
            ps.setString(2, group.getGenre());
            ps.setString(3, group.getPhotoUrl());
            ps.setString(4, group.getWebsiteUrl());
            return ps;
        }, keyHolder);

        if (rowsAffected <= 0) {
            return null;
        }

        group.setGroupId(keyHolder.getKey().intValue());
        return group;
    }

    @Override
    public boolean update(Group group){
        final String sql = "update musical_group set "
                + "group_name = ?, "
                + "genre = ?, "
                + "group_photo_url = ?, "
                + "group_website_url = ? "
                + "where group_id = ?";

        return jdbcTemplate.update(sql, group.getGroupName(), group.getGenre(), group.getPhotoUrl(), group.getWebsiteUrl(), group.getGroupId()) > 0;
    }

    @Override
    @Transactional
    public boolean deleteById(int groupId){
        jdbcTemplate.update("delete from group_person where group_id = ?", groupId);
        jdbcTemplate.update("delete from song where group_id = ?", groupId);
        jdbcTemplate.update("delete from group_video where group_id = ?", groupId);
        return jdbcTemplate.update("delete from musical_group where group_id = ?", groupId) > 0;
    }
}
