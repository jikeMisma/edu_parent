package com.mzc.eduservice.service;

import com.mzc.eduservice.entity.EduChapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mzc.eduservice.entity.chapter.ChapterVo;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author testjava
 * @since 2020-11-21
 */
public interface EduChapterService extends IService<EduChapter> {

    //根据课程id查询章节小结
    List<ChapterVo> getChapterVideoByCourseId(String courseId);
    //删除章节
    Boolean deleteChapter(String chapterId);

    //根据id删除章节
    void removeChapterByCourseId(String courseId);
}
