package com.mzc.eduservice.entity.chapter;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author MaZhiCheng
 * @date 2020/11/21 - 23:17
 * @motto 腹有诗书气自华
 * @博客地址 https://blog.csdn.net/mzc_love
 */

@Data
public class ChapterVo {

    private String id;

    private String title;

    //表示小结
    private List<VideoVo> children = new ArrayList<>();
}
