package jawnathan.data.mappers;

import jawnathan.models.Video;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class VideoMapper implements RowMapper<Video> {
    @Override
    public Video mapRow(ResultSet resultSet, int i) throws SQLException {
        Video video = new Video();
        video.setVideoId(resultSet.getInt("video_id"));
        video.setUrl(resultSet.getString("video_url"));
        video.setName(resultSet.getString("video_name"));
        return video;
    }
}
