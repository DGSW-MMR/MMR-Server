package kr.hs.dgsw.mmr.repo;

import kr.hs.dgsw.mmr.entity.PostEntity;
import kr.hs.dgsw.mmr.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, Integer> {

    @Query(value = "SELECT * FROM post_table p WHERE p.user_id = :user", nativeQuery = true)
    public List<PostEntity> getPostByUser(String user);
}
