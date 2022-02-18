package com.sparta.velogclone.dto.responsedto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class CMResponseDto { // credential mapper

    @ApiModelProperty(value = "결과 여부")
    public String result;
}