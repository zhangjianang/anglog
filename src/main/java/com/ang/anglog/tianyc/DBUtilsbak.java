package com.ang.anglog.tianyc;


import com.ang.anglog.tianyc.Tools.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.*;

/**
 * Created by adimn on 2017/5/19.
 */
public class DBUtilsbak {

    private final static Logger LOG = LoggerFactory.getLogger(DBUtilsbak.class);
    private   DataSource dataSource;
    private static List<DataSource> dsList=new ArrayList<DataSource>();

    static {
        Properties properties = new Properties();
        //// TODO: 2017/6/16 增加分库信息
       InputStream in = DBUtilsbak.class.getResourceAsStream("/splitdb.properties");
        try {
            properties.load(in);
            Integer spnum = Integer.parseInt(properties.getProperty("splitnum"));
            for (int i=0;i<spnum;i++) {
                String url = "url" + i;
                String username = "username" + i;
                String password = "password" + i;
                properties.setProperty("url", properties.getProperty(url));
                properties.setProperty("username", properties.getProperty(username));
                properties.setProperty("password", properties.getProperty(password));
//               DataSource tmpDs= BasicDataSourceFactory.createDataSource(properties);
//                dsList.add(tmpDs);
            }
            in.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private  Boolean dbCached = false;


    public Boolean getDbCached() {
        return dbCached;
    }

//    public DBUtils(Boolean dbCached){
//        this.dbCached = dbCached;
//        if(dbCached){
//            this.dbCached = createDbCache();
//        }
//    }

    public DBUtilsbak() {
        this.dbCached = createDbCache();
    }

    /**
     *  2017/6/16
     *  参数
     * 更根据参数创建分库连接信息
     */
    public DBUtilsbak(String no, Boolean isDefault){
        this.dbCached =createSplitDbCache(no,isDefault);
    }

    private void initDS(String no,Boolean isDefault){
        if(isDefault){
            this.dataSource=dsList.get(0);
        }else if(no!=null && StringUtil.isNotEmpty(no)){
            int index=Integer.parseInt(no);
            this.dataSource= dsList.get(index);
        }
    }

    /**
     * 根据参数创建数据库
     * @param no 分库号
     * @return
     */
    private Boolean createSplitDbCache(String no, Boolean isDefault){

        InputStream in = null;
        dbCached = false;
        Connection connection = null;
        try {
            initDS(no,isDefault);
            if(this.dataSource != null){
                connection = this.dataSource.getConnection();
            }
            dbCached = true;
        } catch (Exception e) {
            e.printStackTrace();

        }finally {
            releaseConnection(connection);
        }
        return dbCached;
    }


    private Boolean createDbCache(){
        InputStream in = null;
        dbCached = false;
        Connection connection = null;
        try {
            if(dataSource == null){
                Properties properties = new Properties();
                in = DBUtilsbak.class.getResourceAsStream("/angsqldb.properties");
                properties.load(in);
//                properties.setProperty("password", DESUtil.decrypt(properties.getProperty("password").getBytes("UTF8")));
//                dataSource = BasicDataSourceFactory.createDataSource(properties);
                in.close();
                connection = dataSource.getConnection();
            }
            dbCached = true;
        } catch (Exception e) {
            e.printStackTrace();

        }finally {
            releaseInputStream(in);
            releaseConnection(connection);
        }
        return dbCached;
    }

    /**
     * 执行查询操作
     *
     * @param sql
     *            查询SQL语句
     * @param args
     *            SQL语句 参数
     * @return 返回查询结果
     */
    public  List<Map<String, Object>> query(String sql, Object... args) {
        ResultSet resultSet = null;
        Connection connection = null;
        PreparedStatement querySql = null;
        List<Map<String, Object>> lres=null;
        try {
            connection = dataSource.getConnection();
            querySql = connection.prepareStatement(sql);
//            PreparedStatement pst = null;
            ResultSet rs = null;

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
            releasePreparedStatement(querySql);
            releaseResultSet(resultSet);
            releaseConnection(connection);
        }
        return null;
    }

    /*执行插入*/
    public String insert(String sql,Object... args){

        Connection connection = null;
        PreparedStatement querySql = null;
        List<Map<String, Object>> lres=null;
        ResultSet rs = null;
        try {
            connection = dataSource.getConnection();
            querySql = connection.prepareStatement(sql);
//            PreparedStatement pst = null;

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
            releasePreparedStatement(querySql);
            releaseResultSet(rs);
            releaseConnection(connection);
        }
        return null;
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
        // map.get("uname");
        return map;
    }

    public static void releaseResultSet(ResultSet resultSet){
        if(resultSet!=null){
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void releasePreparedStatement(PreparedStatement statement){
        if(statement!=null){
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void releaseConnection(Connection connection){
        if(connection!=null){
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public static void releaseInputStream(InputStream in) {
        if (null != in) {
            try {
                in.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    public void insertFinalBatch(Map<String, String> returnMap) {
        Connection connection = null;
        PreparedStatement querySql = null;
        List<Map<String, Object>> lres=null;

        try {
            String insertSQL = "REPLACE INTO companyinfo(name,json) values (?,?)";
            connection = dataSource.getConnection();
            querySql = connection.prepareStatement(insertSQL);
//            PreparedStatement pst = null;


            for(Map.Entry<String,String> entry : returnMap.entrySet()){
                String name = entry.getKey();
                String json = entry.getValue();
                querySql.setString(1,name);
                querySql.setString(2,json);
                querySql.addBatch();
            }

            querySql.executeBatch();


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            releasePreparedStatement(querySql);
            releaseConnection(connection);
        }

    }
}
