package com.ang.anglog.tianyc.insertrds;

import com.alibaba.fastjson.JSONObject;
import com.ang.anglog.tianyc.SearchDetailInfo;
import com.ang.anglog.tianyc.Tools.SqlMap;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

/**
 * Created by adimn on 2017/6/16.
 */
public class InsertRds {
    //传入整数值，可以随便设置，只是为了区分默认构造函数
    private static SearchDetailInfo searchDetailInfo=new SearchDetailInfo(1);
    private  static RdsDao rdsDao=new RdsDao();

    public static void main(String[] args){
        String filename=args[0];
//        String filename =InsertRds.class.getClassLoader().getResource("20170612_ids_final.txt").getFile();
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


    /*批量插入，500条之后才进行插入*/
    public static void batchAddByCompanyId(String companyId){
        Map<String, Object> res=null;
        try {
            res = searchDetailInfo.getSplitDetailInfoByCompanyId(companyId);
//            System.out.println(Thread.currentThread().getName() +":已处理到"+companyId);
            if(res==null){
                System.out.println( "处理id :"+companyId+"组装失败，result为null！！");
                return;
            }
//            Map resultMap=(Map)res.get("result");
            if(res.get("name")!=null){
                String name = res.get("name").toString();

                String jres=JSONObject.toJSONString(res);
                String  encryptedPwd = MD5.MD5_32bit(name);

                Map<String, String> returnMap = SqlMap.addValue(encryptedPwd, jres);
                if(returnMap!=null){
                    rdsDao.insertBachIntoRds(returnMap);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.toString());
            System.out.println(e.getMessage());
            System.out.println(Thread.currentThread().getName()+" :处理id :"+companyId+"失败!!!!");
            System.out.println(res);
        }
    }

    /**
     * 读取文件内容，拼接完成后插入rds中
     */
    public static void readFileDealOne(String fileName) {
        File file = new File(fileName);
//        File file =new File(InsertRds.class.getClassLoader().getResource(fileName).getFile());
        BufferedReader reader = null;
        try {
            System.out.println("以行为单位读取文件内容，一次读一整行：");
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            int countline = 1;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
                // 显示行号
//                System.out.println("读取id文件，当前line " + countline + ": " + tempString);
                countline++;
                //按行处理内容：
                batchAddByCompanyId(tempString.trim());
            }

            Map<String, String> returnMap = SqlMap.getSqlMap();
            if(returnMap!=null){
                rdsDao.insertBachIntoRds(returnMap);
            }

            reader.close();
            System.out.println("读取结束，处理完成！！");
        } catch (IOException e) {
            e.printStackTrace();
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
