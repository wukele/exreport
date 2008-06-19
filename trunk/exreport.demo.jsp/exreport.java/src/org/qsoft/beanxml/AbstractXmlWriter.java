package org.qsoft.beanxml;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;

/**
 * @author qinjinwei
 * 
 */
public abstract class AbstractXmlWriter implements IXmlWriter {

	private IConvertType _iConvertType = new QConvertType();

	private IWriter writer = null;

	public synchronized String Serialize(Object obj) {
		writer = getWriter();
		writeXmlNode(writer, obj);
		return writer.createXml();
	}

	public void writeXmlNode(IWriter writer, Object obj) {

		if (obj == null || obj.getClass().equals(Class.class))
			return;

		Class type = obj.getClass();

		if (TypeUtil.isCommonType(type)) {
			writeOneNode(writer, getConvertType().convertType(type), obj);
		} else if (type.isArray()) {
			writeArrayXmlNode(writer, obj);
		} else {
			writeBeanXmlNode(writer, obj);
		}
	}

	private void writeBeanXmlNode(IWriter writer2, Object obj) {

		Class type = obj.getClass();

		writer2.WriteStartElement(getConvertType().convertType(type));

		BeanInfo info = null;
		try {
			info = Introspector.getBeanInfo(type);
		} catch (IntrospectionException e) {

			e.printStackTrace();
		}

		if (info != null) {
			PropertyDescriptor[] descriptors = info.getPropertyDescriptors();
			for (int i = 0; i < descriptors.length; i++) {
				PropertyDescriptor descriptor = descriptors[i];
				writeXmlNode(writer2, obj, descriptor);
			}
		}

		writer2.WriteEndElement();

	}

	private void writeArrayXmlNode(IWriter writer2, Object array) {

		if (array == null)
			return;

		int len = Array.getLength(array);
		for (int i = 0; i < len; i++) {
			writer2.WriteStartElement("value");

			Object obj = Array.get(array, i);
			writeXmlNode(writer2, obj);

			writer2.WriteEndElement();
		}

	}

	private void writeOneNode(IWriter writer2, String propertyType,
			Object propertyValue) {
		writer2.WriteString(propertyType, propertyValue.toString());
	}

	public void writeXmlNode(IWriter writer2, Object obj, PropertyDescriptor pi) {

		String propertyName = pi.getName();
		if (pi.getReadMethod() == null || propertyName.equals("class"))
			return;

		Object propertyValue = null;
		try {
			propertyValue = pi.getReadMethod().invoke(obj,null);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}

		Class propertyType = pi.getPropertyType();

		if (propertyValue != null) {

			writer2.WriteStartElement(propertyName);

			if (TypeUtil.isCommonType(propertyType)) {
				writeOneNode(writer2, getConvertType()
						.convertType(propertyType), propertyValue);
			} else if (propertyType.isArray()) {

				writeArrayXmlNode(writer2, propertyValue);

			} else if (propertyType.equals(Object.class)) {

				if (propertyValue.getClass().isArray()) {
					Class arrayType = propertyValue.getClass();
					writer2.WriteStartElement(TypeUtil
							.convertArrayType(arrayType)
							+ ".array");
					writeArrayXmlNode(writer2, propertyValue);
					writer2.WriteEndElement();
				} else {
					writeXmlNode(writer2, propertyValue);
				}
			} else {
				writeXmlNode(writer2, propertyValue);
			}
			writer2.WriteEndElement();
		} else {
			writer2.WriteString(propertyName, null);
		}

	}

	public IConvertType getConvertType() {
		return _iConvertType;
	}

	public void setConvertType(IConvertType convertType) {
		_iConvertType = convertType;
	}

	public abstract IWriter getWriter();

}
