package com.example.its.its.cockpit.util;

import java.util.concurrent.Callable;
import java.util.function.Function;

public class Utils {

    public static <T> T runCatching(Callable<T> callable, T orElse) {
        try {
            return callable.call();
        } catch (Exception e) {
            return orElse;
        }
    }

    public static <T> T runCatching(Callable<T> callable) {
        return runCatching(callable, null);
    }

    public static <T, R> R runCatching(Function<T, R> function, T arg, R orElse) {
        try {
            return function.apply(arg);
        } catch (Exception e) {
            return orElse;
        }
    }

    public static <T, R> R runCatching(Function<T, R> function, T arg) {
        return runCatching(function, arg, null);
    }

}
