package insertRds;

import com.ang.anglog.tianyc.insertrds.InsertRds;
import org.junit.Before;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by adimn on 2017/6/19.
 */
public class InsertRdsTest {


    @Test
    public void readFileBatchDealOneTest(){
        //测试数据，更改文件路径
        String filename =InsertRds.class.getClassLoader().getResource("update_id.txt").getFile();
        InsertRds.readFileDealOne(filename);
    }

    @Test
    public void testSingle(){
        //测试数据，更改文件路径
        String filename =InsertRds.class.getClassLoader().getResource("ids_final_single.txt").getFile();
        InsertRds.readFileDealOne(filename);
    }

    @Test
    public void simpleTest(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(sdf.format(new Date()));
    }
}