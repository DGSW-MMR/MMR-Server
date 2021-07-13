package kr.hs.dgsw.mmr.entity;


import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "post_table")
public class PostEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private int postId;

    @Column(name = "summary")
    private String summary;

    @Column(name = "content")
    private String content;

    @Column(name = "like_people")
    private String likePeople;

    @Column(name = "like_num")
    private int likeNum;

    @Column(name = "image_url")
    private String imageUrl;

    @ManyToOne
    @Column(name = "user_id")
    private UserEntity userEntity;

}
