package com.ang.anglog.tianyc.tycModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by adimn on 2017/8/22.
 */
public class CompareResModel {
    private List<String> perLine = new ArrayList<String>();
    private boolean isEqual;
    private String apiData;
    private String localData;
    private String title;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getPerLine() {
        return perLine;
    }

    public void setPerLine(List<String> perLine) {
        List<String> resPer= new ArrayList<String>();
        for(String line:perLine){
            if(!"".equals(line)&&line.length()>1){
                resPer.add(line);
            }
        }
        this.perLine = resPer;
    }

    public boolean isEqual() {
        return isEqual;
    }

    public void setEqual(boolean equal) {
        isEqual = equal;
    }

    public String getApiData() {
        return apiData;
    }

    public void setApiData(String apiData) {
        this.apiData = apiData;
    }

    public String getLocalData() {
        return localData;
    }

    public void setLocalData(String localData) {
        this.localData = localData;
    }

    @Override
    public String toString() {
        String perStr = showPerLine();

            return  "{" + title + "\n" +
                            "相等=" + isEqual + '\n' +
                            perStr +
                            '}' + "\n";
    }

    public String showPerLine(){
        StringBuilder  sb =new StringBuilder();
        if(perLine!=null) {
            for (int i = 0; i < perLine.size(); i++) {
                if(!"".equals(perLine.get(i)))
                    sb.append(perLine.get(i) + "\n");
            }
        }
        return sb.toString();
    }
}
