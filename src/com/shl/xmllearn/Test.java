package com.shl.xmllearn;

import org.xml.sax.*;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class Test implements ContentHandler {
    public static void main(String[] args) throws SAXException, IOException {

        String xmlURI = "D:/WhirLisence.xml";
        Test test = new Test();
        test.readMXL(xmlURI, test);
    }

    public void readMXL(String xmlURI, ContentHandler contenHander) throws SAXException, IOException {
        XMLReader xmlReader = XMLReaderFactory.createXMLReader();
        xmlReader.setContentHandler(contenHander);
        InputSource inputSource = new InputSource(xmlURI);
        xmlReader.parse(inputSource);
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        for (int i = 0; i < this.result.size(); i++) {
            System.out.println(this.result.get(i).getTagName() + "  " + this.result.get(i).getTagValue());
        }
    }

    private List<VO> result = new LinkedList<>();
    private VO elem;
    private class VO {
        private String tagName;
        private Object tagValue;

        public String getTagName() {
            return tagName;
        }

        public void setTagName(String tagName) {
            this.tagName = tagName;
        }

        public Object getTagValue() {
            return tagValue;
        }

        public void setTagValue(Object tagValue) {
            this.tagValue = tagValue;
        }
    }

    @Override
    public void setDocumentLocator(Locator locator) {

    }

    @Override
    public void startDocument() throws SAXException {
        System.out.println(">>>>>>StartDocument");
    }

    @Override
    public void endDocument() throws SAXException {
        System.out.println(">>>>>endDocument");
    }

    @Override
    public void startPrefixMapping(String prefix, String uri) throws SAXException {

    }

    @Override
    public void endPrefixMapping(String prefix) throws SAXException {

    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {
        System.out.println(">>>>>startElement" + "uri = " + uri + " localName = " + localName + " qName = " + qName);
        this.elem = new VO();
        this.elem.setTagName(localName);
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        System.out.println(">>>>>endElement" + "uri = " + uri + " localName = " + localName + " qName = " + qName);
        if (this.elem != null && this.elem.getTagValue() != null && !"".equals(this.elem.getTagValue().toString().replaceAll("\\s*",""))) {
            this.result.add(this.elem);
        }
        this.elem = null;
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        System.out.println(">>>>characters " + new String(ch, start, length));
        String str = new String(ch,start,length);
        str = str == null ? "" : str.replaceAll("\\s*", "");
        if (!"".equals(str)) {
            this.elem.setTagValue(str);
        }

    }

    @Override
    public void ignorableWhitespace(char[] ch, int start, int length) throws SAXException {

    }

    @Override
    public void processingInstruction(String target, String data) throws SAXException {

    }

    @Override
    public void skippedEntity(String name) throws SAXException {

    }
}
