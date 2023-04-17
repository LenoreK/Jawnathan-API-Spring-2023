package jawnathan.data;

import jawnathan.models.Album;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface AlbumRepository {
    List<Album> findAll();

    Album findById(int albumId);

    Album add(Album album);

    boolean update(Album album);

    @Transactional
    boolean deleteById(int albumId);
}
