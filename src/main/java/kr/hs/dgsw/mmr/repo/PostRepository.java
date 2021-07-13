package kr.hs.dgsw.mmr.repo;

import kr.hs.dgsw.mmr.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, Integer> {

}
