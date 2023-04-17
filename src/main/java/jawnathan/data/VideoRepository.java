package jawnathan.data;

import jawnathan.models.Video;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface VideoRepository {
    List<Video> findAll();

    Video findById(int videoId);

    Video add(Video video);

    boolean update(Video video);

    @Transactional
    boolean deleteById(int videoId);
}
