package com.mzc.educenter.controller;


import com.mzc.commonutils.R;
import com.mzc.educenter.entity.UcenterMember;
import com.mzc.educenter.entity.vo.RegisterVo;
import com.mzc.educenter.service.UcenterMemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2020-11-27
 */

@Api(description = "用户中心接口")
@RestController
@CrossOrigin
@RequestMapping("/educenter/member")
public class UcenterMemberController {

    @Autowired
    private UcenterMemberService memberService;

    //登录
    //member中传入手机号和密码
    @ApiOperation(value = "用户登录接口")
    @PostMapping("login")
    public R loginUser(@RequestBody UcenterMember member) {
        //member对象封装手机号和密码
        //调用service方法实现登录
        //返回token值，使用jwt生成
        String token = memberService.login(member);
        return R.ok().data("token",token);
    }


    //注册
    @ApiOperation(value = "注册接口")
    @PostMapping("register")
    public  R registerUser(@RequestBody RegisterVo registerVo){

        memberService.register(registerVo);
        return R.ok();

    }
}

