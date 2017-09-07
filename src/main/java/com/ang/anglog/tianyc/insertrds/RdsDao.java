package com.ang.anglog.tianyc.insertrds;


import com.ang.anglog.tianyc.Tools.DBUtils;
import com.ang.anglog.tianyc.Tools.ReadPropertyTool;
import com.ang.anglog.tianyc.Tools.StringUtil;

import java.util.List;
import java.util.Map;

/**
 * Created by adimn on 2017/6/16.
 */
public class RdsDao {
    private DBUtils dbUtils;
    public RdsDao(){
        //构造插入datasource
        this.dbUtils=new DBUtils();
    }

    public String  insertIntoRds(String companyName,String detail){
        if(companyName!=null&& StringUtil.isNotEmpty(companyName)){
//            String sql = "replace into companyinfo (name,json) values( ? ,? )";
            String sql = "replace into companyinfo_test (name,json) values( ? ,? )";
            String res = null;

            res = dbUtils.insert(0,sql,new Object[]{companyName,detail});
            String add= ReadPropertyTool.getPropertyByParam("angsqldb.properties","insertAdd");
            if(add.equals("true")) {
                String res2 = dbUtils.insert(2,sql,new Object[]{companyName,detail});
            }
            return  res.toString();
        }
        return  null;
    }

    public List<String> getFromRds(int start ,int length){
        String sql = "select json from companyinfo limit "+start+","+length;
        return dbUtils.getJsonInfoByPage(sql);
    }

    public int insertBachIntoRds(Map<String, String> returnMap) {
        int res=0;
//        String insertSQL = "REPLACE INTO companyinfo(name,json) values (?,?)";
        String insertSQL = "REPLACE INTO companyinfo_test(name,json) values (?,?)";
        res = dbUtils.insertFinalBatch(0,insertSQL,returnMap);

        String add= ReadPropertyTool.getPropertyByParam("angsqldb.properties","insertAdd");
        if(add.equals("true")) {
            dbUtils.insertFinalBatch(2,insertSQL,returnMap);
        }
        return res;
    }
}
