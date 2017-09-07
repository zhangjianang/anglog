package com.ang.anglog.tianyc.tycDao;


import com.ang.anglog.tianyc.Tools.DBUtils;
import com.ang.anglog.tianyc.Tools.ReadPropertyTool;
import com.ang.anglog.tianyc.Tools.StringUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by adimn on 2017/5/19.
 */
public class CompanyInvestorDao {
    private DBUtils dbUtils;
    public CompanyInvestorDao(){
        this.dbUtils=new DBUtils();
    }

    public CompanyInvestorDao(String no,Boolean isDefault){
        this.dbUtils=new DBUtils(no,isDefault);
    }

    public List<Map<String,Object>> getCompanyInvestorByCompanyId(String companyId){
        List<Map<String,Object>> res=null;
        List<Map<String,Object>> res2=null;
        if(companyId!=null&& StringUtil.isNotEmpty(companyId)){
            //old 20170831
//            String sql="select a.company_id,a.amount,b.id,b.name,b.type from company_investor as a LEFT JOIN human as b on a.investor_id=b.id where company_id= ?";

            String sql="select a.company_id,a.amount,a.investor_type,b.id,b.name from company_investor as a LEFT JOIN human as b on a.investor_id=b.id where company_id=? and a.investor_type=1";

            String sql2="select id, company_id,investor_id,amount,investor_type from company_investor where company_id = ? and investor_type =2";

            res= dbUtils.query(sql,companyId);

            res2= dbUtils.query(sql2,companyId);
            List<Map<String, Object>> compInvestor = conInvestorCompany(res2);
            return convertCompanyInvestorData(res,compInvestor);
        }
        return res;
    }

    private List<Map<String,Object>> conInvestorCompany(List<Map<String,Object>> res1){
        List<Map<String,Object>> lres=null;
        if(res1!=null) {
            lres = new ArrayList<Map<String, Object>>();
            for (Map<String, Object> per : res1) {
                Map conMap = new HashMap();
                conMap.put("amount", per.get("amount"));
                conMap.put("id", per.get("investor_id"));
                conMap.put("type", "非自然人股东");
                conMap.put("companyId", per.get("company_id"));
                Object investor_id = per.get("investor_id");
                if(investor_id!=null){
                    String investor_company_id=investor_id.toString();
                    String no = getModeRes(investor_company_id);
                    CompanyDao company = new CompanyDao(no,false);
                    Map<String, Object> companyInfo = company.getCompanyInfo(investor_company_id);
                    if(companyInfo!=null) {
                        conMap.put("name", companyInfo.get("name"));
                        conMap.put("base", companyInfo.get("base"));
                        conMap.put("regStatus", companyInfo.get("regStatus"));
                        conMap.put("legalPersonName", companyInfo.get("legalPersonName"));
                    }
                }
                lres.add(conMap);
            }
        }
        return lres;
    }

    private String getModeRes(String companyId){
        String fno= ReadPropertyTool.getPropertyByParam("splitdb.properties","splitnum");
        Long lno=1L;
        if(fno!=null&&!"".equals(fno)){
            lno= Long.parseLong(fno);
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

    private List<Map<String,Object>> convertCompanyInvestorData(List<Map<String,Object>> input,List<Map<String, Object>> compInvestor){
        List<Map<String,Object>> lres=null;
        if(input!=null) {
            lres = new ArrayList<Map<String, Object>>();
            for (Map<String, Object> tmpMap : input) {
                Map conMap = new HashMap();
                conMap.put("amount", tmpMap.get("amount"));
                conMap.put("id", tmpMap.get("id"));
                conMap.put("name", tmpMap.get("name"));
                conMap.put("type", "自然人股东");
                conMap.put("companyId", tmpMap.get("company_id"));
                lres.add(conMap);
            }
        }
        if(compInvestor!=null){
            if(lres==null)
                lres = new ArrayList<Map<String, Object>>();
            for(Map<String,Object> per:compInvestor){
                lres.add(per);
            }
        }

        return lres;
    }
}
