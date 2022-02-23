package com.sparta.velogclone.dto.requestdto;

import com.sparta.velogclone.domain.ImageFile;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Getter
@Setter
public class PostRequestDto {
    private String title;
    private String content;
    private List<ImageIdRequestDto> imageIdList;
    private List<ImageFile> imageFileList;
}
