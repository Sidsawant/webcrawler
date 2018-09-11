package com.sidsawant.webcrawler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.sidsawant.webcrawler.orchestration.CrawlerOrchestrator;
import com.sidsawant.webcrawler.orchestration.WebCrawlerOrchestrator;

@SpringBootApplication
@ComponentScan(basePackages = { "com.sidsawant.webcrawler" })
public class WebcrawlerApplication implements CommandLineRunner {

	@Autowired
	CrawlerOrchestrator webCrawlerOrchestror;

	public static void main(String[] args) {
		
		SpringApplication app = new SpringApplication(WebcrawlerApplication.class);
		app.setBannerMode(Banner.Mode.CONSOLE);
		app.run(args);
	}

	@Override
	public void run(String... args) throws Exception {

		webCrawlerOrchestror.startCrawler();
		webCrawlerOrchestror.prepareSiteMap();
		// webCrawlerOrchestror.getMapOfPages().get(args[0]);

	}
}
