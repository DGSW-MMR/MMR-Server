package kr.hs.dgsw.mmr.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@Entity(name = "material_table")
public class MaterialEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "material_id")
    private int materialId;

    @Column(name = "name")
    private String name;

    @Column(name = "url")
    private String url;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private PostEntity postEntity;

    public MaterialEntity(String name, String url, PostEntity postEntity) {
        this.name = name;
        this.url = url;
        this.postEntity = postEntity;
    }

    public MaterialEntity() { }
}
