package org.uppower.project.cashiermanagesystem.service;

import cn.windyrjc.utils.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.uppower.project.cashiermanagesystem.dao.RolesMapper;
import org.uppower.project.cashiermanagesystem.model.result.RolesResult;

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
 * @date 2019/10/2120:54
 */
@Service
public class RolesService {

    @Autowired
    RolesMapper rolesMapper;

    public Response<List<RolesResult>>index(){
        return Response.success(rolesMapper.list());
    }
}
