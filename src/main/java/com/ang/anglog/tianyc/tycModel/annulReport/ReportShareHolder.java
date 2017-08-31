package com.ang.anglog.tianyc.tycModel.annulReport;

/**
 * Created by adimn on 2017/6/27.
 */
public class ReportShareHolder {
    private String investorName;
    private String paidAmount;
    private String paidTime;
    private String paidType;
    private String subscribeAmount;
    private String subscribeTime;
    private String subscribeType;

    public String getInvestorName() {
        return investorName;
    }

    public void setInvestorName(String investorName) {
        this.investorName = investorName;
    }

    public String getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(String paidAmount) {
        this.paidAmount = paidAmount;
    }

    public String getPaidTime() {
        return paidTime;
    }

    public void setPaidTime(String paidTime) {
        this.paidTime = paidTime;
    }

    public String getPaidType() {
        return paidType;
    }

    public void setPaidType(String paidType) {
        this.paidType = paidType;
    }

    public String getSubscribeAmount() {
        return subscribeAmount;
    }

    public void setSubscribeAmount(String subscribeAmount) {
        this.subscribeAmount = subscribeAmount;
    }

    public String getSubscribeTime() {
        return subscribeTime;
    }

    public void setSubscribeTime(String subscribeTime) {
        this.subscribeTime = subscribeTime;
    }

    public String getSubscribeType() {
        return subscribeType;
    }

    public void setSubscribeType(String subscribeType) {
        this.subscribeType = subscribeType;
    }

    @Override
    public String toString() {
        return "ReportShareHolder{" +
                "investorName='" + investorName + '\'' +
                ", paidAmount='" + paidAmount + '\'' +
                ", paidTime='" + paidTime + '\'' +
                ", paidType='" + paidType + '\'' +
                ", subscribeAmount='" + subscribeAmount + '\'' +
                ", subscribeTime='" + subscribeTime + '\'' +
                ", subscribeType='" + subscribeType + '\'' +
                '}';
    }
}
