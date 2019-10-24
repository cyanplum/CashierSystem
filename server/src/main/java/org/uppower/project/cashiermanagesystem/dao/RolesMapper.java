package org.uppower.project.cashiermanagesystem.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.uppower.project.cashiermanagesystem.model.entity.RolesEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.uppower.project.cashiermanagesystem.model.result.RolesResult;

import java.util.List;

/**
 * <p>
 * 角色表 Mapper 接口
 * </p>
 *
 * @author tuqikang
 * @since 2019-10-13
 */
public interface RolesMapper extends BaseMapper<RolesEntity> {


        IPage<RolesResult> list(Page page);
}
