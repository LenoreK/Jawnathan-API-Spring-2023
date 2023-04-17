package jawnathan.data.mappers;

import jawnathan.models.Song;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SongMapper implements RowMapper<Song> {
    @Override
    public Song mapRow(ResultSet resultSet, int i) throws SQLException {
        Song song = new Song();
        song.setSongId(resultSet.getInt("song_id"));
        song.setName(resultSet.getString("song_name"));
        song.setPlaySongUrl(resultSet.getString("song_url"));
        song.setPhotoUrl(resultSet.getString("photo_url"));
        song.setBuySongUrl(resultSet.getString("buy_song_url"));
        song.setAlbumId(resultSet.getInt("album_id"));
        song.setGroupId(resultSet.getInt("group_id"));
        return song;
    }
}
