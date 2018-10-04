package com.avastreader.reader_test;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;


public class RssFeed_SAXParser {
    public RssFeed getFeed(String urlStr) throws ParserConfigurationException, SAXException, IOException {
        URL url = new URL(urlStr);
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance(); // 構建SAX解析工廠
        SAXParser saxParser = saxParserFactory.newSAXParser(); // 解析工廠生產解析器
        XMLReader xmlReader = saxParser.getXMLReader(); // 通過saxParser構建xmlReader閱讀器
        RssHandler rssHandler = new RssHandler();
        xmlReader.setContentHandler(rssHandler);        // 使用url打開流，並將流作為 xmlReader解析的輸入源並解析
        InputSource inputSource = null;
        InputStream stream = null;
        inputSource = new InputSource(url.openStream());
        inputSource.setEncoding("UTF-8");
        xmlReader.parse(inputSource);
        return rssHandler.getRssFeed();
    }
}


