package org.uppower.project.cashiermanagesystem.model.enums;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.uppower.project.cashiermanagesystem.exceptions.ServerException;

/**
 * create by:
 * *      ____        ___  ___       __          __
 * *    /  _  \     /   |/   |      | |        / /
 * *   | | | |     / /|   /| |     | |  __   / /
 * *  | | | |     / / |__/ | |    | | /  | / /
 * * | |_| |_    / /       | |   | |/   |/ /
 * * \_______|  /_/        |_|  |___/|___/
 *
 * @date 2019/10/18 16:11
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum  DiscountAuthEnum {


    FULL(1,"满减"),
    DISCOUNTS(0,"打折");

    private Integer auth;

    private String name;

    public static Integer getCodeByMsg(String name){
        DiscountAuthEnum[] values = DiscountAuthEnum.values();
        for (DiscountAuthEnum value:values){
            if (value.name.equals(name))
            {
                return value.auth;
            }
        }
        throw new ServerException("内部错误！");
    }

    public static String getMsgByCode(Integer code){
        DiscountAuthEnum[] values = DiscountAuthEnum.values();
        for (DiscountAuthEnum value:values){
            if (value.getAuth()-code==0){
                return value.name;
            }
        }
        throw new ServerException("内部错误！");
    }
}
