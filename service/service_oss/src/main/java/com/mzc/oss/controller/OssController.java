package com.mzc.oss.controller;

import com.mzc.commonutils.R;
import com.mzc.oss.service.OssService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author MaZhiCheng
 * @date 2020/11/19 - 17:48
 * @motto 腹有诗书气自华
 * @博客地址 https://blog.csdn.net/mzc_love
 */

@Api(description = "OOS对象存储接口")
@RestController
@CrossOrigin(maxAge = 5600)
@RequestMapping("/eduoss/fileoss")
public class OssController {

    @Autowired
    private OssService ossservice;

    @ApiOperation(value = "上传头像")
    //上传头像的方法
    @PostMapping
    public R uploadOssFiles(
            @ApiParam(name = "file",value = "上传头像文件")
            MultipartFile file){
        //获取上传的文件
        //返回上传的文件路径
        String url = ossservice.uploadFileAvatar(file);
        return R.ok().data("url",url);
    }
}
