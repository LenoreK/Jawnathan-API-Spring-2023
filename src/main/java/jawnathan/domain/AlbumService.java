package jawnathan.domain;

import jawnathan.data.AlbumRepository;
import jawnathan.models.Album;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AlbumService {
    private final AlbumRepository repository;

    public AlbumService(AlbumRepository repository) {
        this.repository = repository;
    }

    public List<Album> findAll() {
        return repository.findAll();
    }

    public Album findById(int albumId) {
        return repository.findById(albumId);
    }

    public Result<Album> add(Album album) {
        Result<Album> result = validate(album);
        if (!result.isSuccess()) {
            return result;
        }

        if (album.getAlbumId() != 0) {
            result.addMessage("albumId cannot be set for `add` operation", ResultType.INVALID);
            return result;
        }

        album = repository.add(album);
        result.setPayload(album);
        return result;
    }

    public Result<Album> update(Album album) {
        Result<Album> result = validate(album);
        if (!result.isSuccess()) {
            return result;
        }

        if (album.getAlbumId() <= 0) {
            result.addMessage("albumId must be set for `update` operation", ResultType.INVALID);
            return result;
        }

        if (!repository.update(album)) {
            String msg = String.format("albumId: %s, not found", album.getAlbumId());
            result.addMessage(msg, ResultType.NOT_FOUND);
        }

        return result;
    }

    public boolean deleteById(int albumId) {
        return repository.deleteById(albumId);
    }

    private Result<Album> validate(Album album){
        Result<Album> result = new Result<>();
        if (album == null){
            result.addMessage("album cannot be null", ResultType.INVALID);
            return result;
        }
        if (Validations.isNullOrBlank(album.getName())){
            result.addMessage("album name is required", ResultType.INVALID);
        }
        if (Validations.isNullOrBlank(String.valueOf(album.getReleaseYear()))){
            result.addMessage("release year is required", ResultType.INVALID);
        }
        return result;
    }
}
