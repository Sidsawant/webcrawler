/**
 * 
 */
package com.sidsawant.webcrawler;

import com.sidsawant.webcrawler.orchestration.WebCrawlerOrchestrator;

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
		webCrawlerOrchestror.startWebCrawler(args[0]);
	}

	
}
