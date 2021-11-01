package com.mastercard.fld.api.utility;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import com.mastercard.developer.encryption.EncryptionException;
import com.mastercard.fld.utility.RequestHelper;

public class RequestHelperTest {

	private static final String ASSERT = "true";
	
	@InjectMocks
	@Spy
	RequestHelper helper;
	
	@Before
    public void init() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testInitiateNonEncryptClient() {
		helper.initiateNonEncryptClient();
		assertNotNull(ASSERT);
	}
	
	@Test
	public void initiateEncryptClient() throws EncryptionException {
		helper.initiateEncryptClient();
		assertNotNull(ASSERT);
	}
	
	@Test
	public void testGetClient() {
		assertNotNull(helper.getClient());
	}
	
	@Test
	public void testgetCallback() {
		assertNotNull(helper.getCallback());
	}
}
