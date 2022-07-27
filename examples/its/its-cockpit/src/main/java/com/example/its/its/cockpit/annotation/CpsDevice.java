package com.example.its.its.cockpit.annotation;


import com.example.its.its.cockpit.model.monitor.Cps;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface CpsDevice {
    Cps value();
}
