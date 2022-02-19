package com.sparta.velogclone.dto.requestdto;

import com.sparta.velogclone.domain.ImageFile;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ImageFileRequestDto {
    private String originalFileName;
    private String convertedFileName;
    private String filePath;
    private Long fileSize;

    public ImageFile toEntity() {
        return ImageFile.builder()
                .originalFileName(this.originalFileName)
                .convertedFileName(this.convertedFileName)
                .filePath(this.filePath)
                .fileSize(this.fileSize)
                .build();
    }
}
