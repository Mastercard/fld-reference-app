package com.mastercard.fld.fraud.confirmed;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.mastercard.developer.encryption.EncryptionException;
import com.mastercard.fld.api.fld.confirmed.ApiCallback;
import com.mastercard.fld.api.fld.confirmed.ApiClient;
import com.mastercard.fld.utility.PropertyFileReader;

public class BaseClassUtil1Test {

    private static final String BASE_URL = "mastercard.fld.api.url";

    @Test
    public void testGetClient() {

        ApiClient client = BaseClassUtil.getClient();
        assertNotNull(client);
    }

    @Test
    public void testLoadProperties() {

        PropertyFileReader.loadProperties();
        assertNotNull(PropertyFileReader.getProperty(BASE_URL));
    }

    @Test
    public void testGetProperty() {

        assertNotNull(PropertyFileReader.getProperty(BASE_URL));
    }

    @Test
    public void testSetUpEnv() {

        BaseClassUtil.setUpEnv();
        assertNotNull(BASE_URL);
    }

    @Test
    public void testSetUpEncryptionEnv() throws EncryptionException {

        BaseClassUtil.setUpEncryptionEnv();
        assertNotNull(BASE_URL);
    }

    @Test
    public void testGetCallback() throws EncryptionException {

        ApiCallback call = BaseClassUtil.getCallback();
        assertNotNull(call);
    }

}
