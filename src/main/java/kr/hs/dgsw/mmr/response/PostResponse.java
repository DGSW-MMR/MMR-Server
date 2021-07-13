package kr.hs.dgsw.mmr.response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class PostResponse {

    private int id;
    private String title;
    private String content;
    private String summary;
    private String imgUrl;
    private int likeNum;
    private String userName;
    private List<MaterialResponse> materials;

    public PostResponse() {

    }


}
