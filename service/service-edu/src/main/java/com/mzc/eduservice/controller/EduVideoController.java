package com.mzc.eduservice.controller;


import com.mzc.commonutils.R;
import com.mzc.eduservice.entity.EduVideo;
import com.mzc.eduservice.service.EduVideoService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
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

    //添加小节
    @PostMapping("addVideo")
    public R addVideo(@RequestBody EduVideo eduVideo) {
        videoService.save(eduVideo);
        return R.ok();
    }

    //删除小节
    // TODO 后面这个方法需要完善：删除小节时候，同时把里面视频删除
    @DeleteMapping("{id}")
    public R deleteVideo(@PathVariable String id) {
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

