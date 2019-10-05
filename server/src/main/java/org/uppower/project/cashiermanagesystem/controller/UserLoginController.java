package org.uppower.project.cashiermanagesystem.controller;

import cn.windyrjc.security.web.AuthenticationService;
import cn.windyrjc.security.web.annotation.AuthMapping;
import cn.windyrjc.security.web.beans.UserDetails;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.uppower.project.cashiermanagesystem.model.UserInfo;
import org.uppower.project.cashiermanagesystem.model.UserLoginVO;
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
 * @date ：Created in 2019/10/5 4:41 下午
 * @description：
 * @modified By：
 * @version:
 */
@Service
@AuthMapping("/auth/form")
public class UserLoginController implements AuthenticationService<UserLoginVO> {
    @NotNull
    @Override
    public UserDetails loadUserByCredential(UserLoginVO userLoginVO, @NotNull PasswordEncoder passwordEncoder) {
        List<String> roles = Arrays.asList("ADMIN", "BOSS");
        List<String> permission = Arrays.asList("VIEW");
        UserInfo userInfo = new UserInfo();
        userInfo.setName("Tu");
        userInfo.setOpenId("asdasdadsa21412412");
        userInfo.setPermissions(permission);
        userInfo.setPhone("21214124");
        userInfo.setRoles(roles);
        UserDetails userDetails = new UserDetails();
        userDetails.setPermissions(permission);
        userDetails.setUserDetail(userInfo);
        userDetails.setRoles(roles);
        userDetails.setId("1");
        return userDetails;
    }
}
