package org.uppower.project.cashiermanagesystem.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.uppower.project.cashiermanagesystem.model.entity.DealRecordEntity;

import java.util.List;

/**
 * <p>
 * 交易记录表 Mapper 接口
 * </p>
 *
 * @author tuqikang
 * @since 2019-10-13
 */
public interface DealRecordMapper extends BaseMapper<DealRecordEntity> {


    int insertEntity(@Param("entity") DealRecordEntity entity);

    DealRecordEntity selectForId(@Param("id") int id);

    IPage<DealRecordEntity> selectByUserId(Page page, @Param("userId") Integer userId);
}
