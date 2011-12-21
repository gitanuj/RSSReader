package org.free2talk.sax.rssreader;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import android.text.Html;

public class RSSHandler extends DefaultHandler {
	
	RSSFeed feed;
	RSSItem item;
	String lastElementName = "";
	boolean bFoundChannel = false;
	int depth = 0;
	int currentstate = 0;
	StringBuffer str;
	
	/*
	* Constructor
	*/
	RSSHandler() {
	}
	
	/*
	* getFeed - this returns our feed when all of the parsing is complete
	*/
	RSSFeed getFeed() {
		return feed;
	}
	
	public void startDocument() throws SAXException {
		// initialize our RSSFeed object - this will hold our parsed contents
		feed = new RSSFeed();
		// initialize the RSSItem object - you will use this as a crutch to grab
		// the info from the channel
		// because the channel and items have very similar entries..
		item = new RSSItem();
	}
	
	public void endDocument() throws SAXException {
	}
	
	public void startElement(String namespaceURI, String localName,String qName, Attributes atts) throws SAXException {
		depth++;
		str = new StringBuffer("");
		
		if (localName.equals("channel")) {
			currentstate = 0;
			return;
		}
		
		if (localName.equals("item")) {
			// create a new item
			item = new RSSItem();
			return;
		}
		
		if (localName.equals("title")) {
			currentstate = RSSConstants.ITEM_TITLE;
			return;
		}
		
		if (localName.equals("author")) {
			currentstate = RSSConstants.ITEM_AUTHOR;
			return;
		}
		
		if (localName.equals("category")) {
			currentstate = RSSConstants.ITEM_CATEGORY;
			return;
		}
		
		if (localName.equals("link")) {
			currentstate = RSSConstants.ITEM_LINK;
			return;
		}
		
		if (localName.equals("description")) {
			currentstate = RSSConstants.ITEM_DESCRIPTION;
			return;
		}
		
		if (localName.equals("pubDate")) {
			currentstate = RSSConstants.ITEM_PUBDATE;
			return;
		}
		
		// if you don't explicitly handle the element, make sure you don't wind
		// up erroneously storing a newline or other bogus data into one of our
		// existing elements
		currentstate = 0;
	}
	
	public void endElement(String namespaceURI, String localName, String qName) throws SAXException {
		depth--;
		
		if (localName.equals("item")) {
			// add our item to the list!
			feed.addItem(item);
			return;
		}
		else {
			String result = Html.fromHtml(str.toString().trim()).toString();
			switch (currentstate)
			{
				case RSSConstants.ITEM_TITLE:
					item.setTitle(result);
					currentstate = 0;
					break;
				case RSSConstants.ITEM_AUTHOR:
					item.setAuthor(result);
					currentstate = 0;
					break;
				case RSSConstants.ITEM_CATEGORY:
					item.setCategory(result);
					currentstate = 0;
					break;
				case RSSConstants.ITEM_LINK:
					item.setLink(result);
					currentstate = 0;
					break;
				case RSSConstants.ITEM_DESCRIPTION:
					item.setDescription(result);
					currentstate = 0;
					break;
				case RSSConstants.ITEM_PUBDATE:
					item.setPubDate(result);
					currentstate = 0;
					break;
				default:
					return;
			}
		}
	}
	
	public void characters(char ch[], int start, int length) {
		str.append(new String(ch,start,length));
	}
}
