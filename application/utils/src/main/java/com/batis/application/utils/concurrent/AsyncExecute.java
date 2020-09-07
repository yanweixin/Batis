package com.batis.application.utils.concurrent;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Note:
 * 1. {@link Map#forEach(BiConsumer)} is being processed sequentially
 * 2. {@link HashMap} is not thread safe, do not use {@link Collection#parallelStream()} to add element to it. You can
 * use {@link java.util.concurrent.ConcurrentHashMap} or use {@link Collectors#toMap(Function, Function)} to add element in
 * parallel.
 */

@Component
public class AsyncExecute {
    /**
     * @param function a one-parameter function be executed asynchronously
     * @param t        parameter for the function
     * @param <T>      type of input
     * @param <R>      type of result
     * @return
     */
    @Async("async")
    public <T, R> CompletableFuture<R> function(Function<? super T, ? extends R> function, T t) {
        Objects.requireNonNull(function);
        return CompletableFuture.completedFuture(function.apply(t));
    }

    @Async("async")
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
    @Async("async")
    public <T, R> CompletableFuture<List<CompletableFuture<? extends R>>> batchFunction(List<Function<? super T, ? extends R>> functions, T t) {
        if (functions.stream().anyMatch(Objects::isNull)) {
            throw new NullPointerException();
        }
        return CompletableFuture.completedFuture(functions.stream().map(it -> function(it, t)).collect(Collectors.toList()));
    }

    /**
     * Usage has been deprecated, use {@link #mapFunction} instead.
     *
     * @param stringFunctionMap
     * @param t
     * @param <T>
     * @param <R>
     * @return
     */
    @Deprecated
    public <T, R> Map<String, R> mapFuture(Map<String, Function<T, R>> stringFunctionMap, T t) {
        final Map<String, CompletableFuture<R>> futureMap = new HashMap<>();
        final Map<String, R> dataMap = new HashMap<>();
        stringFunctionMap.forEach((key, value) -> futureMap.put(key, function(value, t))); // forEach cannot be paralleled
        CompletableFuture.allOf(futureMap.values().toArray(new CompletableFuture<?>[0])).thenAccept(
                (justVoid) -> futureMap.forEach((key, value) -> dataMap.put(key, value.join()))
        );
        return dataMap;
    }

    /**
     * Get a map of string and one-parameter function , return a map of string and result of the function.
     *
     * @param functionMap
     * @param t
     * @param <T>
     * @param <R>
     * @return
     */
    public <T, R> Map<String, Optional<R>> mapFunction(Map<String, Function<T, R>> functionMap, T t) {
//        final Map<String, R> dataMap = new ConcurrentHashMap<>();
//        functionMap.entrySet().parallelStream().forEach(entry -> dataMap.put(entry.getKey(), entry.getValue().apply(t)));
//        return dataMap;
        return functionMap.entrySet().parallelStream()
                .collect(Collectors.toMap(Map.Entry::getKey, it -> Optional.ofNullable(it.getValue().apply(t))));
    }

    public <T, U, R> Map<String, Optional<R>> mapBiFunction(Map<String, BiFunction<T, U, R>> biFunctionMap, T t, U u) {
//        final Map<String, R> dataMap = new ConcurrentHashMap<>();
//        biFunctionMap.entrySet().parallelStream().forEach(entry -> dataMap.put(entry.getKey(), entry.getValue().apply(t, u)));
//        return dataMap;
        return biFunctionMap.entrySet().parallelStream()
                .collect(Collectors.toMap(Map.Entry::getKey, it -> Optional.ofNullable(it.getValue().apply(t, u))));
    }

    public <T> void join(List<CompletableFuture<T>> futures) {
        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();
    }

}
