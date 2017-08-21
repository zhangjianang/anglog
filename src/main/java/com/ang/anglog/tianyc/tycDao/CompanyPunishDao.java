package com.ang.anglog.tianyc.tycDao;


import com.ang.anglog.tianyc.Tools.ConvertTool;
import com.ang.anglog.tianyc.Tools.DBUtils;
import com.ang.anglog.tianyc.Tools.StringUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by adimn on 2017/5/19.
 */
public class CompanyPunishDao {
    private DBUtils dbUtils;
    public CompanyPunishDao(){
        this.dbUtils=new DBUtils();
    }

    public CompanyPunishDao(String no,Boolean isDefault){
        this.dbUtils=new DBUtils(no,isDefault);
    }

    public List<Map<String,Object>> getPunishInfoByCompanyId(String companyId){
        List<Map<String,Object>> result=null;
        if(companyId!=null&& StringUtil.isNotEmpty(companyId)){
            String sql="select company_id,base,content,department_name,name,punish_number,type from company_punishment_info where company_id=?";
            result= dbUtils.query(sql,companyId);
            return conPunishData(result);
        }
        return result;
    }
    private List<Map<String,Object>> conPunishData(List<Map<String,Object>> input){
        List<Map<String,Object>> conList=null;
        Map<String,Object> conMap=null;
        if(input!=null&&input.size()>0){
            conList=new ArrayList<Map<String, Object>>();
            for(Map<String,Object> tmp:input) {
                conMap=new HashMap();
                conMap.put("base",tmp.get("base"));
                conMap.put("content",tmp.get("content"));
                conMap.put("punishNumber",tmp.get("punish_number"));
                conMap.put("type",tmp.get("type"));
                conMap.put("name",tmp.get("name"));
                conMap.put("departmentName",tmp.get("department_name"));
                if( tmp.get("base")!=null) {
                    conMap.put("province", ConvertTool.code2province.get(tmp.get("base").toString().toUpperCase()));
                }else{
                    conMap.put("province","");
                }
                conMap.put("companyId",tmp.get("company_id"));
                conList.add(conMap);
            }
        }
        return conList;
    }
}
