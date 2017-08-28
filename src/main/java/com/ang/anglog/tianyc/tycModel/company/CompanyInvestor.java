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

    public String compareData(CompanyInvestor cmp){
        StringBuilder sb=new StringBuilder();
        if(!CompareTools.compareModelNumEqual(this.getAmount(),cmp.getAmount())){
            sb.append("companyInvestor中,amount值不同,id:"+this.id+",name:"+this.name+",local:"+this.getAmount()+"对比值："+cmp.getAmount());
        }
        if(!CompareTools.compareStringEqual(this.name,cmp.name)){
            sb.append("companyInvestor中,值不同,id:"+this.id+",name:"+this.name+",local:"+this.name+"对比值："+cmp.name);
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
}
