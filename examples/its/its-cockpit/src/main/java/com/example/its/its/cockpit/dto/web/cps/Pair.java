package com.example.its.its.cockpit.dto.web.cps;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Pair<T> {
    private String key;
    private T value;
    private String extra;

    public Pair() { }

    public Pair(String key, T value) {
        this.key = key;
        this.value = value;
    }

    public Pair(String key, T value, String extra) {
        this.key = key;
        this.value = value;
        this.extra = extra;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }
}
