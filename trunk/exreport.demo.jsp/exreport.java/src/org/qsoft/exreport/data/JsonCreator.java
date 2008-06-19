package org.qsoft.exreport.data;

import org.qsoft.beanxml.QXmlCreator;

public class JsonCreator {
	
	private Object obj;
	
	//private static String 
	
	public JsonCreator(Object o)
	{
		this.obj = o;
	}
	
	public String create()
	{
		QXmlCreator creator = new QXmlCreator(this.obj, "json");

		return creator.creatrXml();
	}

}
