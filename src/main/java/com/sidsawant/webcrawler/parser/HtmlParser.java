/**
 * 
 */
package com.sidsawant.webcrawler.parser;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.sidsawant.webcrawler.page.WebPage;

/**
 * @author Siddharth.Sawant
 *
 */

@Service
public class HtmlParser implements Parser {
	private final static Logger LOGGER = Logger.getLogger(HtmlParser.class.getName());

	// Create a Pattern object
	private static final  HtmlSearchPattern htmlSearchPatternAnchor = HtmlSearchPattern.ANCHOR;
	private static final  Pattern patternAnchor = Pattern.compile(htmlSearchPatternAnchor.getHtmlSearchPattern());
	
	// Create a Pattern object
		private final static HtmlSearchPattern htmlImagePatternImage = HtmlSearchPattern.IMAGE;
		private final static Pattern patternImage = Pattern.compile(htmlImagePatternImage.getHtmlSearchPattern());

	// Default constructor
	public HtmlParser() {

	}

	/**
	 * @param url
	 *            of the web page that needs to be parsed
	 */
	public WebPage parse(String url) {
		// TODO - CHECK FOR EXTENSIONS BEFORE DOWNLOADING
		WebPage webPage = new WebPage();
		if (!url.endsWith(".pdf")) {

			try (BufferedReader buffer = new BufferedReader(
					new InputStreamReader(new BufferedInputStream(new URL(url).openStream())))) {
				List<String> webPageLines = buffer.lines().filter(anchor -> anchor.contains("<a href="))
						.collect(Collectors.toList());

				updateLinksForPage(webPageLines, webPage);

			} catch (MalformedURLException e) {
				LOGGER.log(Level.SEVERE, e.getMessage());
			} catch (IOException e) {
				LOGGER.log(Level.SEVERE, e.getMessage());
			}
			webPage.setUrl(url);
		}
		return webPage;
	}

	/**
	 * This method will update all the links
	 * 
	 * @param webPageLines
	 * @param webPage
	 */
	private void updateLinksForPage(List<String> webPageLines, WebPage webPage) {

		Set<String> linkedUrls = new HashSet<>();
		Set<String> linkedImages = new HashSet<>();

		webPageLines.forEach(webPageLine -> {
			Matcher matcher = patternAnchor.matcher(webPageLine);
		
			if (matcher.find()) {
				
				linkedUrls.add(matcher.group(1).split("\"")[0]);
				
			}
			
			Matcher imageMatcher = patternImage.matcher(webPageLine);
			if(imageMatcher.find()) {
				linkedImages.add(imageMatcher.group(1).split("\"")[0]);
			}

		});
		
		
		webPage.setLinks(linkedUrls.stream().filter(link->link.startsWith("/")).collect(Collectors.toSet()));
		webPage.setExternalLinks((linkedUrls.stream().filter(link->link.startsWith("http")).collect(Collectors.toSet())));
		webPage.setImages(linkedImages);
	}
}
