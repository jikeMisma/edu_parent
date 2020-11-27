package com.mzc.msmservice.controller;

import com.mzc.commonutils.R;
import com.mzc.msmservice.service.MsmService;
import com.mzc.msmservice.utils.RandomUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author MaZhiCheng
 * @date 2020/11/27 - 17:18
 * @motto 腹有诗书气自华
 * @博客地址 https://blog.csdn.net/mzc_love
 */
@Api(description = "注册验证码短信接口")
@RestController
@RequestMapping("/edumsm/msm")
@CrossOrigin
public class MSMController {

    @Autowired
    private MsmService msmService;

    @Autowired
    private RedisTemplate<String,String>  redisTemplate;

    //发送短信方法
    @ApiOperation(value = "发送短信验证码方法！")
    @GetMapping("end/{phone}")
    public R sendMsm(@PathVariable String phone){
        //先从redis获取验证码，如果获取到直接返回
        String code = redisTemplate.opsForValue().get(phone);
        if(!StringUtils.isEmpty(code)){
            return R.ok();
        }
        //2.如果获取不到进行阿里云发送
        //生成随机验证码值传给阿里云发送
        code = RandomUtil.getFourBitRandom();
        Map<String,Object> parm = new HashMap<>();
        parm.put("code",code);
        //调用service中发送短信方法
        boolean isSend =  msmService.send(parm,phone);
        if(isSend){
            //发送成功，把发送成功的验证码放到redis中
            //设置有效时间
            redisTemplate.opsForValue().set(phone,code,5, TimeUnit.MINUTES);
            return  R.ok();
        }else{
            return  R.error().message("短信发送失败！");
        }

    }
}
