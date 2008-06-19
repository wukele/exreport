package org.qsoft.beanxml;

public class QXmlCreator implements IXmlCreator {

	// private final static String TYPE = ".writer";

	private String language = "json";

	private Object xmlobj;

	public QXmlCreator(Object xmlobj) {
		this.xmlobj = xmlobj;
	}

	public QXmlCreator(Object xmlobj, String type) {
		this.xmlobj = xmlobj;
		this.language = type;
	}

	public String creatrXml() {
		return getXmlWriter().Serialize(this.xmlobj);
	}

	public IXmlWriter getXmlWriter() {

		IXmlWriter _iXmlWriter = new QJsonWriter();

		return _iXmlWriter;
	}

	public Object getXmlobj() {
		return xmlobj;
	}

	public void setXmlobj(Object xmlobj) {
		this.xmlobj = xmlobj;
	}

}
