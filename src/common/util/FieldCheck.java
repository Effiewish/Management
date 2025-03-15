package common.util;

/**
 * 校验的规则
 * @author ji_zhh
 *
 */
public class FieldCheck {
	/**
	 * 判断字符串是否为null或空串
	 * @param str ：要校验的字段
	 * @return  true: 为空 false:不为空
	 */
	
	public static boolean isNullOrBlank(String str){
		return(str==null || str.trim().length()==0);
	}
	
	
	/**
	 * 将null转换为空
	 * @param str
	 * @return
	 */
	public static String convertNullToEmpty(String str){
		if(str==null)
			return "";
		else
			return str;
	}
	
	/**
	 * 去掉字符串两边的空格
	 * @param str
	 * @return
	 */
	
	public static String trimString(String str){
		if(str==null)
			return "";
		else
			return str.trim();
	}
	

}
