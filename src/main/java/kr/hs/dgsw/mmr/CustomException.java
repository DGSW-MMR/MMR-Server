package kr.hs.dgsw.mmr;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CustomException extends Exception {
    private int code;
    private String message;
}
