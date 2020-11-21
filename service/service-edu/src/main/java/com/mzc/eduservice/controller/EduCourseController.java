package com.mzc.eduservice.controller;


import com.mzc.commonutils.R;
import com.mzc.eduservice.entity.vo.CourseInfoVo;
import com.mzc.eduservice.service.EduCourseService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2020-11-21
 */

@Api(description = "课程管理")
@RestController
@CrossOrigin
@RequestMapping("/eduservice/course")
public class EduCourseController {

    @Autowired
    private EduCourseService courseService;

    //添加课程基本信息
    @PostMapping("addCourceInfo")
    public R addCourceInfo(@RequestBody CourseInfoVo courseInfoVo){
        //返回添加后课程ID
        String id = courseService.saveCourseInfo(courseInfoVo);
        return R.ok().data("courseid",id);
    }

}

