package com.sidsawant.webcrawler.orchestration;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sidsawant.webcrawler.AppConfig;
import com.sidsawant.webcrawler.page.WebPage;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
@TestPropertySource(locations="classpath:application.properties")
public class WebCrawlerOrchestratorTest {

	@Autowired
	CrawlerOrchestrator webCrawlerOrchestrator ;
	
	@Before
	public void setUp() throws Exception {
		Map<String,WebPage> mapOfPages = new HashMap<>();
		WebPage webPageroot = new WebPage();
		webPageroot.setUrl("https://www.prudential.co.uk");
		HashSet<String> rootLinks = new HashSet<>();
		rootLinks.add("/investors");
		rootLinks.add("/insights");
		rootLinks.add("/responsibility");
		webPageroot.setLinks(rootLinks);
		mapOfPages.put("https://www.prudential.co.uk", webPageroot);
		
		WebPage webPageInvestors = new WebPage();
		webPageInvestors.setUrl("https://www.prudential.co.uk/investors");
		HashSet<String> webPagefinancialhighlightsLinks = new HashSet<>();
		webPagefinancialhighlightsLinks.add("/investors/financial-highlights");
		webPagefinancialhighlightsLinks.add("/insights");
		webPageInvestors.setLinks(webPagefinancialhighlightsLinks);
		mapOfPages.put("https://www.prudential.co.uk/investors", webPageInvestors);
		
		//Insights
		WebPage webPageInsights = new WebPage();
		webPageInsights.setUrl("https://www.prudential.co.uk/insights");
		HashSet<String> webPageInsightsLinks = new HashSet<>();
		webPageInsights.setLinks(webPageInsightsLinks);
		
		mapOfPages.put("https://www.prudential.co.uk/insights", webPageInsights);
		
		WebPage webPageInvestorFinancialInsights = new WebPage();
		webPageInvestorFinancialInsights.setUrl("https://www.prudential.co.uk/investors/financial-highlights");
		
		HashSet<String> webPageInvestorFinancialInsightsLinks = new HashSet<>();
		
		
		webPageInvestorFinancialInsights.setLinks(webPageInvestorFinancialInsightsLinks);
		
		mapOfPages.put("https://www.prudential.co.uk/investors/financial-highlights", webPageInvestorFinancialInsights);
		
///////////////////////////////////////////////
		WebPage webPageResponsibilites = new WebPage();
		webPageResponsibilites.setUrl("https://www.prudential.co.uk/responsibility");
		
		HashSet<String> webPageResponsibilitesLinks = new HashSet<>();
		
		webPageResponsibilitesLinks.add("/investors");
		webPageResponsibilites.setLinks(webPageResponsibilitesLinks);
		
		mapOfPages.put("https://www.prudential.co.uk/responsibility", webPageResponsibilites);

		
		
		webCrawlerOrchestrator.setMapOfPages(mapOfPages);
		webCrawlerOrchestrator.setRootURL("https://www.prudential.co.uk");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testStartWebCrawler() {
		
		webCrawlerOrchestrator.setRootURL("https://www.google.com");
		WebPage webPage = webCrawlerOrchestrator.startCrawler();
		assert(webPage.getUrl().equalsIgnoreCase("https://www.google.com"));
	}
	
	

}
