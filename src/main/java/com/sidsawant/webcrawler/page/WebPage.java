package com.sidsawant.webcrawler.page;

import java.util.Set;

public class WebPage {
	String name;
	String url;
	Set<String> externalLinks;
	Set<String> links;
	Set<String> images;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Set<String> getExternalLinks() {
		return externalLinks;
	}

	public void setExternalLinks(Set<String> externalLinks) {
		this.externalLinks = externalLinks;
	}

	public Set<String> getLinks() {
		return links;
	}

	public void setLinks(Set<String> links) {
		this.links = links;
	}

	public Set<String> getImages() {
		return images;
	}

	public void setImages(Set<String> images) {
		this.images = images;
	}

	public String getPage(String url) {
		
		return null;
	}

}
