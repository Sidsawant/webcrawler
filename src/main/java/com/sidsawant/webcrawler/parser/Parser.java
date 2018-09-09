/**
 * 
 */
package com.sidsawant.webcrawler.parser;

import com.sidsawant.webcrawler.page.WebPage;

/**
 * @author Siddharth.Sawant
 *
 */
public interface Parser {
	
	public WebPage parse(String url);

}
