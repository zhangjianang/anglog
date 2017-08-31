package com.ang.anglog.tianyc;

import com.ang.anglog.tianyc.Tools.ReadPropertyTool;
import com.ang.anglog.tianyc.Tools.StringUtil;
import com.ang.anglog.tianyc.tycDao.*;
import com.ang.anglog.tianyc.tycModel.CompanyManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * Created by adimn on 2017/5/22.
 */
public class SearchDetailInfo {
    private  static Logger logger = LoggerFactory.getLogger(SearchDetailInfo.class);
    private CompanyDao companyDao;
    private CompanyPunishDao punishDao;
    private CompanyChageInfoDao companyChageInfoDao;
    private CompanyCheckInfoDao companyCheckInfoDao;
    private CompanyEquityDao companyEquityDao;
    private CompanyInvestorDao companyInvestorDao;
    private CompanyStaffDao companyStaffDao;
    private CompanyBranchDao companyBranchDao;

    private AnnualReprotDao annualReprotDao;
    private MortgageInfoDao mortgageInfoDao;
    private CompanyAbnormalDao companyAbnormalDao;

    private int splitnum=0;
    public SearchDetailInfo(){
        companyDao=new CompanyDao();
        punishDao=new CompanyPunishDao();
        companyChageInfoDao=new CompanyChageInfoDao();
        companyCheckInfoDao=new CompanyCheckInfoDao();
        companyEquityDao=new CompanyEquityDao();
        companyInvestorDao=new CompanyInvestorDao();
        companyStaffDao=new CompanyStaffDao();
        companyBranchDao=new CompanyBranchDao();

        annualReprotDao=new AnnualReprotDao();
        mortgageInfoDao=new MortgageInfoDao();
        companyAbnormalDao=new CompanyAbnormalDao();
    }

    /*取消掉默认构造，采用延迟初始化类
    * 不能确定n的数量
    * */
    public SearchDetailInfo(Integer n){

    }

    private String getModeRes(String companyId){
        String fno= ReadPropertyTool.getPropertyByParam("splitdb.properties","splitnum");
        Long lno=1L;

        if(fno!=null&&!"".equals(fno)){
            lno= Long.parseLong(fno);
            splitnum= Integer.parseInt(fno);
        }
        if(companyId!=null&&!"".equals(companyId)) {
            Long clong = Long.parseLong(companyId);
            if(clong<0){
                return "0";
            }
            String no = String.valueOf(clong % lno);
            return no;
        }
        return "";
    }




    /*根据companyId取*/
    private Boolean initByDaoByCompanyId(String companyId){
        //确定取膜数量
        if("".equals(companyId)){
            logger.warn("传入companyId为空!!");
            return  false;
        }
        String no=getModeRes(companyId);
        if(!"".equals(no)) {
            companyDao = new CompanyDao(no, false);
            punishDao = new CompanyPunishDao(no, false);
            companyChageInfoDao = new CompanyChageInfoDao(no, false);
            companyCheckInfoDao = new CompanyCheckInfoDao(no, false);
            companyEquityDao = new CompanyEquityDao(no, false);
            companyInvestorDao = new CompanyInvestorDao(no, false);
            companyStaffDao = new CompanyStaffDao(no, false);
            //采用默认库
            annualReprotDao = new AnnualReprotDao(no, true);
            mortgageInfoDao = new MortgageInfoDao(no, false);
            companyAbnormalDao = new CompanyAbnormalDao(no, false);
            return true;
        }else{
            //如果为空，就设置为默认连接
            logger.warn("根据id取模失败!!");
            return false;
        }
    }


    private  void initBranchDaoByParentId(String num){
        if(num!=null&&"".equals(num)){
            companyBranchDao=new CompanyBranchDao();
        }else{
            companyBranchDao=new CompanyBranchDao(num,false);
        }
    }


