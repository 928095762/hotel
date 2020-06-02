package cn.kd.dsr.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
 
 
public class DateUtils {
 
 
	/**
	 * 获取时间格式 yyyyMMdd
	 * @return
	 */
	public static String getDirDate() {
		return new SimpleDateFormat("yyyy/MM/dd").format(System.currentTimeMillis());
 
	}
	public  static  Long  date2Long(Date date){
        long time = date.getTime();
        return  time;
    }
 
	public static  Date  getnowDate(){
		 return new Date();
	}
 
    /**
     * 获取时间类型的文件名(不含毫秒值)yyyyMMddHHmmss
     * @return
     */
	public static String getDateformatStringM() {
		return new SimpleDateFormat("yyyyMMddHHmmss").format(System.currentTimeMillis());
	}
 
    /**
     * 获取时间类型的文件名(含毫秒值)yyyyMMddHHmmssSSS
     * @return
     */
	public static String getDateformatStringSSS() {
		return new SimpleDateFormat("yyyyMMddHHmmssSSS").format(System.currentTimeMillis());
	}
 
	/**
	 * 转换String时间为格式:yyyy-MM-dd HH:mm:ss
	 * @param stringDate
	 * @return
	 * @throws ParseException
	 */
	public static Date parseString2Date(String stringDate) throws ParseException {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(stringDate);
	}
 
    /**
     * 获取时间格式为:yyyy-MM-dd HH:mm:ss
     * @return
     */
	public static String getDateformatStringL() {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis());
		
	}
 
    /**
     * 获取时间格式 yyyyMMdd
     * @return
     */
	public static String getDateformatStringS() {
		return new SimpleDateFormat("yyyyMMdd").format(System.currentTimeMillis());
		
	}
 
    /**
     * 获取时间yyyy-MM-dd
     * @return
     */
	public static String getDateformatStringSS() {
		return new SimpleDateFormat("yyyy-MM-dd").format(System.currentTimeMillis());
 
	}
 
    /**
     * 获取时间格式 yyyy
     * @return
     */
	public static String getDateformatStringSs() {
		return new SimpleDateFormat("yyyy").format(System.currentTimeMillis());
		
	}
 
    /**
     * 获取yyyy-MM-dd HH:mm:ss
     * @param date
     * @return
     */
	public static String fmttime (Date date) {
		SimpleDateFormat myFmt=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		return myFmt.format(date);
	}
 
    /**
     * 获取yyyy-MM-dd HH:mm:ss
     * @param date
     * @return
     */
	public static String fmtMsgtime (Date date) {
		SimpleDateFormat myFmt=new SimpleDateFormat("yyyy-MM-dd|HH:mm:ss");
		return myFmt.format(date);
	}
 
	/**
     * 获取yyyy-MM-dd HH:mm:ss
     * @param date
     * @return
     */
	public static String fmttime (Long date) {
		SimpleDateFormat myFmt=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return myFmt.format(date);
	}
	/**
     * 获取yyyy-MM-dd HH:mm:ss
     * @param date
     * @return
     */
	public static Date fmtLongtime2Date (Long date) {
        Date date1 = new Date();
		date1.setTime(date);
		return date1;
	}
 
 
 
 
	/**
	 * 获取今日的整点时间  昨天  -1  明天 1 addDay
	 * @param hour
	 * @param minute
	 * @param second
	 * @param addDay
	 * @param args
	 * @return
	 */
	public static Date getNeedTime(int hour,int minute,int second,int addDay,int ...args){
	    Calendar calendar = Calendar.getInstance();
	    if(addDay != 0){
	        calendar.add(Calendar.DATE,addDay);
	    }
	    calendar.set(Calendar.HOUR_OF_DAY,hour);
	    calendar.set(Calendar.MINUTE,minute);
	    calendar.set(Calendar.SECOND,second);
	    if(args.length==1){
	        calendar.set(Calendar.MILLISECOND,args[0]);
	    }
	    return calendar.getTime();
	}
	public static List<Date> dateToWeek(Date mdate) {  
	    int b = mdate.getDay();  
	    Date fdate;  
	    List<Date> list = new ArrayList<Date>();  
	    Long fTime = mdate.getTime() - b * 24*3600000;  
	    for(int a = 1; a <= 7; a++) {  
	        fdate = new Date();  
	        fdate.setTime(fTime + (a * 24*3600000)); //一周从周日开始算，则使用此方式  
	        //fdate.setTime(fTime + ((a-1) * 24*3600000)); //一周从周一开始算，则使用此方式  
	        list.add(a-1, fdate);  
	    }  
	    return list;  
	}  
	
	/** 
	* 根据当前日期获得所在周的日期区间（周一） 
	*  
	* @return 
	* @author zhaoxuepu 
	* @throws ParseException 
	*/  
	public static Date getMonday(Date date) throws ParseException {  
	     Calendar cal = Calendar.getInstance();  
	     cal.setTime(date);  
	     // 判断要计算的日期是否是周日，如果是则减一天计算周六的，否则会出问题，计算到下一周去了  
	     int dayWeek = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天  
	     if (1 == dayWeek) {  
	         cal.add(Calendar.DAY_OF_MONTH, -1);  
	      }  
	      // System.out.println("要计算日期为:" + sdf.format(cal.getTime())); // 输出要计算日期  
	      // 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一  
	      cal.setFirstDayOfWeek(Calendar.MONDAY);  
	      // 获得当前日期是一个星期的第几天  
	      int day = cal.get(Calendar.DAY_OF_WEEK);  
	      // 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值  
	      cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);  
