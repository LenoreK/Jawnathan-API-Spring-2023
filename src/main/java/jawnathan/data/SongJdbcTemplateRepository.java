package jawnathan.data;

import jawnathan.data.mappers.SongMapper;
import jawnathan.models.Song;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class SongJdbcTemplateRepository implements SongRepository {
    private final JdbcTemplate jdbcTemplate;

    public SongJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Song> findAll() {
        final String sql = "select song_id, song_name, song_url, photo_url, buy_song_url, album_id, group_id from song;";
        return jdbcTemplate.query(sql, new SongMapper());
    }

    @Override
    public Song findById(int songId){
        final String sql = "select song_id, song_name, song_url, photo_url, buy_song_url, album_id, group_id "
                + "from song "
                + "where song_id = ?;";
        return jdbcTemplate.query(sql, new SongMapper(), songId).stream()
                .findFirst()
                .orElse(null);
    }

    @Override
    public Song add(Song song) {
        final String sql = "insert into song (song_name, song_url, photo_url, buy_song_url, album_id, group_id) values (?,?,?,?,?,?);";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, song.getName());
            ps.setString(2, song.getPlaySongUrl());
            ps.setString(3, song.getPhotoUrl());
            ps.setString(4, song.getBuySongUrl());
            ps.setInt(5, song.getAlbumId());
            ps.setInt(6, song.getGroupId());
            return ps;
        }, keyHolder);

        if (rowsAffected <= 0) {
            return null;
        }

        song.setSongId(keyHolder.getKey().intValue());
        return song;
    }

    @Override
    public boolean update(Song song){
        final String sql = "update song set "
                + "song_name = ?, "
                + "song_url = ?, "
                + "photo_url = ?, "
                + "buy_song_url = ?, "
                + "album_id = ?, "
                + "group_id = ? "
                + "where song_id = ?";

        return jdbcTemplate.update(sql, song.getName(), song.getPlaySongUrl(), song.getPhotoUrl(), song.getBuySongUrl(), song.getAlbumId(), song.getGroupId(), song.getSongId()) > 0;
    }

    @Override
    @Transactional
    public boolean deleteById(int songId){
        return jdbcTemplate.update("delete from song where song_id = ?", songId) > 0;
    }
}
