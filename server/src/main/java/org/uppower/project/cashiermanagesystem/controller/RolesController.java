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
import org.uppower.project.cashiermanagesystem.model.result.RolesResult;
import org.uppower.project.cashiermanagesystem.service.RolesService;

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
 * @date 2019/10/2120:53
 */
@RestController
@RequestMapping("/roles")
@Api("权限的操作")
public class RolesController{

    @Autowired
    RolesService rolesService;

    @ApiOperation("查看所有权限")
    @GetMapping
    public ResponsePage<RolesResult> index(@RequestParam(value = "pn",defaultValue = "1")Integer pn){
        return rolesService.index(pn);
    }
}
