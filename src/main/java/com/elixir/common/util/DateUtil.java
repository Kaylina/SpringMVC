package com.elixir.common.util;

import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;


/**
* @ClassName: DateUtil
* @Description:
* @author Jingyan
* @date 2016年3月21日 下午5:48:41
*/ 
public class DateUtil {
    public static Logger logger = Logger.getLogger(DateUtil.class);
    public static String pattern = "yyyy-MM-dd HH:mm:ss";
    
    
    /**
     * 获取两个日期之间的天数
     * @param beginDateStr
     * @param endDateStr
     * @return
     */
    public static long getDaySub(String beginDateStr, String endDateStr) {
		long day = 0;
		java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd");
		java.util.Date beginDate;
		java.util.Date endDate;
		try {
			beginDate = format.parse(beginDateStr);
			endDate = format.parse(endDateStr);
			day = (endDate.getTime() - beginDate.getTime())/ (24 * 60 * 60 * 1000);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return day;
	}

    /**
     * 日期增加
     *
     * @param date
     * @param column
     * @param value
     * @return
     */
    public static Date addDate(Date date, int column, int value) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(column, value);
        return calendar.getTime();
    }
    public static Date long2Date(long time) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(new Date(time));
        return calendar.getTime();
    }
    public static Date string2Date(String str) throws ParseException {
        if (str == null || "".equals(str)) {
            return null;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.parse(str);
    }

    public static Date string2Date(String str, String pattern) {
        if (str == null || "".equals(str)) {
            return null;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        try {
            return simpleDateFormat.parse(str);
        } catch (ParseException e) {
            return null;
        }
    }

    public static Date dateFormatChange(Date date,String format){
    	SimpleDateFormat simpleDateFormatOne=new SimpleDateFormat(format);
    	SimpleDateFormat simpleDateFormatTow=new SimpleDateFormat(format);
    	String day=simpleDateFormatOne.format(date);
    	try {
			return simpleDateFormatTow.parse(day);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
    }

    public static Date getCurrentDate(String value){
    	if(value==null&&"".equals(value)){
    		value=pattern;
    	}
    	SimpleDateFormat simpleDateFormat = new SimpleDateFormat(value);
    	String time=simpleDateFormat.format(new Date());
    	try {
            return simpleDateFormat.parse(time);
        } catch (ParseException e) {
            return null;
        }
    }
 // ***************************************************
    // 名称：dateToStr
    // 功能：将指定的日期转换成字符串
    // 输入：aDteValue: 要转换的日期;
    // aFmtDate: 转换日期的格式, 默认为:"yyyy/MM/dd"
    // 输出：
    // 返回：转换之后的字符串
    // ***************************************************
	public static String dateToStr(Date aDteValue, String aFmtDate) {
		String strRtn = null;

		if (aDteValue == null) {
			return "";
		}
		if (aFmtDate.length() == 0) {
			aFmtDate = "yyyy-MM-dd";
		}
		Format fmtDate = new SimpleDateFormat(aFmtDate);
		try {
			strRtn = fmtDate.format(aDteValue);
		} catch (Exception e) {

		}

		return (strRtn);
	}
    public static Calendar string2Calendar(String str, String pattern) {
        if (str == null || "".equals(str)) {
            return null;
        }
        Date date = null;
        date = string2Date(str, pattern);
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        return calendar;
    }

    public static String date2String(Date date, String pattern) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(date);
    }

    /**
     * 获取当前的日期和时间，格式为yyyy-MM-dd HH:mm:ss
     *
     * @return
     */
	public static String getDateTime() {
		String strCurrentDateTime = "";
		Date currentDateTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		strCurrentDateTime = formatter.format(currentDateTime);
		return strCurrentDateTime;
	}

	                        /**
     * 获取当前的日期的前一天和时间，格式为yyyy-MM-dd HH:mm:ss
     *
     * @return
     */
	public static String getDateTimeBeforeDay() {
		Calendar caldate =Calendar.getInstance();
		String strCurrentDateTime = "";
		caldate.add(Calendar.DAY_OF_MONTH, -1);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		strCurrentDateTime = formatter.format(caldate.getTime());
		return strCurrentDateTime;
	}


	                        /**
     * 获取当前年月日组成的字符串，格式为yyyyMMdd
     *
     * @return
     */
	public static String getYMD() {
		String strYMD = "";
		Date currentDateTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		strYMD = formatter.format(currentDateTime);
		return strYMD;
	}

    /**
     * 获取当前年月日组成的字符串，格式为yyyy-MM-dd
     *
     * @return
     */
	public static String getDay() {
		String strYMD = "";
		Date currentDateTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		strYMD = formatter.format(currentDateTime);
		return strYMD;
	}

	                        /**
     * 获取当前年月日组成的字符串，格式为yyyy-MM-dd
     *
     * @return
     */
	public static String getDayBeforeMonth() {
        Date currentDateTime = new Date();// 得到当前系统时间
        Calendar calendar = Calendar.getInstance();// 日历对象
        calendar.setTime(currentDateTime);// 设置当前日期
        calendar.add(Calendar.MONTH, -1);// 月份减一
		  SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		return formatter.format(calendar.getTime());
	}

    /**
     * 获取当前年字符串，格式为yyyy
     *
     * @return
     */
	public static String getMonth() {
		String strYMD = "";
		Date currentDateTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("MM");
		strYMD = formatter.format(currentDateTime);
		return strYMD;
	}

    /**
     * 获取当前月字符串，格式为yyyy
     *
     * @return
     */
	public static String getYear() {
		String strYMD = "";
		Date currentDateTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
		strYMD = formatter.format(currentDateTime);
		return strYMD;
	}

	                        /**
     * 获取当前年月日时分秒组成的字符串，格式为yyyyMMddHHmmss
     *
     * @return
     */
	public static String getYMDHMS() {
		String strYMDHMS = "";
		Date currentDateTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		strYMDHMS = formatter.format(currentDateTime);
		return strYMDHMS;
	}

	                        /**
     * 获取当前年月日时分秒毫秒组成的字符串，格式为yyyyMMddHHmmssSSS
     *
     * @return
     */
	public static String getYMDHMSS() {
		String strYMDHMSS = "";
		Date currentDateTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		strYMDHMSS = formatter.format(currentDateTime);
		return strYMDHMSS;
	}

    // 得到对比月变化的目标年月
	  //getMonthChange("2003-10-09",1)="2003-11-09";
	  //getMonthChange("2003-10-09",-2)="2003-08-09";
	                          /**
     * 返回变化给定月份量的时间值，并格式化为yyyy-MM的时间字符串。<br>
     * 如：getMonthChange("2003-10-09",1)="2003-11-09"，<br>
     * getMonthChange("2003-10-09",-2)="2003-08-09"。
     *
     * @param strCurrentTime
     *            格式化为yyyy-MM-dd的时间String
     * @param iQuantity
     *            添加的月份时间量
     * @return 更改日期后的时间值，格式化yyyy-MM-dd的时间字符串
     */
	  public static String getMonthChange(String strCurrentTime,int iQuantity)
	  {
	        String strTarget="";
	        int    iYear     = Integer.parseInt(strCurrentTime.substring(0, 4));
	        int    iMonth    = Integer.parseInt(strCurrentTime.substring(5, 7));
	        int    iDay      = Integer.parseInt(strCurrentTime.substring(8, 10));
	        Calendar cal = Calendar.getInstance();
	        cal.set(Calendar.YEAR, iYear);
	        cal.set(Calendar.MONTH, iMonth-1);
	        cal.set(Calendar.DAY_OF_MONTH, iDay);
	        cal.add(Calendar.MONTH, iQuantity);
	        Date currentDate = cal.getTime();
	    	SimpleDateFormat formatter = new SimpleDateFormat ("yyyy-MM-dd");
	        strTarget= formatter.format(currentDate);
	        return strTarget;
	  }
	//-----------------------------------------------------------------------------------
    // 得到对比日期变化的目标日期
    // getDateChange("2003-10-15",1)="2003-10-16";
	  //getDateChange("2003-10-15",-2)="2003-10-13";
	                          /**
     * 返回变化给定日期量的时间值，并格式化为yyyy-MM-dd的时间字符串。<br>
     * 如：getDateChange("2003-10-15",1)="2003-10-16"，<br>
     * getDateChange("2003-10-15",-2)="2003-10-13"
     *
     * @param strCurrentDate
     *            格式化为yyyy-MM-dd的时间String
     * @param iQuantity
     *            添加的日期时间量
     * @return 更改日期后的时间值，格式化yyyy-MM-dd的时间字符串
     */
	public static String getDateChange(String strCurrentDate,int iQuantity)
	  {
	        String strTarget="";
	        int    iYear     = Integer.parseInt(strCurrentDate.substring(0, 4));
	        int    iMonth    = Integer.parseInt(strCurrentDate.substring(5, 7));
	        int    iDay      = Integer.parseInt(strCurrentDate.substring(8, 10));

	        Calendar cal = Calendar.getInstance();
	        cal.set(Calendar.YEAR, iYear);
	        cal.set(Calendar.MONTH, iMonth-1);
	        cal.set(Calendar.DAY_OF_MONTH, iDay);
	        cal.add(Calendar.DATE, iQuantity);
	        Date currentDate = cal.getTime();
	    	SimpleDateFormat formatter = new SimpleDateFormat ("yyyy-MM-dd");
	        strTarget= formatter.format(currentDate);
	        return strTarget;
	  }

	                        /**
     * 返回变化给定日期量的时间值，并格式化yyyy-MM-dd HH:mm:ss的时间字符串；日期为变化后的，时间为00：00：00。<br>
     * 如：getDateChange("2003-10-15",1)="2003-10-16 00:00:00"，<br>
     * getDateChange("2003-10-15",-2)="2003-10-13 00:00:00"
     *
     * @param strCurrentDate
     *            格式化为yyyy-MM-dd的时间String
     * @param iQuantity
     *            添加的日期时间量
     * @return 更改日期后的时间值，格式化yyyy-MM-dd HH:mm:ss的时间字符串；日期为变化后的，时间为23：59：59。
     */
	public static String getDateTimeChange(String strCurrentDate,int iQuantity)
	  {
	        String strTarget="";
	        int    iYear     = Integer.parseInt(strCurrentDate.substring(0, 4));
	        int    iMonth    = Integer.parseInt(strCurrentDate.substring(5, 7));
	        int    iDay      = Integer.parseInt(strCurrentDate.substring(8, 10));

	        Calendar cal = Calendar.getInstance();
	        cal.set(Calendar.YEAR, iYear);
	        cal.set(Calendar.MONTH, iMonth-1);
	        cal.set(Calendar.DAY_OF_MONTH, iDay);
	        cal.add(Calendar.DATE, iQuantity);
	        cal.set(Calendar.HOUR_OF_DAY, 23);
	        cal.set(Calendar.MINUTE, 59);
	        cal.set(Calendar.SECOND, 59);
	        Date currentDate=cal.getTime();
	    	SimpleDateFormat formatter = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
	        strTarget= formatter.format(currentDate);
	        return strTarget;
	  }

    /**
     * @param changeTime
     *            改变的时间（分钟）
     * @return 当前时间前多久或者后多久，格式化yyyy-MM-dd HH:mm:ss的时间字符串。 比如：当前时间俩小时前 getNowTimeChange(-120) ，俩小时后getNowTimeChange(120)
     */
	public static String getNowTimeChange(int changeTime){
		String strCurrentDateTime = "";
		Calendar caldate = null;
		Date currentDateTime = new Date();
		caldate = Calendar.getInstance();
		caldate.setTime(currentDateTime);
		currentDateTime = caldate.getTime();
		caldate.setTime(currentDateTime);
		caldate.add(Calendar.MINUTE, changeTime);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		strCurrentDateTime = formatter.format(caldate.getTime());
		return strCurrentDateTime;
	}

    /**
     * 日期的加减
     *
     * @param type
     *            Y-年 M-月 D-天
     * @param amount
     *            加减的数量
     * @author wangjianhua
     * @return
     */
	public static Date modifyDate(Date date, String type, int amount) {
		if (date == null) {
			date = new Date();
		}
		if (PubMethod.isEmpty(type)) {
			type = "";
		}
		Calendar now = Calendar.getInstance();
		now.setTime(date);
		// PubMethod.toPrint(now.get(Calendar.YEAR) + " " +
		// (now.get(Calendar.MONTH)+1) + " " + now.get(Calendar.DAY_OF_YEAR) + "
		// " + now.get(Calendar.DAY_OF_WEEK));
		if ("Y".equalsIgnoreCase(type)) {
			now.add(Calendar.YEAR, amount);
		} else if ("M".equalsIgnoreCase(type)) {
			now.add(Calendar.MONTH, amount);
		} else if ("D".equalsIgnoreCase(type)) {
			now.add(Calendar.DAY_OF_YEAR, amount);
		}
		return now.getTime();
	}

    /**
     * 分钟的加减
     *
     * @param amount
     *            加减的数量
     * @param format
     * @author wanglx
     * @return
     */
	public static String modifyMinute(Date date, int amount,String format) {

		if (date == null) {
			date = new Date();
		}
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		Calendar Cal= Calendar.getInstance();
		Cal.getTime();
		Cal.setTime(date);
		Cal.add(Calendar.MINUTE,amount);

		return formatter.format(Cal.getTime());
	}

    /**
     * 把格式为不同形式的时间字符串，去掉其中的"-"、" "和":"字符，转换为仅包含数字字符的时间字符串。 如：dateTimeFmtToStr("2011-04-15 16:54:33")="20110415165433"。
     *
     * @param dt
     *            格式为不同形式的时间字符串，如：yyyy-MM-dd HH:mm:ss或yyyy-MM-dd等。
     * @return 仅包含数字字符的时间字符串，如：yyyyMMddHHmmss或yyyyMMdd。
     */
	    public static String dateTimeFmtToStr ( String dt )
	    {
	        if ( dt == null || dt.equals ( "" ) ) {
                return "";
            }
	        String str = "";
	        for ( int i = 0; i < dt.length (); i++ ) {
	            if ( dt.charAt ( i ) == '-' || dt.charAt ( i ) == ' ' || dt.charAt ( i ) == ':' ) {
                    continue;
                }
	            str += dt.charAt ( i );
	        }
	        return str;

	    }

    /**
     * 获取两个日期之间相隔 时间
     */
		public static String datePlus(String startDate, String endDate) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // 将截取到的时间字符串转化为时间格式的字符串
        Date beginTime;
			try {
				beginTime = sdf.parse(startDate);

				Date endtime = sdf.parse(endDate);
            // 默认为毫秒，除以1000是为了转换成秒
            long interval = (endtime.getTime() - beginTime.getTime()) / 1000;// 秒
            long day = interval / (24 * 3600);// 天
            long hour = interval % (24 * 3600) / 3600;// 小时
            long minute = interval % 3600 / 60;// 分钟
            long second = interval % 60;// 秒
            String str = "两个时间相差：" + day + "天" + hour + "小时" + minute + "分" + second + "秒";
				return str;
			} catch (ParseException e) {
				return "";
			}
		}

    /**
     * 通过指定的一种格式, 返回Date时间类型
     *
     * @param dateStr
     *            时间格式为 yyyy/MM/dd 或者 yyyy MM dd 或者 yyyy-MM-dd格式的数据类型. 2003/12/15
     * @return Date
     */
    public static Date getDate(String dateStr) {
        if (null == dateStr || dateStr.equals("")) {
            return null;
        }
        dateStr = dateStr.trim();
        char c = 0;
        for (int i = 0; i < dateStr.length(); i++) {
            c = dateStr.charAt(i);
            if (!StringUtil.isNumber(c)) {
                break;
            }
        }
        if (StringUtil.isNumber(c)) {
            return null;
        }
        StringTokenizer tokenYmd = new StringTokenizer(dateStr, Character.toString(c));
        int year = Integer.parseInt(tokenYmd.nextToken().trim());
        int month = Integer.parseInt(tokenYmd.nextToken().trim());
        String dayStr = tokenYmd.nextToken().trim();
        int index;
        for (index = 0; index < dayStr.length(); index++) {
            c = dayStr.charAt(index);
            if (!StringUtil.isNumber(c)) {
                break;
            }
        }
        int day = Integer.parseInt(dayStr.substring(0, index));
        // added by wallace
        int hour = 0;
        int min = 0;
        int sec = 0;

        if (dateStr.length() > 10) {
            String hms = dateStr.substring(10);
            StringTokenizer tokenHms = new StringTokenizer(hms, ":");
            hour = Integer.parseInt(tokenHms.nextToken().trim());
            min = Integer.parseInt(tokenHms.nextToken().trim());
            sec = Integer.parseInt(tokenHms.nextToken().trim());
        }
        Calendar cal = Calendar.getInstance();
        cal.set(year, month - 1, day, hour, min, sec);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    /**
     * 获取Date
     * 
     * @param timeStr
     *            timeStr
     * @return Date
     */
    public static Date getTime(String timeStr) {
        if (null == timeStr || timeStr.equals("")) {
            return null;
        }

        int length = timeStr.length();
        if (7 == length) {
            timeStr = timeStr + "-01 00:00:00";
        }
        if (10 == length) {
            timeStr = timeStr + " 00:00:00";
        }
        if (length > 7 && length < 10) {
            String[] str = timeStr.split("-");
            if (null == str || str.length != 3) {
                return null;
            }
            if (1 == str[1].length()) {
                str[1] = "0" + str[1];
            }
            if (1 == str[2].length()) {
                str[2] = "0" + str[2];
            }
            timeStr = str[0] + "-" + str[1] + "-" + str[2] + " 00:00:00";
        }
        DateFormat format = new SimpleDateFormat(pattern);
        String regex = "[0-9]{4}-[0-9]{1,2}-[0-9]{1,2} [0-9]{2}:[0-9]{2}:[0-9]{2}";
        Pattern pattern = Pattern.compile(regex);
        if (!pattern.matcher(timeStr).matches()) {
            return null;
        }


        try {
            return format.parse(timeStr);
        } catch (Exception e) {
            System.out.println("--------- DateUtils.getTime() 出现异常! -----------");
            e.printStackTrace();
            return null;
        }
    }
    
    /**
     * 日期比较
     * @param dateStart
     * @param dateEnd
     * @return
     */
    public static int compare_date(String dateStart, String dateEnd) {
        
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        try {
            Date dt1 = df.parse(dateStart);
            Date dt2 = df.parse(dateEnd);
            if (dt1.getTime() > dt2.getTime()) {
                return 1;
            } else if (dt1.getTime() < dt2.getTime()) {
                return -1;
            } else {
                return 0;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return 0;
    }
    
    
   public static int compare(Date dateStart, Date dateEnd) {
        try {
            if (dateStart.getTime() > dateEnd.getTime()) {
                return 1;
            } else if (dateStart.getTime() < dateEnd.getTime()) {
                return -1;
            } else {
                return 0;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return 0;
    }
    
    public static int monthSpace(Date dateStart, Date dateEnd) throws ParseException {
    	Calendar bef = Calendar.getInstance();
    	Calendar aft = Calendar.getInstance();
    	bef.setTime(dateEnd);
    	aft.setTime(dateStart);
    	int result = aft.get(Calendar.MONTH) - bef.get(Calendar.MONTH);
    	int month = (aft.get(Calendar.YEAR) - bef.get(Calendar.YEAR))*12;
    	return result+month;
    }
   
    
    public static Date stringToDate(String str) {  
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");  
        Date date = null;  
        try {  
            date = format.parse(str);   
        } catch (ParseException e) {  
            e.printStackTrace();  
        }  
        return date;  
    }  
    
    
	//两个日期之间差的日
	public static int getDrawingInt(String nowdate, String paydate) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		int diff=0;
		try {
			Date pay = sdf.parse(nowdate);
			Date curr = sdf.parse(paydate);
			long diffDate = pay.getTime() - curr.getTime();
			diffDate = diffDate / 1000 / 24 / 60 / 60;
			diff = (int) diffDate;
		} catch (ParseException e) {
			
		}
		return diff;
	}
	
	//日期加一个月
	public static String getNextMonth(String payDate) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");// 设置日期格式
		String nextMonthDate ="";
		try {
			Date date = df.parse(payDate);
			Calendar c = Calendar.getInstance();
			c.setTime(date); 
			c.add(Calendar.MONTH, 1); 
			Date resDate = c.getTime();
			nextMonthDate = df.format(resDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		System.out.println("---"+nextMonthDate);
		return nextMonthDate;
	}
	
	//日期减一个月
	public static String getBeforeMonth(String payDate) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");// 设置日期格式
		String beforeMonthDate ="";
		try {
			Date date = df.parse(payDate);
			Calendar c = Calendar.getInstance();
			c.setTime(date); 
			c.add(Calendar.MONTH, -1); 
			Date resDate = c.getTime();
			beforeMonthDate = df.format(resDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		System.out.println("---"+beforeMonthDate);
		return beforeMonthDate;
	}
	
	public static boolean isLastDayOfMonth(String dateStr) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date date = sdf.parse(dateStr);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.set(Calendar.DATE, (calendar.get(Calendar.DATE) + 1));
			if (calendar.get(Calendar.DAY_OF_MONTH) == 1) {
				return true;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	//根据年月判断当月天数
    public static int getDaysByYearMonth(int year, int month) {  
        Calendar a = Calendar.getInstance();  
        a.set(Calendar.YEAR, year);  
        a.set(Calendar.MONTH, month - 1);  
        a.set(Calendar.DATE, 1);  
        a.roll(Calendar.DATE, -1);  
        int maxDate = a.get(Calendar.DATE);  
        return maxDate;  
    } 
    public static void main(String[] args) {
    	for(int i=1;i<30;i++){
    	    Calendar c = Calendar.getInstance();  
            c.setTime(new Date());   //设置当前日期  
            c.add(Calendar.DATE, i); //日期加1天  
            Date date = c.getTime();  
    		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
    		String businessDate=df.format(date);
    		System.out.println(businessDate);
    	}
    }
}
