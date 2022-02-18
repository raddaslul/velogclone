package com.sparta.velogclone.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "post")
public class Post extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Lob
    @Column(name = "content", nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "post", orphanRemoval = true)
    @OrderBy("id desc")
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "post", orphanRemoval = true)
    private List<Likes> likeses = new ArrayList<>();

    @OneToOne(mappedBy = "post", orphanRemoval = true)
    private File file;
}