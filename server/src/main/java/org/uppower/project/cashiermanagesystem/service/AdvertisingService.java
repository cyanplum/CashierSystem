package org.uppower.project.cashiermanagesystem.service;

import cn.windyrjc.utils.copy.DataUtil;
import cn.windyrjc.utils.response.Response;
import cn.windyrjc.utils.response.ResponsePage;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.uppower.project.cashiermanagesystem.CashierManageSystemApplication;
import org.uppower.project.cashiermanagesystem.dao.AdvertisingMapper;
import org.uppower.project.cashiermanagesystem.exceptions.ServerException;
import org.uppower.project.cashiermanagesystem.model.entity.AdvertisingEntity;
import org.uppower.project.cashiermanagesystem.model.result.AdvertisingResult;
import org.uppower.project.cashiermanagesystem.model.result.FileInfoResult;
import org.uppower.project.cashiermanagesystem.model.vo.AdvertisingVo;
import org.uppower.project.cashiermanagesystem.utils.AttachmentUtils;

import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

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
 * @date ：Created in 2019/10/21 6:56 下午
 * @description：
 * @modified By：
 * @version:
 */
@Service
public class AdvertisingService {

    @Autowired
    private AdvertisingMapper advertisingMapper;

    @Autowired
    private AttachmentUtils attachmentUtils;


    public Response<AdvertisingResult> index(Integer id) {
        AdvertisingEntity entity = advertisingMapper.selectById(id);
        AdvertisingResult result = null;
        if (entity != null) {
            AdvertisingResult temporary = DataUtil.convert(entity, AdvertisingResult.class);
            temporary.setFile(attachmentUtils.getFileInfoResult(entity.getStoreName()));
            result = temporary;
        }
        return Response.success(result);
    }

    public Response store(AdvertisingVo vo) {
        int success = advertisingMapper.insertVo(vo);
        return success == 1 ? Response.success() : Response.fail("失败,请稍后重试");
    }

    @Transactional(rollbackFor = Exception.class)
    public Response update(Integer id, AdvertisingVo vo) {
        int success = advertisingMapper.updateByVo(id, vo);
        if (success != 1) {
            throw new ServerException("系统错误,更新失败");
        }
        return Response.success();
    }

    @Transactional(rollbackFor = Exception.class)
    public Response deleted(Integer id) {
        int success = advertisingMapper.deleteById(id);
        if (success != 1) {
            throw new ServerException("系统错误,删除失败");
        }
        return Response.success();
    }


    public ResponsePage<AdvertisingResult> list(Integer pn, Integer status) {
        Page page = new Page(pn, CashierManageSystemApplication.PAGESIZE);
        IPage<AdvertisingEntity> advertisingEntitys = advertisingMapper.selectListByStatus(page, status);
        List<AdvertisingResult> results = null;
        if (advertisingEntitys.getRecords() != null && !advertisingEntitys.getRecords().isEmpty()) {
            results = advertisingEntitys.getRecords().stream().map(p -> {
                AdvertisingResult convert = DataUtil.convert(p, AdvertisingResult.class);
                convert.setFile(attachmentUtils.getFileInfoResult(p.getStoreName()));
                return convert;
            }).collect(Collectors.toList());
        }
        return ResponsePage.success(results, advertisingEntitys.getPages(), advertisingEntitys.getTotal());
    }

    public ResponsePage<FileInfoResult> show() {
        List<AdvertisingEntity> entities = advertisingMapper.show();
        List<FileInfoResult> results = null;
        if (entities != null && !entities.isEmpty()) {
            results = entities.stream().map(p -> attachmentUtils.getFileInfoResult(p.getStoreName())).collect(Collectors.toList());
        }
        return ResponsePage.success(results);
    }
}
