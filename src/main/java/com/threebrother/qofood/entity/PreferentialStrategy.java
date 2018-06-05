package com.threebrother.qofood.entity;

import java.io.Serializable;

public class PreferentialStrategy implements Serializable {

    private Integer preferentialStrategyId;

    private Integer preferentialStrategyType;

    public PreferentialStrategy() {
    }

    public Integer getPreferentialStrategyId() {
        return preferentialStrategyId;
    }

    public void setPreferentialStrategyId(Integer preferentialStrategyId) {
        this.preferentialStrategyId = preferentialStrategyId;
    }

    public Integer getPreferentialStrategyType() {
        return preferentialStrategyType;
    }

    public void setPreferentialStrategyType(Integer preferentialStrategyType) {
        this.preferentialStrategyType = preferentialStrategyType;
    }
}
