package jawnathan.data;

import jawnathan.data.mappers.AlbumMapper;
import jawnathan.models.Album;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class AlbumJdbcTemplateRepository implements AlbumRepository {

    private final JdbcTemplate jdbcTemplate;

    public AlbumJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Album> findAll() {
        final String sql = "select album_id, album_name, release_year, photo_url, buy_album_url from album;";
        return jdbcTemplate.query(sql, new AlbumMapper());
    }

    @Override
    public Album findById(int albumId){
        final String sql = "select album_id, album_name, release_year, photo_url, buy_album_url "
                + "from album "
                + "where album_id = ?;";
        return jdbcTemplate.query(sql, new AlbumMapper(), albumId).stream()
                .findFirst()
                .orElse(null);
    }

    @Override
    public Album add(Album album) {
        final String sql = "insert into album (album_name, release_year, photo_url, buy_album_url) values (?,?,?,?);";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, album.getName());
            ps.setInt(2, album.getReleaseYear());
            ps.setString(3, album.getPhotoUrl());
            ps.setString(4, album.getAlbumUrl());
            return ps;
        }, keyHolder);

        if (rowsAffected <= 0) {
            return null;
        }

        album.setAlbumId(keyHolder.getKey().intValue());
        return album;
    }

    @Override
    public boolean update(Album album){
        final String sql = "update album set "
                + "album_name = ?, "
                + "release_year = ?, "
                + "photo_url = ?, "
                + "buy_album_url = ? "
                + "where album_id = ?";

        return jdbcTemplate.update(sql, album.getName(), album.getReleaseYear(), album.getPhotoUrl(), album.getAlbumUrl(), album.getAlbumId()) > 0;
    }

    @Override
    @Transactional
    public boolean deleteById(int albumId){
        jdbcTemplate.update("delete from song where album_id = ?", albumId);
        return jdbcTemplate.update("delete from album where album_id = ?", albumId) > 0;
    }
}
