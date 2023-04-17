package jawnathan.domain;

import jawnathan.data.SongRepository;
import jawnathan.models.Song;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongService {
    private final SongRepository repository;

    public SongService(SongRepository repository) {
        this.repository = repository;
    }

    public List<Song> findAll() {
        return repository.findAll();
    }

    public Song findById(int songId) {
        return repository.findById(songId);
    }

    public Result<Song> add(Song song) {
        Result<Song> result = validate(song);
        if (!result.isSuccess()) {
            return result;
        }

        if (song.getSongId() != 0) {
            result.addMessage("songId cannot be set for `add` operation", ResultType.INVALID);
            return result;
        }

        song = repository.add(song);
        result.setPayload(song);
        return result;
    }

    public Result<Song> update(Song song) {
        Result<Song> result = validate(song);
        if (!result.isSuccess()) {
            return result;
        }

        if (song.getSongId() <= 0) {
            result.addMessage("songId must be set for `update` operation", ResultType.INVALID);
            return result;
        }

        if (!repository.update(song)) {
            String msg = String.format("songId: %s, not found", song.getSongId());
            result.addMessage(msg, ResultType.NOT_FOUND);
        }

        return result;
    }

    public boolean deleteById(int songId) {
        return repository.deleteById(songId);
    }

    private Result<Song> validate(Song song){
        Result<Song> result= new Result<>();
        if (song == null){
            result.addMessage("song cannot be null", ResultType.INVALID);
            return result;
        }
        if (Validations.isNullOrBlank(song.getName())){
            result.addMessage("song name cannot be null", ResultType.INVALID);
        }
        if (Validations.isNullOrBlank(song.getPlaySongUrl())){
            result.addMessage("song url cannot be null", ResultType.INVALID);
        }
        if (Validations.isNullOrBlank(String.valueOf(song.getGroupId()))){
            result.addMessage("groupId cannot be null", ResultType.INVALID);
        }
        return result;
    }
}
