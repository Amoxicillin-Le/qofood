package com.threebrother.qofood.model.Enum;

import org.apache.commons.lang3.EnumUtils;

import java.util.List;

public enum OrderStatusEnum {


    NOT_PAYMENT(1, "未付款"),

    NOT_DELIVER_GOODS(2, "未付款"),

    DELIVER_GOODS(3, "已发货");


    int value;

    String name;

    OrderStatusEnum(int value, String name) {
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

    public static List<OrderStatusEnum> allState = EnumUtils.getEnumList(OrderStatusEnum.class);

    public static OrderStatusEnum getEnumByValue(int value) {
        for (OrderStatusEnum anEnum : allState) {
            if (anEnum.getValue() == value ) {
                return anEnum;
            }
        }
        return null;
    }

    public static String getNameByValue(int value){
        OrderStatusEnum orderStatusEnum = getEnumByValue(value);
        if(null != orderStatusEnum){
            return orderStatusEnum.getName();
        }

        return PreferentialStrategyTypeEnum.NO_PREFERENTIAL_TREATMENT.getName();
    }

}
