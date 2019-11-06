package org.uppower.project.cashiermanagesystem.controller;

import cn.windyrjc.utils.response.Response;
import cn.windyrjc.utils.response.ResponsePage;
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
@Api(value = "优惠劵的操作",description = "优惠券相关接口")
public class DiscountsController {

    @Autowired
    DiscountsService discountsService;

    @ApiOperation("查询已存在的优惠劵")
    @GetMapping
    public ResponsePage<DiscountsResult> index(@RequestParam(value = "pn",defaultValue = "1")Integer pn){
        return discountsService.index(pn);
    }

    @ApiOperation("增加优惠劵")
    @PostMapping
    public Response store(@ApiParam("新增优惠劵的信息")@RequestBody DiscountsVO discountsVO){
        return discountsService.store(discountsVO);
    }

    @ApiOperation("删除优惠劵")
    @DeleteMapping("/{id}")
    public Response delete(@ApiParam("优惠劵id") @PathVariable("id") Integer id){
        return discountsService.delete(id);
    }

    @PatchMapping("/{id}")
    @ApiOperation("修改优惠劵")
    public Response update(@ApiParam("修改的优惠劵的id") @PathVariable("id") Integer id,@ApiParam("修改的优惠劵信息") @RequestBody DiscountsVO discountsVO){
        return discountsService.update(id,discountsVO);
    }

}
