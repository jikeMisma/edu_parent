package com.mzc.eduservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mzc.commonutils.R;
import com.mzc.eduservice.entity.EduCourse;
import com.mzc.eduservice.entity.EduTeacher;
import com.mzc.eduservice.entity.vo.CourseInfoVo;
import com.mzc.eduservice.entity.vo.CoursePublishVo;
import com.mzc.eduservice.service.EduCourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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


    //TODO 完善条件查询带分页
    //按条件查询课程带分页
    @ApiOperation(value = "按条件查询课程带分页")
    @PostMapping("pageCourseCondition/{current}/{limit}")
    public R pageCourseCondition(@PathVariable long current,@PathVariable long limit,
                                 @RequestBody(required = false) EduCourse eduCourse){

        //创建page对象
        Page<EduCourse> pageCourse = new Page<>(current,limit);
        //构建条件
        QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();
        // 多条件组合查询
        // mybatis学过 动态sql
        String name = eduCourse.getTitle();
        String status = eduCourse.getStatus();

        //判断条件值是否为空，如果不为空拼接条件
        if(!StringUtils.isEmpty(name)) {
            //构建条件
            wrapper.like("title",name);
        }
        if(!StringUtils.isEmpty(status)) {
            wrapper.eq("status",status);
        }

        //排序
        wrapper.orderByDesc("gmt_create");

        //调用方法实现条件查询分页
        courseService.page(pageCourse,wrapper);

        long total = pageCourse.getTotal();//总记录数
        List<EduCourse> records = pageCourse.getRecords(); //数据list集合
        return R.ok().data("total",total).data("rows",records);
    }
    //课程列表,基本实现
    @GetMapping("getCourseList")
    public R getCourseList(){
        List<EduCourse> list = courseService.list(null);
        return R.ok().data("list",list);
    }

    //添加课程基本信息
    @PostMapping("addCourceInfo")
    public R addCourceInfo(@RequestBody CourseInfoVo courseInfoVo){
        //返回添加后课程ID
        String id = courseService.saveCourseInfo(courseInfoVo);
        return R.ok().data("courseid",id);
    }

    //根据课程id查询课程基本信息
    @GetMapping("getCourseInfo/{courseId}")
    public R getCourseInfo(@PathVariable String courseId){
        CourseInfoVo courseInfoVo =  courseService.getCourseInfo(courseId);
        return  R.ok().data("courseInfoVo",courseInfoVo);
    }

    //修改课程信息
    @PostMapping("updateCourseInfo")
    public  R updateCourseInfo(@RequestBody CourseInfoVo courseInfoVo){

        courseService.updateCourseInfo(courseInfoVo);
        return R.ok();
    }

    //根据课程id查询课程确认信息
    @GetMapping("getPublishCourseInfo/{courseId}")
    public R getPublishCourseInfo(@PathVariable String courseId){
        CoursePublishVo coursePublishVo = courseService.publishCourseInfo(courseId);
        return  R.ok().data("publishCourse",coursePublishVo);
    }

    //课程最终发布
    //修改课程的状态
    @PostMapping("publishCourse/{id}")
    public R publishCourse(@PathVariable String id){
        EduCourse eduCourse = new EduCourse();
        eduCourse.setId(id);
        eduCourse.setStatus("Normal");
        courseService.updateById(eduCourse);
        return  R.ok();
    }

    //删除课程
    @DeleteMapping("{courseId}")
    public R deleteCourse(@PathVariable String courseId){
        courseService.removeCourse(courseId);
        return R.ok();
    }


}

