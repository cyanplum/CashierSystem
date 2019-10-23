package org.uppower.project.cashiermanagesystem.service;

import cn.windyrjc.utils.copy.DataUtil;
import cn.windyrjc.utils.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.uppower.project.cashiermanagesystem.dao.RulesMapper;
import org.uppower.project.cashiermanagesystem.exceptions.ServerException;
import org.uppower.project.cashiermanagesystem.model.entity.RulesEntity;
import org.uppower.project.cashiermanagesystem.model.result.RulesResult;
import org.uppower.project.cashiermanagesystem.model.vo.RulesVO;
import org.uppower.project.cashiermanagesystem.utils.redis.RedisTemplateService;

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
 * @date 2019/10/1915:39
 */
@Service
public class RulesService {

    @Autowired
    RulesMapper rulesMapper;

    @Autowired
    RedisTemplateService redisTemplateService;

    private static final String RULE_KEY = "userRole";

    private static final Integer EXPIRE = 60 * 60 * 2;

    public Response<RulesResult> index(){

        return Response.success(rulesMapper.index());
    }

    public Response store(RulesVO rulesVO){

        RulesEntity entity = DataUtil.convert(rulesVO,RulesEntity.class);
        if (rulesMapper.selectCount(null)>0) {
            throw  new ServerException("内部错误！");
        }
        else{
            return rulesMapper.insert(entity)==1 ? Response.success() : Response.fail("新增失败");
        }
    }
/*
    public Response delete(Integer id) {

        return rulesMapper.deleteById(id)==1 ? Response.success() : Response.fail("删除失败");
    }*/
    @Transactional(rollbackFor = Exception.class)
    public Response update(Integer id,RulesVO rulesVO) {
        RulesEntity entity = DataUtil.convert(rulesVO,RulesEntity.class);
        entity.setId(id);
        Integer i = rulesMapper.updateById(entity);
        redisTemplateService.del(RULE_KEY);
        RulesResult rulesResult = rulesMapper.index();
        redisTemplateService.set(RULE_KEY,rulesResult,EXPIRE);
        return i==1 ? Response.success() : Response.fail("修改失败");
    }
}
