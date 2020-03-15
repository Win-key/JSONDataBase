package com.winkey.api;

import com.winkey.exception.JSONDataBaseException;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Hashtable;
import java.util.Map;

/**
 * @author Venkatesh Rajendran
 * @since  13-03-2020
 * */
class JSONDataBaseImpl implements JsonDataBase {

	private Map<String, JSONValueObject> jsonStorage;
	private File storageFile;

	JsonDataBase initDataBase(String filePath) throws JSONDataBaseException {
		ObjectInputStream ois = null;
		FileInputStream fis = null;
		try {

		    if(filePath==null || filePath.equals(""))
                filePath = System.getProperty("user.home");

            if(!Files.exists(Paths.get(filePath)))
                throw new JSONDataBaseException("File path does not exist.");

			storageFile = new File(filePath.trim()+"/JSONFileDB.ser");

			if(!storageFile.exists()) {
                if(!storageFile.createNewFile())
                    throw new JSONDataBaseException("Could not create the file in the specified location.");
                jsonStorage = new Hashtable<String, JSONValueObject>();
			}else {
				fis = new FileInputStream(storageFile);
				ois = new ObjectInputStream(fis);
				jsonStorage = (Hashtable<String, JSONValueObject>)ois.readObject();
			}
		}catch (Exception e){
            throw new JSONDataBaseException(e.getMessage());
        }finally {
			try{
			    if(ois != null) {
                    ois.close();
                }
			}
			catch(Exception e) {
                throw new JSONDataBaseException(e.getMessage());
            }
		}
		return this;
	}

	@Override
	public boolean create(String key, String value) throws JSONDataBaseException {
		return create ( key, value, 0);
	}

	@Override
	public boolean create (String key, String value, long timeToLiveMillis) throws JSONDataBaseException{
		ObjectOutputStream oos = null;
		FileOutputStream fos = null;

		if(JSONUtility.isFileSizeExceeds(storageFile))
			throw new NullPointerException("Storage Space Running Out. limited to 1GB");

		if(key == null || key.length() == 0)
			throw new NullPointerException("Key should not be null or empty");
		if(!JSONUtility.isAllowedKeySize(key))
			throw new JSONDataBaseException("Key should not be more than 32 characters");

		if(value == null || value.length() == 0)
			throw new NullPointerException("Value should not be null or empty");
		if(!JSONUtility.isAllowedValueSize(value))
			throw new JSONDataBaseException("Value should not be more than 16KB");

		try {
			JSONObject json = new JSONObject(value);
			json = null;
		}catch (JSONException e) {
			throw new JSONDataBaseException(e.getMessage());
		}

		try {
			timeToLiveMillis = timeToLiveMillis <= 0 ? timeToLiveMillis : timeToLiveMillis+System.currentTimeMillis();
			if(jsonStorage.containsKey(key) ){
				throw new JSONDataBaseException("Key already exist in Database.");
			}
			jsonStorage.put(key, new JSONValueObject(value,timeToLiveMillis));
			fos = new FileOutputStream(storageFile);
			oos = new ObjectOutputStream(fos);
			oos.writeObject(jsonStorage);
		}catch (Exception e){
			throw new JSONDataBaseException(e.getMessage());
		}finally {
			try{
				if(oos != null) {
					oos.close();
				}
			}
			catch(Exception e) {
				throw new JSONDataBaseException(e.getMessage());
			}
		}
		return true;
	}

	@Override
	public String read(String key) throws JSONDataBaseException{
		if(key == null)
			throw new NullPointerException("Key should not be null");
		JSONValueObject jsonVO = jsonStorage.get(key);
		if(jsonVO == null)
			return null;

		long timeToLive = jsonVO.getTimeToLive();
		if(timeToLive <= 0){
			return jsonVO.getJsonValue();
		}else if(!JSONUtility.isJsonExpired(timeToLive))
			return jsonVO.getJsonValue();
		else
			throw new JSONDataBaseException("Json exist with the key is expired.");
	}

	@Override
	public boolean delete(String key) throws JSONDataBaseException{
		ObjectOutputStream oos = null;
		FileOutputStream fos = null;
        if(key == null)
            throw new NullPointerException("Key should not be null");
        try{
		if(jsonStorage.remove(key) != null) {
			fos = new FileOutputStream(storageFile);
			oos = new ObjectOutputStream(fos);
			oos.writeObject(jsonStorage);
			return true;
		}else{
			return false;
		}
        }catch (Exception e){
        	throw new JSONDataBaseException(e.getMessage());
		}finally {
			try{
				if(oos != null) {
					oos.close();
				}
			}
			catch(Exception e) {
				throw new JSONDataBaseException(e.getMessage());
			}
		}

	}

}