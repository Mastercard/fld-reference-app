package com.mastercard.fld.fraud.confirmed;

import java.util.Map;

import com.mastercard.developer.encryption.EncryptionException;
import com.mastercard.fld.api.fld.confirmed.ApiCallback;
import com.mastercard.fld.api.fld.confirmed.ApiClient;
import com.mastercard.fld.api.fld.confirmed.ApiException;
import com.mastercard.fld.utility.ObjectUtility;
import com.mastercard.fld.utility.PropertyFileReader;

/**
 * This class Prepare the Https client with provided User Credential
 *
 * @author Mastercard
 *
 */
public class BaseClassUtil {

    private static final String BASE_URL = "mastercard.fld.api.url";
    private static final String END_POINT = "mastercard.fld.confirmed.client.ref.app.endpoint";
    private static final String CONSUMER_KEY = "mastercard.fld.confirmed.client.ref.app.consumer.key";
    private static final String KEYSTORE_PATH = "mastercard.fld.confirmed.client.p12.path";
    private static final String KEYSTORE_ALIAS = "mastercard.fld.confirmed.client.ref.app.keystore.alias";
    private static final String KEYSTORE_PASS = "mastercard.fld.confirmed.client.ref.app.keystore.password";
    private static final String ENCRYPTION_CERT = "mastercard.fld.confirmed.client.ref.app.encryption.file";
    private static final String ENCRYPTION_KEY = "mastercard.fld.confirmed.client.ref.app.encryption.key";

    private static ApiClient client;

    private BaseClassUtil() {

    }

    public static ApiClient getClient() {

        return client;
    }

    public static void setUpEnv() {

        client = new ApiClient();
        // Below URL is subjected to change as per environment
        client.setBasePath(PropertyFileReader.getProperty(BASE_URL) + PropertyFileReader.getProperty(END_POINT));
        client.setDebugging(true);

        // Enable when working from local system and remove
        client.setConnectTimeout(60000);
        client.setReadTimeout(60000);
        client.setWriteTimeout(60000);

        client.setHttpClient(client.getHttpClient()
            .newBuilder()
            // Add Signing info
            .addInterceptor(ObjectUtility.createSigningInfo(KEYSTORE_PATH, KEYSTORE_PASS, KEYSTORE_ALIAS, CONSUMER_KEY))
            .build());
    }

    public static void setUpEncryptionEnv() throws EncryptionException {

        client = new ApiClient();
        // Below URL is subjected to change as per environment
        client.setBasePath(PropertyFileReader.getProperty(BASE_URL) + PropertyFileReader.getProperty(END_POINT));
        client.setDebugging(true);

        // Enable when working from local system and remove
        client.setConnectTimeout(60000);
        client.setReadTimeout(60000);
        client.setWriteTimeout(60000);

        client.setHttpClient(client.getHttpClient()
            .newBuilder()
            // Add Encryption
            .addInterceptor(ObjectUtility.createEncryptionConfig(ENCRYPTION_CERT, ENCRYPTION_KEY))
            // Add Signing info
            .addInterceptor(ObjectUtility.createSigningInfo(KEYSTORE_PATH, KEYSTORE_PASS, KEYSTORE_ALIAS, CONSUMER_KEY))
            .build());
    }

    public static ApiCallback getCallback() {

        return new ApiCallback() {

            @Override
            public void onUploadProgress(long bytesWritten, long contentLength, boolean done) {

                // empty method
            }

            @Override
            public void onDownloadProgress(long bytesRead, long contentLength, boolean done) {

                // empty method
            }

            @Override
            public void onFailure(ApiException e, int statusCode, Map responseHeaders) {

                // empty method
            }

            @Override
            public void onSuccess(Object result, int statusCode, Map responseHeaders) {

                // empty method
            }
        };
    }
}
