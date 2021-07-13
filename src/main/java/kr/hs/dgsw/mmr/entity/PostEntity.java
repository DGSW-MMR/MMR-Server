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

    @Column(name = "title")
    private String title;

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
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

    public PostEntity(String title, String summary, String content, String likePeople, int likeNum, String imageUrl, UserEntity userEntity) {
        this.title = title;
        this.summary = summary;
        this.content = content;
        this.likePeople = likePeople;
        this.likeNum = likeNum;
        this.imageUrl = imageUrl;
        this.userEntity = userEntity;
    }

}
