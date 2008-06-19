package org.qsoft.beanxml;

public interface IWriter {
	
	public abstract void WriteLine(String line);

	public abstract void WriteStartElement(String elementName);

	public abstract void WriteString(String elementType, String elementValue);
	
	public abstract void WriteString(String elementName,String elementType, String elementValue);

	public abstract void WriteEndElement();

	public abstract String createXml();

	public abstract IXmlEncode getEncode();

	public abstract void setEncode(IXmlEncode _encode);

	public abstract void Trim();
	
	public abstract void WriteWhitespace();

}