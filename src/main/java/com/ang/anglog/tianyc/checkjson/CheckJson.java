package com.ang.anglog.tianyc.checkjson;

import com.alibaba.fastjson.JSONObject;
import com.ang.anglog.tianyc.insertrds.RdsDao;

import java.io.*;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * Created by adimn on 2017/7/21.
 */
public class CheckJson {
    private static int count = 0;
    private  static  String[] talbelist={"comChanInfoList","staffListAll","comAbnoInfoList","staffListAll","investorListAll","mortgageList"};
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
            int idindex = json.indexOf("\"id\":");
            if(idindex<0){
                return null;
            }

            json=json.substring(idindex+4,json.length());
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
        if(startIndex >0) {
            json = json.substring(startIndex , json.length());
            int endIndex = json.indexOf(lastIndex)+startIndex;
            returnStr = jsonSource.substring(0,startIndex)+jsonSource.substring(endIndex+1,jsonSource.length());
        }
        return returnStr;

    }
    public static void main(String[] args) {

//        prepareJson(comChange,"comChanInfoList","]");

        BufferedWriter writer=null;
        BufferedWriter nullwriter=null;
        try {
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("wrong.txt")));
            nullwriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("nullid.txt")));
            RdsDao rdsDao = new RdsDao();
            boolean flow=true;
            int length = 10000;
            int start = 0;
            while(flow){
                List<String> fromRds = rdsDao.getFromRds(start, length);
                if(fromRds.size()>0){
                    for(int j=0;j<fromRds.size();j++){
                        String json = fromRds.get(j);
                        String id = getWrongId(json);
                        if(id==null){
                            nullwriter.write(json);
                            nullwriter.newLine();
                            nullwriter.flush();
                        }
                        if(!"".equals(id)){
                            writer.write(id+"--"+json);
                            writer.newLine();
                            writer.flush();
                        }
                    }
                }else{
                    flow=false;
                }
                start+=length;
                System.out.println("已处理:"+start);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
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
    }
}
