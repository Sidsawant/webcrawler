package com.sidsawant.webcrawler.orchestration;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.sidsawant.webcrawler.page.WebPage;
import com.sidsawant.webcrawler.parser.HtmlParser;

public class WebCrawlerOrchestratorTest {

	WebCrawlerOrchestrator webCrawlerOrchestrator = new WebCrawlerOrchestrator();
	
	@Before
	public void setUp() throws Exception {
		Map<String,WebPage> mapOfPages = new HashMap<>();
		WebPage webPageroot = new WebPage();
		webPageroot.setUrl("https://www.prudential.co.uk");
		HashSet<String> rootLinks = new HashSet<>();
		rootLinks.add("https://www.prudential.co.uk/investors/financial-highlights");
		rootLinks.add("https://www.prudential.co.uk/insights");
		
		mapOfPages.put("https://www.prudential.co.uk", webPageroot);
		
		WebPage webPagefinancialhighlights = new WebPage();
		webPagefinancialhighlights.setUrl("https://www.prudential.co.uk/investors/financial-highlights");
		HashSet<String> webPagefinancialhighlightsLinks = new HashSet<>();
		
		mapOfPages.put("https://www.prudential.co.uk/investors/financial-highlights", webPagefinancialhighlights);
		webCrawlerOrchestrator.setMapOfPages(mapOfPages);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testStartWebCrawler() {
		WebCrawlerOrchestrator webCrawlerOrchestrator = new WebCrawlerOrchestrator();
		WebPage webPage = webCrawlerOrchestrator.startWebCrawler("https://www.google.com");
		assert(webPage.getUrl().equalsIgnoreCase("https://www.google.com"));
	}
	
	@Test
	public final void testDisplayTree() {
		WebPage webPage = webCrawlerOrchestrator.getMapOfPages().get("https://www.prudential.co.uk");
		webCrawlerOrchestrator.display(webPage, " ");
	}

}
