package com.mzc.eduservice.service;

import com.mzc.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mzc.eduservice.entity.vo.CourseInfoVo;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author testjava
 * @since 2020-11-21
 */
public interface EduCourseService extends IService<EduCourse> {

    //添加课程，返回课程id
    String saveCourseInfo(CourseInfoVo courseInfoVo);

    //根据课程id查询课程基本信息
    CourseInfoVo getCourseInfo(String courseId);

    //修改课程信息
    void updateCourseInfo(CourseInfoVo courseInfoVo);
}
