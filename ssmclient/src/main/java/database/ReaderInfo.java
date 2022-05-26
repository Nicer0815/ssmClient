package database;

import lombok.Data;
import org.junit.Test;
import utils.Database;
import utils.DateUtils;

import java.sql.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
@Data
public class ReaderInfo {
    Vector<String> readerId = new Vector<>();
    Vector<String> Name = new Vector<>();
    Vector<String> password = new Vector<>();
    Vector<String> state = new Vector<>();

    public void getReader(){
        Connection connection = Database.getConnection();
        String sql = "Select READERS.READERID,NAME,PASSWORD,STATE from LYN.READERS JOIN LYN.CREDENTIALS ON READERS.READERID = CREDENTIALS.READERID";
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()){
                readerId.add(rs.getString(1));
                Name.add(rs.getString(2));
                password.add(rs.getString(3));
                state.add(rs.getString(4));
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    public int updateReader(String readerId,String Name, String password, String state){
        Connection connection = Database.getConnection();
        String sql1 = "update LYN.READERS set NAME = ? WHERE READERID = ?";
        String sql2 = "update LYN.CREDENTIALS set  PASSWORD = ?, STATE = ? WHERE READERID = ?";
        try {
            PreparedStatement preparedStatement1 = connection.prepareStatement(sql1);
            PreparedStatement preparedStatement2 = connection.prepareStatement(sql2);
            preparedStatement1.setString(1,Name);
            preparedStatement1.setString(2,readerId);

            preparedStatement2.setString(1,password);
            preparedStatement2.setString(2,state);
            preparedStatement2.setString(3,readerId);

            int cnt1 = preparedStatement1.executeUpdate();
            int cnt2 = preparedStatement2.executeUpdate();
            System.out.println("cnt1  = "+cnt1+"\tcnt2 = "+cnt2);

            connection.commit();
            return Math.max(cnt1, cnt2);
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return 0;
    }
    //readerId,Name,sex,phoneNum,password
    public int addReader(Map<String,String>map){

        String readerId = map.get("readerId");
        String Name =  map.get("Name");
        String sex = map.get("sex");
        String phoneNum = map.get("phoneNum");

        long time = System.currentTimeMillis();
        java.sql.Date handleDate = new java.sql.Date(time);
        Date invalidDate = DateUtils.plus4Years(handleDate);
        String password = map.get("password");

        String score = "80";
        String borrowNum = "0";
        String state = "正常";

        Connection connection = Database.getConnection();
        String sql1 = "insert into LYN.READERS(READERID, NAME, SEX, PHONENUM)VALUES (?,?,?,?)";
        String sql2 = "insert into LYN.CREDENTIALS(READERID, HANDLEDATE, INVALIDDATE, PASSWORD, SCORE, BORROWNUM, STATE)VALUES (?,?,?,?,?,?,?)";

        try {
            PreparedStatement preparedStatement1 = connection.prepareStatement(sql1);
            PreparedStatement preparedStatement2 = connection.prepareStatement(sql2);

            preparedStatement1.setString(1,readerId);
            preparedStatement1.setString(2,Name);
            preparedStatement1.setString(3,sex);
            preparedStatement1.setString(4,phoneNum);

            preparedStatement2.setString(1,readerId);
            preparedStatement2.setDate(2, DateUtils.castSQLDate(handleDate));
            preparedStatement2.setDate(3, DateUtils.castSQLDate(invalidDate));
            preparedStatement2.setString(4,password);
            preparedStatement2.setString(5,score);
            preparedStatement2.setString(6,borrowNum);
            preparedStatement2.setString(7,state);

            int cnt1 = preparedStatement1.executeUpdate();
            int cnt2 = preparedStatement2.executeUpdate();
            connection.commit();
            System.out.println("cnt1 = "+cnt1+"\tcnt2 = "+cnt2);
            return Math.min(cnt1,cnt2);

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }


        return 0;
    }


    @Test
    public void test01(){
        ReaderInfo readerInfo = new ReaderInfo();
        readerInfo.getReader();
        System.out.println(readerInfo);
    }
    @Test
    public void test02(){
        ReaderInfo readerInfo = new ReaderInfo();
        int flag = readerInfo.updateReader("55100214","胡梦梦","000001","正常");
        System.out.println(flag);
    }
    @Test
    public void test03(){
        ReaderInfo readerInfo = new ReaderInfo();
        Map<String,String>map = new HashMap<>();
        map.put("readerId","55100215");
        map.put("Name","赵大娘");
        map.put("sex","F");
        map.put("phoneNum","11011011");
        map.put("password","999999");
        int flag = readerInfo.addReader(map);
        System.out.println(flag);
    }
}
