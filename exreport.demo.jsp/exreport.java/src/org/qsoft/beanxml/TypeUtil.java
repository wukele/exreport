package org.qsoft.beanxml;

import java.util.Date;

public class TypeUtil {

	public static boolean isCommonType(Class type) {

		return (type.isPrimitive() || type.equals(String.class)
				|| type.equals(Boolean.class) || type.equals(Date.class)
				|| type.equals(Short.class) || type.equals(Integer.class)
				|| type.equals(Long.class) || type.equals(Float.class)
				|| type.equals(Double.class) || type.equals(Byte.class) || type
				.equals(Character.class))
				|| type.equals(java.sql.Date.class);

	}

	public static boolean isCommonType(String className) {

		if (className.equals("string")) {
			return true;
		} else if (className.equals("bool")) {
			return true;
		} else if (className.equals("date")) {
			return true;
		} else if (className.equals("short")) {
			return true;
		} else if (className.equals("int")) {
			return true;
		} else if (className.equals("long")) {
			return true;
		} else if (className.equals("float")) {
			return true;
		} else if (className.equals("double")) {
			return true;
		} else if (className.equals("byte")) {
			return true;
		} else if (className.equals("char")) {
			return true;
		} else
			return false;
	}
	
	public static boolean isStringType(String className) {

		if (className.equals("string")) {
			return true;
		} else if (className.equals("date")) {
			return true;
		} else
			return false;
	}

	public static Object createObject(String className) {

		Object obj = null;

		Class clazz = createClass(className);
		obj = createObject(clazz);

		return obj;
	}

	public static Object createObject(Class clazz) {

		Object obj = null;
		try {
			obj = clazz.newInstance();

		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}

		return obj;
	}

	public static Class createClass(String className) {

		Class clazz = null;

		if (isCommonType(className)) {
			clazz = createCommonClass(className);
		} else {
			try {
				clazz = Class.forName(className);
				// obj = clazz.newInstance();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		return clazz;
	}

	public static Class createCommonClass(String className) {

		Class clazz = null;

		if (className.equals("bool")) {
			clazz = Boolean.TYPE;
		} else if (className.equals("short")) {
			clazz = Short.TYPE;
		} else if (className.equals("int")) {
			clazz = Integer.TYPE;
		} else if (className.equals("long")) {
			clazz = Long.TYPE;
		} else if (className.equals("float")) {
			clazz = Float.TYPE;
		} else if (className.equals("double")) {
			clazz = Double.TYPE;
		} else if (className.equals("byte")) {
			clazz = Byte.TYPE;
		} else if (className.equals("char")) {
			clazz = Character.TYPE;
		} else if (className.equals("string")) {
			clazz = String.class;
		} else if (className.equals("date")) {
			clazz = Date.class;
		} else
			throw new RuntimeException("不明确的类型[" + className + "].");

		return clazz;
	}

	public static Object createType(String string) {

		return createObject(string);
	}

	public static String convertArrayType(Class type) {

		String arrayTypeName = type.getName();
		String elementTypeName = null;
		if (arrayTypeName.length() == 2) {
			if (arrayTypeName.equals("[I")) {
				elementTypeName = "int";
			} else if (arrayTypeName.equals("[J")) {
				elementTypeName = "int";
			} else if (arrayTypeName.equals("[B")) {
				elementTypeName = "byte";
			} else if (arrayTypeName.equals("[S")) {
				elementTypeName = "short";
			} else if (arrayTypeName.equals("[C")) {
				elementTypeName = "char";
			} else if (arrayTypeName.equals("[F")) {
				elementTypeName = "float";
			} else if (arrayTypeName.equals("[D")) {
				elementTypeName = "double";
			} else if (arrayTypeName.equals("[Z")) {
				elementTypeName = "bool";
			} else {
				throw new RuntimeException("���ܴ������������[" + arrayTypeName
						+ "].");
			}

		} else {
			elementTypeName = arrayTypeName.substring(2,
					arrayTypeName.length() - 1);
		}

		return elementTypeName;
	}

	public static String[] convertCommonTypeArray(Class[] types) {

		if (types == null)
			return null;

		IConvertType _iConvertType = new QConvertType();

		String[] typeNames = new String[types.length];

		for (int i = 0; i < types.length; i++) {
			Class type = types[i];
			if (isCommonType(type)) {
				typeNames[i] = _iConvertType.convertType(type);
			} else if (type.isArray()) {
				typeNames[i] = TypeUtil.convertArrayType(type) + ".array";
			} else {
				typeNames[i] = type.getName();
			}
		}

		return typeNames;

	}

}
