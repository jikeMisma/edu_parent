package com.mzc.vod.service.impl;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.resp.UploadStreamResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.mzc.commonutils.R;
import com.mzc.servicebase.ExceptionHandler.GuliException;
import com.mzc.vod.service.VodService;
import com.mzc.vod.utils.ConstentVodUtils;
import com.mzc.vod.utils.initVodClient;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author MaZhiCheng
 * @date 2020/11/23 - 23:45
 * @motto 腹有诗书气自华
 * @博客地址 https://blog.csdn.net/mzc_love
 */

@Service
public class VodServiceImpl implements VodService {
    @Override
    public String uploadVideoAliyun(MultipartFile file) {

       try{
           /**
            * accessKeyId, accessKeySecret,id和秘钥
            * title 上传后显示的名称
            * fileName 文件原始名称
            */

           String fileName = file.getOriginalFilename();
           String title = fileName.substring(0, fileName.lastIndexOf("."));
           InputStream inputStream =file.getInputStream();

           UploadStreamRequest request = new UploadStreamRequest(ConstentVodUtils.KEY_ID, ConstentVodUtils.KEY_SECRET, title, fileName, inputStream);

           UploadVideoImpl uploader = new UploadVideoImpl();
           UploadStreamResponse response = uploader.uploadStream(request);

           String videoId = "";
           if (response.isSuccess()) {
               videoId = response.getVideoId();
           } else { //如果设置回调URL无效，不影响视频上传，可以返回VideoId同时会返回错误码。其他情况上传失败时，VideoId为空，此时需要根据返回错误码分析具体错误原因
               videoId = response.getVideoId();
           }
           return videoId;

       }catch (Exception e){
           e.printStackTrace();
           return  null;
       }
    }

    @Override
    public void removeMoreAliyunVideo(List videoIdList) {
        try{

            //初始化对象
            DefaultAcsClient client = initVodClient.initVodClient(ConstentVodUtils.KEY_ID, ConstentVodUtils.KEY_SECRET);
            //创建一个删除视频的request对象
            DeleteVideoRequest request = new DeleteVideoRequest();

            //向request设置视频id

            //videoIdList的值转换成1,2,3
            String videoIds = StringUtils.join(videoIdList.toArray(), ",");

            request.setVideoIds(videoIds);
            //调用初始化方法实现删除
            client.getAcsResponse(request);


        }catch (Exception e){
            e.printStackTrace();
            throw  new GuliException(20001,"删除视频失败！");
        }
    }

    public static void main(String[] args) {

        List<String> list = new ArrayList<>();
        list.add("11");
        list.add("22");
        list.add("33");

        String join = StringUtils.join(list.toArray(), ",");
        System.out.println(join);
    }
}
