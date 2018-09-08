package com.sidsawant.webcrawler.parser;

public enum HtmlSearchPattern {

	IMAGE ("img=\"(.*)\""),
	ANCHOR ("href=\"(.*)\"");
	
	private final String htmlSearchPattern;
	
	HtmlSearchPattern(String htmlSearchPattern){
		this.htmlSearchPattern = htmlSearchPattern;
		
	}
	
	public String getHtmlSearchPattern() {
		return this.htmlSearchPattern;
	}
}
