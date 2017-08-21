package com.ang.anglog.tianyc.tycModel.company;

import java.util.List;

/**
 * Created by adimn on 2017/6/27.
 */
public class Company {
    private String actualCapital;
    private String approvedTime;
    private String base;
    private String businessScope;
    private String companyId;
    private String id;
    private String companyOrgType;
    private String companyType;
    private String flag;
    private String fromTime;
    private String legalPersonId;//不一样，don't know why
    private String legalPersonName;
    private String name;
    private String orgNumber;
    private String regCapital;
    private String regInstitute;

    private String regLocation;
    private String regNumber;  //又不一样
    private String regStatus;
    private String sourceFlag;
    private String updatetime;

    // 沟通之后，增加字段
    private String creditCode;
    private String updateTimes;
    private String term;    // ",tmp.get("from_time")+"至"+tmp.get("to_time"));
    private String type;
    private String correctCompanyId;//id

    private String categoryScore;
    private String category_code ;//", getCompanyCategoryCodeByCompanyId(tmp.get("id").toString()));
    private String companyUrl;

    private String estiblishTime;
    private String industry;

    private String keyword;
    private String percentileScore;
    private String parent_id;


    private List<AnnualReport> annualReportList;
    private List<CompanyBranch> branchList;
    private List<CompanyCheck> checkList;
    private List<CompanyChange> comChanInfoList;
    private List<CompanyAbnormal> comAbnoInfoList;
    private List<CompanyEquity> equityList;
//    private List<CompanyIllegal> illegalList;//非工商数据
//    private List<CompanyInvest> investList;//非工商数据
    private List<CompanyInvestor> investorListAll;
    private List<CompanyMortgage> mortgageList;
//    private List<CompanyPunish> punishList;
    private List<CompanyStaff> staffListAll;

    @Override
    public String toString() {
        return "actualCapital:"+actualCapital+",regStatus"+regStatus+",annualReportList"+annualReportList;
    }



    public String getActualCapital() {
        return actualCapital;
    }

    public void setActualCapital(String actualCapital) {
        this.actualCapital = actualCapital;
    }

    public String getApprovedTime() {
        return approvedTime;
    }

