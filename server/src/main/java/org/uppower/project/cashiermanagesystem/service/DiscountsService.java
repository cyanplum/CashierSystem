package org.uppower.project.cashiermanagesystem.service;

import cn.windyrjc.utils.copy.DataUtil;
import cn.windyrjc.utils.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.uppower.project.cashiermanagesystem.dao.DiscountsMapper;
import org.uppower.project.cashiermanagesystem.dao.RolesMapper;
import org.uppower.project.cashiermanagesystem.model.dto.DiscountsDto;
import org.uppower.project.cashiermanagesystem.model.entity.DiscountsEntity;
import org.uppower.project.cashiermanagesystem.model.enums.DiscountAuthEnum;
import org.uppower.project.cashiermanagesystem.model.result.DiscountsResult;
import org.uppower.project.cashiermanagesystem.model.result.RolesResult;
import org.uppower.project.cashiermanagesystem.model.vo.DiscountsVO;
import org.uppower.project.cashiermanagesystem.utils.MoneyManageUtil;

import javax.management.relation.RoleResult;
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

    @Autowired
    RolesMapper rolesMapper;

    public Response<List<DiscountsResult>> index(){
        List<DiscountsDto> discountsDto = discountsMapper.index();
        List<DiscountsResult> discountsResults = new ArrayList<>();
        DiscountsResult result;
        for(DiscountsDto dto:discountsDto){
            result=DataUtil.convert(dto,DiscountsResult.class);
            result.setDiscount(MoneyManageUtil.fenToYuan(dto.getDiscount()));
            if (DiscountAuthEnum.FULL.getAuth()-dto.getPattern()==0)
                result.setName(DiscountAuthEnum.FULL.getName());
            else
                result.setName(DiscountAuthEnum.DISCOUNTS.getName());
            discountsResults.add(result);
        }
        return Response.success(discountsResults);
    }

    @Transactional(rollbackFor = Exception.class)
    public Response store(DiscountsVO discountsVO){
        DiscountsEntity discountsEntity;
        discountsEntity = DataUtil.convert(discountsVO,DiscountsEntity.class);
        discountsEntity.setDiscount(MoneyManageUtil.yuanToFen(discountsVO.getDiscount()));
        discountsEntity.setPattern(DiscountAuthEnum.getCodeByMsg(discountsVO.getName()));
        List<RolesResult> list = rolesMapper.list();
        for (RolesResult rolesResult : list)
        {
            if (discountsVO.getAuth()-rolesResult.getId()==0)
                discountsEntity.setAuth(rolesResult.getId());
        }
        return discountsMapper.insert(discountsEntity)==1 ? Response.success() : Response.fail("新增失败");
    }

    public Response delete(Integer id)
    {
        return discountsMapper.deleteById(id) == 1 ? Response.success() : Response.fail("删除失败!");
    }

    public Response update(Integer id, DiscountsVO discountsVO){
        DiscountsEntity discountsEntity =DataUtil.convert(discountsVO,DiscountsEntity.class);
        discountsEntity.setId(id);
        discountsEntity.setDiscount(MoneyManageUtil.yuanToFen(discountsVO.getDiscount()));
        return Response.success(discountsMapper.updateById(discountsEntity)== 1 ? Response.success() : Response.fail("更新失败!"));
    }
}
