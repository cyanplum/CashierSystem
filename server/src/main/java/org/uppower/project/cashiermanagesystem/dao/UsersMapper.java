package org.uppower.project.cashiermanagesystem.dao;

import org.apache.ibatis.annotations.Param;
import org.uppower.project.cashiermanagesystem.model.dto.UserDiscountDto;
import org.uppower.project.cashiermanagesystem.model.entity.UsersEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.uppower.project.cashiermanagesystem.model.result.UserDiscountResult;
import org.uppower.project.cashiermanagesystem.model.result.UserResult;

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

    List<UserDiscountDto> getDiscount(@Param("id") Integer id);
}
