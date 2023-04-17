package jawnathan.data;

import jawnathan.data.mappers.VideoMapper;
import jawnathan.models.Video;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class VideoJdbcTemplateRepository implements VideoRepository{
    private final JdbcTemplate jdbcTemplate;

    public VideoJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Video> findAll() {
        // limit until we develop a paging solution
        final String sql = "select video_id, video_url, video_name from video;";
        return jdbcTemplate.query(sql, new VideoMapper());
    }

    @Override
    public Video findById(int videoId){
        final String sql = "select video_id, video_url, video_name "
                + "from video "
                + "where video_id = ?;";
        return jdbcTemplate.query(sql, new VideoMapper(), videoId).stream()
                .findFirst()
                .orElse(null);
    }

    @Override
    public Video add(Video video){
        final String sql = "insert into video (video_url, video_name)"
                + "values (?,?);";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, video.getUrl());
            ps.setString(2, video.getName());
            return ps;
        }, keyHolder);

        if (rowsAffected <= 0){
            return null;
        }

        video.setVideoId(keyHolder.getKey().intValue());
        return video;
    }

    @Override
    public boolean update(Video video){
        final String sql = "update video set "
                + "video_url = ?, "
                + "video_name = ? "
                + "where video_id = ?;";

        return jdbcTemplate.update(sql,
                video.getUrl(),
                video.getName(),
                video.getVideoId()) > 0;
    }

    @Override
    @Transactional
    public boolean deleteById(int videoId) {
        jdbcTemplate.update("delete from group_video where video_id = ?", videoId);
        return jdbcTemplate.update("delete from video where video_id = ?", videoId) > 0;
    }
}
