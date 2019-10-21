package org.uppower.project.cashiermanagesystem.service;

import cn.windyrjc.security.web.beans.UserDetails;
import cn.windyrjc.utils.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.uppower.project.cashiermanagesystem.dao.RulesMapper;
import org.uppower.project.cashiermanagesystem.dao.UsersMapper;
import org.uppower.project.cashiermanagesystem.model.UserInfo;
import org.uppower.project.cashiermanagesystem.model.result.RulesResult;
import org.uppower.project.cashiermanagesystem.model.result.UserResult;
import org.uppower.project.cashiermanagesystem.utils.redis.RedisTemplateService;

/**
 * create by:
 * *      ____        ___  ___       __          __
 * *    /  _  \     /   |/   |      | |        / /
 * *   | | | |     / /|   /| |     | |  __   / /
 * *  | | | |     / / |__/ | |    | | /  | / /
 * * | |_| |_    / /       | |   | |/   |/ /
 * * \_______|  /_/        |_|  |___/|___/
 *
 * @date 2019/10/2112:43
 */
@Service
public class UserService {


    @Autowired
    RedisTemplateService redisTemplateService;

    @Autowired
    RulesMapper rulesMapper;

    @Autowired
    UsersMapper usersMapper;

    @Autowired
    RulesService rulesService;

    @Transactional(rollbackFor = Exception.class)
    public Response<UserResult> index(UserInfo userInfo){

        UserResult userResult = usersMapper.show(userInfo.getId());
        RulesResult rulesResult;
        if (redisTemplateService.get("规则", RulesResult.class)!=null)
        {
            rulesResult = redisTemplateService.get("规则", RulesResult.class);
        }
        else
        {
            synchronized (RedisTemplateService.class){
                rulesResult = rulesMapper.index();
                redisTemplateService.set("规则",rulesResult,7200);
            }
        }
        if (userResult.getExperience()>=0&&userResult.getExperience()<rulesResult.getBronze())
            userResult.setGrade("青铜");
        else if (userResult.getExperience()>=rulesResult.getBronze()&&userResult.getExperience()<rulesResult.getSilver())
            userResult.setGrade("白银");
        else if (userResult.getExperience()>=rulesResult.getSilver()&&userResult.getExperience()<rulesResult.getGold())
            userResult.setGrade("黄金");
        else if (userResult.getExperience()>=rulesResult.getGold()&&userResult.getExperience()<rulesResult.getPlatinum())
            userResult.setGrade("铂金");
        else if (userResult.getExperience()>=rulesResult.getPlatinum()&&userResult.getExperience()<rulesResult.getDiamond())
            userResult.setGrade("钻石");
        else if (userResult.getExperience()>=rulesResult.getDiamond())
            userResult.setGrade("王者");

        return Response.success(userResult);
    }
}
