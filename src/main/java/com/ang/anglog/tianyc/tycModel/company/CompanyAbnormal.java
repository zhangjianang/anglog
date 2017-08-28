package com.ang.anglog.tianyc.tycModel.company;


import com.ang.anglog.tianyc.Tools.CompareTools;

/**
 * Created by adimn on 2017/6/27.
 */
public class CompanyAbnormal {
    private String companyId;
    private String createtime;//mysql中错误
    private String id;

    private String putDate;
    private String putDepartment;
    private String putReason;

    private String removeDate;
    private String removeDepartment;
    private String removeReason;

    public String compareAbnormal(CompanyAbnormal cmp){
        StringBuilder sb =new StringBuilder("");
        if(!CompareTools.compareModelStrTime(this.putDate,cmp.putDate)){
            sb.append("companyAbnormalInfoList中,companyId"+this.companyId+"putDate不一致：local"+this.putDate+",api:"+cmp.putDate);
        }
        if(!CompareTools.compareStringEqual(this.putReason,cmp.putReason)){
            sb.append("companyAbnormalInfoList 中,companyId"+this.companyId+"putReason不一致：local"+this.putReason+",api:"+cmp.putReason);
        }
        if(!CompareTools.compareStringEqual(this.putDepartment,cmp.putDepartment)){
            sb.append("companyAbnormalInfoList 中,companyId"+this.companyId+"putDepartment不一致：local"+this.putDepartment+",api:"+cmp.putDepartment);
        }
        if(!CompareTools.compareModelStrTime(this.removeDate,cmp.removeDate)){
            sb.append("companyAbnormalInfoList中,companyId"+this.companyId+"removeDate不一致：local"+this.removeDate+",api:"+cmp.removeDate);
        }
        if(!CompareTools.compareStringEqual(this.removeReason,cmp.removeReason)){
            sb.append("companyAbnormalInfoList 中,companyId"+this.companyId+"removeReason不一致：local"+this.removeReason+",api:"+cmp.removeReason);
        }
        if(!CompareTools.compareStringEqual(this.removeDepartment,cmp.removeDepartment)){
            sb.append("companyAbnormalInfoList 中,companyId"+this.companyId+"removeDepartment不一致：local"+this.removeDepartment+",api:"+cmp.removeDepartment);
        }
        return sb.toString().replaceAll("(?m)^\\s*$(\\n|\\r\\n)", "");
    }

    @Override
    public String toString() {
        return "CompanyAbnormal{" +
                "companyId='" + companyId + '\'' +
                ", createtime='" + createtime + '\'' +
                ", id='" + id + '\'' +
                ", putDate='" + putDate + '\'' +
                ", putDepartment='" + putDepartment + '\'' +
                ", putReason='" + putReason + '\'' +
                ", removeDate='" + removeDate + '\'' +
                ", removeDepartment='" + removeDepartment + '\'' +
                ", removeReason='" + removeReason + '\'' +
                '}';
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPutDate() {
        return putDate;
    }

    public void setPutDate(String putDate) {
        this.putDate = putDate;
    }

    public String getPutDepartment() {
        return putDepartment;
    }

    public void setPutDepartment(String putDepartment) {
        this.putDepartment = putDepartment;
    }

    public String getPutReason() {
        return putReason;
    }

    public void setPutReason(String putReason) {
        this.putReason = putReason;
    }

    public String getRemoveDate() {
        return removeDate;
    }

    public void setRemoveDate(String removeDate) {
        this.removeDate = removeDate;
    }

    public String getRemoveDepartment() {
        return removeDepartment;
    }

    public void setRemoveDepartment(String removeDepartment) {
        this.removeDepartment = removeDepartment;
    }

    public String getRemoveReason() {
        return removeReason;
    }

    public void setRemoveReason(String removeReason) {
        this.removeReason = removeReason;
    }
}