    public Map<String,Object> getDetailInfoByCompanyId(String companyId){
        Map<String,Object> resultMap=null;
//        Map<String,Object> allResMap=new HashMap<String, Object>(3);
        if(companyId!=null&& StringUtil.isNotEmpty(companyId)){
            //插入基本信息
            resultMap=companyDao.getCompanyInfo(companyId);
            if(resultMap!=null){
                //增加其他信息
                resultMap.put("annualReportList",annualReprotDao.getAnnualReportListBaseInfo(companyId));
                resultMap.put("branchList",companyBranchDao.getCompanyBranchByCompanyId(companyId));
                resultMap.put("checkList",companyCheckInfoDao.getCheckInfoById(companyId));
                resultMap.put("comChanInfoList",companyChageInfoDao.getCompanyChangeInfoByCompanyId(companyId));
                resultMap.put("comAbnoInfoList",companyAbnormalDao.getCompanyAbnormalByCompanyId(companyId));
                resultMap.put("equityList",companyEquityDao.getEquityInfoByCompanyId(companyId));
                resultMap.put("illegalList",null);//非工商数据
                resultMap.put("investList",null);//非工商数据
                resultMap.put("investorListAll",companyInvestorDao.getCompanyInvestorByCompanyId(companyId));
                resultMap.put("mortgageList",mortgageInfoDao.getMortgageInfoByCompanyId(companyId));
                resultMap.put("punishList",punishDao.getPunishInfoByCompanyId(companyId));
                resultMap.put("staffListAll",companyStaffDao.getCompanyStaffByCompanyId(companyId));

            }
        }
        return resultMap;
    }


    /*根据companyId拆分库进行查找*/
    public Map<String,Object> getSplitDetailInfoByCompanyId(String companyId) {
        /*设置拆分信息*/

        try {
            Boolean isModOk = initByDaoByCompanyId(companyId);
            if(!isModOk){
                return null;
            }
        } catch (Exception e) {
            System.out.println("SearchDetailInfo   initByDaoByCompanyId(companyId) : " + companyId);
            e.printStackTrace();
        }

        Map<String, Object> resultMap = null;
//        Map<String,Object> allResMap=new HashMap<String, Object>(3);
        if (companyId != null && StringUtil.isNotEmpty(companyId)) {
            //插入基本信息
            resultMap = companyDao.getCompanyInfo(companyId);
            if (resultMap != null) {
                //增加其他信息
                resultMap.put("annualReportList", annualReprotDao.getAnnualReportListBaseInfo(companyId));
                resultMap.put("checkList", companyCheckInfoDao.getCheckInfoById(companyId));
                resultMap.put("comChanInfoList", companyChageInfoDao.getCompanyChangeInfoByCompanyId(companyId));
                resultMap.put("comAbnoInfoList", companyAbnormalDao.getCompanyAbnormalByCompanyId(companyId));
                resultMap.put("equityList", companyEquityDao.getEquityInfoByCompanyId(companyId));
                resultMap.put("illegalList", null);//非工商数据
                resultMap.put("investList", null);//非工商数据
                resultMap.put("investorListAll", companyInvestorDao.getCompanyInvestorByCompanyId(companyId));
                resultMap.put("mortgageList", mortgageInfoDao.getMortgageInfoByCompanyId(companyId));
                resultMap.put("punishList", punishDao.getPunishInfoByCompanyId(companyId));
                resultMap.put("staffListAll", companyStaffDao.getCompanyStaffByCompanyId(companyId));

                /*更改根据parent_id重新划分分区*/
                if (this.splitnum > 0) {
                    List<Map<String, Object>> branchList = new ArrayList<Map<String, Object>>();
                    for (int i = 0; i < this.splitnum; i++) {
                        initBranchDaoByParentId(i + "");
                        List<Map<String, Object>> tmp = companyBranchDao.getCompanyBranchByCompanyId(companyId);
                        if (tmp.size() > 0) {
                            for (Map<String, Object> tmap : tmp) {
                                branchList.add(tmap);
                            }
                        }
                    }
                    if (branchList.size() > 0) {
                        resultMap.put("branchList", branchList);
                    } else {
                        resultMap.put("branchList", null);
                    }
                }

            }
        }
        return resultMap;
    }

}
