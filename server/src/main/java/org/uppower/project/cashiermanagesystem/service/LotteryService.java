package org.uppower.project.cashiermanagesystem.service;

import cn.windyrjc.utils.copy.DataUtil;
import cn.windyrjc.utils.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.uppower.project.cashiermanagesystem.dao.LotteryMapper;
import org.uppower.project.cashiermanagesystem.model.dto.LotteryDto;
import org.uppower.project.cashiermanagesystem.model.entity.LotteryEntity;
import org.uppower.project.cashiermanagesystem.model.result.LotteryResult;
import  org.uppower.project.cashiermanagesystem.model.enums.DiscountAuthEnum;
import org.uppower.project.cashiermanagesystem.utils.MoneyManageUtil;

import java.util.ArrayList;
import java.util.List;

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

    public Response<List<LotteryResult>> index()
    {
        List<LotteryResult> list = new ArrayList<>();
        LotteryResult result;
        List<LotteryDto> lotteryDtos = lotteryMapper.index();
        for (LotteryDto dto: lotteryDtos) {
            result = DataUtil.convert(dto,LotteryResult.class);
            result.setDiscount(MoneyManageUtil.fenToYuan(dto.getDiscount()));
            result.setName(DiscountAuthEnum.getMsgByCode(dto.getPattern()));
            list.add(result);
        }
        return Response.success(list);
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
