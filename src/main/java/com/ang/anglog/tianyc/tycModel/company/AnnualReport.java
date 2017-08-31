package com.ang.anglog.tianyc.tycModel.company;


import com.ang.anglog.tianyc.Tools.CompareTools;
import com.ang.anglog.tianyc.tycModel.annulReport.*;


import java.util.List;

/**
 * Created by adimn on 2017/6/27.
 */
public class AnnualReport {

    private ReportBaseInfo baseInfo;
    private List<ReportShareHolder> shareholderList;
    private List<ReportWebInfo> webInfoList;
    private List<ReportOutBoundInvestment> outboundInvestmentList;
    private List<ReportOutGuaranteeInfo> outGuaranteeInfoList;
    private List<ReportEquityChangeInfo> equityChangeInfoList;
    private List<ReportChangeRecord> changeRecordList;

    public String compareData(AnnualReport inAnnual){
        StringBuilder sb = new StringBuilder();
        ReportBaseInfo inBaseInfo = inAnnual.getBaseInfo();
        if(!CompareTools.compareStringEqual(inBaseInfo.getEmail(),baseInfo.getEmail())){
            sb.append("annualReport中baseInfo中 email信息不相等，本地为："+baseInfo.getEmail()+",api为："+inBaseInfo.getEmail());
        }else if(!CompareTools.compareStringEqual(inBaseInfo.getPhoneNumber(),baseInfo.getPhoneNumber())){
            sb.append("annualReport中baseInfo中 phone信息不相等，本地为："+baseInfo.getEmail()+",api为："+inBaseInfo.getEmail());
        }

        if(webInfoList!=null && inAnnual.webInfoList!=null){
            if(webInfoList.size()!=inAnnual.webInfoList.size()){
                sb.append("annualReport中webInfoList条数不相等，本地为:"+webInfoList.size()+",api为："+inAnnual.webInfoList.size());
            }else{
                for(ReportWebInfo perlocal: webInfoList){
                    String name = perlocal.getName();
                    String website = perlocal.getWebsite();
                    for(ReportWebInfo perApi:inAnnual.webInfoList){
                        if(CompareTools.compareStringEqual(name, perApi.getName())){
                            if(!CompareTools.compareStringEqual(website,perApi.getWebsite())){
                                sb.append("annualReport中webInfoList中数据不相等，本地为："+website+",api为："+perApi.getWebsite());
                            }
                        }
                    }
                }
            }
        }else {
            if(webInfoList==null&&inAnnual.webInfoList.size()==0||webInfoList.size()==0&&inAnnual.webInfoList==null){

            }else {
                sb.append("annualReport中webInfoList数据不相等,本地为：" + webInfoList + ",api为：" + inAnnual.webInfoList);
            }
        }

        return sb.toString();
    }


    public ReportBaseInfo getBaseInfo() {
        return baseInfo;
    }

    public void setBaseInfo(ReportBaseInfo baseInfo) {
        this.baseInfo = baseInfo;
    }

    public List<ReportShareHolder> getShareholderList() {
        return shareholderList;
    }

    public void setShareholderList(List<ReportShareHolder> shareholderList) {
        this.shareholderList = shareholderList;
    }

    public List<ReportWebInfo> getWebInfoList() {
        return webInfoList;
    }

    public void setWebInfoList(List<ReportWebInfo> webInfoList) {
        this.webInfoList = webInfoList;
    }

    public List<ReportOutBoundInvestment> getOutboundInvestmentList() {
        return outboundInvestmentList;
    }

    public void setOutboundInvestmentList(List<ReportOutBoundInvestment> outboundInvestmentList) {
        this.outboundInvestmentList = outboundInvestmentList;
    }

    public List<ReportOutGuaranteeInfo> getOutGuaranteeInfoList() {
        return outGuaranteeInfoList;
    }

    public void setOutGuaranteeInfoList(List<ReportOutGuaranteeInfo> outGuaranteeInfoList) {
        this.outGuaranteeInfoList = outGuaranteeInfoList;
    }

    public List<ReportEquityChangeInfo> getEquityChangeInfoList() {
        return equityChangeInfoList;
    }

    public void setEquityChangeInfoList(List<ReportEquityChangeInfo> equityChangeInfoList) {
        this.equityChangeInfoList = equityChangeInfoList;
    }

    public List<ReportChangeRecord> getChangeRecordList() {
        return changeRecordList;
    }

    public void setChangeRecordList(List<ReportChangeRecord> changeRecordList) {
        this.changeRecordList = changeRecordList;
    }

    @Override
    public String toString() {
        return "AnnualReport{" +
                "baseInfo=" + baseInfo.toString() +
                ", shareholderList=" + shareholderList.toString() +
                ", webInfoList=" + webInfoList.toString() +
                ", outboundInvestmentList=" + outboundInvestmentList.toString() +
                ", outGuaranteeInfoList=" + outGuaranteeInfoList.toString() +
                ", equityChangeInfoList=" + equityChangeInfoList.toString() +
                ", changeRecordList=" + changeRecordList.toString()+
                '}';
    }
}
