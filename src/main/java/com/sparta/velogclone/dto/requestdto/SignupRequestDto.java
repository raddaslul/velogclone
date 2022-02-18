package com.sparta.velogclone.dto.requestdto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Setter
@Getter
public class SignupRequestDto {

    @NotBlank(message = "이메일을 입력해주세요.")
    @ApiModelProperty(value = "이메일")
    public String userEmail;

    @NotBlank(message = "닉네임을 입력해주세요.")
    @ApiModelProperty(value = "닉네임")
    public String userName;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    @ApiModelProperty(value = "비밀번호")
    public String password;

    @NotBlank(message = "비밀번호를 한번 더 입력해주세요.")
    @ApiModelProperty(value = "비밀번호 확인")
    public String passwordCheck;
}
