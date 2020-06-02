package com.kd.dsr;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class test2 {





    public static void main(String[] args) throws Exception{
        String strTime1 = "2015-03-06 19:12:25";
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd hh:MM:ss");
        Date date1 = formatter.parse(strTime1);
        String strTime2 = "2015-03-04 00:12:25";
        Date date2 = formatter.parse(strTime2);
        int dif = dateDiff(date1,date2);
        System.out.print(dif);
    }

    public static int dateDiff(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(date1);
        cal2.setTime(date2);
        long ldate1 = date1.getTime() + cal1.get(Calendar.ZONE_OFFSET) + cal1.get(Calendar.DST_OFFSET);
        long ldate2 = date2.getTime() + cal2.get(Calendar.ZONE_OFFSET) + cal2.get(Calendar.DST_OFFSET);
        // Use integer calculation, truncate the decimals
        int hr1 = (int) (ldate1 / 3600000); // 60*60*1000
        int hr2 = (int) (ldate2 / 3600000);

        int days1 = hr1 / 24;
        int days2 = hr2 / 24;

        int dateDiff = days1 - days2;
        return dateDiff;
    }

}
