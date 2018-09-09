package com.sidsawant.webcrawler.orchestration;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.sidsawant.webcrawler.page.WebPage;
import com.sidsawant.webcrawler.parser.HtmlParser;

public class WebCrawlerOrchestratorTest {

	@Before
	public void setUp() throws Exception {
		
		
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

}
