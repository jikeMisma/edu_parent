package com.mzc.oss.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author MaZhiCheng
 * @date 2020/11/19 - 17:48
 * @motto 腹有诗书气自华
 * @博客地址 https://blog.csdn.net/mzc_love
 */
public interface OssService {

    public String uploadFileAvatar(MultipartFile file);
}
