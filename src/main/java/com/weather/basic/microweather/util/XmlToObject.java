package com.weather.basic.microweather.util;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

public class XmlToObject {
    public static Object trans(Class<?> clz,String xmlStr) throws JAXBException, IOException {
        Object obj = null;
        Reader reader = null;
        JAXBContext context = JAXBContext.newInstance(clz);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        reader = new StringReader(xmlStr);
        obj = unmarshaller.unmarshal(reader);
        if (reader != null){
            reader.close();
        }
        return obj;
    }

}
