package org.qsoft.beanxml;

public class QConvertType implements IConvertType {

	public String convertType(Class clazz) {
		
		if(clazz.isPrimitive())
		{
			if(clazz.equals(Boolean.TYPE))
				return "bool";
			else
				return clazz.getName();
		}
		
		String local_name = clazz.getName();
		String common_name = null;
		if(local_name.equals("java.lang.String"))
		{
			common_name = "string";
		}
		else if(local_name.equals("java.lang.Boolean"))
		{
			common_name = "bool";
		}
		else if(local_name.equals("java.util.Date"))
		{
			common_name = "date";
		}
		else if(local_name.equals("java.sql.Date"))
		{
			common_name = "date";
		}
		else if(local_name.equals("java.lang.Short"))
		{
			common_name = "short";
		}
		else if(local_name.equals("java.lang.Integer"))
		{
			common_name = "int";
		}
		else if(local_name.equals("java.lang.Long"))
		{
			common_name = "long";
		}
		else if(local_name.equals("java.lang.Float"))
		{
			common_name = "float";
		}
		else if(local_name.equals("java.lang.Double"))
		{
			common_name = "double";
		}
		else if(local_name.equals("java.lang.Byte"))
		{
			common_name = "byte";
		}
		else if(local_name.equals("java.lang.Character"))
		{
			common_name = "char";
		}
		else if(local_name.equals("java.lang.Object"))
		{
			common_name = "object";
		}
		else
		{
			common_name = clazz.getName();
		}
			
	
		return common_name;
	}

	public String localType(String commonName) {
		
		return commonName;
	}

}
