package com.batis.library.math;

import java.security.SecureRandom;
import java.util.Random;
import java.util.SplittableRandom;
import java.util.concurrent.ThreadLocalRandom;

public class RandomUtils {

    public static int[] randomArray(long size){
        return new Random().ints(size).toArray();
    }

    public static int[] threadLocalRandomArray(long size){
        return ThreadLocalRandom.current().ints(size).toArray();
    }

    public static int[] splittableRandomArray(long size){
        return new SplittableRandom().ints(size).toArray();
    }

    public static int[] secureRandomArray(long size){
        return new SecureRandom().ints(size).toArray();
    }
}
