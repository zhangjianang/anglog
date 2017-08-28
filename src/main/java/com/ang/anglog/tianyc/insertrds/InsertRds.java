package com.ang.anglog.tianyc.insertrds;

import com.alibaba.fastjson.JSONObject;
import com.ang.anglog.AngLog;
import com.ang.anglog.tianyc.SearchDetailInfo;
import com.ang.anglog.tianyc.Tools.SqlMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by adimn on 2017/6/16.
 */
public class InsertRds {
    private  static Logger logger = LoggerFactory.getLogger(InsertRds.class);
    //传入整数值，可以随便设置，只是为了区分默认构造函数
    private static SearchDetailInfo searchDetailInfo=new SearchDetailInfo(1);
    private  static RdsDao rdsDao=new RdsDao();
    private static Map<String,String> wrongMap = new HashMap();

    public static void main(String[] args){
        String filename=args[0];
        readFileDealOne(filename);
    }

    //插入一条数据
   public static void addOneByCompanyId(String companyId){
       String jres= "";
       try {
           Map<String, Object> res = searchDetailInfo.getSplitDetailInfoByCompanyId(companyId);
           System.out.println(Thread.currentThread().getName() +":已处理到"+companyId);
           if(res==null){
               System.out.println( "处理id :"+companyId+"组装失败，result为null！！");
               return;
           }
//           Map resultMap=(Map)res.get("result");
           if(res.get("name")!=null){
               String name = res.get("name").toString();

               jres= JSONObject.toJSONString(res);
               String  encryptedPwd = MD5.MD5_32bit(name);
               rdsDao.insertIntoRds(encryptedPwd,jres);
           }
       } catch (Exception e) {
           e.printStackTrace();
           System.out.println(jres);
           System.out.println(Thread.currentThread().getName()+" :处理id :"+companyId+"失败!!!!");
       }
   }


    /*批量插入，100条之后才进行插入*/
    public static int batchAddByCompanyId(String companyId){
        Map<String, Object> res=null;
        try {
            res = searchDetailInfo.getSplitDetailInfoByCompanyId(companyId);
            if(res==null){
                logger.warn("处理id :"+companyId+"组装失败，result为null！！");
                wrongMap.put(companyId,"result为null");
                return 0;
            }
            if(res.get("name")!=null){
                String name = res.get("name").toString();

                String jres=JSONObject.toJSONString(res);
                String  encryptedPwd = MD5.MD5_32bit(name);

                Map<String, String> returnMap = SqlMap.addValue(encryptedPwd, jres);
                if(returnMap!=null){
                    return rdsDao.insertBachIntoRds(returnMap);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.warn(" 处理id :"+companyId+"失败，转json出错");
            wrongMap.put(companyId,"转json出错");
        }
        return 0;
    }

    /**
     * 读取文件内容，拼接完成后插入rds中
     */
    public static void readFileDealOne(String fileName) {
        File file = new File(fileName);
        BufferedReader reader = null;
        try {
            System.out.println("以行为单位读取文件内容，一次读一整行：");
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            int countline = 0;
            int succline = 0;
            while ((tempString = reader.readLine()) != null) {
                // 显示行号
//                System.out.println("读取id文件，当前line " + countline + ": " + tempString);
                if(!"".equals(tempString.trim())) {
                    countline++;
                    //按行处理内容：
                    succline += batchAddByCompanyId(tempString.trim());
                }
            }

            Map<String, String> returnMap = SqlMap.getSqlMap();
            if(returnMap!=null){
                succline +=rdsDao.insertBachIntoRds(returnMap);
            }
            reader.close();
            logger.info("读取结束，拼装插入处理完成! 读取条数："+countline+",执行成功："+succline);
            if(wrongMap.size()>0){
                logger.error("读取结束，拼装插入处理完成! 读取条数："+countline+",执行成功："+succline+",失败条数："+wrongMap.size()+",错误id信息为："+wrongMap.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
            logger.warn("读取"+fileName+"出错！");
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }
}
