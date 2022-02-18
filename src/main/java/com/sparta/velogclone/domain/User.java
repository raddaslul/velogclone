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
@Table(name = "user")
public class User extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "user_id", nullable = false, unique = true)
    private String userId;

    @Column(name = "user_name", nullable = false, unique = true)
    private String userName;

    @Column(name = "password")
    private String password;

    @OneToMany(mappedBy = "user", orphanRemoval = true)
    @OrderBy("id desc")
    private List<Post> posts = new ArrayList<>();

    @OneToMany(mappedBy = "user", orphanRemoval = true)
    @OrderBy("id desc")
    private List<Comment> comments = new ArrayList<>();
}