package utils;

import org.junit.Test;

import java.sql.*;

public class Database {
    public static Connection connection;
    public static Connection getConnection(){
        if(connection == null){
            try{
                Class.forName("oracle.jdbc.driver.OracleConnection");
                String dbURL = "jdbc:oracle:thin:@localhost:1521:ORCL";
                connection = DriverManager.getConnection(dbURL, "wry3", "wry3");
                System.out.println("数据库连接成功！");

            }catch (Exception e){
                e.printStackTrace();
            }

        }
        return connection;
    }

    @Test
    public void test(){
        Connection connection = Database.getConnection();
        try {
            Statement statement = connection.createStatement();
            String sql = "select * from LYN.STAFF";
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()){
                System.out.print(rs.getString(1)+"\t");
                System.out.print(rs.getString(2)+"\t");
                System.out.println(rs.getString(3)+"\t");
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }
}
