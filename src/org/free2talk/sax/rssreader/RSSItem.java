package org.free2talk.sax.rssreader;

public class RSSItem {
	private String title = null;
	private String author = null;
	private String category = null;
	private String link = null;
	private String description = null;
	private String pubDate = null;
	
	public RSSItem() {
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPubDate() {
		return pubDate;
	}

	public void setPubDate(String pubDate) {
		this.pubDate = pubDate;
	}
	
	@Override
	public String toString() {
		
		if(title.length() > RSSConstants.MAX_TITLE_LENGTH)
			return title.substring(0, RSSConstants.MAX_TITLE_LENGTH) + "...";
		else
			return title;
	}
}
