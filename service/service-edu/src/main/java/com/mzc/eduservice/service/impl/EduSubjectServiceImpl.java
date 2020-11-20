package com.mzc.eduservice.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fasterxml.jackson.databind.introspect.AnnotationCollector;
import com.mzc.eduservice.entity.EduSubject;
import com.mzc.eduservice.entity.excel.SubjectData;
import com.mzc.eduservice.entity.subject.OneSubject;
import com.mzc.eduservice.entity.subject.TwoSubject;
import com.mzc.eduservice.listener.SubjectExceListener;
import com.mzc.eduservice.mapper.EduSubjectMapper;
import com.mzc.eduservice.service.EduSubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2020-11-20
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {

    @Override
    public void saveSubject(MultipartFile file,EduSubjectService subjectService) {

        try{
            InputStream in = file.getInputStream();
            //调用方法进行读取
            EasyExcel.read(in, SubjectData.class,new SubjectExceListener(subjectService)).sheet().doRead();
        }catch (Exception e){
            e.printStackTrace();
        }
        EasyExcel.read();
    }

    @Override
    public List<OneSubject> getAllOneTwoSubject() {

        //1.查询出所有的一级分类 pid = 0
        QueryWrapper<EduSubject> wrapperOne = new QueryWrapper<>();
        wrapperOne.eq("parent_id",0);
        List<EduSubject> oneSubjeceList = baseMapper.selectList(wrapperOne);

        //2.查询出所有的二级分类 pid!=0
        QueryWrapper<EduSubject> wrapperTwo = new QueryWrapper<>();
        wrapperOne.ne("parent_id",0);
        List<EduSubject> TwoSubjeceList = baseMapper.selectList(wrapperTwo);

        //创建list集合用于存储最终的数据
        List<OneSubject> finalSubjectList = new ArrayList<>();

        //3.封装一级分类
        //查询出来的所有一级分类list集合进行遍历，点到一个一级分类对象，获取每个一级分类对象值
        //封装到要求的集合finalSubject
        for(int i = 0;i < oneSubjeceList.size();i++){
            //得到oneSubjeceList中每个对象
            EduSubject eduSubject = oneSubjeceList.get(i);

            //eduSubject中的值获取出来放到OneSubject

            OneSubject oneSubject = new OneSubject();
//            oneSubject.setId(eduSubject.getId());
//            oneSubject.setTitle(eduSubject.getTitle());
            BeanUtils.copyProperties(eduSubject,oneSubject);
            //多个OneSubject放到finalSubjectLst
            finalSubjectList.add(oneSubject);

            //在一级分类循环遍历所有的二级分类
            //创建LIst结合封装一级分类的二级分类
            List<TwoSubject> twoFinalSubjectList = new ArrayList<>();
            //遍历二级分类集合
            for (int m = 0; m < TwoSubjeceList.size(); m++) {
                //获取每个二级分类
                EduSubject tSubject = TwoSubjeceList.get(m);
                //判断二级分类的parentid是否和一级分类id相同
                if(tSubject.getParentId().equals(eduSubject.getId())){
                    //twoSubject把twoSubjec的值放到TwoSubject里面，再放到twoFinalSubjectList里面
                    TwoSubject twoSubject = new TwoSubject();
                    BeanUtils.copyProperties(tSubject,twoSubject);
                    twoFinalSubjectList.add(twoSubject);
                }

            }

            //把一级下面的所有二级放到一级里面
            oneSubject.setChildren(twoFinalSubjectList);
        }
        //4.封装出二级分类

        return finalSubjectList;
    }
}
