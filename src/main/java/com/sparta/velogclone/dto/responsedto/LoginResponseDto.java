package com.sparta.velogclone.dto.responsedto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponseDto {

    @ApiModelProperty(value = "토큰 정보")
    public String token;
    @ApiModelProperty(value = "이메일")
    public String email;
    @ApiModelProperty(value = "유저 이름")
    public String userName;
    @ApiModelProperty(value = "응답 여부")
    public String result;
}