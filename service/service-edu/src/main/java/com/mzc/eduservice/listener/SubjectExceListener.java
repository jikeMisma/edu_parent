package com.mzc.eduservice.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mzc.eduservice.entity.EduSubject;
import com.mzc.eduservice.entity.excel.SubjectData;
import com.mzc.eduservice.service.EduSubjectService;
import com.mzc.servicebase.ExceptionHandler.GuliException;

import java.security.GeneralSecurityException;

/**
 * @author MaZhiCheng
 * @date 2020/11/20 - 15:39
 * @motto 腹有诗书气自华
 * @博客地址 https://blog.csdn.net/mzc_love
 */
public class SubjectExceListener extends AnalysisEventListener<SubjectData> {

    //因为SubjectExceListener不能交给spring管理，需要自己new，不能注入其他对象
    private EduSubjectService subjectService;

    public SubjectExceListener() {}
    public SubjectExceListener(EduSubjectService subjectService) {
        this.subjectService = subjectService;
    }

    //读取Excel中内容
    @Override
    public void invoke(SubjectData subjectData, AnalysisContext analysisContext) {
        if(subjectData == null){
            throw new GuliException(20001,"文件数据为空！");
        }

        //一行一行读取，每次读取都是两个值，第一个值是一级分类，第二个值是二级分类
        //判断一级分类是否重复
        EduSubject exitOneSubect = this.exitsOneSubject(subjectService, subjectData.getOneSubjectName());
        if(exitOneSubect == null) {//没有相同一级分类，进行添加
            exitOneSubect = new EduSubject();
            exitOneSubect.setParentId("0");
            exitOneSubect.setTitle(subjectData.getOneSubjectName());
            subjectService.save(exitOneSubect);
        }

        String pid = exitOneSubect.getId();
        //添加二级分类
        EduSubject exitTwoSubect = this.exitsTwoSubject(subjectService,subjectData.getTwoSubjectName(),pid);
        if(exitTwoSubect == null) {//没有相同二级分类，进行添加
            exitTwoSubect = new EduSubject();
            exitTwoSubect.setParentId(pid);
            exitTwoSubect.setTitle(subjectData.getTwoSubjectName());
            subjectService.save(exitTwoSubect);
        }

    }

    //判断一级分类不能重复添加
    private EduSubject exitsOneSubject(EduSubjectService subjectService,String name){
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title",name);
        wrapper.eq("parent_id","0");
        EduSubject oneSubject = subjectService.getOne(wrapper);
        return oneSubject;
    }

    //判断二级分类不能重复添加
    private EduSubject exitsTwoSubject(EduSubjectService subjectService,String name,String pid){
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title",name);
        wrapper.eq("parent_id",pid);
        EduSubject twoSubject = subjectService.getOne(wrapper);
        return twoSubject;
    }
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
