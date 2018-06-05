package com.threebrother.qofood.model.PO;

import java.io.Serializable;
import java.util.List;

public class GoodsPO implements Serializable {

    String userOpenId;

    List<GoodsModel> goodsList;

    Integer totalNum;


    public class GoodsModel implements Serializable{
        Integer goodsId;

        Integer goodsNum;

        public GoodsModel() {
        }

        public Integer getGoodsId() {
            return goodsId;
        }

        public void setGoodsId(Integer goodsId) {
            this.goodsId = goodsId;
        }

        public Integer getGoodsNum() {
            return goodsNum;
        }

        public void setGoodsNum(Integer goodsNum) {
            this.goodsNum = goodsNum;
        }
    }

    public GoodsPO() {
    }

    public String getUserOpenId() {
        return userOpenId;
    }

    public void setUserOpenId(String userOpenId) {
        this.userOpenId = userOpenId;
    }

    public List<GoodsModel> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List<GoodsModel> goodsList) {
        this.goodsList = goodsList;
    }

    public Integer getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(Integer totalNum) {
        this.totalNum = totalNum;
    }
}
