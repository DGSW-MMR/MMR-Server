package kr.hs.dgsw.mmr.repo;

import kr.hs.dgsw.mmr.entity.MaterialEntity;
import kr.hs.dgsw.mmr.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MaterialRepository extends JpaRepository<MaterialEntity, Integer> {

    @Query(value = "SELECT * FROM material_table m where m.post_id = :postId", nativeQuery = true)
    List<MaterialEntity> getMaterialEntityByPostId(int postId);

    @Query(value = "DELETE FROM material_table M where m.post_id = :postId", nativeQuery = true)
    List<MaterialEntity> deleteMaterialEntitiesByPostId(int postId);

}
