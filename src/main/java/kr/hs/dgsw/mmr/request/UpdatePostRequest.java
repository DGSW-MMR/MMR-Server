package kr.hs.dgsw.mmr.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class UpdatePostRequest {

    private int postId;
    private String userId;
    private String title;
    private String summary;
    private String content;
    private String imgUrl;

    private List<MaterialRequest> materials;

    public UpdatePostRequest() {
    }
}
