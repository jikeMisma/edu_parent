package com.mzc.eduservice.client;

import com.mzc.commonutils.R;
import io.swagger.annotations.ApiParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author MaZhiCheng
 * @date 2020/11/24 - 17:58
 * @motto 腹有诗书气自华
 * @博客地址 https://blog.csdn.net/mzc_love
 */
@FeignClient("service-vod")
@Component
public interface VodClient {

    //定义方法路径
    //根据视频id删除阿里云视频
    @DeleteMapping("enuvod/video/removeAliyunVideo/{id}")
    public R removeAliyunVideo(@PathVariable("id") String id);

    @DeleteMapping("enuvod/video/delete-batch")
    public R deleteBatch(
            @ApiParam(name = "videoIdList", value = "云端视频id", required = true)
            @RequestParam("videoIdList") List<String> videoIdList);

}
