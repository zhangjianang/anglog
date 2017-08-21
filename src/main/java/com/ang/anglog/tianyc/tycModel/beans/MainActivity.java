package com.ang.anglog.tianyc.tycModel.beans;


import com.ang.anglog.tianyc.Tools.ReadPropertyTool;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


/**
 * Created by adimn on 2017/6/27.
 */
public class MainActivity{

public static void  main(String[] args){
    Gson gson = new Gson();
    java.lang.reflect.Type type = new TypeToken<Status>() {}.getType();
    String json= ReadPropertyTool.getPropertyByParam("result.json","json");
    System.out.println(json);
    Status jsonBean = gson.fromJson(json, type);
    System.out.println(jsonBean.toString());
}

}
