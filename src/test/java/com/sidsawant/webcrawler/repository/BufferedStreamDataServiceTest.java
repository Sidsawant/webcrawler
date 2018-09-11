/**
 * 
 */
package com.sidsawant.webcrawler.repository;

import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;



/**
 * @author Siddharth.Sawant
 *
 */


public class BufferedStreamDataServiceTest {
	
	
	private DataService bufferedDataService;
	
	

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		
		bufferedDataService = new BufferedStreamDataService();
		
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link com.sidsawant.webcrawler.repository.BufferedStreamDataService#fetchData(java.lang.String)}.
	 */
	@Test
	public final void testFetchData() {
		List<String> lines = bufferedDataService.fetchData("https://www.google.com");
		assert(!lines.isEmpty());
	}
	
	/**
	 * Test method for {@link com.sidsawant.webcrawler.repository.BufferedStreamDataService#fetchData(java.lang.String)}.
	 */
	@Test
	public final void testFetchData_invalidurl() {
		List<String> lines = bufferedDataService.fetchData("https://www.google.abc");
		assertNull(lines);;
	}

}
