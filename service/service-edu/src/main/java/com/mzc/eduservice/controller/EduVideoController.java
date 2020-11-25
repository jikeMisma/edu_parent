package com.mzc.eduservice.controller;


import com.mzc.commonutils.R;
import com.mzc.eduservice.client.VodClient;
import com.mzc.eduservice.entity.EduVideo;
import com.mzc.eduservice.service.EduVideoService;
import com.mzc.servicebase.ExceptionHandler.GuliException;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StreamUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2020-11-21
 */
@Api(description = "章节小节接口")
@RestController
@CrossOrigin
@RequestMapping("/eduservice/video")
public class EduVideoController {

    @Autowired
    private EduVideoService videoService;

    //注入VodClient
    @Autowired
    private VodClient vodClient;

    //添加小节
    @PostMapping("addVideo")
    public R addVideo(@RequestBody EduVideo eduVideo) {
        videoService.save(eduVideo);
        return R.ok();
    }

    //删除小节
    @DeleteMapping("{id}")
    public R deleteVideo(@PathVariable String id) {
        //根据小节id获取视频id
        EduVideo video = videoService.getById(id);
        String videoSourceId = video.getVideoSourceId();
        //判断小节id是否有视频id
        if(!StringUtils.isEmpty(videoSourceId)){
            //根据视频id远程调用实现视频删除
            R result = vodClient.removeAliyunVideo(videoSourceId);
            if(result.getCode() == 20001){
                throw new GuliException(20001,"删除视频失败，熔断器Hystrix......");
            }
        }

        videoService.removeById(id);

        return R.ok();
    }

    //根据videoid查询
    @GetMapping("getVideoById/{videoid}")
    public R getVideoById(@PathVariable String videoid){

        EduVideo eduVideo = videoService.getById(videoid);
        return R.ok().data("eduVideo",eduVideo);
    }
    //修改小节 TODO
    @PostMapping("updateVideo")
    public R updateVideo(@RequestBody EduVideo eduVideo){
        boolean flag = videoService.updateById(eduVideo);
        if(flag){
            return R.ok();
        }else{
            return R.error();
        }
    }

}

