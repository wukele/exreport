package org.qsoft.exreport.test;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BasicDynaClass;
import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.beanutils.DynaProperty;

public class Qtest {



	public RDto[] report1() {
		RDto[] rd = new RDto[1000];
		for (int i = 0; i < rd.length; i++) {
			rd[i] = new RDto();
			rd[i].setCode(format(i%100));
			rd[i].setName("K.K." + i);
			rd[i].setSum(new Double(Math.random() * 10000).longValue());
			rd[i].setLastmonth((new Double(Math.random() * 10000).longValue()));
			rd[i].setLastyear((new Double(Math.random() * 10000).longValue()));
			rd[i].setTerm(new Long(new Double(Math.random() * 10000)
					.longValue()));
		}
		return rd;
	}
	
	public RDto[] report(int len) {
		
		String[] areas = {"东城","海淀","朝阳"};
		RDto[] rd = new RDto[len];
		for (int i = 0; i < rd.length; i++) {
			rd[i] = new RDto();
			rd[i].setCode(format(i%100));
			rd[i].setName("北京." + (new Double(Math.random() * 10).intValue()));
			rd[i].setArea(areas[new Double(Math.random() * 3).intValue()]);
			rd[i].setSum(new Double(Math.random() * 10000).longValue());
			rd[i].setLastmonth((new Double(Math.random() * 10000).longValue()));
			rd[i].setLastyear((new Double(Math.random() * 10000).longValue()));
			rd[i].setTerm(new Long(new Double(Math.random() * 10000)
					.longValue()));
		}
		return rd;
	}

	private String format(int i) {

		if (i < 10)
			return "S000" + i;
		else if (i < 100)
			return "S00" + i;
		else if (i < 1000)
			return "S0" + i;

		return "S" + i;
	}

	public List createList() {
		List list = new ArrayList();

		DynaProperty[] props = new DynaProperty[] {
				new DynaProperty("rowname", String.class),
				new DynaProperty("type", String.class),
				new DynaProperty("condition", String.class),
				new DynaProperty("times", String.class),
				new DynaProperty("value", Double.class) };

		BasicDynaClass dynaClass = new BasicDynaClass("Mydynabean", null, props);

		for (int i = 0; i < 1000; i++) {

			DynaBean bean = null;
			try {
				bean = dynaClass.newInstance();
				//bean.set("rowname2","dsfsd撒旦法" + Math.round(Math.random() * 2 + 1));
				bean.set("rowname","fff" + Math.round(Math.random() * 10 + 1));
				bean.set("type","aaa" + Math.round(Math.random() * 3 + 1));
				bean.set("condition","www" + Math.round(Math.random() * 2 + 1));
				bean.set("times",Math.random() > 0.5 ? "ff" : "tt");
				bean.set("value",new Double(Math.random() * 10000));
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InstantiationException e) {
				e.printStackTrace();
			}
			list.add(bean);
		}
		return list;

	}

}
