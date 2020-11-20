package com.mzc.eduservice.entity.subject;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author MaZhiCheng
 * @date 2020/11/20 - 17:14
 * @motto 腹有诗书气自华
 * @博客地址 https://blog.csdn.net/mzc_love
 */

//一级分类
@Data
public class OneSubject {

    private String id;

    private String title;

    //一个一级分类有多个二级分类
    private List<TwoSubject> children = new ArrayList<>();
}
