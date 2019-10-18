package org.uppower.project.cashiermanagesystem.controller;

import cn.windyrjc.utils.response.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.uppower.project.cashiermanagesystem.service.*;
import org.uppower.project.cashiermanagesystem.model.result.*;
import org.uppower.project.cashiermanagesystem.model.vo.*;

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
 * @date 2019/10/18 15:14
 */
@RestController
@RequestMapping("/discounts")
@Api("优惠劵的操作")
public class DiscountsController {

    @Autowired
    DiscountsService discountsService;

    @ApiOperation("查询已存在的优惠劵")
    @GetMapping
    public Response<List<DiscountsResult>> index(){
        return discountsService.index();
    }

    @ApiOperation("增加优惠劵")
    @PostMapping
    public Response store(@RequestBody DiscountsVO discountsVO){
        return discountsService.store(discountsVO);
    }

    @ApiOperation("删除优惠劵")
    @DeleteMapping("/{id}")
    public Response delete(@ApiParam("优惠劵id") @PathVariable Integer id){
        return discountsService.delete(id);
    }

    @ApiOperation("修改优惠劵")
    @PatchMapping("/{id}")
    public Response update(@ApiParam("修改的优惠劵的id") @PathVariable Integer id,@ApiParam("修改的优惠劵信息") @RequestBody DiscountsVO discountsVO){
        return discountsService.update(id,discountsVO);
    }

}
