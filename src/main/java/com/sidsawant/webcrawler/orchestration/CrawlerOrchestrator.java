package com.sidsawant.webcrawler.orchestration;

import org.springframework.stereotype.Service;

import com.sidsawant.webcrawler.page.WebPage;

@Service
public interface CrawlerOrchestrator{
	
	public WebPage startCrawler();
	public void prepareSiteMap();

}
