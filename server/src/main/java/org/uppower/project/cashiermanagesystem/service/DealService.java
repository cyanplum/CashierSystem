package org.uppower.project.cashiermanagesystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.uppower.project.cashiermanagesystem.dao.DealRecordMapper;
import org.uppower.project.cashiermanagesystem.model.entity.DealRecordEntity;
import org.uppower.project.cashiermanagesystem.model.entity.jsonobject.CommodityInfo;
import org.uppower.project.cashiermanagesystem.utils.FastJsonUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * █████▒█      ██  ▄████▄   ██ ▄█▀       ██████╗ ██╗   ██╗ ██████╗
 * ▓██   ▒ ██  ▓██▒▒██▀ ▀█   ██▄█▒        ██╔══██╗██║   ██║██╔════╝
 * ▒████ ░▓██  ▒██░▒▓█    ▄ ▓███▄░        ██████╔╝██║   ██║██║  ███╗
 * ░▓█▒  ░▓▓█  ░██░▒▓▓▄ ▄██▒▓██ █▄        ██╔══██╗██║   ██║██║   ██║
 * ░▒█░   ▒▒█████▓ ▒ ▓███▀ ░▒██▒ █▄       ██████╔╝╚██████╔╝╚██████╔╝
 * ▒ ░   ░▒▓▒ ▒ ▒ ░ ░▒ ▒  ░▒ ▒▒ ▓▒       ╚═════╝  ╚═════╝  ╚═════╝
 * ░     ░░▒░ ░ ░   ░  ▒   ░ ░▒ ▒░
 * ░ ░    ░░░ ░ ░ ░        ░ ░░ ░
 * ░     ░ ░      ░  ░
 *
 * @author ：涂齐康
 * @date ：Created in 2019/10/14 2:39 下午
 * @description：
 * @modified By：
 * @version:
 */
@Service
public class DealService {
    @Autowired
    private DealRecordMapper dealRecordMapper;

    /**
     * 测试jsonHandler是否有问题
     */
    public void testInsert() {
        DealRecordEntity dre = new DealRecordEntity();
        dre.setTotalPrices(100);
        dre.setUserId(1);
        List<CommodityInfo> commodityInfos = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            CommodityInfo commodityinfo = new CommodityInfo();
            commodityinfo.setBarcode(i + "");
            commodityinfo.setName(i + "");
            commodityinfo.setPrice(i * 10);
            commodityInfos.add(commodityinfo);
        }
        String s = FastJsonUtil.convertObjectToJSON(commodityInfos);
        dre.setCommodity(s);
        int success = dealRecordMapper.insertEntity(dre);
        if (success != 1) {
            System.out.println("失败");
        } else {
            System.out.println("成功");
        }
    }

    public void testQuery() {
        DealRecordEntity dealRecordEntity = dealRecordMapper.selectForId(2);
        System.out.println(dealRecordEntity.getCreateTime());
        String s = dealRecordEntity.getCommodity();
        List<CommodityInfo> commodityInfos = FastJsonUtil.toList(s, CommodityInfo.class);
        for (CommodityInfo c : commodityInfos) {
            System.out.println(c.getBarcode() + "\t" + c.getName() + "\t" + c.getPrice());
        }
    }

}
