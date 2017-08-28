package tianyc.Tools;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by adimn on 2017/6/29.
 */
public class CompareToolsTest {
    @Test
    public void compareTime() throws Exception {

    }

    @Test
    public void main() throws Exception {
        String atime="1478016000000";
//        DateFormat adf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        try {
////            Date dapi=adf.parse(atime);
//            adf.format(new Date(Long.parseLong(atime)));

//            System.out.println(adf.format(atime));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }


        long millisecond = Long.parseLong(atime);
        Date date = new Date(millisecond);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        System.out.println("毫秒[1483159625851]对应日期时间字符串：" + format.format(date));

//        Date d2=new SimpleDateFormat(format.format(date));
//        System.out.print(d2.getTime());


        System.out.println("2016-11-02 12:00:00".substring(0,10));
    }

}