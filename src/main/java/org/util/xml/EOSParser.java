package org.util.xml;

import org.util.text.analyzer.NamedEntityExtractor;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * PackALunch
 * Created by Sadra on 1/6/16.
 */
public class EOSParser extends DefaultHandler {

    NamedEntityExtractor namedEntityExtractor ;

    public EOSParser() {
        super();
        namedEntityExtractor = new NamedEntityExtractor();
    }

    boolean id = false;
    boolean sourceName = false;
    boolean captureDateTime = false;
    boolean publicationDateTime = false;
    boolean title = false;
    boolean translatedTitle = false;
    boolean sourceCoverage = false;
    boolean url = false;
    boolean language = false;
    boolean sourceType = false;
    boolean robotName = false;
    boolean text = false;

    /*
        <Id>en_2014-02-25_3a18847e42e8c9a37646b9f49e903de7e21b5c63</Id>
        <SourceName>Panarmenian.NET</SourceName>
        <CaptureDateTime>2014-02-25 15:43:53</CaptureDateTime>
        <PublicationDateTime>25 Feb 2014 00:00:00</PublicationDateTime>
        <Title>Obama says U.S. may leave no troops in Afghanistan after 2014</Title>
        <TranslatedTitle/>
        <SourceCoverage>ARMENIA</SourceCoverage>
        <Url>http://www.panarmenian.net/eng/news/176259/</Url>
        <Encoding>UTF-8</Encoding>
        <Language>English</Language>
        <SourceType>Mainstream Media</SourceType>
        <RobotName>panarmeni11512</RobotName>
        <Text>News contents</text>
    */

    public void startElement(String uri, String localName,String qName,
                             Attributes attributes) throws SAXException {

        if (qName.equalsIgnoreCase("CaptureDateTime"))
            captureDateTime = true;

        if (qName.equalsIgnoreCase("PublicationDateTime"))
            publicationDateTime = true;

        if (qName.equalsIgnoreCase("Url"))
            url = true;

        if (qName.equalsIgnoreCase("Language"))
            language = true;

        if (qName.equalsIgnoreCase("Title"))
            title = true;

        if (qName.equalsIgnoreCase("Text"))
            text = true;
    }

    public void endElement(String uri, String localName, String qName) throws SAXException {

    }

    public void characters(char ch[], int start, int length) throws SAXException {

        if (captureDateTime) {
           // System.out.println(new String(ch, start, length));
            captureDateTime = false;
        }

        if (publicationDateTime) {
          //  System.out.println(new String(ch, start, length));
            publicationDateTime = false;
        }

        if (url) {
          //  System.out.println(new String(ch, start, length));
            url = false;
        }
        if (language) {
          //  System.out.println(new String(ch, start, length));
            language = false;
        }

        if (text) {
          //  System.out.println(new String(ch, start, length));
          // namedEntityExtractor.extractNamedEntities(new String(ch, start, length));
            text = false;
        }

        if (title) {
            System.out.println(new String(ch, start, length));
            namedEntityExtractor.extractNamedEntities(new String(ch, start, length));
            title = false;
        }

    }

}
