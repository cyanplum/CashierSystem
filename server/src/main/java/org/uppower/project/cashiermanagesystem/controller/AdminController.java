package org.uppower.project.cashiermanagesystem.controller;

import cn.windyrjc.utils.response.ResponsePage;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.uppower.project.cashiermanagesystem.model.UserInfo;
import org.uppower.project.cashiermanagesystem.model.result.DealRecordResult;
import org.uppower.project.cashiermanagesystem.service.AdminService;

import java.time.LocalDateTime;

/**
 * create by:
 * *      ____        ___  ___       __          __
 * *    /  _  \     /   |/   |      | |        / /
 * *   | | | |     / /|   /| |     | |  __   / /
 * *  | | | |     / / |__/ | |    | | /  | / /
 * * | |_| |_    / /       | |   | |/   |/ /
 * * \_______|  /_/        |_|  |___/|___/
 *
 * @date 2019/10/2519:09
 */
@RequestMapping("/admin")
@RestController
public class AdminController {

    @Autowired
    AdminService adminService;

    @ApiOperation("查询交易记录表")
    @GetMapping
    public ResponsePage<DealRecordResult> index(UserInfo userInfo, @RequestParam(value = "pn",defaultValue = "1")Integer pn,
                                                @RequestParam(value = "dateTime",required = false) long dateTime){
        return adminService.index(userInfo,pn,dateTime);
    }
}
