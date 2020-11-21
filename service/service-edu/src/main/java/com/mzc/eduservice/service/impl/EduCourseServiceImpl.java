package com.mzc.eduservice.service.impl;

import com.mzc.eduservice.entity.EduCourse;
import com.mzc.eduservice.entity.EduCourseDescription;
import com.mzc.eduservice.entity.vo.CourseInfoVo;
import com.mzc.eduservice.mapper.EduCourseMapper;
import com.mzc.eduservice.service.EduCourseDescriptionService;
import com.mzc.eduservice.service.EduCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mzc.servicebase.ExceptionHandler.GuliException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2020-11-21
 */
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {

    @Autowired
    private EduCourseDescriptionService courseDescriptionService;

    //添加课程信息
    @Override
    public String saveCourseInfo(CourseInfoVo courseInfoVo) {
        //向课程表中添加课程的基本信息
        //CourseInfoVo转换为EduCourse
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoVo,eduCourse);
        int insert = baseMapper.insert(eduCourse);

        if(insert <= 0){
            //添加失败
            throw  new GuliException(20001,"添加课程失败");
        }

        //获取添加之后的id
        String cid = eduCourse.getId();
        //2.想课程简介表添加信息
        //edu_course_description
        EduCourseDescription courseDescription = new EduCourseDescription();
        courseDescription.setId(cid);
        courseDescription.setDescription(courseInfoVo.getDescription());
        courseDescriptionService.save(courseDescription);

        return cid;
    }
}
