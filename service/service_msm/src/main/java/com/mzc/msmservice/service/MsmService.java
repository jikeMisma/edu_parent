package com.mzc.msmservice.service;

import java.util.Map;

/**
 * @author MaZhiCheng
 * @date 2020/11/27 - 17:19
 * @motto 腹有诗书气自华
 * @博客地址 https://blog.csdn.net/mzc_love
 */
public interface MsmService {
    //调用service中发送短信方法
    boolean send(Map<String, Object> parm, String phone);
}
