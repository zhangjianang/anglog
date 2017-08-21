package com.ang.anglog.tianyc.tycDao;



import com.ang.anglog.tianyc.Tools.DBUtils;
import com.ang.anglog.tianyc.Tools.StringUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by adimn on 2017/5/22.
 */
//// TODO: 2017/5/22  test
public class CompanyCheckInfoDao {
    private DBUtils dbUtils;
    public CompanyCheckInfoDao(){
        this.dbUtils=new DBUtils();
    }

    /*增加分库信息*/
    public CompanyCheckInfoDao(String no,Boolean isDefault){
        this.dbUtils=new DBUtils(no,isDefault);
    }

    public List<Map<String,Object>> getCheckInfoById(String companyId){
        List<Map<String,Object>>  res=null;
        if(companyId!=null&& StringUtil.isNotEmpty(companyId)){
            String sql="select company_id,check_org,check_type,check_date,check_result from company_check_info where company_id = ?";
            res= dbUtils.query(sql,companyId);
            return convertCheckInfoData(res);
        }
        return res;
    }

    private List<Map<String,Object>> convertCheckInfoData(List<Map<String,Object>> input){
        List<Map<String,Object>> conList=null;
        Map<String,Object> conMap=null;
        if(input!=null&&input.size()>0){
            conList=new ArrayList<Map<String, Object>>();
            for(Map<String,Object> tmp:input) {
                conMap=new HashMap();
                conMap.put("checkDate",tmp.get("check_date"));
                conMap.put("checkOrg",tmp.get("check_org"));
                conMap.put("checkResult",tmp.get("check_result"));
                conMap.put("checkType",tmp.get("check_type"));
                conMap.put("companyId",tmp.get("company_id"));
                conList.add(conMap);
            }
        }
        return conList;
    }
}
