package com.sparta.velogclone.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "file")
public class File extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "original_file_name", nullable = false)
    private String originalFileName;

    @Column(name = "converted_file_name", nullable = false)
    private String convertedFileName;

    @Column(name = "file_path", nullable = false)
    private String filePath;

    @Column(name = "file_size", nullable = false)
    private String fileSize;

    @OneToOne
    @JoinColumn(name = "post_id")
    private Post post;
}