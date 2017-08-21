package com.ang.anglog.tianyc.checkjson;

import com.alibaba.fastjson.JSONObject;
import com.ang.anglog.tianyc.SearchDetailInfo;
import com.ang.anglog.tianyc.insertrds.MD5;


import java.util.Map;

/**
 * Created by adimn on 2017/7/21.
 */
public class CheckLocalRes {
    public static SearchDetailInfo searchDetailInfo=new SearchDetailInfo();
//    /插入一条数据
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
//                rdsDao.insertIntoRds(encryptedPwd,jres);
                System.out.println(jres);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(jres);
            System.out.println(Thread.currentThread().getName()+" :处理id :"+companyId+"失败!!!!");
        }
    }

    public static void main(String[] args){
        addOneByCompanyId("50204436");
    }
}
