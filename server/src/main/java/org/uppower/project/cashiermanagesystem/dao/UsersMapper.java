package org.uppower.project.cashiermanagesystem.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.uppower.project.cashiermanagesystem.model.UserInfo;
import org.uppower.project.cashiermanagesystem.model.dto.UserDiscountDto;
import org.uppower.project.cashiermanagesystem.model.entity.UsersEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.uppower.project.cashiermanagesystem.model.result.UserDiscountResult;
import org.uppower.project.cashiermanagesystem.model.result.UserResult;
import org.uppower.project.cashiermanagesystem.model.vo.UserRegisterVo;

import java.util.List;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author tuqikang
 * @since 2019-10-13
 */
public interface UsersMapper extends BaseMapper<UsersEntity> {

    UserResult show(@Param("id") Integer id);

    IPage<UserDiscountDto> getDiscount(Page page, @Param("id") Integer id, @Param("status") Integer status);

    int registerByOpenId(@Param("openId") String openId, @Param("vo") UserRegisterVo vo, @Param("vipCode") String vipCode);

    int updateByOpenId(@Param("openId") String openId, @Param("vo") UserRegisterVo vo);

    UserInfo selectByOpenId(@Param("openid") String openid);

    int insertAndGetId(@Param("userInfo") UserInfo userInfo);

    String selectVipById(@Param("userId") Integer userId);
}
