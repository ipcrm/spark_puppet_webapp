package com.puppet.sample;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;
import spark.Spark;
import spark.utils.IOUtils;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class TestRoutes 
{
    @BeforeClass
    public static void beforeClass() {
      App.main(null);
    }
   
    @AfterClass
    public static void afterClass() {
      Spark.stop();
    }

    @Test
    public void testEnMsg() {
        TestResponse res = request("GET", "/en");
        assertEquals(200, res.status);
        assertTrue(res.body.contains("Hello World!"));
	}

    @Test
    public void testSpMsg() {
        TestResponse res = request("GET", "/sp");
        assertEquals(200, res.status);
        assertTrue(res.body.contains("¡Hola Mundo!"));
    }

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

    }
}
