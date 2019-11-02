package org.uppower.project.cashiermanagesystem.service;

import cn.windyrjc.utils.copy.DataUtil;
import cn.windyrjc.utils.response.Response;
import cn.windyrjc.utils.response.ResponsePage;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.uppower.project.cashiermanagesystem.CashierManageSystemApplication;
import org.uppower.project.cashiermanagesystem.dao.DealRecordMapper;
import org.uppower.project.cashiermanagesystem.model.UserInfo;
import org.uppower.project.cashiermanagesystem.model.entity.DealRecordEntity;
import org.uppower.project.cashiermanagesystem.model.entity.jsonobject.CommodityInfo;
import org.uppower.project.cashiermanagesystem.model.entity.jsonobject.CommodityInfoResult;
import org.uppower.project.cashiermanagesystem.model.result.DealRecordResult;
import org.uppower.project.cashiermanagesystem.utils.FastJsonUtil;
import org.uppower.project.cashiermanagesystem.utils.MoneyManageUtil;

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
 * @date 2019/10/2416:03
 */
@Service
public class DealRecordService {

    @Autowired
    DealRecordMapper dealRecordMapper;

    public ResponsePage<DealRecordResult> index(UserInfo userInfo, Integer pn){
        Page page = new Page(pn, CashierManageSystemApplication.PAGESIZE);
        IPage<DealRecordEntity> entity = dealRecordMapper.selectByUserId(page,userInfo.getUserId());
        List<DealRecordResult> dealRecordResults = null;
        List<DealRecordResult> list = new ArrayList<>();
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
