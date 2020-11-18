package com.mzc.servicebase.ExceptionHandler;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author MaZhiCheng
 * @date 2020/11/15 - 10:44
 * @motto 腹有诗书气自华
 * @博客地址 https://blog.csdn.net/mzc_love
 */

@Data
@AllArgsConstructor//生成有参数的构造
@NoArgsConstructor//生成无参构造
public class GuliException extends RuntimeException {

    @ApiModelProperty(value = "状态码")
    private Integer code;

    private String msg;


}
