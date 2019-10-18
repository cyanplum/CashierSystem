package org.uppower.project.cashiermanagesystem.service;

import cn.windyrjc.utils.copy.DataUtil;
import cn.windyrjc.utils.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.uppower.project.cashiermanagesystem.dao.DiscountsMapper;
import org.uppower.project.cashiermanagesystem.model.dto.DiscountsDto;
import org.uppower.project.cashiermanagesystem.model.entity.DiscountsEntity;
import org.uppower.project.cashiermanagesystem.model.enums.DiscountAuthEnum;
import org.uppower.project.cashiermanagesystem.model.result.DiscountsResult;
import org.uppower.project.cashiermanagesystem.model.vo.DiscountsVO;

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
 * @date 2019/10/18 15:50
 */
@Service
public class DiscountsService {

    @Autowired
    DiscountsMapper discountsMapper;

    public Response<List<DiscountsResult>> index(){
        List<DiscountsDto> discountsDto = discountsMapper.index();
        List<DiscountsResult> discountsResults = new ArrayList<>();
        DiscountsResult result;
        for(DiscountsDto dto:discountsDto){
            result=DataUtil.convert(dto,DiscountsResult.class);
            if (DiscountAuthEnum.FULL.getAuth()-dto.getPattern()==0)
                result.setName(DiscountAuthEnum.FULL.getName());
            else
                result.setName(DiscountAuthEnum.DISCOUNTS.getName());
            discountsResults.add(result);
        }
        return Response.success(discountsResults);
    }

    public Response store(DiscountsVO discountsVO){
        DiscountsEntity discountsEntity;
        discountsEntity = DataUtil.convert(discountsVO,DiscountsEntity.class);
        discountsEntity.setPattern(DiscountAuthEnum.getCodeByMsg(discountsVO.getName()));
        if (discountsVO.getAuth().equals("会员"))
            discountsEntity.setAuth(3);
        return discountsMapper.insert(discountsEntity)==1 ? Response.success() : Response.fail("新增失败");
    }

    public Response delete(Integer id)
    {
        return discountsMapper.deleteById(id) == 1 ? Response.success() : Response.fail("删除失败!");
    }

    public Response update(Integer id, DiscountsVO discountsVO){
        DiscountsEntity discountsEntity =DataUtil.convert(discountsVO,DiscountsEntity.class);
        discountsEntity.setId(id);
        return Response.success(discountsMapper.updateById(discountsEntity)== 1 ? Response.success() : Response.fail("更新失败!"));
    }
}