//	  	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd EEE");  
	  	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd ");  
	      String begindate = sdf.format(cal.getTime());  
	      begindate = begindate + " " + "00:00:00";
	     
	
	       Date beginWeekDate = DateUtils.parseString2Date(begindate);
	    
	       
	      return beginWeekDate;  
	}  
	/** 
	* 根据当前日期获得所在周的日期区间（周一和周日日期） 
	*  
	* @return 
	* @author zhaoxuepu 
	* @throws ParseException 
	*/  
	public static Date getSunday(Date date) throws ParseException {  
	     Calendar cal = Calendar.getInstance();  
	     cal.setTime(date);  
	     int dayWeek = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天  
	     if (1 == dayWeek) {  
	         cal.add(Calendar.DAY_OF_MONTH, -1);  
	      }  
	      cal.setFirstDayOfWeek(Calendar.MONDAY);  
	      int day = cal.get(Calendar.DAY_OF_WEEK);  
	      cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);  
  
	  		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd ");  
	    
	      cal.add(Calendar.DATE, 6);  
	      String enddate = sdf.format(cal.getTime());
	     
	      enddate = enddate + " " + "23:59:59";
	   
	     Date endWeekDate = DateUtils.parseString2Date(enddate);
	     
	       
	      return endWeekDate;  
	}  
	/** 
     * 获取当前时间 
     *  
     */
    public static String getNowTime() {  
        Calendar cal = Calendar.getInstance();  
        SimpleDateFormat dft = new SimpleDateFormat("yyyyMMdd");  
        String lastMonth = dft.format(cal.getTime());  
        return lastMonth;  
    }  
  
    /** 
     * 判断当天是否为本月第一天 
     *
     * @return 
     */  
    public static boolean isFirstDayOfMonth() {  
        boolean flag = false;  
        Calendar calendar = Calendar.getInstance();  
        int today = calendar.get(Calendar.DAY_OF_MONTH);
        if (1 == today) {  
            flag = true;  
        }  
        return flag;  
    }  
  
    /** 
     * 获取当前月份最后一天 
     *  
     * @return
     * @throws ParseException 
     */  
    public static Date getlastMonthDate() throws ParseException {  
        SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");  
        Calendar calendar = Calendar.getInstance();  
        calendar.setTime(new Date());  
        // calendar.add(Calendar.MONTH, -1);  
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));  
        String endtime = dft.format(calendar.getTime());
        endtime = endtime + " " + "23:59:59";
	     
        return DateUtils.parseString2Date(endtime);
      
    } 
    /** 
     * 获取当前月份第一天 
     *  
     * @return
     * @throws ParseException 
     */  
    public static Date getfirstMonthDate() throws ParseException {  
        SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");  
        Calendar calendar = Calendar.getInstance();  
        calendar.setTime(new Date());  
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH)); 
        String begindate = dft.format(calendar.getTime());
        begindate = begindate + " " + "00:00:00";
	     
        return DateUtils.parseString2Date(begindate);
    }

	/**
	 * 返回两个时间段相隔的天数
	 * @param date1
	 * @param date2
	 * @return
	 */
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

    /**
     * 定时器任务执行
     * java.util.Timer.scheduleAtFixedRate(TimerTask task, Date firstTime, long period)
     */
	/*public static void timer(int year,int month,int day,int hour,int min,int second) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month-1);
        calendar.set(Calendar.DATE, day);
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, min);
        calendar.set(Calendar.SECOND, second);
        Date time = calendar.getTime();
		Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                System.out.println("-------设定要指定任务--------");
            }
        }, time, 1000 );// 这里设定将延时每天固定执行  time, 1000 * 60 * 60 * 24
    }*/
 
	  /**
     * 定时器,什么时候执行任务:参数例如: 2018-07-06 11:20:00
     * java.util.Timer.scheduleAtFixedRate(TimerTask task, Date firstTime, long period)
	 * @throws ParseException 
     */
	/*public static ResResultDto timer(String stime)   {
		ResResultDto d = new ResResultDto();
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dfd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 SimpleDateFormat dfs = new SimpleDateFormat("yyyy/MM/dd/HH/mm/ss");
		  Date tdate = null;
		try {
			tdate = dfd.parse(stime);
		} catch (ParseException e) {
			e.printStackTrace();
			 d.setStatus(2);
			 d.setMeg("时间格式错误");
			 return d;
			
		}  
		 String strtime = dfs.format(tdate);
		 String[]  stimes= strtime.split("/");
		 
        calendar.set(Calendar.YEAR, Integer.valueOf(stimes[0]));
        calendar.set(Calendar.MONTH, Integer.valueOf(stimes[1])-1);
        calendar.set(Calendar.DATE, Integer.valueOf(stimes[2]));
        calendar.set(Calendar.HOUR_OF_DAY, Integer.valueOf(stimes[3]));
        calendar.set(Calendar.MINUTE, Integer.valueOf(stimes[4]));
        calendar.set(Calendar.SECOND, Integer.valueOf(stimes[5]));
        Date time = calendar.getTime();
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                System.out.println("-------设定要指定任务--------");
            }
        }, time, 1000 );// 这里设定将延时每天固定执行  time, 1000 * 60 * 60 * 24
        d.setStatus(0);
        d.setMeg("启动定时器成功");
        return  d;
    }*/
 
	
}
