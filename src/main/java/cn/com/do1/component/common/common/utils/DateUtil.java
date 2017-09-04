package cn.com.do1.component.common.common.utils;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.text.*;

public class DateUtil {
	
	/**
	 * 
	 * 功能：格式化字符转换为日期
	 * @param str
	 * @param format
	 * @return
	 */
	public static Date strToDate(String str , String format){
		Date date = null ;
		try{
			SimpleDateFormat simple = new SimpleDateFormat(format);
			date = simple.parse(str);
		}catch(Exception e){
			e.printStackTrace();
		}
		return date ;
	}
	
	public static String fotmatDate1(Date myDate) {
		SimpleDateFormat formatter = new SimpleDateFormat(
				"yyyy年MM月dd日 HH时mm分ss秒");
		String strDate = formatter.format(myDate);
		return strDate;
	}
	
	public static String fotmatDateYM(Date myDate) {
		if(myDate != null){
			SimpleDateFormat formatter = new SimpleDateFormat(
					"yyyy-MM");
			String strDate = formatter.format(myDate);
			return strDate;
		}
		else{
			return "";
		}
	}
	
	public static java.util.Date strToDate(String myDate) {
		java.util.Date strDate = null;
		try{
			
			SimpleDateFormat timeformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			//Date date = Calendar.getInstance().getTime();
			return timeformat.parse(myDate);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return strDate;
	}

	
	public static String fotmatDate2(Date myDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日");
		String strDate = formatter.format(myDate);
		return strDate;
	}

	public static String fotmatDate3(Date myDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String strDate = formatter.format(myDate);
		return strDate;
	}
	
	public static String fotmatDateYMHm(Date myDate) {
		if(myDate == null){
			return "";
		}
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String strDate = formatter.format(myDate);
		return strDate;
	}

	public static String fotmatDate4(Date myDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String strDate = formatter.format(myDate);
		return strDate;
	}

	public static String fotmatDate5(Date myDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
		String strDate = formatter.format(myDate);
		return strDate;
	}

	public static String fotmatDate6(Date myDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("MM-dd HH:mm");
		String strDate = formatter.format(myDate);
		return strDate;
	}

	public static String fotmatDate7(Date myDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		String strDate = formatter.format(myDate);
		return strDate;
	}

	public static String fotmatDate8(Date myDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
		String strDate = formatter.format(myDate);
		return strDate;
	}

	public static String fotmatDate9(String myDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
		Date dDate = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(myDate,
				new ParsePosition(0));
		String strDate = formatter.format(dDate);
		return strDate;
	}

	public static String fotmatDate10(String myDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("MM");
		Date dDate = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(myDate,
				new ParsePosition(0));
		String strDate = formatter.format(dDate);
		return strDate;
	}

	public static String fotmatDate11(String myDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("dd");
		Date dDate = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(myDate,
				new ParsePosition(0));
		String strDate = formatter.format(dDate);
		return strDate;
	}

	public static String fotmatDate12() {
		Date myDate = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String strDate = formatter.format(myDate);
		return strDate;
	}

	public static String fotmatDate13(String myDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date dDate = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(myDate,
				new ParsePosition(0));
		String strDate = formatter.format(dDate);
		return strDate;
	}

	public static String fotmatDate14(Date myDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
		String strDate = formatter.format(myDate);
		return strDate;
	}

	public static String fotmatDate15(Date myDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("dd");
		String strDate = formatter.format(myDate);
		return strDate;
	}

	public static String fotmatDate16(String myDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date dDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(myDate,
				new ParsePosition(0));
		String strDate = formatter.format(dDate);
		return strDate;
	}

	public static String fotmatDate17(String myDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date dDate = new SimpleDateFormat("yyyyMMdd").parse(myDate,
				new ParsePosition(0));
		String strDate = formatter.format(dDate);
		return strDate;
	}

	public static Date fotmatDate18(String myDate) {
		// SimpleDateFormat formatter = new SimpleDateFormat ("yyyyMMddHHmmss");
		if(myDate == null || "".equals(myDate)) return null;
		Date dDate = (Date) new SimpleDateFormat("yyyyMMddHHmmss").parse(myDate,
				new ParsePosition(0));
		return dDate;
	}
	
	public static String fotmatDate19(String myDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		Date dDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(myDate,
				new ParsePosition(0));
		String strDate = formatter.format(dDate);
		return strDate;
	}
	public static String fotmatDate20(String myDate) {
		if(myDate == null || "".equals(myDate)) return null;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date dDate = new SimpleDateFormat("yyyy-MM-dd").parse(myDate,
				new ParsePosition(0));
		String strDate = formatter.format(dDate);
		return strDate;
	}
	public static Date fotmatDate21(String myDate) {
		//SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
		Date dDate = new SimpleDateFormat("HH:mm:ss").parse(myDate,
				new ParsePosition(0));
		return dDate;
	}
	
	public static Date fotmatDate22(String myDate) {
		Date dDate = (Date) new SimpleDateFormat("yyyy-MM-dd").parse(myDate,
				new ParsePosition(0));
		return dDate;
	}
	
	public static String fotmatDate23(Date myDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM");
		String strDate = formatter.format(myDate);
		return strDate;
	}
	
	public static String fotmatDate24(Date myDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("HHmmss");
		String strDate = formatter.format(myDate);
		return strDate;
	}
	
	public static java.util.Date strToDateSimple(String myDate) {
		java.util.Date strDate = null;
		try{
			return new SimpleDateFormat("yyyy-MM-dd").parse(myDate);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return strDate;
	}
	
	/**
	 * 把日期转成对应格式 如：yyyyMMddHHmmssZ等等
	 * 
	 * @param myDate
	 * @return
	 */
	public static String formatDate(Date myDate, String type) {
		SimpleDateFormat formatter = new SimpleDateFormat(type);
		String strDate = formatter.format(myDate);
		return strDate;
	}

	/**
	 * 条码接口报文格式化工具类
	 * 
	 * @author Yang
	 * @version 1.0
	 * 
	 */
	public static String formatRespData(String respData) {
		if (respData == null || "".equals(respData)) {
			return "";
		}
		respData = respData.replaceAll("</ ", "</").replaceAll("< /", "</")
				.replaceAll(" >", ">").replaceAll("< ", "<");
		respData = respData.substring(respData.indexOf(">") + 1, respData
				.length());
		respData = "<?xml version=\"1.0\" encoding=\"GBK\"?>" + respData;

		return respData;
	}

	/**
	 * 判断输入的参数是否是Date类型 
	 * @param strValue  输入参数
	 * @return boolean  True:是Date类型;False:不是Date类型
	 */
	public static boolean isDate(String dateStr) {
		
		try {
			if (dateStr == null) {
				return false;
			}
			dateStr = dateStr.trim();
			String maxDateStr = "20991231";
			SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
			Date date = df.parse(dateStr+"235959");	
			Date maxDate = df.parse(maxDateStr+"235959");
			if (date.before(new Date())){
				return false;
			}
			if (date.after(maxDate)){
				return false;
			}
			SimpleDateFormat df1 = new SimpleDateFormat("yyyyMMdd");
			Date date1 = df1.parse(dateStr);
			String dateStr2 = df.format(date1).substring(0,8);

			if (!dateStr.equals(dateStr2)) {
				return false;
			}

			return true;
		}
		catch (Exception e) {
			return false;
		}

	}
	
	
	/**
	 * 将时间字符串转换为时间对象
	 * @param dateTimeStr 格式：yyyyMMddhhmmss
	 * @return Date
	 */
	public static Date stringToDateTimeNoSplit(String dateTimeStr) {		
		if (!isDateTimeNoSplit(dateTimeStr)) {
			return null;
		}
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddhhmmss");
		java.util.Date date = null;
		try {
			date = df.parse(dateTimeStr.trim());
		} catch (ParseException ex) {
			ex.printStackTrace();
		}
		return date;
	}
	/**
	 *  判断是否是日期时间
	 * @param strDate "yyyyMMddhhmmss"
	 * @return false不是，true为是
	 */
	public static boolean isDateTimeNoSplit(String strDate){
		if(strDate==null){
			return false;
		}
		String date="^(((((1[6-9]|[2-9]\\d)\\d{2})(0?[13578]|1[02])(0?[1-9]|[12]\\d|3[01]))|(((1[6-9]|[2-9]\\d)\\d{2})(0?[13456789]|1[012])(0?[1-9]|[12]\\d|30))|(((1[6-9]|[2-9]\\d)\\d{2})0?2(0?[1-9]|1\\d|2[0-8]))|(((1[6-9]|[2-9]\\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))0?229))((([0-1]{1})([0-9]{1}))|(2?[0-3]))([0-5][0-9])([0-5][0-9]))";
		Pattern p1 = Pattern
		.compile(date);
		Matcher m1 = p1.matcher(strDate);
		if (m1.matches()) {
			return true;
		} else {
			return false;
		}
	}
	/**
	 * 将时间字符串转换为时间对象
	 * @param dateTimeStr 格式：yyyyMMddhhmmss
	 * @return Date
	 */
	public static Date StringToDateTimeNoSplit(String dateTimeStr) {		
		if (!isDateTimeNoSplit(dateTimeStr)) {
			return null;
		}
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddhhmmss");
		java.util.Date date = null;
		try {
			date = df.parse(dateTimeStr.trim());
		} catch (ParseException ex) {
			ex.printStackTrace();
		}
		return date;
	}
	
	
	/**
	 * 获得当前年份
	 * @return yyyy
	 */
	public static String getCurrentYear()
	{
		int year =  Calendar.getInstance().get(Calendar.YEAR);
		return String.valueOf(year);
	}
	
	/**
	 * 获取当前月份
	 * @return
	 */
	public static String getCurrentMonth()
	{
		int month =  Calendar.getInstance().get(Calendar.MONTH) + 1;
		String str = null;
		if (month<10){
			str = "0" + month;
		}
		else{
			str = String.valueOf(month);
		}
		return str;
	}
	
	
	/**
	 * 获取当前日
	 * @return
	 */
	public static String getCurrentDay()
	{
		String str = null;
		int day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
		if (day<10){
			str = "0" + day;
		}
		else{
			str = String.valueOf(day);
		}
		return str;
	}
	
	/**
	 * 返回参数日期上个月的最后一天
	 * @param currDate
	 * @return
	 */
	public static Date lastDayInMonth(Date currDate){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(currDate);
		calendar.set(Calendar.DAY_OF_MONTH, 0);
		return calendar.getTime();
	}
	

	/**
	 * 获取当前日期的前一天
	 */
	public static String getYesDay() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		SimpleDateFormat sdf  = new SimpleDateFormat("yyyy-MM-dd");
		String yesDate = sdf.format(calendar.getTime());
		return yesDate;
	}
	
	/**
	 * 获取某时间的后一天
	 */
	public static Date addDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, 1);
		return calendar.getTime();
	}
	
	 /**   
     * 获取单位个小时后（前）的时间   
     */    
    public static Date getTimeDayNext(int hour) {     
    	Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR_OF_DAY, hour);    
        return calendar.getTime();     
    }  
    
   
    
	
	public static void main(String[] arg) {
		Date date = addDay(new Date());
		System.out.println(date);
	}
}
