package com.ang.anglog.tianyc.tycModel.annulReport;

/**
 * Created by adimn on 2017/6/27.
 */
public class ReportOutBoundInvestment {
    private String creditCode;
    private String outcompanyName;

    public String getCreditCode() {
        return creditCode;
    }

    public void setCreditCode(String creditCode) {
        this.creditCode = creditCode;
    }

    public String getOutcompanyName() {
        return outcompanyName;
    }

    public void setOutcompanyName(String outcompanyName) {
        this.outcompanyName = outcompanyName;
    }

    public String getRegNum() {
        return regNum;
    }

    public void setRegNum(String regNum) {
        this.regNum = regNum;
    }

    private String regNum;

    @Override
    public String toString() {
        return "ReportOutBoundInvestment{" +
                "creditCode='" + creditCode + '\'' +
                ", outcompanyName='" + outcompanyName + '\'' +
                ", regNum='" + regNum + '\'' +
                '}';
    }
}
