package org.util.xml;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;


/**
 * PackALunch
 * Created by Sadra on 12/30/15.
 */
public class XMLReader {

    public static void readXML(ArrayList<String> fileList) {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            DefaultHandler handler = new EOSParser();

            for (String file : fileList){
                saxParser.parse(file, handler);
            }


        } catch (Exception e)  {
            e.printStackTrace();
        }
    }

}
