package com.mzc.eduservice.service.impl;

import com.mzc.eduservice.entity.EduCourse;
import com.mzc.eduservice.entity.EduCourseDescription;
import com.mzc.eduservice.entity.vo.CourseInfoVo;
import com.mzc.eduservice.entity.vo.CoursePublishVo;
import com.mzc.eduservice.mapper.EduCourseMapper;
import com.mzc.eduservice.service.EduChapterService;
import com.mzc.eduservice.service.EduCourseDescriptionService;
import com.mzc.eduservice.service.EduCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mzc.eduservice.service.EduVideoService;
import com.mzc.servicebase.ExceptionHandler.GuliException;
import org.slf4j.Logger;
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

    //注入小结和章节的service
    @Autowired
    private EduVideoService eduVideoService;

    @Autowired
    private EduChapterService chapterService;

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

    //根据课程id查询课程基本信息
    @Override
    public CourseInfoVo getCourseInfo(String courseId) {
        //1.先查询课程表
        EduCourse eduCourse = baseMapper.selectById(courseId);

        CourseInfoVo courseInfoVo = new CourseInfoVo();
        BeanUtils.copyProperties(eduCourse,courseInfoVo);


        //2.在查询课程的简介表
        EduCourseDescription courseDescription = courseDescriptionService.getById(courseId);
        courseInfoVo.setDescription(courseDescription.getDescription());

        return courseInfoVo;
    }

    @Override
    public void updateCourseInfo(CourseInfoVo courseInfoVo) {
        //1.修改课程表
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoVo,eduCourse);
        int update = baseMapper.updateById(eduCourse);
        if(update ==0){
            throw new GuliException(20001,"修改课程信息失败");
        }

        //2.修改藐视的信息
        EduCourseDescription description = new EduCourseDescription();
        description.setId(courseInfoVo.getId());
        description.setDescription(courseInfoVo.getDescription());
        courseDescriptionService.updateById(description);
    }

    //根据课程id查询课程确认信息
    @Override
    public CoursePublishVo publishCourseInfo(String courseId) {
        //调用mapper
        CoursePublishVo publishCourseInfo = baseMapper.getPublishCourseInfo(courseId);
        return publishCourseInfo;
    }

    //删除课程
    @Override
    public void removeCourse(String courseId) {

        //1.根据id删除小结
        eduVideoService.removeVideoByCourseId(courseId);

        //2、根据id删除章节
        chapterService.removeChapterByCourseId(courseId);

        //3.根据id删除描述
        courseDescriptionService.removeById(courseId);

        //4.根据id删除课程本身
        int result = baseMapper.deleteById(courseId);
        //System.out.println("===========================================>"+result);
        if(result == 0){
            throw  new GuliException(200001,"删除课程失败！");
        }


    }
}
