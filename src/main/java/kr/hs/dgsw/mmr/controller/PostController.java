package kr.hs.dgsw.mmr.controller;

import kr.hs.dgsw.mmr.CustomException;
import kr.hs.dgsw.mmr.request.CreatePostRequest;
import kr.hs.dgsw.mmr.request.DeletePostRequest;
import kr.hs.dgsw.mmr.request.UpdatePostRequest;
import kr.hs.dgsw.mmr.response.BaseResponse;
import kr.hs.dgsw.mmr.response.PostResponse;
import kr.hs.dgsw.mmr.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/post")
public class PostController {

    final private PostService postService;

    @GetMapping("")
    public BaseResponse<List<PostResponse>> getAllPost() {
        List<PostResponse> postResponses = postService.getAllPost();
        return new BaseResponse<>(200, "성공적으로 조회하였습니다", postResponses);
    }

    @GetMapping("/{id}")
    public BaseResponse<PostResponse> getPostByIdx(@PathVariable int id) throws CustomException {
        PostResponse postResponse = postService.getPostById(id);
        return new BaseResponse<>(200, "성공적으로 조회하였습니다", postResponse);
    }

    @PostMapping("")
    public BaseResponse<Boolean> createPost(@RequestBody CreatePostRequest postRequest) throws CustomException {
        postService.createPost(postRequest);

        return new BaseResponse<>(200, "성공적으로 생성하였습니다", true);
    }

    @PutMapping("")
    public BaseResponse<Boolean> updatePost(@RequestBody UpdatePostRequest postRequest) throws CustomException {
        postService.updatePost(postRequest);

        return new BaseResponse<>(200, "성공적으로 생성하였습니다", true);
    }


    @DeleteMapping("")
    public BaseResponse<Boolean> deletePost(@RequestBody DeletePostRequest postRequest) throws CustomException {
        boolean result = postService.deletePost(postRequest);

        return new BaseResponse<>(200, "성공적으로 삭제하였습니다", result);
    }

    @PostMapping("/like/{userId}/{postId}")
    public BaseResponse<Boolean> likePost(@PathVariable String userId, @PathVariable int postId) throws CustomException {
        boolean result = postService.likePost(postId, userId);

        if (result) {
            return new BaseResponse<>(200, "좋아요를 눌렀습니다", true);
        }

        return new BaseResponse<>(200, "좋아요를 취소하였습니다", false);

    }

    @GetMapping("/my/{userId}")
    public BaseResponse<List<PostResponse>> getMyPost(@PathVariable String userId) throws CustomException {
        return new BaseResponse<>(200, "조회 성공", postService.getMyPost(userId));
    }

    @GetMapping("/like/{userId}/{postId}")
    public BaseResponse<Boolean> checkLikePost(@PathVariable String userId, @PathVariable int postId) throws CustomException {
        return new BaseResponse<>(200, "조회 성공", postService.checkLike(userId, postId));
    }


}
