package com.sidsawant.webcrawler.orchestration;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.sidsawant.webcrawler.page.WebPage;

@Service
public interface CrawlerOrchestrator{
	
	public WebPage startCrawler();
	public void prepareSiteMap();
	public void setMapOfPages(Map<String, WebPage> mapOfPages);
	public void setRootURL(String string);

}
