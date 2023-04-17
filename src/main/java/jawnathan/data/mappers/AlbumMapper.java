package jawnathan.data.mappers;

import jawnathan.models.Album;
import jawnathan.models.Gig;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AlbumMapper implements RowMapper<Album> {

    @Override
    public Album mapRow(ResultSet resultSet, int i) throws SQLException {
        Album album = new Album();
        album.setAlbumId(resultSet.getInt("album_id"));
        album.setName(resultSet.getString("album_name"));
        album.setReleaseYear(resultSet.getInt("release_year"));
        album.setPhotoUrl(resultSet.getString("photo_url"));
        album.setAlbumUrl(resultSet.getString("buy_album_url"));
        return album;
    }
}
