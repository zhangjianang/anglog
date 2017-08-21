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
public class CompanyStaffDao {
    private DBUtils dbUtils;
    public CompanyStaffDao(){
        this.dbUtils=new DBUtils();
    }
    /*增加分库信息*/
    public CompanyStaffDao(String no,Boolean isDefault){
        this.dbUtils=new DBUtils(no,isDefault);
    }

    public List<Map<String,Object>> getCompanyStaffByCompanyId(String companyId){
        List<Map<String,Object>> res=null;
        if(companyId!=null&& StringUtil.isNotEmpty(companyId)){
            String sql="select company_id,human.id,name,type,staff_type_name from company_staff left join human on company_staff.staff_id = human.id where company_id = ?";
            res= dbUtils.query(sql,companyId);
            return convertCompanyStaffData(res);
        }
        return res;
    }
    //// TODO: 2017/6/14 更新程序了，合并信息
    private List<Map<String,Object>> convertCompanyStaffData(List<Map<String,Object>> input){
        List<Map<String,Object>> lres=null;
        if(input!=null) {
            lres = new ArrayList<Map<String, Object>>();
            for (Map<String, Object> tmpMap : input) {
//                Map conMap = new HashMap();
//                conMap.put("id", tmpMap.get("id"));
//                conMap.put("name", tmpMap.get("name"));
//                conMap.put("type", tmpMap.get("type"));
//                conMap.put("typeJoin", tmpMap.get("staff_type_name"));
//                lres.add(conMap);

                checkAdd(tmpMap.get("company_id"),tmpMap.get("id"),tmpMap.get("name"),tmpMap.get("type"),tmpMap.get("staff_type_name"),lres);
            }
        }
        return lres;
    }
    //// TODO: 2017/6/14  更新其他版本的数据

    private void checkAdd(Object companyId,Object id,Object name,Object type,Object typeJoin,List<Map<String,Object>> newResList){
        if(newResList!=null){
            boolean  addFlag=true;
            for(int i=0;i<newResList.size();i++){
                Map<String,Object> curMap=newResList.get(i);
                if(curMap.get("id")!=null&&curMap.get("name")!=null&&curMap.get("type")!=null) {
                    if (curMap.get("id").equals(id) && curMap.get("name").equals(name) && curMap.get("type").equals(type)) {
                        String join = curMap.get("typeJoin").toString();
                        join += "," + typeJoin;
                        curMap.put("typeJoin", join);
                        addFlag = false;
                    }
                }
            }
            if(addFlag){
                Map conMap = new HashMap();
                conMap.put("id", id);
                conMap.put("name", name);
                conMap.put("type", type);
                conMap.put("typeJoin", typeJoin);
                conMap.put("companyId", companyId);
                newResList.add(conMap);
            }
        }
    }


}
