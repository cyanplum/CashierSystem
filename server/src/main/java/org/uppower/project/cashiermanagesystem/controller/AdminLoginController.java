package org.uppower.project.cashiermanagesystem.controller;

import cn.windyrjc.security.web.AuthenticationService;
import cn.windyrjc.security.web.annotation.AuthMapping;
import cn.windyrjc.security.web.beans.UserDetails;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.uppower.project.cashiermanagesystem.dao.AdminMapper;
import org.uppower.project.cashiermanagesystem.exceptions.ServerException;
import org.uppower.project.cashiermanagesystem.model.AdminLoginVo;
import org.uppower.project.cashiermanagesystem.model.UserInfo;
import org.uppower.project.cashiermanagesystem.model.entity.AdminEntity;
import java.util.Arrays;
import java.util.List;

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
 * @date ：Created in 2019/11/2 9:52 上午
 * @description：
 * @modified By：
 * @version:
 */
@Service
@AuthMapping("/admin/auth/form")
public class AdminLoginController implements AuthenticationService<AdminLoginVo> {

    @Autowired
    private AdminMapper adminMapper;

    @NotNull
    @Override
    public UserDetails loadUserByCredential(AdminLoginVo adminLoginVo, @NotNull PasswordEncoder passwordEncoder) {
        String username = adminLoginVo.getUsername();
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("username",username);
        AdminEntity adminEntity = null;
        if ((adminEntity=adminMapper.selectOne(wrapper))==null){
            throw new ServerException("该用户不存在");
        }
        if (!passwordEncoder.matches(adminLoginVo.getPassword(),adminEntity.getPassword())){
            throw new ServerException("密码错误");
        }

        List<String> roles = Arrays.asList("ADMIN", "BOSS");
        List<String> permission = Arrays.asList("VIEW");

        UserInfo userInfo = new UserInfo();
        userInfo.setOpenId("1");
        userInfo.setUserId(1);
        userInfo.setSessionKey("1");
        userInfo.setPhone("1");
        userInfo.setNickname("admin");
        userInfo.setRoles(roles);
        userInfo.setPermissions(permission);

        UserDetails userDetails = new UserDetails();
        userDetails.setPermissions(permission);
        userDetails.setUserDetail(userInfo);
        userDetails.setRoles(roles);
        userDetails.setId(userInfo.getOpenId());
        return userDetails;

    }
}
