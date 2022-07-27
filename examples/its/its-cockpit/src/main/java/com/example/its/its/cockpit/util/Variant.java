package com.example.its.its.cockpit.util;

import java.util.function.Function;

public class Variant<A, B> {

    private A a;
    private B b;

    public static <A, B> Variant<A, B> of(A a, B b) {
        //if (a != null && b != null || a == b) {
        if (!(a != null ^ b != null)) {
            throw new IllegalArgumentException("Exectly one argument must be not null for a variant");
        }
        Variant<A, B> v = new Variant<>();
        v.a = a;
        v.b = b;
        return v;
    }

    public A getA() {
        return a;
    }

    public B getB() {
        return b;
    }

    public boolean isA() {
        return this.a != null;
    }

    public boolean isB() {
        return this.b != null;
    }

    public <T> T map(Function<A, T> amap, Function<B, T> bmap) {
        return isA() ? amap.apply(a) : bmap.apply(b);
    }
}
