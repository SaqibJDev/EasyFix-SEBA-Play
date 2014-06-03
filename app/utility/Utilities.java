package utility;

public class Utilities {
	public static String getSafeString(String str){
		if(str!=null)return str;
		else return "";
	}
	
	public static String getSpacedString(String str){
		return str.replace('-', ' ');
	}
	
	public static String getDashedString(String str){
		return str.replace(' ', '-');
	}

}
