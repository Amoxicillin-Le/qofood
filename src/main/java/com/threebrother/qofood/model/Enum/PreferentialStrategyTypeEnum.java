package com.threebrother.qofood.model.Enum;

import org.apache.commons.lang3.EnumUtils;

import java.util.List;

public enum PreferentialStrategyTypeEnum {

    NO_PREFERENTIAL_TREATMENT(0, "无优惠"),

    BUY_UP_AND_REDUCE(1, "买满立减"),

    SINGLE_PRICE_DISCOUNT(2, "单价优惠");

    int value;

    String name;

    PreferentialStrategyTypeEnum(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static List<PreferentialStrategyTypeEnum> allState = EnumUtils.getEnumList(PreferentialStrategyTypeEnum.class);

    public static PreferentialStrategyTypeEnum getEnumByValue(int value) {
        for (PreferentialStrategyTypeEnum anEnum : allState) {
            if (anEnum.getValue() == value ) {
                return anEnum;
            }
        }
        return null;
    }

    public static String getNameByValue(int value){
        PreferentialStrategyTypeEnum preferentialStrategyTypeEnum = getEnumByValue(value);
        if(null != preferentialStrategyTypeEnum){
            return preferentialStrategyTypeEnum.getName();
        }

        return PreferentialStrategyTypeEnum.NO_PREFERENTIAL_TREATMENT.getName();
    }
}
