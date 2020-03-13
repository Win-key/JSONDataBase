package com.winkey.api;

import com.winkey.exception.JSONDataBaseException;

/**
 * @author Venkatesh Rajendran
 * @since  13-03-2020
 * */
public interface JsonDataBase {

    /**
     * @apiNote Creates DataBase instance with file at root path
     * */
    static JsonDataBase getInstance() throws JSONDataBaseException {
        return new JSONDataBaseImpl().initDataBase(null);
    }

    /**
     * @apiNote Creates DataBase instance with file at given path
     * */
    static JsonDataBase getInstance(String filePath) throws  JSONDataBaseException{
        return new JSONDataBaseImpl().initDataBase(filePath);
    }

    /**
     * @apiNote Creates Json entry in the file with Time to live
     * */
    boolean create(String key, String value, long timeToLiveMillis) throws JSONDataBaseException;

    /**
     * @apiNote Creates Json entry in the file with Time to live = 0 (which means TimeToLive is not assigned)
     * */
    boolean create(String key, String value) throws JSONDataBaseException;

    /**
     * @apiNote Returns data based on Key and null if not found
     * */
    String read(String key) throws JSONDataBaseException;

    /**
     * @apiNote  Removes the key (and its corresponding value) from this
     *           database. This method does nothing if the key is not available.
     * */
    boolean delete(String key) throws JSONDataBaseException ;

}
