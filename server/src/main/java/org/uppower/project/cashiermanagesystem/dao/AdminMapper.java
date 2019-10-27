package org.uppower.project.cashiermanagesystem.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.uppower.project.cashiermanagesystem.model.entity.AdminEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.uppower.project.cashiermanagesystem.model.entity.DealRecordEntity;
import org.uppower.project.cashiermanagesystem.model.result.DealRecordResult;

import java.time.LocalDateTime;

/**
 * <p>
 * 管理员 Mapper 接口
 * </p>
 *
 * @author tuqikang
 * @since 2019-10-13
 */
public interface AdminMapper extends BaseMapper<AdminEntity> {


    IPage<DealRecordEntity> index(Page page, @Param("localDateTime") LocalDateTime localDateTime);

}
