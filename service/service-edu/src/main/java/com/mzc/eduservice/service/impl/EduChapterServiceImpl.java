package com.mzc.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mzc.eduservice.entity.EduChapter;
import com.mzc.eduservice.entity.EduVideo;
import com.mzc.eduservice.entity.chapter.ChapterVo;
import com.mzc.eduservice.entity.chapter.VideoVo;
import com.mzc.eduservice.mapper.EduChapterMapper;
import com.mzc.eduservice.service.EduChapterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mzc.eduservice.service.EduVideoService;
import com.mzc.servicebase.ExceptionHandler.GuliException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2020-11-21
 */
@Service
public class EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, EduChapter> implements EduChapterService {

    @Autowired
    private EduVideoService videoService;//注入小结service
    //根据课程id查询章节小结
    @Override
    public List<ChapterVo> getChapterVideoByCourseId(String courseId) {
        //1根据课程id查询课程里面所有章节
        QueryWrapper<EduChapter> wrapperChapter = new QueryWrapper<>();
        wrapperChapter.eq("course_id",courseId);
        List<EduChapter> eduChapterList = baseMapper.selectList(wrapperChapter);

        //2.根据课程id查询课程里面所有小结
        QueryWrapper<EduVideo> wrapperVideo = new QueryWrapper<>();
        wrapperVideo.eq("course_id",courseId);
        List<EduVideo> eduVideoList = videoService.list(wrapperVideo);

        //创建list用于最终封装数据
        List<ChapterVo> finalList = new ArrayList<>();

        //3.遍历查询章节list集合进行封装
        //遍历查询章节的list
        for (int i = 0; i < eduChapterList.size(); i++) {
            //每个章节
            EduChapter eduChapter = eduChapterList.get(i);
            //把eduChapter复制到EduVideo
            ChapterVo chapterVo = new ChapterVo();
            BeanUtils.copyProperties(eduChapter,chapterVo);
            finalList.add(chapterVo);

            //创建集合用于小结封装
            List<VideoVo> videoList = new ArrayList<>();
            //4遍历查询小结list进行封装
            for (int m = 0; m < eduVideoList.size(); m++) {
                //得到每个小结
                EduVideo eduVideo = eduVideoList.get(m);
                //判断小结里chapter_id和章节id是否一样
                if(eduVideo.getChapterId().equals(eduChapter.getId())){
                    //进行封装
                    VideoVo videoVo = new VideoVo();
                    BeanUtils.copyProperties(eduVideo,videoVo);
                    //放到小结的封装中
                    videoList.add(videoVo);
                }
            }

            //把封装之后的小结list集合放到章节对象中
            chapterVo.setChildren(videoList);
        }

        return finalList;
    }

    @Override
    public Boolean deleteChapter(String chapterId) {
        //1.根据chapterid查询小结表，如果有数据就不进行删除
        QueryWrapper<EduVideo> wrapper = new QueryWrapper<>();
        wrapper.eq("chapter_id",chapterId);
        int count = videoService.count(wrapper);
        //判断
        if(count > 0){//能查询出小结，不删除
            throw  new GuliException(20001,"不能删除");
        }else{//查不出数据，可以删除
            int delete = baseMapper.deleteById(chapterId);
            return delete > 0;
        }
    }
}
