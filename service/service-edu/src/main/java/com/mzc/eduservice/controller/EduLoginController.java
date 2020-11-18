package com.mzc.eduservice.controller;

import com.mzc.commonutils.R;
import org.springframework.web.bind.annotation.*;

/**
 * @author MaZhiCheng
 * @date 2020/11/18 - 18:56
 * @motto 腹有诗书气自华
 * @博客地址 https://blog.csdn.net/mzc_love
 */

@RestController
@CrossOrigin
@RequestMapping("eduservice/user")
public class EduLoginController {

    //lgoin
    @PostMapping("login")
    public R login(){
        return R.ok().data("token","admin");
    }
    //info
    @GetMapping("info")
    public R info(){
        return R.ok().data("roles","[admin]").data("name","admin").data("avatar","https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=1489346502,1355781396&fm=26&gp=0.jpg");
    }
}
