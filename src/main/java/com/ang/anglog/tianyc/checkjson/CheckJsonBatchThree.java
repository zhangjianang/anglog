package com.ang.anglog.tianyc.checkjson;

import com.alibaba.fastjson.JSONObject;
import com.ang.anglog.tianyc.Tools.SQLUtil;


import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * Created by adimn on 2017/7/21.
 */
public class CheckJsonBatchThree {
    private static int count = 0;
    private  static  String[] talbelist={"outGuaranteeInfoList","webInfoList","changeRecordList","equityChangeInfoList","shareholderList","outboundInvestmentList","mchangelist","mpawnlist","mpeoplelist", "annualReportList","branchList","comChanInfoList","staffListAll","comAbnoInfoList","staffListAll","investorListAll","mortgageList","punishList"};


    public  static String getWrongId(String json){

        String sourceJosn = json;
        String id="";
        try {
            Map mapObj = JSONObject.parseObject(json, Map.class);
        }catch (Exception e){
            count++;
            if(count%1==0){
                System.out.println("errorCount :"+count);
            }

            try {
                for (int i = 0; i < talbelist.length; i++) {
                    json = prepareJson(json, talbelist[i], "]");
                }
            }catch (Exception e2){
                e2.printStackTrace();
                return null;
            }
            int idindex = json.indexOf("\"correctCompanyId\":");
            if(idindex<0){
                return null;
            }

            json=json.substring(idindex+19,json.length());
            String[] splits = json.split(Pattern.quote(","));
            try{
                if(splits.length>0){
                    id = splits[0].trim();
                }
            }catch(Exception e1){
                e1.printStackTrace();
                System.out.println("error: "+sourceJosn);
            }
        }
        return id;
    }

    public static String getSimpleWrongId(String json){
        String sourceJosn = json;
        String id="";
        try {
            Map mapObj = JSONObject.parseObject(json, Map.class);
        }catch (Exception e) {
            count++;
            if(count%1==0){
                System.out.println("errorCount :"+count);
            }

            int idindex = json.indexOf("\"correctCompanyId\":");
            if (idindex < 0) {
                return null;
            }
            json = json.substring(idindex + 19, json.length());
            String[] splits = json.split(Pattern.quote(","));
            try{
                if(splits.length>0){
                    id = splits[0].trim();
                }
            }catch(Exception e1){
                e1.printStackTrace();
                System.out.println("error: "+sourceJosn);
            }
        }
        return id;
    }

    public static  String prepareJson(String json,String indexValue,String lastIndex){
        String jsonSource = json;
        int startIndex = json.indexOf(indexValue);
        String returnStr=json;

            while (startIndex > 0) {
                json = json.substring(startIndex, json.length());
                int endIndex = json.indexOf(lastIndex) + startIndex;
                returnStr = jsonSource.substring(0, startIndex) + jsonSource.substring(endIndex + 1, jsonSource.length());
                json=returnStr;
                startIndex = returnStr.indexOf(indexValue);
            }

        return returnStr;

    }
    public static void main(String[] args) {

//        System.err.println(getWrongId(jsonwrong));
//        if(true){
//            return;
//        }
//        prepareJson(comChange,"comChanInfoList","]");
        long starttime = new Date().getTime();
        BufferedWriter writer=null;
        BufferedWriter nullwriter=null;
        BufferedWriter idwriter = null;
        long chuliCount = 0;
        try {
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("wrong.txt")));
            idwriter =new BufferedWriter(new OutputStreamWriter(new FileOutputStream("idwrong.txt")));
            nullwriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("nullid.txt")));
            boolean flow=true;
            int length = 10000;
            int start = 0;
            String startValue = "0";

            while(flow){
                Connection connection=null;
                Statement statement = null;
                ResultSet rs = null;
                List<String> lres=new ArrayList<String>();
                try {
                    connection = SQLUtil.getConnection();
                    statement = connection.createStatement();
                    rs = statement.executeQuery("select name,json from companyinfo where name > '"+startValue+"'limit " + length);
                    while (rs.next()){
                        lres.add(rs.getString(2));
                        startValue = rs.getString(1);
                    }
                    if(lres.size()>0){
                        for(int j=0;j<lres.size();j++){
                            chuliCount++;
                            String json = lres.get(j);
                            //之前方法
//                            String id = getWrongId(json);
                            String id = getSimpleWrongId(json);
                            if(id==null){
                                nullwriter.write(json);
                                nullwriter.newLine();
                                nullwriter.flush();
//                                System.out.println("errorjson :"+json);
                            }else if(!"".equals(id)){
                                writer.write(id+"--"+json);
                                writer.newLine();
                                writer.flush();
                                idwriter.write(id);
                                idwriter.newLine();
                                idwriter.flush();
//                                System.out.println("id"+id);
                            }

                        }
                    }else{
                        flow=false;
                    }

                }catch (Exception ee){
                    ee.printStackTrace();
                }finally {
                    start+=length;
                    long time = new Date().getTime();
                    long usetime=time-starttime;
                    starttime=time;
                    System.out.println("用时"+usetime+":已处理:"+start);
                    SQLUtil.close(connection,statement,rs);
                }
                System.out.println(startValue);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if(writer != null){
                    writer.close();
                }
                if(nullwriter!=null){
                    nullwriter.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        System.out.println(chuliCount);
    }
}
