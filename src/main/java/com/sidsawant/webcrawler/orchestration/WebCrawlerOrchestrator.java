package com.sidsawant.webcrawler.orchestration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.sidsawant.webcrawler.page.WebPage;
import com.sidsawant.webcrawler.parser.HtmlParser;
import com.sidsawant.webcrawler.tree.TreeNode;

public class WebCrawlerOrchestrator {

	private static final Logger LOGGER = Logger.getLogger(WebCrawlerOrchestrator.class.getName());

	private Set<String> visitedLinks;
	private Set<String> notVisitedLinks;

	private Map<String, WebPage> mapOfPages;

	private Map<String, WebPage> displayed = new HashMap<>();;

	TreeNode<String> treeNode;
	String rootURL ;
	
	

	public WebPage startWebCrawler(String rootURL) {

		return orchestrateCrawler(rootURL);

	}

	private WebPage orchestrateCrawler(String rootURL) {
		HtmlParser htmlParser = new HtmlParser();
		this.rootURL = rootURL;
		return startPageRoot(htmlParser);

	}

	private WebPage startPageRoot(HtmlParser htmlParser) {

		visitedLinks = new TreeSet<>();
		notVisitedLinks = new TreeSet<>();
		WebPage webPage = htmlParser.parse(rootURL);

		mapOfPages = new HashMap<>();
		mapOfPages.put(rootURL, webPage);
		

		visitedLinks.add(rootURL);
		// create webPages for all the links to be visted

		notVisitedLinks.addAll(webPage.getLinks());

		addVistedPage(htmlParser);
		return webPage;

	}

	private void addVistedPage(HtmlParser htmlParser) {

		String link = notVisitedLinks.iterator().next();
		
		if (link.startsWith("/")) {
			WebPage webPage = htmlParser.parse(rootURL + link);
			webPage.setUrl(rootURL + link);

			if(null!=webPage.getLinks()) {
			notVisitedLinks.addAll(webPage.getLinks());
			}
			
			notVisitedLinks.removeAll(visitedLinks);

			mapOfPages.put(webPage.getUrl(), webPage);
			

		}
		notVisitedLinks.remove(link);
		visitedLinks.add(link);

		if (notVisitedLinks.iterator().hasNext()) {
			addVistedPage(htmlParser);
		}
	}

	public void display(WebPage webPage) {

		
		List<WebPage> children = webPage.getChildren();
		if(!displayed.containsKey(webPage.getUrl())) {
			displayed.put(webPage.getUrl(), webPage);
			if (children != null) {
				for (int i = 0; i < children.size(); i++) {
					LOGGER.log(Level.INFO, webPage.getUrl());
					if (children.get(i).getChildren()!=null && children.get(i).getChildren().size() > 0) {
						display(children.get(i));
					}
				}
			}
		}
		

	}

	// This needs to go ina separate Class
	public void prepareSiteMap() {



		for (Map.Entry<String, WebPage> entry : mapOfPages.entrySet()) {
			
			if(entry.getValue()!=null && entry.getValue().getLinks()!=null) {
				Set<String> links = entry.getValue().getLinks();
				List<WebPage> children = new ArrayList<>();
				for(String link : links) {
					if(link.startsWith("/")) {
						WebPage webPage = mapOfPages.get(rootURL+link);
						
						
							children.add(webPage);
						
					}
					
					
				}
				entry.getValue().setChildren(children);
				
			}
		}
	}

}
