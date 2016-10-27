package com.kevinearls;

import java.util.Dictionary;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

/**
 * Created by kearls on 17/02/16.
 */
public class DictionaryTest {
    public static void main(String args[] ) {
        Map<String, String> myMap = new HashMap<>();
        myMap.put("1", "one");
        myMap.put("2", "two");
        myMap.put("3", "three");

        //Hashtable<String, String> myHashTable = (Hashtable<String, String>) myMap;
        Dictionary<String, String> myHashTable = new Hashtable<>();
        for (String key : myMap.keySet()){
            myHashTable.put(key, myMap.get(key));
        }
        System.out.println(myHashTable);

    }
}
