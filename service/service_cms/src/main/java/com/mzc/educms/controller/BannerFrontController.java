package com.mzc.educms.controller;

import com.mzc.commonutils.R;
import com.mzc.educms.entity.CrmBanner;
import com.mzc.educms.service.CrmBannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author MaZhiCheng
 * @date 2020/11/25 - 18:04
 * @motto 腹有诗书气自华
 * @博客地址 https://blog.csdn.net/mzc_love
 */

/**
 * banner的前台调用接口
 *
 */

@RestController
@CrossOrigin
@RequestMapping("/educms/bannerfront")
public class BannerFrontController {
    @Autowired
    private CrmBannerService bannerService;

    //查询所有幻定片的方法
    @GetMapping("getAllBanner")
    public R getAllBanner(){
        List<CrmBanner> list = bannerService.selectAllBanner();
        return  R.ok().data("list",list);
    }

}
