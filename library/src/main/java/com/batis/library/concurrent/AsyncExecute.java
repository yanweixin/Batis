package com.batis.library.concurrent;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class AsyncExecute {
    /**
     * @param function a one-parameter function be executed asynchronously
     * @param t        parameter for the function
     * @param <T>      type of input
     * @param <R>      type of result
     * @return
     */
    @Async
    public <T, R> CompletableFuture<R> function(Function<? super T, ? extends R> function, T t) {
        Objects.requireNonNull(function);
        return CompletableFuture.completedFuture(function.apply(t));
    }

    @Async
    public <T, U, R> CompletableFuture<R> biFunction(BiFunction<? super T, ? super U, ? extends R> biFunction, T t, U u) {
        Objects.requireNonNull(biFunction);
        return CompletableFuture.completedFuture(biFunction.apply(t, u));
    }

    /**
     * @param functions a list of one-parameter functions to be executed asynchronously
     * @param t         parameter for all functions
     * @param <T>       type of input
     * @param <R>       type of result
     * @return return a list of future objects to be joined
     */
    @Async
    public <T, R> CompletableFuture<List<CompletableFuture<? extends R>>> batchFunction(List<Function<? super T, ? extends R>> functions, T t) {
        if (functions.stream().anyMatch(Objects::isNull)) {
            throw new NullPointerException();
        }
        return CompletableFuture.completedFuture(functions.stream().map(it -> function(it, t)).collect(Collectors.toList()));
    }

    public <T> void join(List<CompletableFuture<T>> futures) {
        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();
    }

}
