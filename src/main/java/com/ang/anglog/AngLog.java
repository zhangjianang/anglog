package com.ang.anglog;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ang on 2017/8/17.
 */
public class AngLog {

    private  static Logger logger = LoggerFactory.getLogger(AngLog.class);

    public static void main(String[] args) throws InterruptedException {
//        for (int i = 0; i < 1; i++) {
//            logger.error("this is for time {} with error",i);
//        }
//        for (int i = 0; i < 10; i++) {
//            logger.debug("this is for time {} with error",i);
//        }


            Map<String,Integer> m = new HashMap<String,Integer>();

            m.put("zhangsan", 19);
            m.put("lisi", 49);
            m.put("wangwu", 19);
            m.put("lisi",20);
            m.put("hanmeimei", null);

            System.out.println(m.size());

    }
}
