package org.free2talk.sax.rssreader;

import java.util.ArrayList;

public class RSSFeed {
	private ArrayList<RSSItem> itemList;
	
	public RSSFeed() {
		itemList = new ArrayList<RSSItem>(0);
	}
	
	public void addItem(RSSItem item) {
		itemList.add(item);
	}
	
	public RSSItem getItem(int index) {
		return itemList.get(index);
	}
	
	public ArrayList<RSSItem> getAllItems() {
		return itemList;
	}
	
	public int getItemListSize() {
		return itemList.size();
	}
}
