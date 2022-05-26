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
public class StaffInfo {
    Vector<String> jobId = new Vector<>();
    Vector<String> personId = new Vector<>();
    Vector<String> Name = new Vector<>();
    Vector<String> phoneNum = new Vector<>();

    public void getStaff(){
        Connection connection = Database.getConnection();
        String sql = "Select JOBID, PERSONID, NAME, PHONENUM from LYN.STAFF";

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()){
                jobId.add(rs.getString(1));
                personId.add(rs.getString(2));
                Name.add(rs.getString(3));
                phoneNum.add(rs.getString(4));
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    public int updateStaff(String jobId,String personId,String Name,String password,String phoneNum){
        Connection connection = Database.getConnection();
        String sql = "update LYN.Staff set NAME = ?,PASSWORD = ?, PHONENUM = ? WHERE JOBID = ? AND PERSONID = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,Name);
            preparedStatement.setString(2,password);
            preparedStatement.setString(3,phoneNum);
            preparedStatement.setString(4,jobId);
            preparedStatement.setString(5,personId);
            int cnt = preparedStatement.executeUpdate();
            connection.commit();
            return cnt;
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return 0;
    }
    //jobId,personId,Name,password,sex,phoneNum
    public int addStaff(Map<String,String>map){
        Connection connection = Database.getConnection();
        String jobId = map.get("jobId");
        String personId = map.get("personId");
        String Name = map.get("Name");
        String password = map.get("password");
        String sex = map.get("sex");
        String phoneNum = map.get("phoneNum");
        String rank = "用户管理员";

        String sql = "insert into LYN.STAFF(JOBID, PERSONID, NAME, PASSWORD, RANK, SEX, PHONENUM) VALUES (?,?,?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,jobId);
            preparedStatement.setString(2,personId);
            preparedStatement.setString(3,Name);
            preparedStatement.setString(4,password);
            preparedStatement.setString(5,rank);
            preparedStatement.setString(6,sex);
            preparedStatement.setString(7,phoneNum);

            int cnt = preparedStatement.executeUpdate();
            connection.commit();
            System.out.println("cnt = "+cnt);
            return cnt;
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }


        return 0;
    }

    @Test
    public void test01(){
        StaffInfo staffInfo = new StaffInfo();
        staffInfo.getStaff();
        System.out.println(staffInfo);
    }
    @Test
    public void test02(){
        StaffInfo staffInfo = new StaffInfo();
        int flag = staffInfo.updateStaff("4004","1110002224004","刘悦","111111","1504004");
        staffInfo.getStaff();
        System.out.println(staffInfo);
    }
    @Test
    public void test03(){
        StaffInfo staffInfo = new StaffInfo();
        Map<String,String>map = new HashMap<>();
        //jobId,personId,Name,password,sex,phoneNum
        map.put("jobId","4005");
        map.put("personId","1110002224004");
        map.put("Name","张三疯");
        map.put("password","888888");
        map.put("sex","M");
        map.put("phoneNum","17777777");
        int flag = staffInfo.addStaff(map);

    }

}
