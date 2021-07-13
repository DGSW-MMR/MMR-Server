package kr.hs.dgsw.mmr.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DeletePostRequest {

    private int postId;
    private String userId;

}
