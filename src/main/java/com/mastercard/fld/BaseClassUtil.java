package com.mastercard.fld;

import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.Map;
import java.util.Properties;

import com.mastercard.developer.encryption.EncryptionException;
import com.mastercard.developer.interceptors.OkHttpFieldLevelEncryptionInterceptor;
import com.mastercard.developer.interceptors.OkHttpOAuth1Interceptor;
import com.mastercard.developer.utils.AuthenticationUtils;
import com.mastercard.fld.api.fld.ApiCallback;
import com.mastercard.fld.api.fld.ApiClient;
import com.mastercard.fld.api.fld.ApiException;
import com.mastercard.fld.utility.EncryptionHelper;

import okhttp3.logging.HttpLoggingInterceptor;

/**
 * This class Prepare the Https client with provided User Credential
 * 
 * @author Mastercard
 *
 */
public class BaseClassUtil {

	private static final String BASE_URL = "mastercard.fld.api.url";
	private static final String CONSUMER_KEY = "mastercard.fld.client.ref.app.consumer.key";
	private static final String KEYSTORE_PATH = "mastercard.fld.client.p12.path";
	private static final String KEYSTORE_ALIAS = "mastercard.fld.client.ref.app.keystore.alias";
	private static final String KEYSTORE_PASS = "mastercard.fld.client.ref.app.keystore.password";
	private static final String ENCRYPTION_CERT = "mastercard.fld.client.ref.app.encryption.file";
	private static final String ENCRYPTION_KEY = "mastercard.fld.client.ref.app.encryption.key";

	private static Properties prop = null;

	private static String propertyFile = "./application.properties";
	
	private static ApiClient client;

	private BaseClassUtil() {
	}

	public static ApiClient getClient() {
		return client;
	}

	public static void loadProperties() {
		if (prop == null || prop.isEmpty()) {
			try {
				InputStream input = BaseClassUtil.class.getClassLoader().getResourceAsStream(propertyFile);
				prop = new Properties();
				if (input == null) {
					return;
				}
				prop.load(input);
			} catch (Exception e) {
				throw new IllegalArgumentException(e);
			}
		}
	}

	public static String getProperty(String key) {
		loadProperties();
		return prop.getProperty(key);
	}

	public static void setUpEnv() {
		loadProperties();
		PrivateKey signingKey = null; // Provided by the OAuth1 Signer lib
		try {
			signingKey = AuthenticationUtils.loadSigningKey(prop.getProperty(KEYSTORE_PATH),
					prop.getProperty(KEYSTORE_ALIAS), prop.getProperty(KEYSTORE_PASS));
		} catch (IOException | KeyStoreException | CertificateException | NoSuchAlgorithmException
				| UnrecoverableKeyException exception) {
			HttpLoggingInterceptor.Logger.DEFAULT.log(exception.toString());
		}

		client = new ApiClient();
		// Below URL is subjected to change as per environment
		client.setBasePath(prop.getProperty(BASE_URL));
		client.setDebugging(true);

		client.setHttpClient(client.getHttpClient().newBuilder()
				.addInterceptor(new OkHttpOAuth1Interceptor(prop.getProperty(CONSUMER_KEY), signingKey)) // Provided by
																											// the
																											// OAuth1
																											// Signer
																											// lib
				.build());
	}

	public static void setUpEncryptionEnv() throws EncryptionException {
		loadProperties();
		PrivateKey signingKey = null; // Provided by the OAuth1 Signer lib
		try {
			signingKey = AuthenticationUtils.loadSigningKey(prop.getProperty(KEYSTORE_PATH),
					prop.getProperty(KEYSTORE_ALIAS), prop.getProperty(KEYSTORE_PASS));
		} catch (IOException | KeyStoreException | CertificateException | NoSuchAlgorithmException
				| UnrecoverableKeyException exception) {
			HttpLoggingInterceptor.Logger.DEFAULT.log(exception.toString());
		}

		client = new ApiClient();
		// Below URL is subjected to change as per environment
		client.setBasePath(prop.getProperty(BASE_URL));
		client.setDebugging(true);

		client.setHttpClient(client.getHttpClient().newBuilder()
				.addInterceptor(new OkHttpFieldLevelEncryptionInterceptor(EncryptionHelper
						.encryptionConfig(prop.getProperty(ENCRYPTION_CERT), prop.getProperty(ENCRYPTION_KEY)))) // Encryption
				.addInterceptor(new OkHttpOAuth1Interceptor(prop.getProperty(CONSUMER_KEY), signingKey)) // Provided by
																											// the
																											// OAuth1
																											// Signer
																											// lib
				.build());
	}
	
	public static ApiCallback getCallback() {
        return new ApiCallback() {
            public void onUploadProgress(long bytesWritten, long contentLength, boolean done) {
            	//empty method
            }
            public void onDownloadProgress(long bytesRead, long contentLength, boolean done) { 
            	//empty method
            }
            public void onFailure(ApiException e, int statusCode, Map responseHeaders) {
            	//empty method
            }
            public void onSuccess(Object result, int statusCode, Map responseHeaders) {
            	//empty method
            }
        };
    }
}