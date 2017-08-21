package com.ang.anglog.tianyc.randomCheck;


import com.ang.anglog.tianyc.SearchDetailInfo;
import com.ang.anglog.tianyc.Tools.CompareTools;
import com.ang.anglog.tianyc.Tools.JsonTools;
import com.ang.anglog.tianyc.Tools.ReadPropertyTool;
import com.ang.anglog.tianyc.Tools.StringUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by adimn on 2017/6/12.
 */
public class RandomCheck {
    //传入两个值，一个更新id文件名，一个是文件行数
    //随机选取十条数据，对比结果，一旦有不同值，就直接抛出错误
    private static SearchDetailInfo searchLocal;
    private Boolean useSplit;//是否采用getSplitDetailInfoByCompanyId()方法
    //默认从单个库导数据
    public RandomCheck(){
        searchLocal=new SearchDetailInfo();
        this.useSplit=false;
    }
    //
    public RandomCheck(Boolean useSplit){
        //必须加参数，使得程序走到splitDb里面；
        this.useSplit=true;
        searchLocal=new SearchDetailInfo(0);
    }

    //company表中需要进行比对信息
    private static final  HashSet<String> companyBaseSet=new HashSet<String>(){{
       add("name");add("companyOrgType");/*industry*/add("legalPersonName");add("regCapital"); add("businessScope");add("regLocation");add("companyType");
        add("regInstitute");add("regStatus");
        add("creditCode");//待更新数据之后需要比对信息，company表中的property1字段
        add("regNumber");//comChanInfoList 中的companyId为：126400107
        //add("id"); add("updatetime");
//        add("industry");
    }};
    //company相关list,进行比较的
    private static final  HashSet<String> companyContSet=new HashSet<String>(){{
        add("branchList");add("checkList");add("comChanInfoList");add("comAbnoInfoList");add("equityList");add("investorListAll");add("punishList");add("staffListAll");
    }};
    //company相关list中，不必对的column，
    //// TODO: 2017/6/16  待解决问题
    private  static final HashSet<String> companyListColumnBlackSet=new HashSet<String>(){{
        add("id");add("type");
        add("companyName");//comChanInfoList中的companyName，都是为null的。
        add("changeItem");//comChanInfoList中的126395189,key为：changeItem，API值为:null，本地值为:null
        //add("removeDate");//comAbnoInfoListcompanyId为：126444017,key为：removeDate  api中为空，直接报错了；
        add("removeDepartment");
        add("regDate");//时间戳类型，待解决
    }};


    private  static  final  HashSet<String> compareDateSet=new HashSet<String>(){{
//        "changeTime".equals(key)||"createtime".equals(key)||"checkDate".equals(key)||"removeDate".equals(key)||"putDate".equals(key)
        add("changeTime");add("createtime");add("checkDate");add("removeDate");add("putDate");add("removeDate");
//        add("regDate");
    }};

    public static void main(String[] args){
        //输入文件地址
        String filePath=args[0];
        String maxLine=args[1];

        String compareNum= ReadPropertyTool.getPropertyByParam("compareNum.properties","compareNum");
        if(StringUtil.isEmpty(compareNum)){
            compareNum="30";
        }

//        getApiStrByName("深圳市堃鹏文化发展有限公司");
//        Map<String, Object> urlRes = JsonTools.getMapByUrl(genGetUrl("深圳市堃鹏文化发展有限公司"));

        RandomCheck rd=new RandomCheck(true);
        rd.randomCompareData(filePath,maxLine,compareNum);
    }

    public static TreeSet<Integer> setDataset(String maxLine,String compareNum){
        //测试时候打开
//        filePath=RandomCheck.class.getClassLoader().getResource("20170612_ids_final.txt").getFile();
        //得到随机行数
        TreeSet<Integer> reset=new TreeSet<Integer>();
        if(maxLine!=null&&StringUtil.isNotEmpty(maxLine)){
            Integer intMaxLine=Integer.parseInt(maxLine);
            if(compareNum!=null&&StringUtil.isNotEmpty(compareNum)) {
                Integer intCompareNum=Integer.parseInt(compareNum);
                randomSet(1, intMaxLine,intCompareNum, reset);
            }else{
                randomSet(1, intMaxLine, 10, reset);
            }
            System.out.println(reset);
            return reset;
        }
        return null;
    }

    /**
     *逐行对比
     * @param filePath
     * @return
     */
    public  Boolean randomCompareData(String filePath,String maxLine,String compareNum){
        TreeSet<Integer> reset=setDataset(maxLine,compareNum);
        //根据行号，得到companyId
        List companyIds=readFromFile(filePath,reset);
        if(companyIds==null){
            System.out.println("读取companyID文件结果为null");
            return false;
        }
        System.out.println(companyIds);
        for(int i=0;i<companyIds.size();i++){
            String companyId=companyIds.get(i).toString();

            Map<String,Object> localRes= null;
            if(this.useSplit){
                localRes=searchLocal.getSplitDetailInfoByCompanyId(companyId);
            }else{
                localRes =searchLocal.getDetailInfoByCompanyId(companyId);
            }
            if(localRes!=null){

                String name=localRes.get("name").toString();
                Map<String,Object> apiAllRes= JsonTools.getMapByUrl(JsonTools.genGetUrl(name));
                Map<String,Object> apiRes=(Map<String,Object>)apiAllRes.get("result");
                try {
                    compareMapValue(localRes, apiRes);
                }catch( Exception e){
                    e.printStackTrace();
                }
            }else{
                System.out.println("local result is null: "+companyId);
            }
        }
        return  true;
    }

