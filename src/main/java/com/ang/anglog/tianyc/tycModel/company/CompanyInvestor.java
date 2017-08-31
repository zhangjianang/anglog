package com.ang.anglog.tianyc.tycModel.company;


import com.ang.anglog.tianyc.Tools.CompareTools;

/**
 * Created by adimn on 2017/6/27.
 */
public class CompanyInvestor {
    private String amount;
    private String id;
    private String name;
    private String type;
    private String companyId;

    private String base;
    private String regStatus;
    private String legalPersonName;

    public String compareData(CompanyInvestor cmp){
        StringBuilder sb=new StringBuilder();
        if(!CompareTools.compareModelNumEqual(getAmount(),cmp.getAmount())){
            sb.append("companyInvestor中,amount值不同,id:"+id+",name:"+name+",local:"+getAmount()+"对比值："+cmp.getAmount());
        }
        if(!CompareTools.compareStringEqual(name,cmp.name)){
            sb.append("companyInvestor中,值不同,id:"+id+",name:"+name+",local:"+name+"对比值："+cmp.name);
        }
        if(!CompareTools.compareStringEqual(base,cmp.base)){
            sb.append("companyInvestor中,值不同,id:"+id+",name:"+name+",local:"+base+"对比值："+cmp.base);
        }
        if(!CompareTools.compareStringEqual(regStatus,cmp.regStatus)){
            sb.append("companyInvestor中,值不同,id:"+id+",name:"+name+",local:"+regStatus+"对比值："+cmp.regStatus);
        }
        if(!CompareTools.compareStringEqual(legalPersonName,cmp.legalPersonName)){
            sb.append("companyInvestor中,值不同,id:"+id+",name:"+name+",local:"+legalPersonName+"对比值："+cmp.legalPersonName);
        }
        return sb.toString().replaceAll("(?m)^\\s*$(\\n|\\r\\n)", "");
    }

    @Override
    public String toString() {
        return "CompanyInvestor{" +
                "amount='" + amount + '\'' +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", companyId='" + companyId + '\'' +
                ", base='" + base + '\'' +
                ", regStatus='" + regStatus + '\'' +
                ", legalPersonName='" + legalPersonName + '\'' +
                '}';
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public String getRegStatus() {
        return regStatus;
    }

    public void setRegStatus(String regStatus) {
        this.regStatus = regStatus;
    }

    public String getLegalPersonName() {
        return legalPersonName;
    }

    public void setLegalPersonName(String legalPersonName) {
        this.legalPersonName = legalPersonName;
    }
}
