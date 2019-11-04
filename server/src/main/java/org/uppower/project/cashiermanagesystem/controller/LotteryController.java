package org.uppower.project.cashiermanagesystem.controller;

import cn.windyrjc.utils.response.Response;
import cn.windyrjc.utils.response.ResponsePage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.DispatcherServlet;
import org.uppower.project.cashiermanagesystem.model.result.LotteryResult;
import org.uppower.project.cashiermanagesystem.service.LotteryService;

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
 * @date 2019/10/18 21:15
 */

@RestController
@RequestMapping("/lottery")
@Api("抽奖池的操作")
public class LotteryController {


    @Autowired
    LotteryService lotteryService;

    @ApiOperation("抽奖池的查询")
    @GetMapping
    public ResponsePage<LotteryResult> index(@RequestParam(value = "pn",defaultValue = "1")Integer pn){
        return lotteryService.index(pn);
    }

    @ApiOperation("抽奖池中奖券的插入")
    @GetMapping("/{id}")
    public Response store(@ApiParam("抽奖劵的id") @PathVariable("id") Integer id){
        return lotteryService.store(id);
    }

    @ApiOperation("抽奖池中奖券的修改")
    @PatchMapping
    public Response update(@ApiParam("id") @RequestParam("id") Integer id,@RequestParam("status") @PathVariable Integer status){
        return lotteryService.update(id,status);
    }

    @ApiOperation("抽奖池中奖券的删除")
    @DeleteMapping("/{id}")
    public Response delete(@ApiParam("奖池抽奖劵的id") @PathVariable("id") Integer id){
        return lotteryService.delete(id);
    }
}
