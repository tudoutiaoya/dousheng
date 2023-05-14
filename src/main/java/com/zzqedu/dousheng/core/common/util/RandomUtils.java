package com.zzqedu.dousheng.core.common.util;

import java.util.Random;

public class RandomUtils {
    
    /**
     * 生成介于指定最小值和最大值之间的随机整数
     * 
     * @param min 最小值（包括）
     * @param max 最大值（包括）
     * @return 随机整数
     */
    public static int generateRandomInt(int min, int max) {
        if (min > max) {
            throw new IllegalArgumentException("Min cannot be greater than Max.");
        }
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }

    /**
     * 头像随机1-9，都存储在七牛云上
     * @return avatar随机整数
     */
    public static int generateAvatarNumber() {
        return generateRandomInt(1, 9);
    }

}
