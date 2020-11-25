package com.mzc.educms.service;

import com.mzc.educms.entity.CrmBanner;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 首页banner表 服务类
 * </p>
 *
 * @author testjava
 * @since 2020-11-25
 */
public interface CrmBannerService extends IService<CrmBanner> {

    //查询所有幻定片的方法
    List<CrmBanner> selectAllBanner();
}
