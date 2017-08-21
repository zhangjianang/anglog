package com.ang.anglog.tianyc.Tools;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.ang.anglog.tianyc.Tools.StringUtil.isNotEmpty;

/**
 * Created by adimn on 2017/5/19.
 */
public class DBUtils {

//    private final static Logger LOG = LoggerFactory.getLogger(DBUtils.class);

    private static DataSource dataSource= null;

    private static DataSource insertDataSource= null;

    private static DataSource insertDataSource2= null;


    /**
     *  2017/6/16
     *  参数
     * 更根据参数创建分库连接信息
     */
    public DBUtils(String no,Boolean isDefault) {
        try{
            if(isDefault){
                dataSource = SQLUtil.getDataSource(0+"");
            }else{
                dataSource = SQLUtil.getDataSource(no);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public DBUtils() {
        try{
            insertDataSource = SQLUtil.getDataSource("insert");
            String add=ReadPropertyTool.getPropertyByParam("angsqldb.properties","insertAdd");
            if(add.equals("true")) {
                insertDataSource2 = SQLUtil.getDataSource("insert2");
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public  List<Map<String, Object>> inQuery(String sql, Object... args) {
        ResultSet resultSet = null;
        Connection connection = null;
        PreparedStatement querySql = null;
        List<Map<String, Object>> lres=null;
        ResultSet rs = null;
        try {
            connection = insertDataSource.getConnection();
            querySql = connection.prepareStatement(sql);
            rs = null;
            // 设置参数
            for (int i = 0; i < args.length; i++) {
                if (args[i] != null)
                    querySql.setObject(i + 1, args[i]);
            }
            // 执行查询语句
            rs = querySql.executeQuery();

            lres=new ArrayList<Map<String, Object>>();
            while(rs.next()){
                lres.add(rsToMap(rs));
            }
            return lres;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            SQLUtil.close(connection,querySql,rs);
        }
        return null;
    }



    public  List<Map<String, Object>> query(String sql, Object... args) {
        ResultSet resultSet = null;
        Connection connection = null;
        PreparedStatement querySql = null;
        List<Map<String, Object>> lres=null;
        ResultSet rs = null;
        try {
            connection = dataSource.getConnection();
            querySql = connection.prepareStatement(sql);
            rs = null;
            // 设置参数
            for (int i = 0; i < args.length; i++) {
                if (args[i] != null)
                    querySql.setObject(i + 1, args[i]);
            }
            // 执行查询语句
            rs = querySql.executeQuery();

            lres=new ArrayList<Map<String, Object>>();
            while(rs.next()){
                lres.add(rsToMap(rs));
            }
            return lres;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            SQLUtil.close(connection,querySql,rs);
        }
        return null;
    }

    /*执行插入*/
    public String insert(Integer num, String sql,Object... args){

        Connection connection = null;
        PreparedStatement querySql = null;
        List<Map<String, Object>> lres=null;
        ResultSet rs = null;
        try {
            if(num==0) {
                connection = insertDataSource.getConnection();
            }else if(num==2){
                connection = insertDataSource2.getConnection();
            }
            querySql = connection.prepareStatement(sql);
            // 设置参数
            for (int i = 0; i < args.length; i++) {
                if (args[i] != null)
                    querySql.setObject(i + 1, args[i]);
            }
            // 执行插入语句
            rs = querySql.executeQuery();
            return rs.toString();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            SQLUtil.close(connection,querySql,rs);
        }
        return null;
    }


    public List<String> getJsonInfoByPage(String sql){
        Connection connection = null;
        PreparedStatement querySql = null;
        List<String> lres=new ArrayList<String>();
        ResultSet rs = null;
        try {

            connection = insertDataSource.getConnection();
            querySql = connection.prepareStatement(sql);
            // 设置参数

            // 执行插入语句
            rs = querySql.executeQuery(sql);
            while (rs.next()){
                lres.add(rs.getString(1));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            SQLUtil.close(connection,querySql,rs);
        }
        return lres;
    }
    /**
     * 将结果集转换成Map
     *
     * @param rs
     *            结果集
     * @return map
     * @throws SQLException
     */
    public static Map<String, Object> rsToMap(ResultSet rs) throws SQLException {
        Map<String, Object> map = new HashMap<String, Object>();
        // 获取结果集的元信息(列名，列类型，大小，列数量等等)
        ResultSetMetaData rsmd = rs.getMetaData();
        int count = rsmd.getColumnCount();// 获取结果集中的列的数量
        for (int i = 1; i <= count; i++) {
            // 根据列的下标获取列名称
            String columnName = rsmd.getColumnName(i);
            Object value = rs.getObject(i);
            map.put(columnName.toLowerCase(), value);
        }
        return map;
    }


    /*批量插入*/
    public void insertFinalBatch(Integer num, String sql ,Map<String, String> returnMap) {
        Connection connection = null;
        PreparedStatement querySql = null;
        List<Map<String, Object>> lres=null;
        String json=null;
        try {
            if(num==0) {
                connection = insertDataSource.getConnection();
            }else{
                connection = insertDataSource2.getConnection();
            }
            if(isNotEmpty(sql)) {
                querySql = connection.prepareStatement(sql);

                for(Map.Entry<String,String> entry : returnMap.entrySet()){
                    String name = entry.getKey();
                    json = entry.getValue();
                    querySql.setString(1,name);
                    querySql.setString(2,json);
                    querySql.addBatch();
                }
                querySql.executeBatch();
            }

        } catch (Exception e) {
            System.out.println(json);
            e.printStackTrace();
        } finally {
            SQLUtil.close(connection,querySql,null);
        }

    }
}
