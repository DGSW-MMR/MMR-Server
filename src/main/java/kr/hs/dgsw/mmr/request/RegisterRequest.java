package kr.hs.dgsw.mmr.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class RegisterRequest {

    private String id;
    private String pw;
    private String name;

    public RegisterRequest() {
    }

}
