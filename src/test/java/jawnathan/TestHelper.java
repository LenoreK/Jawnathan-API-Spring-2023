package jawnathan;

import jawnathan.domain.Result;
import jawnathan.domain.ResultType;
import jawnathan.models.Album;

public class TestHelper {
    public static <T> Result<T> makeResult(String message, ResultType type) {
        Result<T> result = new Result<>();
        result.addMessage(message, type);
        return result;
    }

    public static <T> Result<T> makeResult(T payload) {
        Result<T> result = new Result<>();
        result.setPayload(payload);
        return result;
    }

    public static Album makeAlbum(){
        Album album = new Album();
        album.setAlbumId(1);
        album.setName("Groovy");
        album.setReleaseYear(2023);
        album.setPhotoUrl("something.com");
        album.setAlbumUrl("something.com");
        return album;
    }
}
