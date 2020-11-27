package com.mzc.educenter.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author MaZhiCheng
 * @date 2020/11/27 - 21:12
 * @motto 腹有诗书气自华
 * @博客地址 https://blog.csdn.net/mzc_love
 */

@Data
@ApiModel(value="注册对象", description="注册对象")
public class RegisterVo {

    @ApiModelProperty(value = "昵称")
    private String nickname;

    @ApiModelProperty(value = "手机号")
    private String mobile;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "验证码")
    private String code;
}
