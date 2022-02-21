package com.sparta.velogclone.dto.responsedto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ImageFileResponseDto {
    private String imageUrl;
    private Long imageId;

    public ImageFileResponseDto (String imageUrl, Long imageId) {
        this.imageUrl = imageUrl;
        this.imageId = imageId;
    }
}
