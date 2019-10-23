package org.uppower.project.cashiermanagesystem.controller;

import cn.windyrjc.utils.response.Response;
import cn.windyrjc.utils.response.ResponsePage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.uppower.project.cashiermanagesystem.model.result.AdvertisingResult;
import org.uppower.project.cashiermanagesystem.model.vo.AdvertisingVo;
import org.uppower.project.cashiermanagesystem.service.AdvertisingService;

/**
 * █████▒█      ██  ▄████▄   ██ ▄█▀       ██████╗ ██╗   ██╗ ██████╗
 * ▓██   ▒ ██  ▓██▒▒██▀ ▀█   ██▄█▒        ██╔══██╗██║   ██║██╔════╝
 * ▒████ ░▓██  ▒██░▒▓█    ▄ ▓███▄░        ██████╔╝██║   ██║██║  ███╗
 * ░▓█▒  ░▓▓█  ░██░▒▓▓▄ ▄██▒▓██ █▄        ██╔══██╗██║   ██║██║   ██║
 * ░▒█░   ▒▒█████▓ ▒ ▓███▀ ░▒██▒ █▄       ██████╔╝╚██████╔╝╚██████╔╝
 * ▒ ░   ░▒▓▒ ▒ ▒ ░ ░▒ ▒  ░▒ ▒▒ ▓▒       ╚═════╝  ╚═════╝  ╚═════╝
 * ░     ░░▒░ ░ ░   ░  ▒   ░ ░▒ ▒░
 * ░ ░    ░░░ ░ ░ ░        ░ ░░ ░
 * ░     ░ ░      ░  ░
 *
 * @author ：涂齐康
 * @date ：Created in 2019/10/21 6:39 下午
 * @description：
 * @modified By：
 * @version:
 */
@Api(value = "广告管理的相关接口")
@RestController
@RequestMapping("/advertising")
public class AdvertisingController {

    @Autowired
    private AdvertisingService advertisingService;

    @GetMapping("/{id}")
    @ApiOperation("查看某广告信息")
    public Response<AdvertisingResult> index(@PathVariable Integer id) {
        return advertisingService.index(id);
    }

    @GetMapping
    @ApiOperation("拿到广告列表")
    public ResponsePage<AdvertisingResult> list(@RequestParam(value = "pn", defaultValue = "1") @ApiParam("页码") Integer pn,
                                                @RequestParam(value = "status", required = false) @ApiParam("显示状态0显示 1不显示") Integer status) {
        return advertisingService.list(pn, status);
    }

    @PostMapping
    @ApiOperation("添加一个广告")
    public Response store(@RequestBody AdvertisingVo vo) {
        return advertisingService.store(vo);
    }

    @PatchMapping("/{id}")
    @ApiOperation("修改一个广告")
    public Response update(@PathVariable Integer id, @RequestBody AdvertisingVo vo) {
        return advertisingService.update(id, vo);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("删除一个广告")
    public Response deleted(@PathVariable Integer id) {
        return advertisingService.deleted(id);
    }
}
