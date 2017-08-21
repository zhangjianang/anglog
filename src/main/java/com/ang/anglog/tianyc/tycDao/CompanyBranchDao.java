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
//// TODO: 2017/5/22  test
public class CompanyBranchDao {
    private DBUtils dbUtils;
    public CompanyBranchDao(){
        this.dbUtils=new DBUtils();
    }

    /**
     * 增加分库信息
     * @param no
     * @param isDefault
     */
    public CompanyBranchDao(String no,Boolean isDefault){
        this.dbUtils=new DBUtils(no,isDefault);
    }

    public List<Map<String,Object>> getCompanyBranchByCompanyId(String companyId){
        List<Map<String,Object>> res=null;
        if(companyId!=null&& StringUtil.isNotEmpty(companyId)){
//            String sql="select category_code,name,reg_number,reg_status,legal_person_name from company LEFT JOIN company_category on company.id = company_category.company_id  where company.parent_id = ?";
            String sql="select company_id, category_code,name,reg_number,reg_status,legal_person_name from company LEFT JOIN company_category_20170411 on company.id = company_category_20170411.company_id  where company.parent_id = ?";
            res= dbUtils.query(sql,companyId);
            return convertCompanyBranchData(res);
        }
        return res;
    }

    private List<Map<String,Object>> convertCompanyBranchData(List<Map<String,Object>> input){
        List<Map<String,Object>> lres=null;
        if(input!=null) {
            lres = new ArrayList<Map<String, Object>>();
            for (Map<String, Object> tmpMap : input) {
                Map conMap = new HashMap();
                conMap.put("childname", tmpMap.get("name"));
                conMap.put("regNumber", tmpMap.get("reg_number"));
                conMap.put("regStatus", tmpMap.get("reg_status"));
                //// TODO: 2017/5/23 确定category_code
                //company_category
                conMap.put("category_code", tmpMap.get("category_code"));
                conMap.put("legalPersonName", tmpMap.get("legal_person_name"));
                conMap.put("companyId", tmpMap.get("company_id"));
                lres.add(conMap);
            }
        }
        return lres;
    }
}
