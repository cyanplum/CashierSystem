package org.uppower.project.cashiermanagesystem.service;

import cn.windyrjc.utils.copy.DataUtil;
import cn.windyrjc.utils.response.Response;
import cn.windyrjc.utils.response.ResponsePage;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.uppower.project.cashiermanagesystem.CashierManageSystemApplication;
import org.uppower.project.cashiermanagesystem.dao.LotteryMapper;
import org.uppower.project.cashiermanagesystem.model.dto.LotteryDto;
import org.uppower.project.cashiermanagesystem.model.entity.LotteryEntity;
import org.uppower.project.cashiermanagesystem.model.result.LotteryResult;
import  org.uppower.project.cashiermanagesystem.model.enums.DiscountAuthEnum;
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
 * @date 2019/10/18 21:20
 */
@Service
public class LotteryService {


    @Autowired
    LotteryMapper lotteryMapper;

    public ResponsePage<LotteryResult> index(Integer pn)
    {
        Page page = new Page(pn, CashierManageSystemApplication.PAGESIZE);
        List<LotteryResult> list = null;

        IPage<LotteryDto> lotteryDtos = lotteryMapper.index(page);
        list = lotteryDtos.getRecords().stream().map(p->{
            LotteryResult result= DataUtil.convert(p,LotteryResult.class);
            result.setDiscount(MoneyManageUtil.fenToYuan(p.getDiscount()));
            result.setName(DiscountAuthEnum.getMsgByCode(p.getPattern()));
            return result;
        }).collect(Collectors.toList());

        return ResponsePage.success(list,lotteryDtos.getPages(),lotteryDtos.getTotal());
    }

    public Response store(Integer id)
    {
        LotteryEntity lotteryEntity = new LotteryEntity();
        lotteryEntity.setDiscountId(id);
        lotteryEntity.setStatus(0);
        return lotteryMapper.insert(lotteryEntity)==1 ? Response.success() : Response.fail("新增失败");
    }

    public Response delete(Integer id)
    {
        return lotteryMapper.deleteById(id)==1 ? Response.success() : Response.fail("删除失败");
    }

    public Response update(Integer id,Integer isShow)
    {
        return lotteryMapper.isShow(id,isShow)==1 ? Response.success() : Response.fail("修改失败");
    }
}
