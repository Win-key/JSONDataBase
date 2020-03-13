package com.winkey;

import com.winkey.api.JsonDataBase;
import com.winkey.exception.JSONDataBaseException;

public class KickStart {

    public static void main(String[] args) {
        try {
            JsonDataBase dataBase = JsonDataBase.getInstance();
            System.out.println( dataBase.create("VenkateshWithOutTTL", "{name : 'Venkatesh Rajendran', age : 24, company : 'Capgemini'}"));
            System.out.println( dataBase.read("VenkateshWithOutTTL"));
            System.out.println(dataBase.delete("VenkateshWithOutTTL"));

        }catch (JSONDataBaseException e){
            e.printStackTrace();
        }

    }

}
