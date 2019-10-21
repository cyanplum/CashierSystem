package org.uppower.project.cashiermanagesystem.controller;

import cn.windyrjc.security.web.AuthenticationService;
import cn.windyrjc.security.web.annotation.AuthMapping;
import cn.windyrjc.security.web.beans.UserDetails;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.uppower.project.cashiermanagesystem.exceptions.ServerException;
import org.uppower.project.cashiermanagesystem.model.UserInfo;
import org.uppower.project.cashiermanagesystem.model.UserLoginVO;
import org.uppower.project.cashiermanagesystem.model.enums.WeChatErrcodeEnum;
import org.uppower.project.cashiermanagesystem.utils.AppPropertiesUtil;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
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
@EnableConfigurationProperties(AppPropertiesUtil.class)
@AuthMapping("/auth/form")
public class UserLoginController implements AuthenticationService<UserLoginVO> {

    @Autowired
    private AppPropertiesUtil appPropertiesUtil;

    @NotNull
    @Override
    public UserDetails loadUserByCredential(UserLoginVO userLoginVO, @NotNull PasswordEncoder passwordEncoder) {
        String code = userLoginVO.getCode();
        // 创建Httpclient对象
        CloseableHttpClient httpclient = HttpClients.createDefault();
        String resultString = "";
        CloseableHttpResponse response = null;
        String url = String.format(appPropertiesUtil.getWxLoginUrl(), appPropertiesUtil.getAppId(), appPropertiesUtil.getSecret(), code);
        // 创建uri
        URIBuilder builder = null;
        try {
            builder = new URIBuilder(url);
            URI uri = builder.build();
            // 创建http GET请求
            HttpGet httpGet = new HttpGet(uri);
            // 执行请求
            response = httpclient.execute(httpGet);
            // 判断返回状态是否为200
            if (response.getStatusLine().getStatusCode() == 200) {
                resultString = EntityUtils.toString(response.getEntity(), "UTF-8");
            } else {
                throw new ServerException("获取信息失败");
            }

        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 解析json，拿到需要的数据
        JSONObject jsonObject = (JSONObject) JSONObject.parse(resultString);
        String seesionKey = jsonObject.get("session_key") + "";
        String openid = jsonObject.get("openid") + "";
        Integer errcode = Integer.valueOf(jsonObject.get("errcode") + "");

        //判断是否成功请求微信request接口
        /*if (errcode == null) {
            throw new ServerException("请求失败");
        } else {
            if (errcode - WeChatErrcodeEnum.SUCCESS.getCode() != 0) {
                throw new ServerException(WeChatErrcodeEnum.getMsgByCode(errcode));
            }
        }*/

        System.out.println("session_key==" + seesionKey);
        System.out.println("openid==" + openid);


        List<String> roles = Arrays.asList("ADMIN", "BOSS");
        List<String> permission = Arrays.asList("VIEW");
        UserInfo userInfo = new UserInfo();
        userInfo.setName("Tu");
        userInfo.setOpenId(openid);
        userInfo.setPermissions(permission);
        userInfo.setPhone("21214124");
        userInfo.setSessionKey(seesionKey);
        userInfo.setRoles(roles);
        userInfo.setId(1);
        UserDetails userDetails = new UserDetails();
        userDetails.setPermissions(permission);
        userDetails.setUserDetail(userInfo);
        userDetails.setRoles(roles);
        userDetails.setId("1");
        return userDetails;
    }
}
