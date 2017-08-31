package com.ang.anglog.tianyc.tycModel.annulReport;

/**
 * Created by adimn on 2017/6/27.
 */
public class ReportEquityChangeInfo {
    private String changeTime;
    private String investorName;
    private String ratioAfter;
    private String ratioBefore;

    public String getChangeTime() {
        return changeTime;
    }

    public void setChangeTime(String changeTime) {
        this.changeTime = changeTime;
    }

    public String getInvestorName() {
        return investorName;
    }

    public void setInvestorName(String investorName) {
        this.investorName = investorName;
    }

    public String getRatioAfter() {
        return ratioAfter;
    }

    public void setRatioAfter(String ratioAfter) {
        this.ratioAfter = ratioAfter;
    }

    public String getRatioBefore() {
        return ratioBefore;
    }

    public void setRatioBefore(String ratioBefore) {
        this.ratioBefore = ratioBefore;
    }

    @Override
    public String toString() {
        return "ReportEquityChangeInfo{" +
                "changeTime='" + changeTime + '\'' +
                ", investorName='" + investorName + '\'' +
                ", ratioAfter='" + ratioAfter + '\'' +
                ", ratioBefore='" + ratioBefore + '\'' +
                '}';
    }
}
