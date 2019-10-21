package org.uppower.project.cashiermanagesystem.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.uppower.project.cashiermanagesystem.model.entity.AdvertisingEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.uppower.project.cashiermanagesystem.model.result.AdvertisingResult;
import org.uppower.project.cashiermanagesystem.model.vo.AdvertisingVo;

/**
 * <p>
 * 广告表 Mapper 接口
 * </p>
 *
 * @author tuqikang
 * @since 2019-10-13
 */
public interface AdvertisingMapper extends BaseMapper<AdvertisingEntity> {

    int insertVo(@Param("vo") AdvertisingVo vo);

    IPage<AdvertisingEntity> selectListByStatus(Page page, @Param("status") Integer status);

    int updateByVo(@Param("id") Integer id, @Param("vo") AdvertisingVo vo);
}
