package utils;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateUtils {

    public static Date cstStringToDate(String cst) {
        ////Sun Mar 12 00:00:00 CST 2023
        ////Wed Jul 04 10:36:06 CST 2018
        Date date = null;
        try {
            date = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US).parse(cst);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static java.sql.Date castSQLDate(Date date){
        try{
            SimpleDateFormat DateFormat =  new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());
            System.out.println(DateFormat.format(sqlDate));
            return sqlDate;
        }catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    public static Boolean equals(Date date1,Date date2){
        System.out.println("------->"+ date1.getTime());
        System.out.println(date2.getTime());
        return date1.getTime()==date2.getTime();
    }

    public static Date plus15Days(Date date){
        //System.out.println("ori:"+date);
        // 15Ìì
        int time = 15 * 24 * 60 * 60 * 1000;
        date.setTime(date.getTime()+time);
        //System.out.println("aft:"+date);

        return date;
    }

    public static Date plus4Years(Date date){
        //System.out.println("ori:"+date);
        long time = 4L * 365 * 24 * 60 * 60 * 1000;
        date.setTime(date.getTime()+time);

        return date;
    }
}