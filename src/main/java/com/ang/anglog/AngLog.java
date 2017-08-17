package com.ang.anglog;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by ang on 2017/8/17.
 */
public class AngLog {

    private  static Logger logger = LoggerFactory.getLogger(AngLog.class);

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 1; i++) {
            logger.error("this is for time {} with error",i);
        }
        for (int i = 0; i < 10; i++) {
            logger.debug("this is for time {} with error",i);
        }
    }
}
