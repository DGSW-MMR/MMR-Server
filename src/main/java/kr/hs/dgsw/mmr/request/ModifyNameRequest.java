package kr.hs.dgsw.mmr.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ModifyNameRequest {

    private String id;
    private String name;

    public ModifyNameRequest () { }
}
