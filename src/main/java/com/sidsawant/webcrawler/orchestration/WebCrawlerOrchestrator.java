package com.sidsawant.webcrawler.orchestration;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Logger;

import com.sidsawant.webcrawler.page.WebPage;
import com.sidsawant.webcrawler.parser.HtmlParser;

public class WebCrawlerOrchestrator {
	
	private static final Logger LOGGER = Logger.getLogger(WebCrawlerOrchestrator.class.getName());


	private Set<String> visitedLinks;
	private Set<String> notVisitedLinks;

	public void startWebCrawler(String rootURL) {

		orchestrateCrawler(rootURL);

	}

	private void orchestrateCrawler(String rootURL) {
		HtmlParser htmlParser = new HtmlParser();
		addVistedPageRoot(htmlParser, rootURL);
	}

	private void addVistedPageRoot(HtmlParser htmlParser, String rootURL) {

		visitedLinks = new TreeSet<>();
		notVisitedLinks = new TreeSet<>();
		WebPage webPage = htmlParser.parse(rootURL);
		visitedLinks.add(rootURL);
		// create webPages for all the links to be visted

		notVisitedLinks.addAll(webPage.getLinks());
		addVistedPage(htmlParser,rootURL);

	}

	private void addVistedPage(HtmlParser htmlParser, String rootURL) {

		String link = notVisitedLinks.iterator().next();
		LOGGER.info("link " + link);

		if (link.startsWith("/")) {
			WebPage webPage = htmlParser.parse(rootURL + link);

			notVisitedLinks.addAll(webPage.getLinks());
			notVisitedLinks.removeAll(visitedLinks);

		}
		notVisitedLinks.remove(link);
		visitedLinks.add(link);

		if (notVisitedLinks.iterator().hasNext()) {
			addVistedPage(htmlParser, rootURL);
		}
	}

	

}
