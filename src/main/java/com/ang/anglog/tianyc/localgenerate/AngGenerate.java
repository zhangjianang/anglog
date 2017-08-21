package com.ang.anglog.tianyc.localgenerate;



import com.ang.anglog.tianyc.Tools.DBUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by adimn on 2017/8/15.
 */
public class AngGenerate {
    private DBUtils dbUtils = new DBUtils();

    public String getIdByName(String name){
        String companyId ="";
        String sql = "select id from company where name = ?";
        List<Map<String, Object>> query = dbUtils.inQuery(sql, name);
        if(query.size()>0){
            Map<String, Object> findMap = query.get(0);
            companyId = findMap.get("id").toString();
        }
        return companyId;
    }

    public String getHumanIdByCompanyId(String companyId){
        List<String> humanIds= new ArrayList<String>();
        String sql = "select investor_id from company_investor where company_id = ?";
        List<Map<String, Object>> investors = dbUtils.inQuery(sql, companyId);
        for(Map<String,Object> per : investors){
            String investor_id = per.get("investor_id").toString();
            humanIds.add(investor_id);
        }
        String sql2 = "select staff_id  from company_staff where company_id = ?";
        List<Map<String, Object>> staff = dbUtils.inQuery(sql2, companyId);
        for(Map<String,Object> per : staff){
            String investor_id = per.get("staff_id").toString();
            humanIds.add(investor_id);
        }
        return convertList2String(humanIds);
    }

    public String getAnnualIdByCompanyId(String companyId){
        List<String> annualIds = new ArrayList<String>();
        String sql = "select id from annual_report where company_id = ?";
        List<Map<String, Object>> annual = dbUtils.inQuery(sql, companyId);
        if(annual.size()>0){
            for(Map<String,Object> per :annual){
                String perId = per.get("id").toString();
                annualIds.add(perId);
            }
        }
        return convertList2String(annualIds);
    }

    public String convertList2String(List<String> inlist){
        if(inlist == null || !(inlist.size()>0)){
            return "(-1)";
        }else {
            StringBuilder sb = new StringBuilder();
            sb.append("(");
            for(String cur : inlist){
                sb.append(cur+",");
            }
            String subs = sb.toString().substring(0, sb.length() - 1);
            subs += ")";
            return subs;
        }
    }

    public static void main(String[] args) {
        AngGenerate ag= new AngGenerate();
        String id = ag.getIdByName("深圳怡化电脑股份有限公司1");
        System.out.println(id);
        System.out.println(ag.getHumanIdByCompanyId(id));
        System.out.println(ag.getAnnualIdByCompanyId(id));
    }

}
