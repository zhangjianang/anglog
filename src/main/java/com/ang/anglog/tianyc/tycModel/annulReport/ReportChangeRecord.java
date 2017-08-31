package com.ang.anglog.tianyc.tycModel.annulReport;

/**
 * Created by adimn on 2017/6/27.
 */
public class ReportChangeRecord {
    private String changeItem;
    private String changeTime;
    private String contentAfter;
    private String contentBefore;

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

    @Override
    public String toString() {
        return "ReportChangeRecord{" +
                "changeItem='" + changeItem + '\'' +
                ", changeTime='" + changeTime + '\'' +
                ", contentAfter='" + contentAfter + '\'' +
                ", contentBefore='" + contentBefore + '\'' +
                '}';
    }
}
