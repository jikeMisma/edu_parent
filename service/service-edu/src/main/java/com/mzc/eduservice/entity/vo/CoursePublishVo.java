package com.mzc.eduservice.entity.vo;

import lombok.Data;

/**
 * @author MaZhiCheng
 * @date 2020/11/23 - 13:12
 * @motto 腹有诗书气自华
 * @博客地址 https://blog.csdn.net/mzc_love
 */
@Data
public class CoursePublishVo {

    private String id;
    private String title;
    private String cover;
    private Integer lessonNum;
    private String subjectLevelOne;
    private String subjectLevelTwo;
    private String teacherName;
    private String price;//只用于显示
}
