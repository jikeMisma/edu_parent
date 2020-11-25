package com.mzc.educms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mzc.educms.entity.CrmBanner;
import com.mzc.educms.mapper.CrmBannerMapper;
import com.mzc.educms.service.CrmBannerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 首页banner表 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2020-11-25
 */
@Service
public class CrmBannerServiceImpl extends ServiceImpl<CrmBannerMapper, CrmBanner> implements CrmBannerService {

    @Override
    public List<CrmBanner> selectAllBanner() {
        //根据id进行降序排列，显示排列后的前两条数据
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.orderByDesc("id");
        //last方法可以拼接sql语句
        wrapper.last("limit 2");

        List<CrmBanner> list = baseMapper.selectList(wrapper);
        return list;
    }
}
