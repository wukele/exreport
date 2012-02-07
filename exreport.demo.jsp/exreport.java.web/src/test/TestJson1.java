package test;

import java.math.BigInteger;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.qsoft.exreport.data.JsonReport;
import org.qsoft.exreport.test.Qtest;
import org.qsoft.exreport.test.RDto;

public class TestJson1 {


	public static void main(String[] map) {

		long lstart = System.currentTimeMillis();
		int sum = 50;
		String ss = testa(sum);
		System.out.println(ss);
		
		long lend = System.currentTimeMillis();
		long last = (lend - lstart) / 1000;
		
		System.out.println(ss.length() + ",this last " + last + " s.");
	}
	
	public static String testa(int len) {
		Map m = new HashMap();
		Qtest q = new Qtest();

		RDto[]  objs = q.report(len);
		String[] strs = JsonReport.convert(objs,new String[]{"code","name","area","sum","lastmonth","lastyear","term"});
		m.put(JsonReport.DTO_DATA_ARRAYS, strs);

		m.put("number", new BigInteger("111111111"));
		m.put("date", new Date().toLocaleString());
		m.put("unit", "万元");
		m.put("industry", "金融");
		m.put("area", "beijing");
		m.put("owner", "国有");
		m.put(JsonReport.REPORT_DTO_FIELDS_KEY, "code,name,area,sum,lastmonth,lastyear,term");
		m.put(JsonReport.HIDDEN_FIELDS, "lastmonth,lastyear");
		m.put(JsonReport.EXCEL_DATA_LENGTH, new Integer(objs.length));
		String ss = JsonReport.createExreportJson(m);
		return ss;
	}

	public static String test() {
		Map m = new HashMap();
		Qtest q = new Qtest();

		RDto[]  objs = q.report1();
		String[] strs = JsonReport.convert(objs,new String[]{"code","name","sum","lastmonth","lastyear","term"});
		m.put(JsonReport.DTO_DATA_ARRAYS, strs);

		m.put("number", new BigInteger("111111111111111111"));
		m.put("date", new Date().toLocaleString());
		m.put("unit", "万元");
		m.put("industry", "金融");
		m.put("area", "beijing");
		m.put("owner", "国有");
		m.put(JsonReport.REPORT_DTO_FIELDS_KEY, "code,name,sum,lastmonth,lastyear,term");
		m.put(JsonReport.HIDDEN_FIELDS, "lastmonth,lastyear");
		m.put(JsonReport.EXCEL_DATA_LENGTH, new Integer(objs.length));
		String ss = JsonReport.createExreportJson(m);
		return ss;
	}
	
	public static String testDyna() {
		Map m = new HashMap();
		Qtest q = new Qtest();

		Object obj = q.createList();
		m.put(JsonReport.DTO_DATA_ARRAYS, obj);

		m.put("number", "1234");
		m.put("date", new Date().toLocaleString());
		m.put("unit", "万元");
		m.put("industry", "金融");
		m.put("area", "beijing");
		m.put("owner", "国有");

		String ss = JsonReport.createExreportJson(m);
		return ss;
	}

	
	
}
