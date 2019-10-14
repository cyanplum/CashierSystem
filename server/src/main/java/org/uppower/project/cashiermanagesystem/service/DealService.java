package org.uppower.project.cashiermanagesystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.uppower.project.cashiermanagesystem.dao.DealRecordMapper;
import org.uppower.project.cashiermanagesystem.model.entity.DealRecordEntity;
import org.uppower.project.cashiermanagesystem.model.entity.jsonobject.Commodityinfo;

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
    public void testInsert(){
        DealRecordEntity dre = new DealRecordEntity();
        dre.setTotalPrices(100);
        dre.setUserId(1);
        List<Commodityinfo> commodityinfos = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Commodityinfo commodityinfo = new Commodityinfo();
            commodityinfo.setBarcode(i+"");
            commodityinfo.setName(i+"");
            commodityinfo.setPrice(i*10);
            commodityinfos.add(commodityinfo);
        }
        dre.setCommodity(commodityinfos);
        int success = dealRecordMapper.insert(dre);
        if (success!=1){
            System.out.println("失败");
        }else {
            System.out.println("成功");
        }
    }

    public void testQuery(){
        DealRecordEntity dealRecordEntity = dealRecordMapper.selectById(1);
        System.out.println(dealRecordEntity.getCreateTime());
        List<Commodityinfo> commodity = dealRecordEntity.getCommodity();
        for (Commodityinfo c : commodity){
            System.out.println(c.getBarcode()+"\t"+c.getName()+"\t"+c.getPrice());
        }
    }

}
