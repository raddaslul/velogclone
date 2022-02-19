package com.sparta.velogclone.dto.requestdto;

import com.sparta.velogclone.domain.ImageFile;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class PostRequestDto {
    private String title;
    private String content;
    private ImageFile imageFile;
}
