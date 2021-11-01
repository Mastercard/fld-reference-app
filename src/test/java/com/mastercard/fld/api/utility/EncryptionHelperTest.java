package com.mastercard.fld.api.utility;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import com.mastercard.developer.encryption.EncryptionException;
import com.mastercard.fld.utility.EncryptionHelper;

public class EncryptionHelperTest {
	
	@Before
    public void init() {
    }

    @Test
    public void testEncryptionConfig() {
    	try {
			assertNotNull(EncryptionHelper.encryptionConfig("src/test/resources/dummy.pem","571d7e899c5e08f8d31468ef8c81fea65abf70fa31392db6f77e4755b9e574a7"));
		} catch (EncryptionException e) {
			e.printStackTrace();
		}
    }

}
