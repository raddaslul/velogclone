package com.sparta.velogclone.domain;

import com.sparta.velogclone.dto.requestdto.ImageFileRequestDto;
import lombok.*;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class ImageFile extends Timestamped {
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
    private Long fileSize;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    public void addPost(Post post) {
        this.post = post;
    }

    public void updateImageFile(ImageFileRequestDto imageFileRequestDto) {
        this.originalFileName = imageFileRequestDto.getOriginalFileName();
        this.convertedFileName = imageFileRequestDto.getConvertedFileName();
        this.filePath = imageFileRequestDto.getFilePath();
        this.fileSize = imageFileRequestDto.getFileSize();
    }
}