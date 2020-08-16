package com.batis.application.utils.concurrent;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;
import java.util.function.Function;

class AsyncExecuteTest {
    static final AsyncExecute classUnderTest = new AsyncExecute();

    private static Function<Integer, Integer> testFunction;

    @DisplayName("Initialization")
    @BeforeAll
    static void setUp() {
        testFunction = (num) -> {
            try {
                TimeUnit.MILLISECONDS.sleep(num);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return num;
        };
    }

    @Test
    void function() {

    }

    @Test
    void batchFunction() {
    }

    @Test
    void mapFuture() {
    }

    @Test
    void mapFunction() {
    }

    @Test
    void join() {
    }
}