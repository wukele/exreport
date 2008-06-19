package org.qsoft.exreport.test;

public class RDto {
	
	private String code;
	private String name;
	private String area;
	private long sum;
	private long lastmonth;
	private long lastyear;
	private Long term;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public long getLastmonth() {
		return lastmonth;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public void setLastmonth(long lastmonth) {
		this.lastmonth = lastmonth;
	}
	public long getLastyear() {
		return lastyear;
	}
	public void setLastyear(long lastyear) {
		this.lastyear = lastyear;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getSum() {
		return sum;
	}
	public void setSum(long sum) {
		this.sum = sum;
	}
	public Long getTerm() {
		return term;
	}
	public void setTerm(Long term) {
		this.term = term;
	}
	

}
