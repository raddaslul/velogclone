package com.sparta.velogclone.dto.requestdto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class PostRequestDto {
    private String title;
    private String content;
}
