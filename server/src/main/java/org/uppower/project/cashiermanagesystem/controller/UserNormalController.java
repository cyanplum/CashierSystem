package org.uppower.project.cashiermanagesystem.controller;

import cn.windyrjc.utils.response.ResponsePage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.uppower.project.cashiermanagesystem.model.result.AdvertisingResult;
import org.uppower.project.cashiermanagesystem.model.result.FileInfoResult;
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
 * @date ：Created in 2019/11/4 2:06 下午
 * @description：
 * @modified By：
 * @version:
 */
@RestController
@RequestMapping("/users/normal")
@Api(value = "用户通用接口，如广告的显示、物品的交易等")
public class UserNormalController {

    @Autowired
    private AdvertisingService advertisingService;

    @GetMapping("/advertising")
    @ApiOperation("用户的广告列表")
    public ResponsePage<FileInfoResult> advertisingShow(){
        return advertisingService.show();
    }

//    @PostMapping("/deal")
//    @ApiOperation("用户购买物品")
//    public







}
