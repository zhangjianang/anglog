package com.ang.anglog.tianyc.tycModel.company;


import com.ang.anglog.tianyc.tycModel.mortgage.*;

import java.util.List;

/**
 * Created by adimn on 2017/6/27.
 */
public class CompanyMortgage {

    private String amount;
    private String base;
    private String id;
    private String overviewTerm;

    private String province;

    private String publishDate;
    private String regDate;
    private String regDepartment;
    private String regNum;
    private String remark;

    private String scope;
    private String status;
    private String type;


    private List<MortgageChange> mchangelist;
    private List<MortgagePawn> mpawnlist;
    private List<MortgagePeople> mpeoplelist;

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOverviewTerm() {
        return overviewTerm;
    }

    public void setOverviewTerm(String overviewTerm) {
        this.overviewTerm = overviewTerm;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public String getRegDate() {
        return regDate;
    }

    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }

    public String getRegDepartment() {
        return regDepartment;
    }

    public void setRegDepartment(String regDepartment) {
        this.regDepartment = regDepartment;
    }

    public String getRegNum() {
        return regNum;
    }

    public void setRegNum(String regNum) {
        this.regNum = regNum;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<MortgageChange> getMchangelist() {
        return mchangelist;
    }

    public void setMchangelist(List<MortgageChange> mchangelist) {
        this.mchangelist = mchangelist;
    }

    public List<MortgagePawn> getMpawnlist() {
        return mpawnlist;
    }

    public void setMpawnlist(List<MortgagePawn> mpawnlist) {
        this.mpawnlist = mpawnlist;
    }

    public List<MortgagePeople> getMpeoplelist() {
        return mpeoplelist;
    }

    public void setMpeoplelist(List<MortgagePeople> mpeoplelist) {
        this.mpeoplelist = mpeoplelist;
    }
}
