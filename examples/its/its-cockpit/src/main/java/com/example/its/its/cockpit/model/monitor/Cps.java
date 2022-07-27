package com.example.its.its.cockpit.model.monitor;

import com.example.its.its.cockpit.model.cps.its.ITS;
import com.example.its.its.cockpit.model.cps.roomba.Roomba;

public enum Cps {
    ITS, ROOMBA;

    public Class<?> getType() {
    	if (this == ITS) {
    		return ITS.class;
    	}  else if (this == ROOMBA) {
    		return Roomba.class;
    	}
        return null;
    }
}
