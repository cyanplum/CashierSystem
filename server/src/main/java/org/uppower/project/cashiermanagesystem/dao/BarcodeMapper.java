package org.uppower.project.cashiermanagesystem.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.uppower.project.cashiermanagesystem.model.entity.BarcodeEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 条形码表 Mapper 接口
 * </p>
 *
 * @author tuqikang
 * @since 2019-10-13
 */
public interface BarcodeMapper extends BaseMapper<BarcodeEntity> {

    IPage<BarcodeEntity> selectByCondition(@Param("page") Page page, @Param("code") String code, @Param("name") String name);
}
