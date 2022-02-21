package com.sparta.velogclone.dto.responsedto;

public class ImageFileResponseDto {
    private String imageUrl;
    private Long imageId;

    public ImageFileResponseDto (String imageUrl, Long imageId) {
        this.imageUrl = imageUrl;
        this.imageId = imageId;
    }
}
