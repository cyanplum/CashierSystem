package org.uppower.project.cashiermanagesystem.dao;

import org.uppower.project.cashiermanagesystem.model.entity.RulesEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.uppower.project.cashiermanagesystem.model.result.RulesResult;

import java.util.List;

/**
 * <p>
 * 规则表 Mapper 接口
 * </p>
 *
 * @author tuqikang
 * @since 2019-10-13
 */
public interface RulesMapper extends BaseMapper<RulesEntity> {

    List<RulesResult> index();
}
