package com.ang.anglog.tianyc.tycModel;



import com.alibaba.fastjson.JSONObject;
import com.ang.anglog.tianyc.SearchDetailInfo;
import com.ang.anglog.tianyc.Tools.CompareTools;

import com.ang.anglog.tianyc.Tools.JsonTools;
import com.ang.anglog.tianyc.Tools.ReadPropertyTool;
import com.ang.anglog.tianyc.Tools.StringUtil;

import com.ang.anglog.tianyc.randomCheck.RandomCheck;
import com.ang.anglog.tianyc.tycModel.company.*;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.*;

/**
 * Created by adimn on 2017/6/27.
 */
public class CompanyManager {
    public static void main(String[] args){
        //输入文件地址
        String filePath=args[0];
        String maxLine=args[1];
        String compareNum= ReadPropertyTool.getPropertyByParam("compareNum.properties","compareNum");
        if(StringUtil.isEmpty(compareNum)){
            compareNum="30";
        }
        CompanyManager cm=new CompanyManager();
        cm.randomCompareData(filePath,maxLine,compareNum);
    }

    private Company local;
    private Company api;

    private List<String> diffInfo;

    public List<String> getDiffInfo() {
        return diffInfo;
    }

    public Boolean randomCompareData(String filePath, String maxLine, String compareNum){
        TreeSet<Integer> reset= RandomCheck.setDataset(maxLine,compareNum);
        //根据行号，得到companyId
        List companyIds=RandomCheck.readFromFile(filePath,reset);
        if(companyIds==null){
            System.out.println("读取companyID文件结果为null");
            return false;
        }
        System.out.println(companyIds);
        for(int i=0;i<companyIds.size();i++){
            String companyId=companyIds.get(i).toString();
            System.out.println("比较第"+i+"条,comanyId:"+companyId);
            compareTwoByCompanyId(companyId);
            System.out.println();
        }
        return  true;
    }



    public void initApiDataByName(String name){
        Map<String,Object> json = JsonTools.getMapByUrl(JsonTools.genGetUrl(name));
        if(json!=null) {
            String result = JSONObject.toJSONString(json.get("result"));
            Gson gson = new Gson();
            java.lang.reflect.Type companyType = new TypeToken<Company>() {}.getType();
            System.out.println("api结果："+result);
            this.api = gson.fromJson(result, companyType);
        }else{
            this.api=null;
        }
    }

    public void initLocalData(Map<String,Object> localMap){
        if(localMap!=null) {
            String result = JSONObject.toJSONString(localMap);
            Gson gson = new Gson();
            java.lang.reflect.Type companyType = new TypeToken<Company>() {}.getType();
            System.out.println("local结果:"+result);
            this.local = gson.fromJson(result, companyType);
        }else{
            this.local=null;
        }
    }

    public void initAllByCompanyId(String companyId){
       SearchDetailInfo searchDetailInfo=new SearchDetailInfo(0);
       Map<String,Object> local = searchDetailInfo.getSplitDetailInfoByCompanyId(companyId);
        initLocalData(local);
        if(local!=null) {
            Object name = local.get("name");
            if(name!=null){
                initApiDataByName(name.toString());
            }
        }
    }

