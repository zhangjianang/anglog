package com.ang.anglog.tianyc.tycDao;

import java.io.InputStream;
import java.util.Properties;

/**
 * Created by adimn on 2017/5/27.
 */
public class ReadIndustryTxt {
    public static Properties p = new Properties();
    static{
        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("categoryCodeNew.properties");
        try {
            p.load(is);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
     //  System.out.println(getIndustryModelMap());
        System.out.println(ReadIndustryTxt.p.get("514"));
    }
}
