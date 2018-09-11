/**
 * 
 */
package com.sidsawant.webcrawler.parser;

import java.util.HashSet;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.sidsawant.webcrawler.page.WebPage;

/**
 * @author Siddharth.Sawant
 *
 */
public class HtmlParserTest {
	
	String url = "https://www.google.com";

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
	 * Test method for {@link com.sidsawant.webcrawler.parser.HtmlParser#parse(java.lang.String)}.
	 */
	@Test
	public final void testParse() {
		HtmlParser htmlParser = new HtmlParser();
		WebPage webPage = htmlParser.parse(url);
		assert(webPage.getUrl().equalsIgnoreCase("https://www.google.com"));
		
		
	}

	/**
	 * Test method for {@link com.sidsawant.webcrawler.parser.HtmlParser#parse(java.lang.String)}.
	 */
	@Test
	public final void testParse_AnchorRegex() {
		assert(true);
	}
}
