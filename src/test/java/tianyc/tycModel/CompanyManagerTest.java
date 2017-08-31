package tianyc.tycModel;

import com.ang.anglog.tianyc.SearchDetailInfo;
import com.ang.anglog.tianyc.Tools.ReadPropertyTool;
import com.ang.anglog.tianyc.Tools.StringUtil;
import com.ang.anglog.tianyc.insertrds.MD5;
import com.ang.anglog.tianyc.randomCheck.RandomCheck;
import com.ang.anglog.tianyc.tycModel.CompanyManager;
import com.ang.anglog.tianyc.tycModel.CompareResModel;
import org.junit.Before;
import org.junit.Test;


import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * Created by adimn on 2017/6/27.
 */
public class CompanyManagerTest {
    private CompanyManager companyManager;
    private SearchDetailInfo searchDetailInfo;

    @Before
    public void setUp() throws Exception {
        companyManager=new CompanyManager();
        searchDetailInfo=new SearchDetailInfo(0);
    }

    @Test
    public void mainTest(){
        String  filePath=RandomCheck.class.getClassLoader().getResource("20170612_ids_final_part.txt").getFile();
        String maxLine="10";
        String compareNum= ReadPropertyTool.getPropertyByParam("compareNum.properties","compareNum");
        if(StringUtil.isEmpty(compareNum)){
            compareNum="30";
        }
        CompanyManager cm=new CompanyManager();
        cm.randomCompareData(filePath,maxLine,compareNum);
    }

    @Test
    public void initLocalData() throws Exception {
       Map<String,Object> res= searchDetailInfo.getSplitDetailInfoByCompanyId("59235171");
//        companyManager.initLocalData(res);
    }

    @Test
    public void initApiDataByName() throws Exception {
//        companyManager.initApiDataByName("钦州市钦州港新茂科技有限责任公司");
        System.out.println(MD5.MD5_32bit("北京千羽寻一商贸有限公司"));
    }

    @Test
    public void compareTwoByCompanyId() throws Exception {
        //companyInvestorList
//        companyManager.compareTwoByCompanyId("59235171");
    }


    @Test
    public void compareTwoInvestorNull(){
        //companyInvestorList 为null 情况
//        companyManager.compareTwoByCompanyId("126398472");
    }

    @Test
    public void compareInvestor(){
//        companyManager.compareTwoByCompanyId("355358");
//
//        companyManager.compareTwoByCompanyId("84303303");
////        assertEquals(0,companyManager.getDiffInfo().size());
    }

    @Test
    public void compareCompanyStaff(){
//        companyManager.compareTwoByCompanyId("126398472");
    }
    @Test
    public void compareCompanyChange(){
        //companyChange
//        companyManager.compareTwoByCompanyId("59235171");
//        companyManager.compareTwoByCompanyId("114477769");

//        companyManager.compareTwoByCompanyId("117493560");
//
//        companyManager.compareTwoByCompanyId("126389757");
//        126389727

//        companyManager.compareTwoByCompanyId("8230846");
    }
    @Test
    public void compareAnnualReport(){
        CompareResModel df = new CompareResModel();
        companyManager.compareTwoByCompanyId("34288995",df);
        System.out.println(df.toString());
    }

    @Test
    public void compareCompanyAbnormal(){
        CompareResModel df = new CompareResModel();
        companyManager.compareTwoByCompanyId("11302687",df);
        companyManager.compareTwoByCompanyId("16253287",df);
    }
}