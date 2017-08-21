package com.ang.anglog.tianyc.tycModel.company;


import com.ang.anglog.tianyc.Tools.CompareTools;

/**
 * Created by adimn on 2017/6/27.
 */
public class CompanyChange {
    private String id;
    private String companyId;
    private String changeItem;
    private String changeTime;
    private String contentAfter;
    private String contentBefore;
    private String createtime;//改动，这里t必须小写了

    public String compareChange(CompanyChange cmp){
        StringBuilder sb=new StringBuilder("");
        if(!CompareTools.compareModelStrTime(this.createtime,cmp.createtime)){
            sb.append("companyChangeInfo中,companyId:"+this.companyId+",id:"+this.id+",createTime不一致：local,"+CompareTools.getStrTime(this.createtime)+",api:"+cmp.createtime+"\r\n");
        }
        if(!CompareTools.compareStringEqual(this.contentAfter,cmp.contentAfter)){
            sb.append("companyChangeInfo中,companyId:"+this.companyId+",contentAfter不一致：local"+this.contentAfter+",api:"+cmp.contentAfter+"\r\n");
        }
        if(!CompareTools.compareStringEqual(this.contentBefore,cmp.contentBefore)){
            sb.append("companyChangeInfo中,companyId:"+this.companyId+",contentBefore不一致：local"+this.contentBefore+",api:"+cmp.contentBefore+"\r\n");
        }
        if(!CompareTools.compareStringEqual(this.getChangeItem(),cmp.getChangeItem())){
            sb.append("companyChangeInfo中,companyId:"+this.companyId+",changeItem不一致：local"+this.changeItem+",api:"+cmp.changeItem+"\r\n");
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        return "CompanyChange{" +
                "id='" + id + '\'' +
                ", companyId='" + companyId + '\'' +
                ", changeItem='" + changeItem + '\'' +
                ", changeTime='" + changeTime + '\'' +
                ", contentAfter='" + contentAfter + '\'' +
                ", contentBefore='" + contentBefore + '\'' +
                ", createtime='" + createtime + '\'' +
                '}';
    }
    //待定，数据库中没有此字段
//    private List<String> companyName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getChangeItem() {
        return changeItem;
    }

    public void setChangeItem(String changeItem) {
        this.changeItem = changeItem;
    }

    public String getChangeTime() {
        return changeTime;
    }

    public void setChangeTime(String changeTime) {
        this.changeTime = changeTime;
    }

    public String getContentAfter() {
        return contentAfter;
    }

    public void setContentAfter(String contentAfter) {
        this.contentAfter = contentAfter;
    }

    public String getContentBefore() {
        return contentBefore;
    }

    public void setContentBefore(String contentBefore) {
        this.contentBefore = contentBefore;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

//    public List<String> getCompanyName() {
//        return companyName;
//    }
//
//    public void setCompanyName(List<String> companyName) {
//        this.companyName = companyName;
//    }
}
