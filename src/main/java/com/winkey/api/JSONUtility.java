package com.winkey.api;

import java.io.File;

/**
 * @author Venkatesh Rajendran
 * @since  13-03-2020
 * */
class JSONUtility {

    /**
     * @return true if Json Expired
     * */
    static boolean isJsonExpired(long timeToLive){
        return timeToLive <= System.currentTimeMillis();
    }

    /**
     * @implNote Key limited to 32 characters
     * */
    static boolean isAllowedKeySize(String key){
        return key.length() < 32;
    }

    /**
     * @implNote Json value limited to 16KB
     * */
    static boolean isAllowedValueSize(String value){
        return value.length() < 16 * 1000;
    }

    /**
    * @implNote File size limited to 1 GB
    * */
    static boolean isFileSizeExceeds(File file){
        return file.length()/(1024 * 1024 * 1024) < 1;
    }

}
