package com.zzqedu.dousheng.dto.req;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
public class LoginReqDto {
    @NotBlank(message = "用户名不能为空")
    @Length(message = "用户名最长32个字符", max = 32)
    private String username;

    @NotBlank(message = "密码不能为空")
    @Length(message = "密码最长32个字符", max = 32)
    private String password;

}