    public void setApprovedTime(String approvedTime) {
        this.approvedTime = approvedTime;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public String getBusinessScope() {
        return businessScope;
    }

    public void setBusinessScope(String businessScope) {
        this.businessScope = businessScope;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCompanyOrgType() {
        return companyOrgType;
    }

    public void setCompanyOrgType(String companyOrgType) {
        this.companyOrgType = companyOrgType;
    }

    public String getCompanyType() {
        return companyType;
    }

    public void setCompanyType(String companyType) {
        this.companyType = companyType;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getFromTime() {
        return fromTime;
    }

    public void setFromTime(String fromTime) {
        this.fromTime = fromTime;
    }

    public String getLegalPersonId() {
        return legalPersonId;
    }

    public void setLegalPersonId(String legalPersonId) {
        this.legalPersonId = legalPersonId;
    }

    public String getLegalPersonName() {
        return legalPersonName;
    }

    public void setLegalPersonName(String legalPersonName) {
        this.legalPersonName = legalPersonName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrgNumber() {
        return orgNumber;
    }

    public void setOrgNumber(String orgNumber) {
        this.orgNumber = orgNumber;
    }

    public String getRegCapital() {
        return regCapital;
    }

    public void setRegCapital(String regCapital) {
        this.regCapital = regCapital;
    }

    public String getRegInstitute() {
        return regInstitute;
    }

    public void setRegInstitute(String regInstitute) {
        this.regInstitute = regInstitute;
    }

    public String getRegLocation() {
        return regLocation;
    }

    public void setRegLocation(String regLocation) {
        this.regLocation = regLocation;
    }

    public String getRegNumber() {
        return regNumber;
    }

    public void setRegNumber(String regNumber) {
        this.regNumber = regNumber;
    }

    public String getRegStatus() {
        return regStatus;
    }

    public void setRegStatus(String regStatus) {
        this.regStatus = regStatus;
    }

    public String getSourceFlag() {
        return sourceFlag;
    }

    public void setSourceFlag(String sourceFlag) {
        this.sourceFlag = sourceFlag;
    }

    public String getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }

    public String getCreditCode() {
        return creditCode;
    }

    public void setCreditCode(String creditCode) {
        this.creditCode = creditCode;
    }

    public String getUpdateTimes() {
        return updateTimes;
    }

    public void setUpdateTimes(String updateTimes) {
        this.updateTimes = updateTimes;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCorrectCompanyId() {
        return correctCompanyId;
    }

    public void setCorrectCompanyId(String correctCompanyId) {
        this.correctCompanyId = correctCompanyId;
    }

    public String getCategoryScore() {
        return categoryScore;
    }

    public void setCategoryScore(String categoryScore) {
        this.categoryScore = categoryScore;
    }

    public String getCategory_code() {
        return category_code;
    }

    public void setCategory_code(String category_code) {
        this.category_code = category_code;
    }

    public String getCompanyUrl() {
        return companyUrl;
    }

    public void setCompanyUrl(String companyUrl) {
        this.companyUrl = companyUrl;
    }

    public String getEstiblishTime() {
        return estiblishTime;
    }

    public void setEstiblishTime(String estiblishTime) {
        this.estiblishTime = estiblishTime;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getPercentileScore() {
        return percentileScore;
    }

    public void setPercentileScore(String percentileScore) {
        this.percentileScore = percentileScore;
    }

    public String getParent_id() {
        return parent_id;
    }

    public void setParent_id(String parent_id) {
        this.parent_id = parent_id;
    }

    public List<AnnualReport> getAnnualReportList() {
        return annualReportList;
    }

    public void setAnnualReportList(List<AnnualReport> annualReportList) {
        this.annualReportList = annualReportList;
    }

    public List<CompanyBranch> getBranchList() {
        return branchList;
    }

    public void setBranchList(List<CompanyBranch> branchList) {
        this.branchList = branchList;
    }

    public List<CompanyCheck> getCheckList() {
        return checkList;
    }

    public void setCheckList(List<CompanyCheck> checkList) {
        this.checkList = checkList;
    }

    public List<CompanyChange> getComChanInfoList() {
        return comChanInfoList;
    }

    public void setComChanInfoList(List<CompanyChange> comChanInfoList) {
        this.comChanInfoList = comChanInfoList;
    }

    public List<CompanyAbnormal> getComAbnoInfoList() {
        return comAbnoInfoList;
    }

    public void setComAbnoInfoList(List<CompanyAbnormal> comAbnoInfoList) {
        this.comAbnoInfoList = comAbnoInfoList;
    }

    public List<CompanyEquity> getEquityList() {
        return equityList;
    }

    public void setEquityList(List<CompanyEquity> equityList) {
        this.equityList = equityList;
    }

//    public List<CompanyIllegal> getIllegalList() {
//        return illegalList;
//    }
//
//    public void setIllegalList(List<CompanyIllegal> illegalList) {
//        this.illegalList = illegalList;
//    }
//
//    public List<CompanyInvest> getInvestList() {
//        return investList;
//    }
//
//    public void setInvestList(List<CompanyInvest> investList) {
//        this.investList = investList;
//    }

    public List<CompanyInvestor> getInvestorListAll() {
        return investorListAll;
    }

    public void setInvestorListAll(List<CompanyInvestor> investorListAll) {
        this.investorListAll = investorListAll;
    }

    public List<CompanyMortgage> getMortgageList() {
        return mortgageList;
    }

    public void setMortgageList(List<CompanyMortgage> mortgageList) {
        this.mortgageList = mortgageList;
    }

//    public List<CompanyPunish> getPunishList() {
//        return punishList;
//    }
//
//    public void setPunishList(List<CompanyPunish> punishList) {
//        this.punishList = punishList;
//    }

    public List<CompanyStaff> getStaffListAll() {
        return staffListAll;
    }

    public void setStaffListAll(List<CompanyStaff> staffListAll) {
        this.staffListAll = staffListAll;
    }
}
