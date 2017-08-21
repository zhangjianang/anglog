package com.ang.anglog.tianyc.Tools;

/**
 * Created by adimn on 2017/6/23.
 */
public class StringUtil {
    public static boolean isNotEmpty(String companyId) {
        if(companyId==null || "".equals(companyId)){
            return false;
        } else{
            return true;
        }
    }
    public static boolean isEmpty(String companyId) {
        if(companyId==null || "".equals(companyId.trim())){
            return true;
        } else{
            return false;
        }
    }

}
