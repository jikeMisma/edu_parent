package com.mzc.vod.utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author MaZhiCheng
 * @date 2020/11/24 - 13:53
 * @motto 腹有诗书气自华
 * @博客地址 https://blog.csdn.net/mzc_love
 */
@Component
public class ConstentVodUtils implements InitializingBean {

    //读取配置文件
    @Value("${aliyun.vod.file.keyid}")
    private  String keyid;

    @Value("${aliyun.vod.file.keysecret}")
    private  String keysecret;

    public static  String KEY_ID;
    public static  String KEY_SECRET;

    @Override
    public void afterPropertiesSet() throws Exception {
        KEY_ID = keyid;
        KEY_SECRET =keysecret;
    }
}
