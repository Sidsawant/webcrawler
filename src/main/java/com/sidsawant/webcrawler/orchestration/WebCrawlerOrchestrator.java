package com.sidsawant.webcrawler.orchestration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.sidsawant.webcrawler.page.WebPage;
import com.sidsawant.webcrawler.parser.HtmlParser;

@Service
public class WebCrawlerOrchestrator implements CrawlerOrchestrator {

	private static final Logger LOGGER = Logger.getLogger(WebCrawlerOrchestrator.class.getName());

	private Set<String> visitedLinks;
	private Set<String> notVisitedLinks;

	@Autowired
	HtmlParser htmlParser;

	private Map<String, WebPage> mapOfPages;

	private Map<String, WebPage> displayed = new HashMap<>();;

	@Value("${rooturl}")
	private String rootURL;

	public static final String INTERNALURLIDENTIFIER = "/";
	public static final String EXTERNALURLIDENTIFIER = "/";

	/**
	 * @return the mapOfPages
	 */
	public Map<String, WebPage> getMapOfPages() {
		return mapOfPages;
	}

	/**
	 * @param mapOfPages
	 *            the mapOfPages to set
	 */
	public void setMapOfPages(Map<String, WebPage> mapOfPages) {
		this.mapOfPages = mapOfPages;
	}

	@Override
	public WebPage startCrawler() {

		return orchestrateCrawler(rootURL);

	}

	private WebPage orchestrateCrawler(String rootURL) {

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

		if (link.startsWith(INTERNALURLIDENTIFIER) ) {

			WebPage webPage = htmlParser.parse(rootURL + link);
			webPage.setUrl(rootURL + link);

			if (null != webPage.getLinks()) {
				notVisitedLinks.addAll(webPage.getLinks());
			}

			notVisitedLinks.removeAll(visitedLinks);

			mapOfPages.put(webPage.getUrl(), webPage);

		}
		if ( link.startsWith(rootURL)) {

			WebPage webPage = htmlParser.parse( link);
			webPage.setUrl( link);

			if (null != webPage.getLinks()) {
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

	// intentinally kept system.out for better viewing of results
	public void display(WebPage webPage, String appender) {

		System.out.println(appender + webPage.getUrl());
		List<WebPage> children = webPage.getChildren();
		if (!displayed.containsKey(webPage.getUrl())) {

			displayed.put(webPage.getUrl(), webPage);
			if (children != null) {
				for (int i = 0; i < children.size(); i++) {

					display(children.get(i), appender + appender);

				}
			}
		}

	}

	/**
	 * @return the rootURL
	 */
	public String getRootURL() {
		return rootURL;
	}

	/**
	 * @param rootURL
	 *            the rootURL to set
	 */
	public void setRootURL(String rootURL) {

		this.rootURL = rootURL;
	}

	// This needs to go ina separate Class
	public void prepareSiteMap() {

		for (Map.Entry<String, WebPage> entry : mapOfPages.entrySet()) {

			if (entry.getValue() != null && entry.getValue().getLinks() != null) {
				Set<String> links = entry.getValue().getLinks();
				System.out.println(entry.getValue().getUrl());
				List<WebPage> children = new ArrayList<>();
				for (String link : links) {
					if (link.startsWith(INTERNALURLIDENTIFIER)) {
						WebPage webPage = mapOfPages.get(rootURL + link);

						System.out.println("\t" + webPage.getUrl());
						children.add(webPage);

					} else {
						WebPage webPage = mapOfPages.get(link);

						System.out.println("\t" + webPage.getUrl());
						children.add(webPage);
					}

					Set<String> externalLinks = entry.getValue().getExternalLinks();
					if (!externalLinks.isEmpty()) {
						System.out.println("\t\t" + "external Links");
					}
					for (String externallink : externalLinks) {

						System.out.println("\t" + externallink);

					}

					Set<String> imageLinks = entry.getValue().getImages();
					if (!imageLinks.isEmpty()) {
						System.out.println("\t\t" + "image Links");
					}

					for (String imageLink : imageLinks) {

						System.out.println("\t" + rootURL + imageLink);

					}

				}
				entry.getValue().setChildren(children);

			}
		}

		// print the root

	}

}
