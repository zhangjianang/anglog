package com.ang.anglog.tianyc.Tools;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by adimn on 2017/6/28.
 */
public class CompareTools {

    public static void main(String[] args){

    }

//    public static void compareTime(String ltime,String atime,String tablename,String compayId)throws Exception{
//        if(ltime!=null&& atime!=null&& StringUtil.isNotEmpty(ltime)&&StringUtil.isNotEmpty(atime)){
//            DateFormat adf=null;
//            DateFormat ldf=null;
//            if(atime.length()>10) {
//                adf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//创建日期转换对象HH:mm:ss为时分秒，年月日为yyyy-MM-dd
//            }else{
//                adf=new SimpleDateFormat("yyyy-MM-dd");
//            }
//            if(ltime.length()>10) {
//                ldf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//创建日期转换对象HH:mm:ss为时分秒，年月日为yyyy-MM-dd
//            }else{
//                ldf=new SimpleDateFormat("yyyy-MM-dd");
//            }
//
//            try {
//                Date dlocal = ldf.parse(ltime);//将字符串转换为date类型
//                Date dapi = adf.parse(atime);
////                dlocal.getTime()!=dlocal.getTime()
//                if(dlocal.getYear()!=dapi.getYear()||dlocal.getMonth()!=dapi.getMonth()||dlocal.getDay()!=dapi.getDay()){
//                    System.out.println("no");
//                    throw new Exception("Time不一致,companyId为："+compayId+"表为："+tablename);
//                }
//            } catch (ParseException e) {
//                e.printStackTrace();
//            }
//        }
//    }


    public static void compareStrTime(String ltime,String atime,String tablename,String companyId)throws Exception{
        if(ltime!=null&& atime!=null&& StringUtil.isNotEmpty(ltime)&&StringUtil.isNotEmpty(atime)) {
            String cltime=getStrTime(ltime);
            String catime=getStrTime(atime);
           if(!cltime.equals(catime)){
               throw new Exception("Time不一致,companyId为："+companyId+"表为："+tablename+",local:"+ltime+",api"+atime);
           }
        }else if(ltime==null&&atime==null||StringUtil.isEmpty(ltime)&&StringUtil.isEmpty(atime)){

        }else{
            throw new Exception("Time不一致,companyId为："+companyId+"表为："+tablename+",local:"+ltime+",api"+atime);
        }
    }

    public static String getStrTime(String ltime){
        if(ltime==null||"".equals(ltime)){
            return "";
        }
        if(ltime.length()>13&&ltime.contains("-")){
            return ltime.substring(0,10);
        }else if(ltime.length()==13) {
            long millisecond = Long.parseLong(ltime);
            Date date = new Date(millisecond);
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            return format.format(date);
        }else {
            return ltime;
        }
    }


    /*比较整形和浮点型*/
    public static  void compareNumberEqual(String local,String api,String tableName,String companyId) throws Exception{
        if(local!=null&&api!=null){
            int lamount =convertStrInt(local);
            int apiAmount=convertStrInt(api);
            if(lamount!=apiAmount){
                throw new Exception("公司列表信息不一致：表名为：" + tableName + "companyId为：" + companyId + ",key为：amount ，API值为:" + apiAmount+ "，本地值为:" +lamount);
            }
        }
    }


    /*比较字符型*/
    public static   Boolean compareStringEqual(Object olocal,Object oapi){
        if(olocal!=null&&oapi!=null){
            String local=olocal.toString();
            String api=oapi.toString();
            local=local.trim().replaceAll("\r|\n","").replaceAll("\r\n","").replaceAll("\n\r","");
            api=api.trim().replaceAll("\r|\n","").replaceAll("\r\n","").replaceAll("\n\r","");
            if(local.equals(api)){
                return true;
            }else{
                return false;
            }
        }else if(olocal==null&&oapi==null){
            return true;
        }
        return  false;
    }


    //model类型比较数值相等
    public static boolean compareModelNumEqual(String local,String api){
        int ilocal=convertStrInt(local);
        int iapi=convertStrInt(api);
        if(iapi==ilocal){
            return true;
        }else{
            return false;
        }
    }
    public static boolean  compareModelStrTime(String ltime,String atime){
        if(ltime!=null&& atime!=null&& StringUtil.isNotEmpty(ltime)&&StringUtil.isNotEmpty(atime)) {
            String cltime=getStrTime(ltime);
            String catime=getStrTime(atime);
            if(!cltime.equals(catime)){
               return false;
            }else{
                return true;
            }
        }else if(ltime==null&&atime==null||StringUtil.isEmpty(ltime)&&StringUtil.isEmpty(atime)){
            return true;
        }else{
           return false;
        }
    }

    public static  int convertStrInt(String data){
        if(data!=null&&!"".equals(data)){
            Integer amount =null;
            int adx = data.lastIndexOf(".");//查找小数点的位置
            if(adx>0){
                String astrNum = data.substring(0,adx);
                amount=Integer.parseInt(astrNum);
            }else{
                amount=Integer.parseInt(data);
            }
            return amount;
        }else{
            return 0;
        }
    }
}
