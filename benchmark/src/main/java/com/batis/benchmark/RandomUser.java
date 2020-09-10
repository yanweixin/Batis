package com.batis.benchmark;

import com.batis.library.generator.RandomString;

import java.util.concurrent.ThreadLocalRandom;

public final class RandomUser {
    private final RandomString randomString;

    public RandomUser() {
        this.randomString = new RandomString(10, ThreadLocalRandom.current());
    }
}
