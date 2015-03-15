/* 
PURPOSE:        JUnit 4.11 integration and unit testing.
					*) These specific tests are Spark framework integration testing.   
                See "CountryRouter.java"for all comments, work history, todo, etc.

AUTHOR:         M. Rais

NOTES:          Still filling in tests and comments
					+) See Atlassian "Clover" product - useful for code coverage.
*/

package router1;

import spark.Spark;
import spark.utils.IOUtils;

import com.google.gson.Gson;

//import junit.framework.Assert;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.AfterClass;
//import org.junit.After;
import org.junit.Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;


public class CountriesTest {
	
	@BeforeClass
	public static void setup() {
		CountryRouter.main(null);  // starting application per framework
		try {
            Thread.sleep(3000); // seems a slight delay is needed or assertions run/fail before server up.
        } catch (Exception e) {
        }
	}

	@AfterClass
	public static void teardown() {
		Spark.stop(); // stops embedded server
	}	

	@Test
	public void testCountriesLoadUSreturnsResults() {
		TestResponse res = request("GET", "/v0/load/US/America");
		//TestResponse res = request("PUT", "/v0/load/US/America");  // See comments in router file as to why I didn't go use PUT/POST, etc in prototype.
		assertEquals(201, res.status);
	}

	@Test
	public void testCountriesLoadFRreturnsResults() {
		TestResponse res = request("GET", "/v0/load/FR/France");
		assertEquals(201, res.status);
	}

	@Test
	public void testCountriesLoadITreturnsResults() {
		TestResponse res = request("GET", "/v0/load/IT/Italy");
		assertEquals(201, res.status);
	}

	@Test
	public void testCountriesDeleteITreturnsResults() {
		TestResponse res = request("GET", "/v0/delete/IT");
		assertEquals(200, res.status);
		assertTrue(res.body.contains("\"Status\":\"Deleted\"")); // Could be more specific - fails with dump unlike other assert errs.
	}

	@Test
	public void testCountriesGetFRreturnsResults() {
		TestResponse res = request("GET", "/v0/load/FR/France"); // Have to load the data - there is probably a better way
		res = request("GET", "/v0/get/FR"); // After data loaded, then get it.
		//Map<String, String> json = res.json();
		assertEquals(200, res.status);
 		assertTrue(res.body.contains("\"FR\":\"France\"")); // Could be more specific - fails with dump unlike other assert errs.
	} 


// Following two methods request/TestResposne are boilerplate.  Above code is customized.
private TestResponse request(String method, String path) {
		try {
			URL url = new URL("http://localhost:4567" + path);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod(method);
			connection.setDoOutput(true);
			connection.connect();
			String body = IOUtils.toString(connection.getInputStream());
			return new TestResponse(connection.getResponseCode(), body);
		} catch (IOException e) {
			e.printStackTrace();
			fail("Sending request failed: " + e.getMessage());
			return null;
		}
	}

	private static class TestResponse {

		public final String body;
		public final int status;

		public TestResponse(int status, String body) {
			this.status = status;
			this.body = body;
		}

		public Map<String,String> json() {
			return new Gson().fromJson(body, HashMap.class);
		}
	}
}