    public void compareTwoByCompanyId(String companyId){
        this.diffInfo=new ArrayList<String>();
        initAllByCompanyId(companyId);
        if(this.local!=null&&this.api!=null){
//            String companyId=local.getCompanyId();
//            add("name");add("companyOrgType");/*industry*/add("legalPersonName");add("regCapital"); add("businessScope");add("regLocation");add("companyType");
//            add("regInstitute");add("regStatus");

            if(!CompareTools.compareStringEqual(local.getActualCapital(),api.getActualCapital())){
                diffInfo.add(companyId+",actualCapital 值不同,local:"+local.getActualCapital()+",api:"+api.getActualCapital());
            }
            if(!CompareTools.compareStringEqual(local.getCompanyOrgType(),api.getCompanyOrgType())){
                diffInfo.add(companyId+",companyOrgType 值不同,local:"+local.getCompanyOrgType()+",api:"+api.getCompanyOrgType());
            }
            if(!CompareTools.compareStringEqual(local.getName(),api.getName())){
                diffInfo.add(companyId+",name 值不同,local:"+local.getName()+",api:"+api.getName());
            }
            if(!CompareTools.compareStringEqual(local.getLegalPersonName(),api.getLegalPersonName())){
                diffInfo.add(companyId+",legalPersonName 值不同,local:"+local.getLegalPersonName()+",api:"+api.getLegalPersonName());
            }

            if(!CompareTools.compareStringEqual(local.getRegCapital(),api.getRegCapital())){
                diffInfo.add(companyId+",regCapital 值不同,local:"+local.getRegCapital()+",api:"+api.getRegCapital());
            }
            if(!CompareTools.compareStringEqual(local.getBusinessScope(),api.getBusinessScope())){
                diffInfo.add(companyId+",businessScope 值不同,local:"+local.getBusinessScope()+",api:"+api.getBusinessScope());
            }
            if(!CompareTools.compareStringEqual(local.getRegLocation(),api.getRegLocation())){
                diffInfo.add(companyId+",regLocation 值不同,local:"+local.getRegLocation()+",api:"+api.getRegLocation());
            }
            if(!CompareTools.compareStringEqual(local.getCompanyType(),api.getCompanyType())){
                diffInfo.add(companyId+",companyType值不同,local:"+local.getCompanyType()+",api:"+api.getCompanyType());
            }
            if(!CompareTools.compareStringEqual(local.getRegInstitute(),api.getRegInstitute())){
                diffInfo.add(companyId+",regInstitute值不同,local:"+local.getRegInstitute()+",api:"+api.getRegInstitute());
            }
            if(!CompareTools.compareStringEqual(local.getRegStatus(),api.getRegStatus())){
                diffInfo.add(companyId+",regStatus值不同,local:"+local.getRegStatus()+",api:"+api.getRegStatus());
            }

//            if(!CompareTools.compareStringEqual(local.getIndustry(),api.getIndustry())){
//                diffInfo.add(companyId+",industry值不同,local:"+local.getIndustry()+",api:"+api.getIndustry());
//            }

//            if(local.getAnnualReportList().size()!=api.getAnnualReportList().size()){
//                diffInfo.add(companyId+",annualReport 值不同");
//            }
            //对比company相关List
            this.compareInvestor(this.local.getInvestorListAll(),api.getInvestorListAll(),diffInfo,companyId);
            this.compareCompanyStaff(this.local.getStaffListAll(),api.getStaffListAll(),diffInfo,companyId);
            this.compareCompanyChange(this.local.getComChanInfoList(),api.getComChanInfoList(),diffInfo,companyId);
            this.compareCompanyAbnormal(this.local.getComAbnoInfoList(),api.getComAbnoInfoList(),diffInfo,companyId);

        }else if(this.local==null&&this.api!=null){
            diffInfo.add(companyId+",local值为null");
        }else if(this.api==null&&this.local!=null){
            diffInfo.add(companyId+",api值为null");
        }
        showDiffInfo();
    }


    //对比company_investor_List
    public void compareInvestor(List<CompanyInvestor> local, List<CompanyInvestor> api, List<String> showDiff, String companyId){
        if(local!=null&&api!=null){
            if(local.size()!=api.size()){
                showDiff.add("companyID:"+companyId+",column:companyInvestorListAll"+",size不同,local:"+local.size()+",api:"+api.size());
            }else {
                Map<String,CompanyInvestor>  apiMap=new HashMap<String, CompanyInvestor>();
                //将list中元素根据name，存放到Map，方便进行对比
                for(int i=0;i<api.size();i++){
                    CompanyInvestor tmp=api.get(i);
                    apiMap.put(tmp.getName(),tmp);
                }
                for(int j=0;j<local.size();j++){
                    CompanyInvestor ltmp=local.get(j);
                    String lname=ltmp.getName();
                    if(lname==null){
                        showDiff.add("companyID:"+companyId+",column:companyInvestorListAll中name为空,local:"+ltmp.toString());
                    }else {
                        CompanyInvestor atmp = apiMap.get(lname);
                        if (ltmp != null && atmp != null) {
                            String res = ltmp.compareData(atmp);
                            if (res != "") {
                                showDiff.add(res);
                            }
                        } else {
                            showDiff.add("companyID:" + companyId + ",column:companyInvestorListAll中name" + ",值不同,local:" + ltmp.toString() + ",api:" + null);
                        }
                    }
                }
            }
        }else if(local==null&&api==null){

        }else{
            showDiff.add("companyID:"+companyId+",column:companyInvestorListAll null值,local:"+local+",api:"+api);
        }
    }

