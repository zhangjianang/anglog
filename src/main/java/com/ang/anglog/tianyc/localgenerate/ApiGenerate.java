package com.ang.anglog.tianyc.localgenerate;

import com.alibaba.fastjson.JSONObject;
import com.ang.anglog.tianyc.Tools.DBUtils;
import com.ang.anglog.tianyc.Tools.JsonTools;
import com.ang.anglog.tianyc.Tools.ReadPropertyTool;
import com.ang.anglog.tianyc.insertrds.MD5;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by adimn on 2017/8/16.
 */
public class ApiGenerate {

    public static String apiGetInRds(String name){
        if(!"".equals(name)) {
            Map<String, Object> apiAllRes = JsonTools.getMapByUrl(JsonTools.genGetUrl(name));
            Map<String,Object> result = (Map<String, Object>) apiAllRes.get("result");
            if(result == null){
                System.out.println("api查询结果为空："+name);
                return null;
            }
            String companId = result.get("companyId").toString();
            result.put("id",companId);
            result.put("correctCompanyId",companId);
            String detail = JSONObject.toJSONString(result);

            DBUtils dbUtils = new DBUtils();
            try {
                String md5name = MD5.MD5_32bit(name);
                String sql = "replace into companyinfo (name,json) values( ? ,? ) ";
                String res0 = dbUtils.insert(0, sql, new Object[]{md5name, detail});

                String add= ReadPropertyTool.getPropertyByParam("angsqldb.properties","insertAdd");
                if(add.equals("true")) {
                    String res2 = dbUtils.insert(2,sql,new Object[]{md5name,detail});
                }
            } catch (Exception e) {
                System.err.println("插入数据库失败:"+name);
                e.printStackTrace();
            }
            System.out.println("插入数据库成功:"+name);
            //生成es文件
            return genEsData(companId,name);
        }else{
            System.out.println("参数错误！");
            return null;
        }
    }

    public static String genEsData(String id,String name){
        if(!"".equals(id)&& !"".equals(name)) {
            Map<String, Object> indexMap = new HashMap<String, Object>();
            Map<String, String> indexInner = new HashMap<String, String>();
            indexInner.put("_index", "company");
            indexInner.put("_type", "company");
            indexInner.put("_id", id);
            indexMap.put("index", indexInner);


            Map<String, String> secondMap = new HashMap<String, String>();
            secondMap.put("id", id);
            secondMap.put("name", name);
            StringBuilder sb = new StringBuilder();

            sb.append(JSONObject.toJSONString(indexMap) + "\n");
            sb.append(JSONObject.toJSONString(secondMap) + "\n");
            return sb.toString();
        }
        return null;
    }


    public static void writeData(String str)
    {
        FileOutputStream out=null;
        try
        {
            String path = ReadPropertyTool.getPropertyByParam("filepath.properties", "esFilePath");
            if(path=="")
                return;

            File file=new File(path);

            file.createNewFile();

            out =new FileOutputStream(file,false); //如果追加方式用true
            StringBuffer sb=new StringBuffer();
            sb.append(str);
            out.write(sb.toString().getBytes("utf-8"));//注意需要转换对应的字符集
        }
        catch(IOException ex)
        {
            System.out.println(ex.getStackTrace());
        }finally {
            try {
                if(out!= null)
                    out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void genEsAndInsert(String[] args){
        StringBuilder sb = new StringBuilder();
        int genok = 0;
        for(int i=0;i<args.length;i++) {
            String es_data = apiGetInRds(args[i]);
            if(es_data!=null) {
                sb.append(es_data);
                genok++;
            }
        }
        writeData(sb.toString());
        System.out.println("共"+args.length+"条，成功"+genok+",保存数据为:"+sb.toString());
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {
//        genEsAndInsert(new String[]{"上海鸿金投资管理有限公司","北京恩科得信息技术有限公司","博洛尼家居装饰（北京）有限公司"});
//        System.out.println(MD5.MD5_32bit("上海鸿金投资管理有限公司"));
//        System.out.println(MD5.MD5_32bit("北京恩科得信息技术有限公司"));
        System.out.println(MD5.MD5_32bit("北京琢玉天成管理咨询有限公司"));

//        genEsAndInsert(new String[]{"宁夏中阿国际文化交流促进会"});
//        genEsAndInsert(args);
    }
}
