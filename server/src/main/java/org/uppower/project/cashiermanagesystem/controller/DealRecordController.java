package org.uppower.project.cashiermanagesystem.controller;

import cn.windyrjc.utils.response.Response;
import cn.windyrjc.utils.response.ResponsePage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.uppower.project.cashiermanagesystem.model.UserInfo;
import org.uppower.project.cashiermanagesystem.model.result.DealRecordResult;
import org.uppower.project.cashiermanagesystem.service.DealRecordService;

/**
 * create by:
 * *      ____        ___  ___       __          __
 * *    /  _  \     /   |/   |      | |        / /
 * *   | | | |     / /|   /| |     | |  __   / /
 * *  | | | |     / / |__/ | |    | | /  | / /
 * * | |_| |_    / /       | |   | |/   |/ /
 * * \_______|  /_/        |_|  |___/|___/
 *
 * @date 2019/10/2415:58
 */
@RestController
@RequestMapping("/dealRecord")
@Api("交易记录的操作")
public class DealRecordController {

    @Autowired
    DealRecordService dealRecordService;

    @ApiOperation("查询交易记录表")
    @GetMapping
    public ResponsePage<DealRecordResult> index(UserInfo userInfo, @RequestParam(value = "pn",defaultValue = "1")Integer pn){
        return dealRecordService.index(userInfo.getUserId(),pn);
    }
}
