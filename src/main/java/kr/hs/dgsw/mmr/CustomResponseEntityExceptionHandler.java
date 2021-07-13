package kr.hs.dgsw.mmr;


import kr.hs.dgsw.mmr.response.BaseResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(CustomException.class)
    public final BaseResponse<?> onError(CustomException exception) {
        return new BaseResponse<>(exception.getCode(), exception.getMessage(), null);
    }
}

