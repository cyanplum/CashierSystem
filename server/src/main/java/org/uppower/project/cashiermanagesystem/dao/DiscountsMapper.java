package org.uppower.project.cashiermanagesystem.dao;


import org.uppower.project.cashiermanagesystem.model.dto.DiscountsDto;
import org.uppower.project.cashiermanagesystem.model.entity.DiscountsEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.uppower.project.cashiermanagesystem.model.result.DiscountsResult;
import java.util.List;

/**
 * <p>
 * 优惠券 Mapper 接口
 * </p>
 *
 * @author tuqikang
 * @since 2019-10-13
 */
public interface DiscountsMapper extends BaseMapper<DiscountsEntity> {


    List<DiscountsDto> index();
}
