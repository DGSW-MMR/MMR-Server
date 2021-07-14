package kr.hs.dgsw.mmr.service;

import kr.hs.dgsw.mmr.CustomException;
import kr.hs.dgsw.mmr.entity.UserEntity;
import kr.hs.dgsw.mmr.repo.UserRepository;
import kr.hs.dgsw.mmr.request.LoginRequest;
import kr.hs.dgsw.mmr.request.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    final private UserRepository userRepository;

    public String login(LoginRequest loginRequest) throws CustomException {

        UserEntity userEntity = userRepository.findById(loginRequest.getId()).orElseThrow(() -> new CustomException(401, "아이디와 패스워드를 확인해주세요"));

        if (userEntity.getPw().equals(loginRequest.getPw())) {
            return userEntity.getName();
        } else {
            throw new CustomException(401, "아이디와 패스워드를 확인해 주세요");
        }

    }

    public Boolean register(RegisterRequest registerRequest) throws CustomException {

        if (userRepository.findById(registerRequest.getId()).isPresent()) {
            throw new CustomException(401, "중복된 아이디입니다");
        }

        userRepository.save(new UserEntity(registerRequest.getId(), registerRequest.getPw(), registerRequest.getName()));
        return true;
    }

    public Boolean modifyName(String id, String name) throws CustomException {
        UserEntity userEntity = userRepository.findById(id).orElseThrow(() -> new CustomException(404, "회원을 찾을 수 없습니다"));

        userEntity.setName(name);

        userRepository.save(userEntity);

        return true;
    }
}
