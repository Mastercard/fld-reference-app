package com.mastercard.fld;


import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.mastercard.developer.encryption.EncryptionException;
import com.mastercard.fld.api.fld.ApiClient;

public class BaseClassUtilTest {

	private static final String BASE_URL = "mastercard.fld.api.url";
	
	@Test
	public void testGetClient() {
		ApiClient client = BaseClassUtil.getClient();
		assertNotNull(client);
	}
	
	@Test
	public void testLoadProperties() {
		BaseClassUtil.loadProperties();
	}
	
	@Test
	public void testGetProperty() {
		String returnurl = BaseClassUtil.getProperty(BASE_URL);
		assertNotNull(returnurl);
	}
	
	@Test
	public void testSetUpEnv() {
		BaseClassUtil.setUpEnv();
	}
	
	@Test
	public void testSetUpEncryptionEnv() throws EncryptionException {
		BaseClassUtil.setUpEncryptionEnv();
	}
	
	
}
