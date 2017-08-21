package com.ang.anglog.tianyc.tycModel.company;


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
}
