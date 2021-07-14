package kr.hs.dgsw.mmr.service;

import kr.hs.dgsw.mmr.CustomException;
import kr.hs.dgsw.mmr.entity.MaterialEntity;
import kr.hs.dgsw.mmr.entity.PostEntity;
import kr.hs.dgsw.mmr.entity.UserEntity;
import kr.hs.dgsw.mmr.repo.MaterialRepository;
import kr.hs.dgsw.mmr.repo.PostRepository;
import kr.hs.dgsw.mmr.repo.UserRepository;
import kr.hs.dgsw.mmr.request.CreatePostRequest;
import kr.hs.dgsw.mmr.request.DeletePostRequest;
import kr.hs.dgsw.mmr.request.UpdatePostRequest;
import kr.hs.dgsw.mmr.response.MaterialResponse;
import kr.hs.dgsw.mmr.response.PostResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    final PostRepository postRepository;
    final MaterialRepository materialRepository;
    final UserRepository userRepository;


    public List<PostResponse> getAllPost() {


        List<PostEntity> postEntities = postRepository.findAll();

        ArrayList<PostResponse> postResponses = new ArrayList<>();


        postEntities.forEach(it -> {

            PostResponse postResponse = new PostResponse();
            postResponse.setId(it.getPostId());
            postResponse.setContent(it.getContent());
            postResponse.setImgUrl(it.getImageUrl());
            postResponse.setLikeNum(it.getLikeNum());
            postResponse.setTitle(it.getTitle());
            postResponse.setSummary(it.getSummary());
            postResponse.setUserName(it.getUserEntity().getName());


            ArrayList<MaterialResponse> materialResponses = new ArrayList<>();
            materialRepository.getMaterialEntityByPostId(it.getPostId())
                    .forEach(m -> materialResponses.add(new MaterialResponse(m.getName(), m.getUrl())));

            postResponse.setMaterials(materialResponses);

            postResponses.add(postResponse);
        });

        return postResponses;
    }

    public PostResponse getPostById(int id) throws CustomException {
        PostEntity postEntity = postRepository.findById(id).orElseThrow(() -> new CustomException(404, "존재하지 않는 게시글입니다"));

        ArrayList<MaterialResponse> materialResponses = new ArrayList<>();

        materialRepository.getMaterialEntityByPostId(postEntity.getPostId()).forEach(it -> {
            MaterialResponse materialResponse = new MaterialResponse(it.getName(), it.getUrl());

            materialResponses.add(materialResponse);
        });

        PostResponse postResponse = new PostResponse();

        postResponse.setId(postEntity.getPostId());
        postResponse.setTitle(postEntity.getTitle());
        postResponse.setContent(postEntity.getContent());
        postResponse.setSummary(postEntity.getSummary());
        postResponse.setLikeNum(postEntity.getLikeNum());
        postResponse.setImgUrl(postEntity.getImageUrl());
        postResponse.setMaterials(materialResponses);
        postResponse.setUserName(postEntity.getUserEntity().getName());

        return postResponse;
    }

    public void createPost(CreatePostRequest postRequest) throws CustomException {

        UserEntity user = userRepository.findById(postRequest.getUserId()).orElseThrow(() -> new CustomException(404, "존재하지 않는 회원입니다"));

        PostEntity postEntity = new PostEntity(
                postRequest.getTitle(),
                postRequest.getSummary(),
                postRequest.getContent(),
                "",
                0,
                postRequest.getImgUrl(),
                user
        );

        PostEntity post = postRepository.save(postEntity);

        postRequest.getMaterials().forEach(it -> {
            MaterialEntity materialEntity = new MaterialEntity(it.getName(), it.getUrl(), post);
            materialRepository.save(materialEntity);
        });

    }

    public void updatePost(UpdatePostRequest postRequest) throws CustomException {

        UserEntity user = userRepository.findById(postRequest.getUserId()).orElseThrow(() -> new CustomException(404, "존재하지 않는 회원입니다"));
        PostEntity original = postRepository.findById(postRequest.getPostId()).orElseThrow(() -> new CustomException(404, "존재하지 않는 게시글입니다"));

        PostEntity postEntity = new PostEntity(
                postRequest.getPostId(),
                postRequest.getTitle(),
                postRequest.getSummary(),
                postRequest.getContent(),
                original.getLikePeople(),
                original.getLikeNum(),
                postRequest.getImgUrl(),
                original.getUserEntity()
        );

        PostEntity updatePost = postRepository.save(postEntity);

        materialRepository.deleteMaterialEntitiesByPostId(original.getPostId());

        postRequest.getMaterials().forEach(it -> {
            materialRepository.save(new MaterialEntity(it.getName(), it.getUrl(), updatePost));
        });

    }

    public Boolean deletePost(DeletePostRequest deletePostRequest) throws CustomException {

        if (postRepository.findById(deletePostRequest.getPostId())
                .orElseThrow(() -> new CustomException(404, "존재하지 않는 회원입니다")).getUserEntity().getId()
                .equals(deletePostRequest.getUserId())) {
            postRepository.deleteById(deletePostRequest.getPostId());
            return true;
        }

        throw new CustomException(403, "회원 정보가 올바르지 않습니다");
    }

    public Boolean likePost(int postId, String userId) throws CustomException {
        PostEntity postEntity = postRepository.findById(postId).orElseThrow(() -> new CustomException(404, "존재하지 않는 게시글입니다"));
        ArrayList<String> people = new ArrayList<>(Arrays.asList(postEntity.getLikePeople().split("&")));
        boolean result = true;

        if (people.contains(userId)) {
            people.removeIf(it -> it.equals(userId));
            result = false;
        } else {
            people.add(userId);
        }

        String peopleString = people.stream().reduce((x, y) -> x + "&" + y).orElse("");
        postEntity.setLikePeople(peopleString);

        if (result) {
            postEntity.setLikeNum(postEntity.getLikeNum() + 1);
        } else {
            postEntity.setLikeNum(postEntity.getLikeNum() - 1);
        }

        postRepository.save(postEntity);
        return result;
    }

    public List<PostResponse> getMyPost(String userId) throws CustomException {
        UserEntity user = userRepository.findById(userId).orElseThrow(() -> new CustomException(404, ""));
        ArrayList<PostResponse> postResponses = new ArrayList<>();
        postRepository.getPostByUser(user.getId()).forEach(it -> {
            PostResponse postResponse = new PostResponse();
            postResponse.setId(it.getPostId());
            postResponse.setContent(it.getContent());
            postResponse.setImgUrl(it.getImageUrl());
            postResponse.setLikeNum(it.getLikeNum());
            postResponse.setTitle(it.getTitle());
            postResponse.setSummary(it.getSummary());
            postResponse.setUserName(it.getUserEntity().getName());


            ArrayList<MaterialResponse> materialResponses = new ArrayList<>();
            materialRepository.getMaterialEntityByPostId(it.getPostId())
                    .forEach(m -> materialResponses.add(new MaterialResponse(m.getName(), m.getUrl())));

            postResponse.setMaterials(materialResponses);

            postResponses.add(postResponse);
        });

        return postResponses;
    }

    public Boolean checkLike(String userId, int postId) throws CustomException {
        UserEntity userEntity = userRepository.findById(userId).orElseThrow(() -> new CustomException(404, "회원 정보가 없습니다"));
        PostEntity postEntity = postRepository.findById(postId).orElseThrow(() ->
                new CustomException(404, "존재 하지 않는 게시글입니다"));

        return Arrays.asList(postEntity.getLikePeople().split("&")).contains(userId);
    }

}