    public  Boolean compareOneByCompanyId(String companyId){
        Map<String,Object> localRes;
        if(this.useSplit) {
            localRes=searchLocal.getSplitDetailInfoByCompanyId(companyId);
        }else{
            localRes = searchLocal.getDetailInfoByCompanyId(companyId);
        }
        if(localRes!=null){

            String name=localRes.get("name").toString();
            Map<String,Object> apiAllRes=JsonTools.getMapByUrl(JsonTools.genGetUrl(name));
            Map<String,Object> apiRes=(Map<String,Object>)apiAllRes.get("result");
            try {
                compareMapValue(localRes, apiRes);
            }catch( Exception e){
                e.printStackTrace();
            }

        }else{
            System.out.println("local single compare result null: "+companyId);
        }
        return  true;
    }

    /**
     * 按字段比较本地值和远程调用值
     * @param localMap
     * @param apiMap
     * @return
     */
    private  void compareMapValue(Map<String,Object> localMap,Map<String,Object> apiMap) throws Exception{
        if(localMap!=null && apiMap!=null){
            localMap.get("");
            int i=0;
            for(Map.Entry<String,Object> entry:localMap.entrySet()){
                String key=entry.getKey();
                i++;
                //company基本信息比较
                if(companyBaseSet.contains(key)){
                    if(entry.getValue()!=null) {
                        String localCompValue = entry.getValue().toString();
//                        !localCompValue.equals(apiMap.get(key).toString())
                        if (localCompValue != null && !CompareTools.compareStringEqual(localCompValue,apiMap.get(key))) {
                            throw new Exception("基本信息不一致，companyId为："+apiMap.get("companyId")+",key为："+key+"，API值为:" + apiMap.get(key).toString()+"，本地值为:"+localCompValue);
                        }
                    }else if(apiMap.get(key)!=null){
                        throw new Exception("基本信息缺失，companyId为："+apiMap.get("companyId")+",key为："+key+"，API值为:" + apiMap.get(key).toString()+"，本地值为:null");
                    }
                }else if("updatetime".equals(key)){
                    DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//创建日期转换对象HH:mm:ss为时分秒，年月日为yyyy-MM-dd
                    String localTime=entry.getValue().toString();
                    String apiTime=  df.format( apiMap.get(key));
                    try {
                        Date dlocal = df.parse(localTime);//将字符串转换为date类型
                        Date dapi = df.parse(apiTime);
                        if(dlocal.getTime()!=dlocal.getTime()){
                            throw new Exception("updateTime不一致");
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                } else if(companyContSet.contains(key)){
                    if("staffListAll".equals(key)){
                        System.out.println();
                    }
                    //company相关list比较
                    List<Map<String, Object>> apiListValue=null;
                    if(apiMap.get(key)!=null) {
                        apiListValue = JsonTools.parseJSON2List(apiMap.get(key).toString());
                    }
                    if(entry.getValue()!=null) {
                        List<Map<String, Object>> localListValue = (ArrayList<Map<String, Object>>) entry.getValue();
                        if (localListValue != null && apiListValue != null) {
                            if (localListValue.size() != apiListValue.size()) {
                                throw new Exception("公司列表信息不一致，companyId为："+apiMap.get("companyId")+",key为："+key+"，API值为:" + apiListValue.size()+"，本地值为:"+localListValue.size());
                            }else{
                                compareListValue(localListValue,apiListValue,key);
                            }
                        }
                    }else if(apiListValue!=null&&apiListValue.size()>0){
                        throw new Exception("公司列表信息本地为空，companyId为："+apiMap.get("companyId")+",key为："+key+"，API值为:" + apiListValue.size()+"，本地值为:null");
                    }else if(apiListValue==null||apiListValue.size()==0){
                    }
                }else if("annualReportList".equals(key)||"mortgageList".equals(key)){ //两个复杂list比较
                    if(entry.getValue()!=null) {
                        List<Map<String, Object>> localComplexValue = (ArrayList<Map<String, Object>>) entry.getValue();
                        List<Map<String, Object>> apiComplexValue =  JsonTools.parseJSON2List(apiMap.get(key).toString()) ;
                        if (localComplexValue.size() != apiComplexValue.size()) {
                            throw new Exception("report 或者mortgage信息不一致companyId为："+apiMap.get("companyId")+",key为："+key+"，API值为:" + apiComplexValue.size()+"，本地值为:"+localComplexValue.size());
                        }else{
                            if(key.equals("annualReportList")){

                            }
                        }
                    }
                }
            }
            System.out.println("map count"+i);
        }
    }

    /**
     * 比较list中的map信息,根据companyId拍好顺序，并且之前检查了list中数目一致
     * @param local
     * @param api
     */
    private  void compareListValue(List<Map<String,Object>> local,List<Map<String,Object>> api,String tableName)throws Exception{
        if(local!=null&&api!=null){
            sortByCompanyId(local,tableName);
            sortByCompanyId(api,tableName);
            for(int i=0;i<local.size();i++){
                Map<String,Object> lmap=local.get(i);
                Map<String,Object> amap=api.get(i);

                for(Map.Entry<String,Object> entry:lmap.entrySet()){
                    String key = entry.getKey();
                    //自己加了companyId信息，但是还没有用了
                    if(!"companyId".equals(key)){
                        if(entry.getValue()!=null&&!CompareTools.compareStringEqual(entry.getValue(),amap.get(key))&&!companyListColumnBlackSet.contains(key)){
                            if(compareDateSet.contains(key)){
                                CompareTools.compareStrTime(entry.getValue().toString(),amap.get(key).toString(),tableName,lmap.get("companyId").toString());
                            }else  if ("amount".equals(key)) {
                                CompareTools.compareNumberEqual(entry.getValue().toString(),amap.get(key).toString(),tableName,lmap.get("companyId").toString());
                            }else {
                                throw new Exception("公司列表信息不一致：表名为：" + tableName + "companyId为：" + lmap.get("companyId") + ",key为：" + key + "，API值为:" + amap.get(key) + "，本地值为:" + entry.getValue());
                            }
                            //判断条件，本地为null值，api中有值(空字符串或者为null除外)
                        }else if(entry.getValue()==null&&amap.get(key)!=null&&!amap.get(key).equals("")&&!companyListColumnBlackSet.contains(key)){

                            throw new Exception("公司列表信息不一致lelelel：表名为：" + tableName + "companyId为：" + lmap.get("companyId") + ",key为：" + key + "，API值为:" + amap.get(key) + "，本地值为:" + null);
                        }
                    }

                }
            }
        }
    }



    private  String getColumnByTableName(String tablename){
        if(tablename.equals("equitylist")){
            return "regNumber";
        }else if("comChanInfoList".equals(tablename)||"comAbnoInfoList".equals(tablename)
                ||"punishList".equals(tablename)||"equityList".equals(tablename)){
            return "id";
        }else if("staffListAll".equals(tablename)||"investorListAll".equals(tablename)){
            //// TODO: 2017/6/14 查看其它表
            return "name";
        }
        return null;
    }

    private  void sortByCompanyId(List<Map<String,Object>> list,String tablename){
        //changInfoList,investorListAll,staffLIstAll
        //不行 equitylist
        final String colum=getColumnByTableName(tablename);
        if(list!=null&&list.size()>1&&colum!=null) {
            Collections.sort(list, new Comparator<Map<String, Object>>() {
                public int compare(Map<String, Object> o1, Map<String, Object> o2) {
                    String name1,name2;
                    if(o1.get(colum)!=null) {
                        name1= o1.get(colum).toString();//name1是从你list里面拿出来的一个
                    }else{
                        name1="";
                    }
                    if(o2.get(colum)!=null){
                        name2 = o2.get(colum).toString(); //name1是从你list里面拿出来的第二个name
                    }else{
                        name2="";
                    }
                    return name1.compareTo(name2);
                }
            });
        }
    }


    private  void compareAnnualReport(List<Map<String, Object>> local,List<Map<String,Object>>api){

    }


    /**
     *根据 最大值，最新值，和个数，生成特定数量的随机数（按行来选取）
     * @param min
     * @param max
     * @param n
     * @param set
     */
    public static  void randomSet(int min, int max, int n, TreeSet<Integer> set) {
        if (n > (max - min + 1) || max < min) {
            return;
        }
        for (int i = 0; i < n; i++) {
            // 调用Math.random()方法
            int num = (int) (Math.random() * (max - min)) + min;
            set.add(num);// 将不同的数存入HashSet中
        }
        int setSize = set.size();
        // 如果存入的数小于指定生成的个数，则调用递归再生成剩余个数的随机数，如此循环，直到达到指定大小
        if (setSize < n) {
            randomSet(min, max, n - setSize, set);// 递归
        }
    }

    /**
     * 根据随机行数，得到10个companyId
     * @param fileName
     * @param set
     * @return
     */
    public static List<String> readFromFile(String fileName,TreeSet<Integer> set){
        if(fileName==null||StringUtil.isEmpty(fileName)){
            return null;
        }
        ArrayList<String> companyIds=new ArrayList<String>();
        File file = new File(fileName);
        BufferedReader reader = null;
        try {
            System.out.println("以行为单位读取文件内容，一次读一整行：");
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            int countline = 1;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
                // 显示行号
                if(set.contains(countline)){
                    companyIds.add(tempString);
                }
                countline++;
            }
            reader.close();
            System.out.println("读取结束，处理完成！！");
            return companyIds;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
        return null;
    }

}
