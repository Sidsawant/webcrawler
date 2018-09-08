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
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import com.sidsawant.webcrawler.page.WebPage;

/**
 * @author Siddharth.Sawant
 *
 */
public class HtmlParser {
	private final static Logger LOGGER = Logger.getLogger(HtmlParser.class.getName());

	// Default constructor
	public HtmlParser() {
	}

	public WebPage parse(String url) {
		// TODO - CHECK FOR EXTENSIONS BEFORE DOWNLOADING
		WebPage webPage = new WebPage();
		try (BufferedReader buffer = new BufferedReader(
				new InputStreamReader(new BufferedInputStream(new URL(url).openStream())))) {
			List<String> webPageLines = buffer.lines().filter(anchor -> anchor.contains("<a href="))
					.collect(Collectors.toList());

			updateLinksForPage(webPageLines, webPage);

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return webPage;
	}

	private void updateLinksForPage(List<String> webPageLines, WebPage webPage) {

		// Create a Pattern object
		HtmlSearchPattern htmlSearchPatternAnchor = HtmlSearchPattern.ANCHOR;
		Pattern pattern = Pattern.compile(htmlSearchPatternAnchor.getHtmlSearchPattern());

		Set<String> linkedUrls = new HashSet<String>();
		// TODO find a better way

		webPageLines.forEach(item -> {
			Matcher matcher = pattern.matcher(item);
			if (matcher.find()) {
				String urlHrefs[] = matcher.group(0).replace("href=\"", "").split("\"");
				linkedUrls.add(urlHrefs[0]);

				LOGGER.info("url to be " + urlHrefs[0]);

			}

		});

		webPage.setLinks(linkedUrls);

	}
}
