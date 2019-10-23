package org.uppower.project.cashiermanagesystem.service;

import cn.windyrjc.utils.copy.DataUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.uppower.project.cashiermanagesystem.Log.Log;
import org.uppower.project.cashiermanagesystem.dao.OprationLogMapper;
import org.uppower.project.cashiermanagesystem.model.entity.OperationLogEntity;

/**
 * create by:
 * *      ____        ___  ___       __          __
 * *    /  _  \     /   |/   |      | |        / /
 * *   | | | |     / /|   /| |     | |  __   / /
 * *  | | | |     / / |__/ | |    | | /  | / /
 * * | |_| |_    / /       | |   | |/   |/ /
 * * \_______|  /_/        |_|  |___/|___/
 *
 * @date 2019/10/2221:13
 */
@Service
public class LogService {

    @Autowired
    OprationLogMapper oprationLogMapper;

    public Integer store(Log log){
        OperationLogEntity operationLogEntity = DataUtil.convert(log,OperationLogEntity.class);
        return oprationLogMapper.insert(operationLogEntity);
    }
}
