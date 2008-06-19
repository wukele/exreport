package org.qsoft.exreport.data;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.beanutils.BeanUtils;


/**
 * 
 * 提供给报表使用� createJson方法的参数为map，其中包含替换字段和数据dto的集合
 * 
 * @author qinjinwei
 * 
 */
public class JsonReport {
	
	public static final String JAF_SIGNAL = ",";

	public static final String EXCEL_DATA_LENGTH = "EXCEL_DATA_LENGTH";

	public static final String HIDDEN_FIELDS = "HIDDEN_FIELDS";

	public static final String REPORT_DTO_FIELDS_KEY = "REPORT_DTO_FIELDS_KEY";

	public static final String DTO_DATA_ARRAYS = "DTO_DATA_ARRAYS";

	public static final String META_DATA_ARRAYS = "META_DATA_ARRAYS";

	/**
	 * @param map
	 * @return json js script
	 */
	public static String createExreportJson(Map map) {

		// int size = map.size();

		Set set = map.keySet();

		List list = new ArrayList();

		Object[] objs = set.toArray();
		for (int i = 0; i < objs.length; i++) {
			if (!objs[i].toString().endsWith(DTO_DATA_ARRAYS)
					&& !objs[i].toString().endsWith(META_DATA_ARRAYS)) {
				list.add(objs[i].toString());
			}
		}

		map.put(META_DATA_ARRAYS, list.toArray());

		JsonCreator creator = new JsonCreator(map);

		return creator.create();
	}
	
	public static String[] convert(Object[] arrays, String[] properties) {

		if (arrays == null || arrays.length == 0)
			return null;

		String[] array = new String[properties.length];
		StringBuffer[] arraysb = new StringBuffer[properties.length];

		String value = null;
		for (int i = 0; i < arrays.length; i++) {
			Object dto = arrays[i];
			for (int j = 0; j < properties.length; j++) {
				try {
					value = BeanUtils.getProperty(dto, properties[j]);

					if (value == null) {
						value = "";
					}

					value = value.replace(',', ' ');

					if (i == 0) {
						arraysb[j] = new StringBuffer();
						arraysb[j].append(value);
					} else {
						arraysb[j].append(JAF_SIGNAL);
						arraysb[j].append(value);
					}

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		for (int j = 0; j < properties.length; j++) {
			array[j] = arraysb[j].toString().replaceAll("\r\n", "");
		}

		return array;

	}

	public static String[] convert(List dtoList, String[] properties) {

		if (dtoList == null || dtoList.size() == 0)
			return null;

		if (properties == null) {
			try {
				String[] beanpros = (String[]) BeanUtils.describe(
						dtoList.get(0)).keySet().toArray(new String[] {});
				
				if(beanpros != null) return convert(dtoList.toArray(), beanpros);
				
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			}
		}

		return convert(dtoList.toArray(), properties);

	}

	
}
