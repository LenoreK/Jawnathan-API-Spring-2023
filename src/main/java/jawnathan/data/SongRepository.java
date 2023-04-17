package jawnathan.data;

import jawnathan.models.Song;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface SongRepository {
    List<Song> findAll();

    Song findById(int songId);

    Song add(Song song);

    boolean update(Song song);

    @Transactional
    boolean deleteById(int songId);
}
