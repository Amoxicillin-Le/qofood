package com.threebrother.qofood.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class OrderIdUtil extends Thread{

    private static String date;

    private static long orderNum = 0l;

    /**
     * 生成订单编号
     * @author zhaoxiaolezi
     * @date 2018/6/5 14:26
     */
    public static synchronized String getOrderNo() {
        String str = new SimpleDateFormat("yyMMddHHmm").format(new Date());

        if(date == null|| !date.equals(str)){
            date = str;
            orderNum  = 0l;
        }

        orderNum ++;
        long orderNo = Long.parseLong((date)) * 10000;
        orderNo += orderNum;;
        return orderNo + getFourBitRandomNumberStr();
    }

    /**
     * 获取四位随机数字符串
     * @author zhaoxiaolezi
     * @date 2018/6/5 14:25
     */
    public static String getFourBitRandomNumberStr(){
        Random d = new Random();

        String str = "";

        for(int i=0; i<4; i++){

            int num = d.nextInt(10);
            str += num + "";
        }
        return str;
    }


    public static void main(String[] args) throws InterruptedException {
            System.out.println(OrderIdUtil.getOrderNo());
    }



}
