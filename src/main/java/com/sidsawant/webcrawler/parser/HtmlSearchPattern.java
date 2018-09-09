package com.sidsawant.webcrawler.parser;

public enum HtmlSearchPattern {

	IMAGE ("img=\"(.*)\""),
	ANCHOR ("href=\"(.*)\"");
	
	private final String htmlMatchPattern;
	
	HtmlSearchPattern(String htmlMatchPattern){
		this.htmlMatchPattern = htmlMatchPattern;
		
	}
	
	public String getHtmlSearchPattern() {
		return this.htmlMatchPattern;
	}
}
