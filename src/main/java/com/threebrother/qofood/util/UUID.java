package com.threebrother.qofood.util;

import java.util.Random;

public abstract class UUID {

    static Random r = new Random();

    /**
     * 根据一个范围，生成一个随机的整数
     *
     * @param min 最小值（包括）
     * @param max 最大值（包括）
     * @return 随机数
     */
    public static int random(int min, int max) {
        return r.nextInt(max - min + 1) + min;
    }
}
