package kr.hs.dgsw.mmr.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LikePostRequest {
    private String userId;
    private int postId;
}
