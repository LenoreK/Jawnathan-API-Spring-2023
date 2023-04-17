package jawnathan.domain;

import jawnathan.data.VideoRepository;
import jawnathan.models.Video;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideoService {
    private final VideoRepository repository;

    public VideoService(VideoRepository repository) {
        this.repository = repository;
    }

    public List<Video> findAll() {
        return repository.findAll();
    }

    public Video findById(int videoId) {
        return repository.findById(videoId);
    }

    public Result<Video> add(Video video) {
        Result<Video> result = validate(video);
        if (!result.isSuccess()) {
            return result;
        }

        if (video.getVideoId() != 0) {
            result.addMessage("videoId cannot be set for `add` operation", ResultType.INVALID);
            return result;
        }

        video = repository.add(video);
        result.setPayload(video);
        return result;
    }

    public Result<Video> update(Video video) {
        Result<Video> result = validate(video);
        if (!result.isSuccess()) {
            return result;
        }

        if (video.getVideoId() <= 0) {
            result.addMessage("videoId must be set for `update` operation", ResultType.INVALID);
            return result;
        }

        if (!repository.update(video)) {
            String msg = String.format("videoId: %s, not found", video.getVideoId());
            result.addMessage(msg, ResultType.NOT_FOUND);
        }

        return result;
    }

    public boolean deleteById(int videoId) {
        return repository.deleteById(videoId);
    }

    private Result<Video> validate(Video video){
        Result<Video> result = new Result<>();
        if (video == null){
            result.addMessage("video cannot be null", ResultType.INVALID);
            return result;
        }
        if (Validations.isNullOrBlank(video.getName())){
            result.addMessage("Name is required", ResultType.INVALID);
        }
        if (Validations.isNullOrBlank(video.getUrl())){
            result.addMessage("Video Url is required", ResultType.INVALID);
        }
        return result;
    }
}
