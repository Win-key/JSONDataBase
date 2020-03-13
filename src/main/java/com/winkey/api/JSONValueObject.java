package com.winkey.api;

import java.io.Serializable;

/**
 * @author Venkatesh Rajendran
 * @since  13-03-2020
 * */
class JSONValueObject implements Serializable {

    private static final long SerialVersionUID = 10l;
    private String jsonValue;
    private long timeToLive;

    JSONValueObject(String jsonValue, long timeToLive) {
        this.jsonValue = jsonValue;
        this.timeToLive = timeToLive;
    }

    String getJsonValue() {
        return jsonValue;
    }

    long getTimeToLive() {
        return timeToLive;
    }

}
