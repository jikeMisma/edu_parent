package com.mzc.vod.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author MaZhiCheng
 * @date 2020/11/23 - 23:45
 * @motto 腹有诗书气自华
 * @博客地址 https://blog.csdn.net/mzc_love
 */
public interface VodService {
    //上传功能
    String uploadVideoAliyun(MultipartFile file);

    //删除多个视频的方法
    void removeMoreAliyunVideo(List videoIdList);
}
