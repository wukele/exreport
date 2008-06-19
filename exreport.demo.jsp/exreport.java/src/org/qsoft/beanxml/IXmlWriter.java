package org.qsoft.beanxml;

import java.beans.PropertyDescriptor;

public interface IXmlWriter {

    void writeXmlNode(IWriter writer, Object obj);

    void writeXmlNode(IWriter writer, Object obj, PropertyDescriptor pi); 

    String Serialize(Object obj);  
}
