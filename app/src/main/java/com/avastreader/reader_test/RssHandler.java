package com.avastreader.reader_test;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


import android.util.Log;

public class RssHandler extends DefaultHandler {
    RssFeed rssFeed;
    RssItem rssItem;
    String lastElementName = "";// 標記變量，用於標記在解析過程中我們關心的幾個標籤，若不是我們關心的標籤記做0
    final int RSS_TITLE = 1;// 若是 title 標籤，記做1，注意有兩個title，但我們都保存在item的成員變量中
    final int RSS_LINK = 2;// 若是 link 標籤，記做2
    final int RSS_AUTHOR = 3;
    final int RSS_CATEGORY = 4;// 若是category標籤,記做 4
    final int RSS_PUBDATE = 5; // 若是pubdate標籤,記做5,注意有兩個pubdate,但我們都保存在item的pubdate成員變量中
    final int RSS_COMMENTS = 6;
    final int RSS_DESCRIPTION = 7;// 若是 description 標籤，記做3

    int currentFlag = 0;

    public RssHandler() {
    }

    @Override
    public void startDocument() throws SAXException {
        super.startDocument();
        rssFeed = new RssFeed();
        rssItem = new RssItem();
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);        // 獲取字符串
        String text = new String(ch, start, length);
        Log.i("i", "要獲取的內容：" + text);

        switch (currentFlag) {
            case RSS_TITLE:
                rssItem.setTitle(text);
                currentFlag = 0;// 設置完後，重置為開始狀態
                break;
            case RSS_PUBDATE:
                rssItem.setPubdate(text);
                currentFlag = 0;// 設置完後，重置為開始狀態
                break;
            case RSS_CATEGORY:
                rssItem.setCategory(text);
                currentFlag = 0;// 設置完後，重置為開始狀態
                break;
            case RSS_LINK:
                rssItem.setLink(text);
                currentFlag = 0;// 設置完後，重置為開始狀態
                break;
            case RSS_AUTHOR:
                rssItem.setAuthor(text);
                currentFlag = 0;// 設置完後，重置為開始狀態
                break;
            case RSS_DESCRIPTION:
                rssItem.setDescription(text);
                currentFlag = 0;// 設置完後，重置為開始狀態
                break;
            case RSS_COMMENTS:
                rssItem.setComments(text);
                currentFlag = 0;// 設置完後，重置為開始狀態
                break;
            default:
                break;
        }
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);
        if ("chanel".equals(localName)) {            // 這個標籤內沒有我們關心的內容，所以不作處理，currentFlag = 0;
            currentFlag = 0;
            return;
        } if ("item".equals(localName)) {
            rssItem = new RssItem();
            return;
        }
        if ("title".equals(localName)) {
            currentFlag = RSS_TITLE;
            return;
        }
        if ("description".equals(localName)) {
            currentFlag = RSS_DESCRIPTION;
            return;
        }
        if ("link".equals(localName)) {
            currentFlag = RSS_LINK;
            return;
        }
        if ("pubDate".equals(localName)) {
            currentFlag = RSS_PUBDATE;
            return;
        }
        if ("category".equals(localName)) {
            currentFlag = RSS_CATEGORY;
            return;
        }
        if ("author".equals(localName)) {
            currentFlag = RSS_AUTHOR;
            return;
        }
        if ("comments".equals(localName)) {
            currentFlag = RSS_COMMENTS;
            return;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);        // 如果解析一個item節點結束，就將rssItem添加到rssFeed中。
        if ("item".equals(localName)) {
            rssFeed.addItem(rssItem);
            return;
        }
    }

    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
    }

    public RssFeed getRssFeed() {
        return rssFeed;
    }
}
