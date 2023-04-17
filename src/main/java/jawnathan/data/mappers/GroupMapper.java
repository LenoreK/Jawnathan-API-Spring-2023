package jawnathan.data.mappers;

import jawnathan.models.Group;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GroupMapper implements RowMapper<Group> {
    @Override
    public Group mapRow(ResultSet resultSet, int i) throws SQLException {
        Group group = new Group();
        group.setGroupId(resultSet.getInt("group_id"));
        group.setGroupName(resultSet.getString("group_name"));
        group.setGenre(resultSet.getString("genre"));
        group.setPhotoUrl(resultSet.getString("group_photo_url"));
        group.setWebsiteUrl(resultSet.getString("group_website_url"));
        return group;
    }
}
