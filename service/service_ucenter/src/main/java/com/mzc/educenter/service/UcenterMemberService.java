package com.mzc.educenter.service;

import com.mzc.educenter.entity.UcenterMember;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mzc.educenter.entity.vo.RegisterVo;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author testjava
 * @since 2020-11-27
 */
public interface UcenterMemberService extends IService<UcenterMember> {

    //调用service中方法实现登录
    String login(UcenterMember ucenterMember);

    void register(RegisterVo registerVo);
}
