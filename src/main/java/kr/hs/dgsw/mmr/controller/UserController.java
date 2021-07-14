package kr.hs.dgsw.mmr.controller;


import kr.hs.dgsw.mmr.CustomException;
import kr.hs.dgsw.mmr.request.LoginRequest;
import kr.hs.dgsw.mmr.request.ModifyNameRequest;
import kr.hs.dgsw.mmr.request.RegisterRequest;
import kr.hs.dgsw.mmr.response.BaseResponse;
import kr.hs.dgsw.mmr.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/user")
public class UserController {

    final private UserService userService;

    @PostMapping("/login")
    public BaseResponse<String> login(@RequestBody LoginRequest loginRequest) throws CustomException {
        String result = userService.login(loginRequest);

        return new BaseResponse<>(200, "로그인 하였습니다", result);
    }

    @PostMapping("/register")
    public BaseResponse<Boolean> register(@RequestBody RegisterRequest registerRequest) throws CustomException {
        Boolean result = userService.register(registerRequest);

        return new BaseResponse<>(200, "회원가입 하였습니다", result);
    }

    @PutMapping("")
    public BaseResponse<Boolean> modifyName(@RequestBody ModifyNameRequest request) throws CustomException {
        return new BaseResponse<>(200, "수정에 성공했습니다", userService.modifyName(request.getId(), request.getName()));
    }

}
