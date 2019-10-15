package org.uppower.project.cashiermanagesystem.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.uppower.project.cashiermanagesystem.exceptions.ServerException;

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
 * @date ：Created in 2019/10/15 7:54 下午
 * @description：
 * @modified By：
 * @version:
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum WeChatErrcodeEnum {

    SUCCESS(0, "成功"),
    BUSY(-1, "系统繁忙，请稍后重试"),
    INVALID(40029, "code无效,请确保数据正确"),
    OFENERR(45011, "请求过于频繁,请稍后重试");


    private Integer code;

    private String info;

    public static Integer getCodeByMsg(String name) {
        WeChatErrcodeEnum[] values = WeChatErrcodeEnum.values();
        for (WeChatErrcodeEnum value : values) {
            if (value.getInfo().equals(name)) {
                return value.code;
            }
        }
        throw new ServerException("内部错误！");
    }

    public static String getMsgByCode(Integer code) {
        WeChatErrcodeEnum[] values = WeChatErrcodeEnum.values();
        for (WeChatErrcodeEnum value : values) {
            if (value.getCode() - code == 0) {
                return value.info;
            }
        }
        throw new ServerException("内部错误！");
    }

}
