package com.sparta.velogclone.dto.requestdto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor  // 기본 생성자 추가
public class LoginRequestDto {

    @NotBlank(message = "이메일을 입력해주세요.")
    @Email(message = "이메일 형식으로 입력해주세요.")
    @ApiModelProperty(value = "이메일")
    private String userEmail;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    @ApiModelProperty(value = "비밀번호")
    private String password;

    @ApiModelProperty(value = "유저 이름")
    private String userName;

    public LoginRequestDto (String userEmail, String password, String userName){
        this.userEmail = userEmail;
        this.password = password;
        this.userName = userName;
    }
}