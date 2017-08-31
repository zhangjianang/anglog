package com.ang.anglog.tianyc.tycDao;

import com.ang.anglog.tianyc.SearchDetailInfo;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by adimn on 2017/8/31.
 */
public class CompanyInvestorDaoTest {
    SearchDetailInfo searchDetailInfo;
    @Before
    public void setUp() throws Exception {
        searchDetailInfo = new SearchDetailInfo(0);
    }

    @Test
    public void getCompanyInvestorByCompanyId() throws Exception {
        Map<String, Object> res = searchDetailInfo.getSplitDetailInfoByCompanyId("513429");
        System.out.println(res.toString());

    }

}