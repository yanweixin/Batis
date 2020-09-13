package com.batis.application.web;

import org.springframework.web.servlet.mvc.condition.RequestCondition;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.regex.Pattern;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ApiVersionCondition implements RequestCondition<ApiVersionCondition> {
    private final static Pattern VERSION_PREFIX_PATTERN = Pattern.compile("v(\\d+)/");
    private final int[] versions;

    public ApiVersionCondition(int[] versions) {
        this.versions = versions;
    }

    @Override
    public ApiVersionCondition combine(ApiVersionCondition other) {
//        int[] target = (int[]) Array.newInstance(int[].class, this.versions.length + versions.length);
//        System.arraycopy(this.versions, 0, target, 0, this.versions.length);
//        System.arraycopy(versions, 0, target, this.versions.length, versions.length);
        int[] target = Stream.of(this.versions, versions).flatMapToInt(IntStream::of).distinct().toArray();
        return new ApiVersionCondition(target);
    }

    @Override
    public ApiVersionCondition getMatchingCondition(HttpServletRequest request) {
        System.err.println("------------------" + request.getRequestURL());
        return this;
    }

    @Override
    public int compareTo(ApiVersionCondition other, HttpServletRequest request) {
        int thisMax = Arrays.stream(this.versions).max().getAsInt();
        int otherMax = Arrays.stream(other.versions).max().getAsInt();
        return otherMax - thisMax;
    }
}
