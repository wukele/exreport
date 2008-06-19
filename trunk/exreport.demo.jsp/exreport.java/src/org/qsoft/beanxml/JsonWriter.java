package org.qsoft.beanxml;

import java.util.Stack;

/**
 * @author qinjinwei
 * 
 */
public class JsonWriter implements IWriter {

	private static char IndentChars = '\t';

	private static char LeftChars = '{';

	private static char RightChars = '}';

	private static char SectChars = '\"';

	private static char SplitChars = ':';

	private static char DotChars = ',';

	private Stack stack = new Stack();

	private static IXmlEncode encode = new QXmlEncode();

	private StringBuffer buffer = new StringBuffer();

	public void WriteStartElement(String elementName) {
		WriteWhitespace();
		if (elementName != null)
			buffer.append(StartElement(elementName) + SplitChars);
		buffer.append(LeftChars);
		buffer.append("\r\n");
		stack.push(RightChars + "");

	}

	public void WriteString(String elementName, String elementValue) {
		// WriteWhitespace();
		// if(elementValue == null)
		// {
		// buffer.append(NullElement(elementName));
		// buffer.append("\r\n");
		// }
		// else if(elementName == null)
		// {
		// buffer.append(checkType(elementType, encode.encode((elementValue))));
		// buffer.append("\r\n");
		// }
		// else
		// {
		// buffer.append( StartElement(elementName ) );
		// buffer.append(SplitChars);
		// buffer.append(encode.encode((elementValue)));
		// buffer.append(DotChars);
		// buffer.append("\r\n");
		// }
	}

	private String NullElement(String elementName) {
		return StartElement(elementName) + SplitChars + "null" + DotChars;
	}

	public void WriteEndElement() {
		if (!stack.isEmpty()) {
			Trim();

			String elementName = stack.pop().toString();
			WriteWhitespace();
			buffer.append(elementName);
			buffer.append("\r\n");
		}
	}

	private void WriteWhitespace(int count) {
		for (int i = 0; i < count; i++) {
			buffer.append(IndentChars);
		}
	}

	public void WriteWhitespace() {
		WriteWhitespace(stack.size());
	}

	private String StartElement(String elementName) {
		return SectChars + elementName + SectChars;
	}

	public String createXml() {
		return buffer.toString();
	}

	public IXmlEncode getEncode() {
		return encode;
	}

	public void setEncode(IXmlEncode _encode) {
		encode = _encode;
	}

	public void WriteString(String elementName, String elementType,
			String elementValue) {

		WriteWhitespace();
		if (elementValue == null) {
			buffer.append(NullElement(elementName));
			buffer.append("\r\n");
		} else if (elementName == null) {
			buffer
					.append(checkType(elementType, encode
							.encode((elementValue))));
			buffer.append(DotChars);
			buffer.append("\r\n");
		} else {
			buffer.append(StartElement(elementName));
			buffer.append(SplitChars);
			buffer
					.append(checkType(elementType, encode
							.encode((elementValue))));
			buffer.append(DotChars);
			buffer.append("\r\n");
		}

	}

	private String checkType(String elementType, String value) {

		if (TypeUtil.isStringType(elementType)) {
			return SectChars + value + SectChars;
		}

		return value;
	}

	public void WriteLine(String line) {
		buffer.append(line);
		buffer.append("\r\n");
	}

	public void Trim() {

		char c = buffer.charAt(buffer.length() - 3);
		if (DotChars == c) {
			buffer.deleteCharAt(buffer.length() - 3);
		}

	}

}
