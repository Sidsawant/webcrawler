package com.sidsawant.webcrawler.repository;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.sidsawant.webcrawler.orchestration.WebCrawlerOrchestrator;

@Repository
public class BufferedStreamDataService implements DataService {

	private static final Logger LOGGER = Logger.getLogger(BufferedStreamDataService.class.getName());
	private static final  String ANCHORFINDER = "<a href=";
	private static final  String IMAGEFINDER = "<img";

	@Override
	public List<String> fetchData(String url)  {

		List<String> webPageLines = null;
		if (!url.endsWith(".pdf")) {
			try (BufferedReader buffer = new BufferedReader(
					new InputStreamReader(new BufferedInputStream(new URL(url).openStream())))) {
				webPageLines = buffer.lines()
						.filter(anchor -> anchor.contains(ANCHORFINDER) || anchor.contains(IMAGEFINDER))
						.collect(Collectors.toList());

			} catch (MalformedURLException e) {
				LOGGER.log(Level.SEVERE, "Error fetching data from url" + url);
				LOGGER.log(Level.SEVERE, e.getMessage());
				
			} catch (IOException e) {
				LOGGER.log(Level.SEVERE, "Error fetching data from url" + url);
				LOGGER.log(Level.SEVERE, e.getMessage());
				
				
			}
		}
		return webPageLines;
	}

}
