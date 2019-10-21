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
 * @date ：Created in 2019/10/21 6:46 下午
 * @description：
 * @modified By：
 * @version:
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum DisplayEnum {

    SHOW(0, "显示", "#228B22"),
    BLANK(1, "不显示", "#FF0000");


    private Integer code;

    private String name;

    private String color;

    public static Integer getCodeByMsg(String name) {
        DisplayEnum[] values = DisplayEnum.values();
        for (DisplayEnum value : values) {
            if (value.getName().equals(name)) {
                return value.code;
            }
        }
        throw new ServerException("内部错误！");
    }

    public static String getMsgByCode(Integer code) {
        DisplayEnum[] values = DisplayEnum.values();
        for (DisplayEnum value : values) {
            if (value.getCode() - code == 0) {
                return value.name;
            }
        }
        throw new ServerException("内部错误！");
    }

    public static DisplayEnum getByCode(Integer code) {
        DisplayEnum[] values = DisplayEnum.values();
        for (DisplayEnum value : values) {
            if (value.getCode() - code == 0) {
                return value;
            }
        }
        throw new ServerException("内部错误！");
    }
}
