package org.qsoft.beanxml;

public class QXmlEncode implements IXmlEncode {

	private static String Left_Quot = "&lt;";
	private static String Right_Quot = "&gt;";
	private static String And = "&amp;";
	
	public String encode(String source) {
		
		if(source == null) return source;
		
		String dest = source;
		
		dest = dest.replaceAll("&", And);
		dest = dest.replaceAll("<", Left_Quot);
		dest = dest.replaceAll(">", Right_Quot);
		
		//String dest = dest.replaceAll("<", replacement);
		
		return dest;
	}

}
