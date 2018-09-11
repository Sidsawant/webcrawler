/**
 * 
 */
package com.sidsawant.webcrawler.parser;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sidsawant.webcrawler.orchestration.WebCrawlerOrchestrator;
import com.sidsawant.webcrawler.page.WebPage;
import com.sidsawant.webcrawler.repository.DataService;

/**
 * Primary class for parsing html data and converting into a WebPage format
 * 
 * @author Siddharth.Sawant
 *
 */

@Service
public class HtmlParser implements Parser {
	private final static Logger LOGGER = Logger.getLogger(HtmlParser.class.getName());

	// Create a anchor Pattern object
	private static final HtmlSearchPattern htmlSearchPatternAnchor = HtmlSearchPattern.ANCHOR;
	private static final Pattern patternAnchor = Pattern.compile(htmlSearchPatternAnchor.getHtmlSearchPattern());

	// Create a Image Pattern object improve performance by compiling before the
	// executions start
	private static final  HtmlSearchPattern htmlImagePatternImage = HtmlSearchPattern.IMAGE;
	private static final  Pattern patternImage = Pattern.compile(htmlImagePatternImage.getHtmlSearchPattern());

	private static final String LINKRESOLVER = "\"";

	@Autowired
	DataService dataService;

	

	/**
	 * @param url
	 *            of the web page that needs to be parsed
	 */
	public WebPage parse(String url) {

		WebPage webPage = new WebPage();

		List<String> webPageLines = dataService.fetchData(url);

		updateLinksForPage(webPageLines, webPage, url);
		// Reducing the number of lines by filtering lines that contanin

		webPage.setUrl(url);

		return webPage;
	}

	/**
	 * This method will update all the links
	 * 
	 * @param webPageLines
	 * @param webPage
	 */
	private void updateLinksForPage(List<String> webPageLines, WebPage webPage, String rootUrl) {

		Set<String> linkedUrls = new HashSet<>();
		Set<String> linkedImages = new HashSet<>();

		if (webPageLines == null) {
			return;
		}
		webPageLines.forEach(webPageLine -> {
			Matcher matcher = patternAnchor.matcher(webPageLine);

			if (matcher.find()) {

				linkedUrls.add(matcher.group(1).split(LINKRESOLVER)[0]);

			}

			Matcher imageMatcher = patternImage.matcher(webPageLine);
			if (imageMatcher.find()) {
				linkedImages.add(imageMatcher.group(1).split(LINKRESOLVER)[0]);
			}

		});

		webPage.setLinks(linkedUrls.stream().filter(
				link -> link.startsWith(rootUrl) || link.startsWith(WebCrawlerOrchestrator.INTERNALURLIDENTIFIER))
				.collect(Collectors.toSet()));

		webPage.setExternalLinks(linkedUrls.stream().filter(
				link -> !link.startsWith(rootUrl) && !link.startsWith(WebCrawlerOrchestrator.INTERNALURLIDENTIFIER))		
				.collect(Collectors.toSet()));
		
		webPage.setImages(linkedImages);
	}
}
