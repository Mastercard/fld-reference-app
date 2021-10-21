package com.mastercard.fld;

import java.io.FileOutputStream;
import java.io.IOException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.Base64;

import com.mastercard.developer.encryption.EncryptionException;
import com.mastercard.developer.interceptors.OkHttpFieldLevelEncryptionInterceptor;
import com.mastercard.developer.interceptors.OkHttpOAuth1Interceptor;
import com.mastercard.developer.utils.AuthenticationUtils;
import com.mastercard.fld.api.fld.ApiClient;
import com.mastercard.fld.utility.EncryptionHelper;

import okhttp3.logging.HttpLoggingInterceptor;

public class BaseClassUtil {

    private static ApiClient client;

    private BaseClassUtil () {}

    public static ApiClient getClient(){
        return client;
    }

    public static void setUpEnv() {

    	String consumerKey = "#YOUR 97 CHARACTER CONSUMER KEY HERE#"; // change accordingly
        String signingKeyFilePath = "#PATH TO YOUR P12 FILE HERE#"; // change accordingly
        String signingKeyAlias = "#YOUR KEY ALIAS HERE#"; // change accordingly
        String signingKeyPass = "#YOUR KEY PASSWORD HERE#"; // change accordingly


        PrivateKey signingKey = null; // Provided by the OAuth1 Signer lib
        try {
            signingKey = AuthenticationUtils.loadSigningKey(signingKeyFilePath, signingKeyAlias, signingKeyPass);
        } catch (IOException | KeyStoreException | CertificateException | NoSuchAlgorithmException | UnrecoverableKeyException exception) {
            HttpLoggingInterceptor.Logger.DEFAULT.log(exception.toString());
        }

        client = new ApiClient();
        // Below URL is subjected to change as per environment
        client.setBasePath("https://sandbox.api.mastercard.com/fld/confirmed-frauds");
        client.setDebugging(true);

        
        client.setHttpClient(
                client.getHttpClient()
                        .newBuilder()
                        .addInterceptor(new OkHttpOAuth1Interceptor(consumerKey, signingKey)) // Provided by the OAuth1 Signer lib
                        .build()
        );
        
    }
    
    public static void setUpEncryptionEnv() throws EncryptionException {

    	String consumerKey = "#YOUR 97 CHARACTER CONSUMER KEY HERE#"; // change accordingly
        String signingKeyFilePath = "#PATH TO YOUR P12 FILE HERE#"; // change accordingly
        String signingKeyAlias = "#YOUR KEY ALIAS HERE#"; // change accordingly
        String signingKeyPass = "#YOUR KEY PASSWORD HERE#"; // change accordingly
        String encryptionKey = "#PATH TO YOUR PEM FILE HERE#"; //change accordingly
        String publicFingerPrint  = "#PUBLIC FINGERPRIN#"; //change accordingly

        PrivateKey signingKey = null; // Provided by the OAuth1 Signer lib
        try {
            signingKey = AuthenticationUtils.loadSigningKey(signingKeyFilePath, signingKeyAlias, signingKeyPass);
        } catch (IOException | KeyStoreException | CertificateException | NoSuchAlgorithmException | UnrecoverableKeyException exception) {
            HttpLoggingInterceptor.Logger.DEFAULT.log(exception.toString());
        }

        client = new ApiClient();
        // Below URL is subjected to change as per environment
        client.setBasePath("https://stage.api.mastercard.com/fld/confirmed-frauds");
        client.setDebugging(true);

        
        client.setHttpClient(
                client.getHttpClient()
                        .newBuilder()
                        .addInterceptor(new OkHttpFieldLevelEncryptionInterceptor(EncryptionHelper.encryptionConfig(encryptionKey,publicFingerPrint))) //Encryption 
                        .addInterceptor(new OkHttpOAuth1Interceptor(consumerKey, signingKey)) // Provided by the OAuth1 Signer lib
                        .build()
        );
        
    }

    public static void downloadFile(String fileName, String base64File) {
        byte[] zipData = Base64.getDecoder().decode(base64File);
        try (FileOutputStream fout = new FileOutputStream(fileName)) {
            fout.write(zipData);
        } catch (IOException ioException) {
            HttpLoggingInterceptor.Logger.DEFAULT.log("File download threw an IOException");
        }
    }
}