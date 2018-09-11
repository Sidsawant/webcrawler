/**
 * 
 */
package com.sidsawant.webcrawler;

import com.sidsawant.webcrawler.orchestration.WebCrawlerOrchestrator;
import com.sidsawant.webcrawler.page.WebPage;

/**
 * @author Siddharth.Sawant
 *
 */
public class WebCrawler {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		WebCrawlerOrchestrator webCrawlerOrchestror = new WebCrawlerOrchestrator();
		webCrawlerOrchestror.startCrawler();
		webCrawlerOrchestror.prepareSiteMap();
		WebPage w2 = webCrawlerOrchestror.getMapOfPages().get(args[0]);
		//webCrawlerOrchestror.display(w2, " ");
	}

}
