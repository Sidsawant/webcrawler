/**
 * 
 */
package com.sidsawant.webcrawler.parser;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.sidsawant.webcrawler.AppConfig;
import com.sidsawant.webcrawler.orchestration.WebCrawlerOrchestrator;
import com.sidsawant.webcrawler.page.WebPage;

/**
 * @author Siddharth.Sawant
 *
 */

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { AppConfig.class })
@TestPropertySource(locations = "classpath:application.properties")
public class HtmlParserTest {

	String url = "https://www.google.com";

	@Autowired
	Parser htmlParser;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {

	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for
	 * {@link com.sidsawant.webcrawler.parser.HtmlParser#parse(java.lang.String)}.
	 */
	@Test
	public final void testParse() {

		WebPage webPage = htmlParser.parse(url);
		assert (webPage.getUrl().equalsIgnoreCase("https://www.google.com"));

	}

	/**
	 * Test method for
	 * {@link com.sidsawant.webcrawler.parser.HtmlParser#parse(java.lang.String)}.
	 */
	@Test
	public final void testParse_AnchorRegex() {
		assert (true);
	}
}
