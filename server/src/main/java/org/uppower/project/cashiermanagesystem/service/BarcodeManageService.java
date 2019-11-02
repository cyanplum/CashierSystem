package org.uppower.project.cashiermanagesystem.service;

import cn.windyrjc.utils.copy.DataUtil;
import cn.windyrjc.utils.response.Response;
import cn.windyrjc.utils.response.ResponsePage;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.uppower.project.cashiermanagesystem.CashierManageSystemApplication;
import org.uppower.project.cashiermanagesystem.dao.BarcodeMapper;
import org.uppower.project.cashiermanagesystem.exceptions.ServerException;
import org.uppower.project.cashiermanagesystem.model.entity.BarcodeEntity;
import org.uppower.project.cashiermanagesystem.model.result.BarcodeResult;
import org.uppower.project.cashiermanagesystem.model.vo.BarcodeVo;
import org.uppower.project.cashiermanagesystem.utils.AttachmentUtils;
import org.uppower.project.cashiermanagesystem.utils.MoneyManageUtil;

import java.util.List;
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
 * @date ：Created in 2019/11/2 8:45 上午
 * @description：
 * @modified By：
 * @version:
 */
@Service
public class BarcodeManageService {

    @Autowired
    private BarcodeMapper barcodeMapper;

    @Autowired
    private AttachmentUtils attachmentUtils;

    @Transactional(rollbackFor = Exception.class)
    public Response store(BarcodeVo vo) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("code", vo.getCode());
        BarcodeEntity barcodeEntity = barcodeMapper.selectOne(wrapper);
        if (barcodeEntity != null) {
            throw new ServerException("该条形码已经存在,无法再次添加");
        }
        BarcodeEntity entity = new BarcodeEntity();
        entity.setCode(vo.getCode()).setName(vo.getName()).setPrice(MoneyManageUtil.yuanToFen(vo.getPrice())).setStoreName(vo.getStoreName());
        int success = barcodeMapper.insert(entity);
        return success == 1 ? Response.success() : Response.fail("插入失败，请联系相关人员");
    }

    @Transactional(rollbackFor = Exception.class)
    public Response deleted(Integer id) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("id", id);
        int success = barcodeMapper.delete(wrapper);
        return success == 1 ? Response.success() : Response.fail("删除失败，请联系相关人员");
    }


    public Response update(Integer id, BarcodeVo vo) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("code", vo.getCode());
        BarcodeEntity barcodeEntity = barcodeMapper.selectOne(wrapper);
        if (barcodeEntity != null) {
            throw new ServerException("该条形码已经存在,无法再次添加");
        }
        BarcodeEntity entity = new BarcodeEntity();
        entity.setCode(vo.getCode()).setName(vo.getName()).setPrice(MoneyManageUtil.yuanToFen(vo.getPrice())).setStoreName(vo.getStoreName());
        entity.setId(id);
        int success = barcodeMapper.updateById(entity);
        return success == 1 ? Response.success() : Response.fail("修改失败，请联系相关人员");
    }


    public ResponsePage<BarcodeResult> list(String code, String name, Integer pn) {
        Page page = new Page(pn, CashierManageSystemApplication.PAGESIZE);
        IPage<BarcodeEntity> entities = barcodeMapper.selectByCondition(page, code, name);
        List<BarcodeResult> barcodeResult = entities.getRecords().stream().map(barcodeEntity -> {
            BarcodeResult result = DataUtil.convert(barcodeEntity, BarcodeResult.class);
            result.setPrice(MoneyManageUtil.fenToYuan(barcodeEntity.getPrice()));
            String storeName;
            if ((storeName = barcodeEntity.getStoreName()) != null) {
                result.setFile(attachmentUtils.getFileInfoResult(storeName));
            }
            return result;
        }).collect(Collectors.toList());
        return ResponsePage.success(barcodeResult, entities.getPages(), entities.getTotal());
    }

    public Response<BarcodeResult> index(String code) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("code", code);
        BarcodeEntity barcodeEntity = null;
        if ((barcodeEntity = barcodeMapper.selectOne(wrapper)) == null) {
            throw new ServerException("无该条形码信息");
        }
        BarcodeResult barcodeResult = DataUtil.convert(barcodeEntity, BarcodeResult.class);
        barcodeResult.setPrice(MoneyManageUtil.fenToYuan(barcodeEntity.getPrice()));
        String storeName;
        if ((storeName = barcodeEntity.getStoreName()) != null) {
            barcodeResult.setFile(attachmentUtils.getFileInfoResult(storeName));
        }
        return Response.success(barcodeResult);
    }
}
