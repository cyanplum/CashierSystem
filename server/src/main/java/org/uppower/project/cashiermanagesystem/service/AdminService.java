package org.uppower.project.cashiermanagesystem.service;

import cn.windyrjc.utils.copy.DataUtil;
import cn.windyrjc.utils.response.ResponsePage;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.uppower.project.cashiermanagesystem.CashierManageSystemApplication;
import org.uppower.project.cashiermanagesystem.dao.AdminMapper;
import org.uppower.project.cashiermanagesystem.model.UserInfo;
import org.uppower.project.cashiermanagesystem.model.entity.DealRecordEntity;
import org.uppower.project.cashiermanagesystem.model.entity.jsonobject.CommodityInfo;
import org.uppower.project.cashiermanagesystem.model.entity.jsonobject.CommodityInfoResult;
import org.uppower.project.cashiermanagesystem.model.result.DealRecordResult;
import org.uppower.project.cashiermanagesystem.utils.DateUtils;
import org.uppower.project.cashiermanagesystem.utils.FastJsonUtil;
import org.uppower.project.cashiermanagesystem.utils.MoneyManageUtil;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * create by:
 * *      ____        ___  ___       __          __
 * *    /  _  \     /   |/   |      | |        / /
 * *   | | | |     / /|   /| |     | |  __   / /
 * *  | | | |     / / |__/ | |    | | /  | / /
 * * | |_| |_    / /       | |   | |/   |/ /
 * * \_______|  /_/        |_|  |___/|___/
 *
 * @date 2019/10/2519:11
 */
@Service
public class AdminService {

    @Autowired
    AdminMapper adminMapper;


    public ResponsePage<DealRecordResult> index(UserInfo userInfo, Integer pn, long localDateTime){
        Page page = new Page(pn, CashierManageSystemApplication.PAGESIZE);

        IPage<DealRecordEntity> entity = adminMapper.index(page,DateUtils.timestampToLocalDateTime(localDateTime));
        List<DealRecordResult> dealRecordResults = null;
        dealRecordResults = entity.getRecords().stream().map(p->{
            List<CommodityInfo> commodityInfos = FastJsonUtil.toList(p.getCommodity(),CommodityInfo.class);
            DealRecordResult result = DataUtil.convert(p,DealRecordResult.class);
            List<CommodityInfoResult> commodityInfoResults = new ArrayList<>();
            for (CommodityInfo info:commodityInfos) {
                CommodityInfoResult result1 = DataUtil.convert(info,CommodityInfoResult.class);
                result1.setPrice(MoneyManageUtil.fenToYuan(info.getPrice()));
                commodityInfoResults.add(result1);
            }
            result.setCommodity(commodityInfoResults);
            result.setTotalPrices(MoneyManageUtil.fenToYuan(p.getTotalPrices()));
            return result;
        }).collect(Collectors.toList());
        return ResponsePage.success(dealRecordResults,entity.getPages(),entity.getTotal());
    }

}
