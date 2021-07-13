package kr.hs.dgsw.mmr.response;


import kr.hs.dgsw.mmr.request.MaterialRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MaterialResponse {

    private String name;
    private String url;

    public MaterialResponse() {}
}
