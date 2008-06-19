package org.qsoft.beanxml;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.DynaBean;

/**
 * @author qinjinwei
 * 
 */
public class QJsonWriter extends AbstractXmlWriter {

	public IWriter getWriter() {
		return new JsonWriter();
	}

	public void writeXmlNode(IWriter writer, Object obj) {

		writeXmlNode(writer, null, obj);
		writer.Trim();
	}

	public void writeXmlNode(IWriter writer, String propertyName, Object obj) {

		if (obj == null || obj.getClass().equals(Class.class))
			return;

		Class type = obj.getClass();

		if (TypeUtil.isCommonType(type)) {
			writeOneNode(writer, getConvertType().convertType(type),
					propertyName, obj);
		}else if (obj instanceof Number) {
			
			writeOneNode(writer, "double",
					propertyName, obj);
		
		}else if (type.isArray()) {
			writeArrayXmlNode(writer, propertyName, obj);
		} else if (obj instanceof Collection) {
			Collection c = (Collection) obj;
			writeArrayXmlNode(writer, propertyName, c.toArray());
		} else if (obj instanceof Map) {
			Map map = (Map) obj;

			if (!map.isEmpty()) {

				Iterator iterator = map.entrySet().iterator();

				while (iterator.hasNext()) {

					Entry element = (Entry) iterator.next();

					String name = element.getKey().toString();

					writeXmlNode(writer, name, element.getValue());

				}

			}

		} else if (obj instanceof DynaBean) {
			try {
				Map map = BeanUtils.describe(obj);
				
				writer.WriteWhitespace();
				if (propertyName != null) {
					writer.WriteStartElement(propertyName);
				} else {
					writer.WriteStartElement(null);
				}
				
				if (!map.isEmpty()) {

					Iterator iterator = map.entrySet().iterator();

					while (iterator.hasNext()) {

						Entry element = (Entry) iterator.next();

						String name = element.getKey().toString();
						
						writeXmlNode(writer, name, element.getValue());
						
					}

				}
				
				writer.WriteWhitespace();
				writer.WriteEndElement();
				
				writer.WriteLine(",");
				
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			}
		} else {
			writer.WriteWhitespace();
			if (propertyName != null) {
				writer.WriteStartElement(propertyName);
			} else {
				writer.WriteStartElement(null);
			}
			writeBeanXmlNode(writer, obj);

			writer.WriteEndElement();
			writer.WriteWhitespace();
			writer.WriteLine(",");
		}
	}

	private void writeBeanXmlNode(IWriter writer2, Object obj) {

		Class type = obj.getClass();

		// // writer2.WriteStartElement(getConvertType().convertType(type));

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

		// writer2.WriteEndElement();

	}

	private void writeArrayXmlNode(IWriter writer2, Object array) {

		// if (array == null)
		// return;
		//
		// int len = Array.getLength(array);
		// for (int i = 0; i < len; i++) {
		// // // writer2.WriteStartElement("value");
		//
		// Object obj = Array.get(array, i);
		// writeXmlNode(writer2, obj);
		//
		// // writer2.WriteEndElement();
		// }

	}

	private void writeOneNode(IWriter writer2, String propertyType,
			String propertyName, Object propertyValue) {
		writer2.WriteString(propertyName, propertyType, propertyValue
				.toString());
	}

	public void writeXmlNode(IWriter writer2, Object obj, PropertyDescriptor pi) {

		String propertyName = pi.getName();
		if (pi.getReadMethod() == null || propertyName.equals("class"))
			return;

		Object propertyValue = null;
		try {
			propertyValue = pi.getReadMethod().invoke(obj, null);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}

		Class propertyType = pi.getPropertyType();

		if (propertyValue != null) {

			// // writer2.WriteStartElement(propertyName);

			if (TypeUtil.isCommonType(propertyType)) {
				writeOneNode(writer2, getConvertType()
						.convertType(propertyType), propertyName, propertyValue);
			} else if (propertyType.isArray()) {

				writeArrayXmlNode(writer2, propertyName, propertyValue);

			} else if (propertyType.equals(Object.class)) {

				if (propertyValue.getClass().isArray()) {
					// Class arrayType = propertyValue.getClass();
					// //
					// writer2.WriteStartElement(TypeUtil.convertArrayType(arrayType)
					// + ".array");
					writeArrayXmlNode(writer2, propertyName, propertyValue);
					// writer2.WriteEndElement();
				} else {
					writeXmlNode(writer2, propertyName, propertyValue);
				}
			} else {
				writeXmlNode(writer2, propertyName, propertyValue);
			}
			// writer2.WriteEndElement();
		} else {
			writer2.WriteString(propertyName, getConvertType().convertType(
					propertyType), null);
		}

	}

	private void writeArrayXmlNode(IWriter writer2, String propertyName,
			Object propertyValue) {

		if (propertyValue == null) {
			writer2.WriteString(propertyName, propertyValue.getClass()
					.getName(), null);
			return;
		}

		int len = Array.getLength(propertyValue);

		writer2.WriteWhitespace();
		if (propertyName != null) {
			writer2.WriteLine("\"" + propertyName + "\":[");
		} else
			writer2.WriteLine("[");

		for (int i = 0; i < len; i++) {
			// // writer2.WriteStartElement("value");

			Object obj = Array.get(propertyValue, i);
			writeXmlNode(writer2, null, obj);

			// writer2.WriteEndElement();
		}

		writer2.Trim();

		writer2.WriteWhitespace();
		writer2.WriteLine("],");

	}

}