    //company_staff
    private void compareCompanyStaff(List<CompanyStaff> local, List<CompanyStaff> api, List<String> showDiff, String companyId) {
        if(local!=null&&api!=null){
            if(local.size()!=api.size()){
                showDiff.add("companyID:"+companyId+",column:companyStaff"+",size不同,local:"+local.size()+",api:"+api.size());
            }else {
                Map<String,CompanyStaff>  apiMap=new HashMap<String, CompanyStaff>();
                //将list中元素根据name，存放到Map，方便进行对比
                for(int i=0;i<api.size();i++){
                    CompanyStaff tmp=api.get(i);
                    apiMap.put(tmp.getName(),tmp);
                }
                for(int j=0;j<local.size();j++){
                    CompanyStaff ltmp=local.get(j);
                    String lname=ltmp.getName();
                    if(lname==null){
                        showDiff.add("companyID:"+companyId+",column:companyStaff,local:"+ltmp.toString());
                    }else {
                        CompanyStaff atmp = apiMap.get(lname);
                        if (ltmp != null && atmp != null) {
                            String res = ltmp.compareStaff(atmp);
                            if (res != "") {
                                showDiff.add(res);
                            }
                        } else {
                            showDiff.add("companyID:" + companyId + ",column:companyStaff中name" + ",值不同,local:" + ltmp.toString() + ",api:" + null);
                        }
                    }
                }
            }
        }
    }
    //company_change_info
    private void compareCompanyChange(List<CompanyChange> local, List<CompanyChange> api, List<String> showDiff, String companyId) {
        if(local!=null&&api!=null){
            if(local.size()!=api.size()){
                showDiff.add("companyID:"+companyId+",column:companyChange"+",size不同,local:"+local.size()+",api:"+api.size());
            }else {
                Map<String,CompanyChange>  apiMap=new HashMap<String, CompanyChange>();
                for(int i=0;i<api.size();i++){
                    CompanyChange tmp=api.get(i);
                    if(tmp.getId()==null||"".equals(tmp.getId())){
                        showDiff.add("companyID:"+companyId+",column:companyChange,id为null,api:"+tmp.toString());
                    }
                    apiMap.put(tmp.getId(),tmp);
                }
                for(int j=0;j<local.size();j++){
                    CompanyChange ltmp=local.get(j);
                    if (ltmp.getId()!=null){
                        CompanyChange atmp = apiMap.get(ltmp.getId());
                        if (ltmp != null && atmp != null) {
                            String res = ltmp.compareChange(atmp);
                            if (res != "") {
                                showDiff.add(res);
                            }
                        } else {
                            showDiff.add("companyID:" + companyId + ",column:companyChange，id,值不同,local:" + ltmp.toString() + ",api:" + null);
                        }
                    }
                }
            }
        }
    }
//    company_abnormal_info
    private void compareCompanyAbnormal(List<CompanyAbnormal> local, List<CompanyAbnormal> api, List<String> showDiff, String companyId) {
    if(local!=null&&api!=null){
        if(local.size()!=api.size()){
            showDiff.add("companyID:"+companyId+",column:companyAbnormal"+",size不同,local:"+local.size()+",api:"+api.size());
        }else {
            Map<String,CompanyAbnormal>  apiMap=new HashMap<String, CompanyAbnormal>();
            for(int i=0;i<api.size();i++){
                CompanyAbnormal tmp=api.get(i);
                if(tmp.getId()==null){
                    showDiff.add("companyID:"+companyId+",column:companyAbnormal,id为null,api:"+tmp.toString());
                }
                apiMap.put(tmp.getId(),tmp);
            }
            for(int j=0;j<local.size();j++){

                CompanyAbnormal ltmp=local.get(j);
                String id=ltmp.getId();
                if(id==null){
                    showDiff.add("companyID:"+companyId+",column:companyAbnormal,id 为null,local:"+ltmp.toString());
                }else {
                    CompanyAbnormal atmp = apiMap.get(id);
                    if (ltmp != null && atmp != null) {
                        String res = ltmp.compareAbnormal(atmp);
                        if (res != "") {
                            showDiff.add(res);
                        }
                    } else {
                        showDiff.add("companyID:" + companyId + ",column:companyAbnormal中putDepartment和putDate" + ",值不同,local:" + ltmp.toString() + ",api:" + null);
                    }
                }
            }
        }
    }
}
    public void showDiffInfo(){
        if(this.diffInfo!=null){
            for(int i=0;i<diffInfo.size();i++){
                if(!"".equals(diffInfo.get(i))) {
                    System.out.println(diffInfo.get(i));
                }
            }
        }
    }


}
