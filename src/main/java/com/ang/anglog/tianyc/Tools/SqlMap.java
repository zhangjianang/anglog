package com.ang.anglog.tianyc.Tools;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by adimn on 2017/6/20.
 */
public class SqlMap {
    private Map<String,String> map = new HashMap<String, String>();
    private static SqlMap sqlMap = new SqlMap();
    private SqlMap(){
    }

    public synchronized static Map<String,String> getSqlMap(){
        return sqlMap.map;
    }
    public synchronized static Map<String,String> addValue(String name,String json){
        sqlMap.map.put(name,json);
        if(getSize()>100){
            Map<String,String> newMap =new HashMap<String, String>();
            newMap.putAll(sqlMap.map);
            truncate();
            return newMap;
        }
        return null;
    }

    public  static int getSize(){
        return sqlMap.map.size();
    }

    public  static void truncate(){
        sqlMap.map.clear();
    }



}
