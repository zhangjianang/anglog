package com.ang.anglog.tianyc.tycDao;


import com.ang.anglog.tianyc.Tools.DBUtils;
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
        if(companyId!=null&& StringUtil.isNotEmpty(companyId)){
            String sql="select a.company_id,a.amount,b.id,b.name,b.type from company_investor as a LEFT JOIN human as b on a.investor_id=b.id where company_id= ?";
            res= dbUtils.query(sql,companyId);
            return convertCompanyInvestorData(res);
        }
        return res;
    }

    private List<Map<String,Object>> convertCompanyInvestorData(List<Map<String,Object>> input){
        List<Map<String,Object>> lres=null;
        if(input!=null) {
            lres = new ArrayList<Map<String, Object>>();
            for (Map<String, Object> tmpMap : input) {
                Map conMap = new HashMap();
                conMap.put("amount", tmpMap.get("amount"));
                conMap.put("id", tmpMap.get("id"));
                conMap.put("name", tmpMap.get("name"));
                conMap.put("type", tmpMap.get("type"));
                conMap.put("companyId", tmpMap.get("company_id"));
                lres.add(conMap);
            }
        }

        return lres;
    }
}
