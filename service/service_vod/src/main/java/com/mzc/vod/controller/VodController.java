package com.mzc.vod.controller;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.mzc.commonutils.R;
import com.mzc.servicebase.ExceptionHandler.GuliException;
import com.mzc.vod.service.VodService;
import com.mzc.vod.utils.ConstentVodUtils;
import com.mzc.vod.utils.initVodClient;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author MaZhiCheng
 * @date 2020/11/23 - 23:44
 * @motto 腹有诗书气自华
 * @博客地址 https://blog.csdn.net/mzc_love
 */

@Api(description = "阿里云视频点播接口")
@RestController
@CrossOrigin
@RequestMapping("/enuvod/video")
public class VodController {

    @Autowired
    private VodService vodService;

    //上传视频方法
    @ApiOperation(value = "视频上传接口")
    @PostMapping("uploadAliyunVideo")
    public R uploadAliyunVideo(MultipartFile file){

        String videoId = vodService.uploadVideoAliyun(file);
        //获取上传的文件
        return R.ok().data("videoId",videoId);

    }

    //根据视频id删除阿里云视频
    @DeleteMapping("removeAliyunVideo/{id}")
    public R removeAliyunVideo(@PathVariable String id){

        try{

            //初始化对象
            DefaultAcsClient client = initVodClient.initVodClient(ConstentVodUtils.KEY_ID, ConstentVodUtils.KEY_SECRET);
            //创建一个删除视频的request对象
            DeleteVideoRequest request = new DeleteVideoRequest();
            //向request设置视频id
            request.setVideoIds(id);
            //调用初始化方法实现删除
            client.getAcsResponse(request);
            return R.ok();

        }catch (Exception e){
            e.printStackTrace();
            throw  new GuliException(20001,"删除视频失败！");
        }
    }

    //删除多个视频的方法
    //参数是多个视频的id
    @DeleteMapping("delete-batch")
    public R deleteBatch(
            @ApiParam(name = "videoIdList", value = "云端视频id", required = true)
            @RequestParam("videoIdList") List<String> videoIdList){
        System.out.println("需要删除的id为:"+videoIdList);
        vodService.removeMoreAliyunVideo(videoIdList);
        return R.ok();
    }

}
