package org.uppower.project.cashiermanagesystem.controller;

import cn.windyrjc.security.web.beans.UserDetails;
import cn.windyrjc.utils.response.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.uppower.project.cashiermanagesystem.model.UserInfo;
import org.uppower.project.cashiermanagesystem.model.result.UserDiscountResult;
import org.uppower.project.cashiermanagesystem.model.result.UserResult;
import org.uppower.project.cashiermanagesystem.service.UserService;
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
 * @date 2019/10/2112:30
 */
@Api("用户首页操作")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @ApiOperation("得到用户的基本信息")
    @GetMapping
    public Response<UserResult>index(UserInfo userInfo){
        return userService.index(userInfo);
    }

    @ApiOperation("得到用户的优惠劵")
    @GetMapping("/getDiscount")
    public Response<List<UserDiscountResult>> getDiscount(UserInfo userInfo){
        return userService.getDiscount(userInfo);
    }

}
