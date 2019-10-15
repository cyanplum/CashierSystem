package org.uppower.project.cashiermanagesystem.utils;

import java.text.DecimalFormat;

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
 * @date ：Created in 2019/10/15 4:46 下午
 * @description：
 * @modified By：
 * @version:
 */
public class MoneyManageUtil {

    private static final double STANDARD = 100.0;

    /**
     * 元转换成分
     *
     * @param money
     * @return
     */
    public static int yuanToFen(double money) {
        if (money < 0) {
            try {
                throw new Exception("金额要大于0");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        int result = (int) (money * STANDARD);
        return result;
    }

    /**
     * 分转元
     *
     * @param money
     * @return
     * @throws Exception
     */
    public static double fenToYuan(int money) {
        if (money < 0) {
            try {
                throw new Exception("金额要大于0");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        double result = money / STANDARD;
        return result;
    }

}
