package org.uppower.project.cashiermanagesystem.controller;

import cn.windyrjc.security.web.beans.UserDetails;
import cn.windyrjc.utils.response.Response;
import cn.windyrjc.utils.response.ResponsePage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.uppower.project.cashiermanagesystem.Log.MyLog;
import org.uppower.project.cashiermanagesystem.model.UserInfo;
import org.uppower.project.cashiermanagesystem.model.result.UserDiscountResult;
import org.uppower.project.cashiermanagesystem.model.result.UserResult;
import org.uppower.project.cashiermanagesystem.model.vo.UserRegisterVo;
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
    public Response<UserResult> index(UserInfo userInfo) {
        return userService.index(userInfo);
    }

    @ApiOperation("得到用户的优惠劵")
    @GetMapping("/getDiscount")
    public ResponsePage<UserDiscountResult> getDiscount(UserInfo userInfo,
                                                        @RequestParam(value = "pn",defaultValue = "1") Integer pn,
                                                        @RequestParam(value = "status",required = false) Integer status) {
        return userService.getDiscount(userInfo,pn,status);
    }

    @ApiOperation("用户注册")
    @PostMapping("/register")
    public Response register(UserInfo userInfo, @RequestBody UserRegisterVo vo) {
        System.out.println(userInfo);
        return userService.register(userInfo.getOpenId(), vo);
    }


    @ApiOperation("用户修改个人信息")
    @PatchMapping
    public Response update(UserInfo userInfo, @RequestBody UserRegisterVo vo) {
        return userService.update(userInfo.getOpenId(), vo);
    }

    @ApiOperation("用户获得会员码")
    @GetMapping("/getVip")
    public Response vip(UserInfo userInfo){
        return userService.vip(userInfo);
    }

}
