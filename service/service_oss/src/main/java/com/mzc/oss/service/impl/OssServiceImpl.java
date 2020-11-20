package com.mzc.oss.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.mzc.oss.service.OssService;
import com.mzc.oss.utils.ConstantPropertiesUtil;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.UUID;

/**
 * @author MaZhiCheng
 * @date 2020/11/19 - 17:49
 * @motto 腹有诗书气自华
 * @博客地址 https://blog.csdn.net/mzc_love
 */

@Service
public class OssServiceImpl implements OssService {

    //上传文件到OSS
    @Override
    public String uploadFileAvatar(MultipartFile file) {

        String endpoint = ConstantPropertiesUtil.END_POIND;
        String accessKeyId = ConstantPropertiesUtil.ACCESS_KEY_ID;
        String accessKeySecret = ConstantPropertiesUtil.ACCESS_KEY_SECRET;
        String bucketName = ConstantPropertiesUtil.BUCKET_NAME;

        try{
            // 创建OSSClient实例。
            OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

            // 上传文件流。
            InputStream inputStream = file.getInputStream();


            //获取文件名称
            String fileName = file.getOriginalFilename();

            //在文件名称添加随机唯一值
            String uuid = UUID.randomUUID().toString().replaceAll("-","");
            fileName = uuid+fileName;

            //把文件按照日期进行分类
            //2020/11/12/aa.jpg
            //获取当前日期
            String datePath = new DateTime().toString("yyyy/MM/dd");

            //拼接
            fileName = datePath+"/"+fileName;

            //调用方法实现上传
            //第一个参数：bucket名称
            //第二个参数：上传到oss文件路径和名称
            //第三个参数：上传文件输入流
            ossClient.putObject(bucketName, fileName, inputStream);

            // 关闭OSSClient。
            ossClient.shutdown();

            //把上传之后的文件路径进行返回
            //需要将路径手动拼接出来
            String url = "https://"+bucketName+"."+endpoint+"/"+fileName;
            return url;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
