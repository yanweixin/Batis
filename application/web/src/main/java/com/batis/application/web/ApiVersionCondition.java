package com.batis.application.web;

import com.batis.library.annotation.ApiVersion;
import org.springframework.web.servlet.mvc.condition.RequestCondition;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ApiVersionCondition implements RequestCondition<ApiVersionCondition> {
    private final static Pattern VERSION_PREFIX_PATTERN = Pattern.compile("v(\\d+)/");
    private final int[] versions;
    private final boolean backwardCompatible;

    public ApiVersionCondition(ApiVersion apiVersion) {
        if (apiVersion == null) {
            this.versions = new int[]{1};
            this.backwardCompatible = false;
        } else {
            this.versions = apiVersion.value();
            this.backwardCompatible = apiVersion.backwardCompatible();
        }
    }

    public ApiVersionCondition(int[] versions, boolean backwardCompatible) {
        this.versions = versions;
        this.backwardCompatible = backwardCompatible;
    }

    @Override
    public ApiVersionCondition combine(ApiVersionCondition other) {
//        int[] target = (int[]) Array.newInstance(int[].class, this.versions.length + versions.length);
//        System.arraycopy(this.versions, 0, target, 0, this.versions.length);
//        System.arraycopy(versions, 0, target, this.versions.length, versions.length);
        int[] target = Stream.of(this.versions, other.versions).flatMapToInt(IntStream::of).distinct().toArray();
        return new ApiVersionCondition(target, (this.backwardCompatible && other.backwardCompatible));
    }

    @Override
    public ApiVersionCondition getMatchingCondition(HttpServletRequest request) {
        Matcher matcher = VERSION_PREFIX_PATTERN.matcher(request.getRequestURI());
        if (matcher.find()) {
            Integer version = Integer.valueOf(matcher.group(1));
            if (Arrays.stream(this.versions)
                    .anyMatch(it -> (version.compareTo(it) == 0
                            || this.backwardCompatible && version.compareTo(it) > 0))) {
                return this;
            }
        }
        return null;
    }

    @Override
    public int compareTo(ApiVersionCondition other, HttpServletRequest request) {
        int thisMax = Arrays.stream(this.versions).max().getAsInt();
        int otherMax = Arrays.stream(other.versions).max().getAsInt();
        return otherMax - thisMax;
    }
}
