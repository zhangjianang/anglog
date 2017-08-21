package com.ang.anglog.tianyc.randomCheck;

import java.util.HashSet;
import java.util.Map;

/**
 * Created by adimn on 2017/6/16.
 */
public class CheckData {

    private static final HashSet<String> companyBasicSet=new HashSet<String>(){{
        add("name");add("companyId");
    }};

    private void compareMapValue(Map<String,Object> localMap, Map<String,Object> apiMap){
        if(localMap!=null&& apiMap!=null) {
            for (Map.Entry<String, Object> entry : localMap.entrySet()) {
                String key=entry.getKey();
                if(companyBasicSet.contains(key)){

                }
            }
        }
    }

    private void compareBasic(Map<String,Object> local,String api){

    }


}
