package com.mastercard.fld.utility;

import java.io.IOException;
// import java.net.URI;
// import java.nio.charset.StandardCharsets;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import com.google.gson.Gson;
import com.mastercard.developer.encryption.EncryptionException;
import com.mastercard.developer.interceptors.OkHttpFieldLevelEncryptionInterceptor;
import com.mastercard.developer.interceptors.OkHttpOAuth1Interceptor;
// import com.mastercard.developer.oauth.OAuth;
import com.mastercard.developer.utils.AuthenticationUtils;
// import com.mastercard.fld.api.fld.confirmed.model.FraudDeleteAndConfirm;
// import com.mastercard.fld.api.fld.confirmed.model.FraudState;

import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

public class ObjectUtility {

    private ObjectUtility() {

        // Constructtor
    }

    // Provided by the OAuth1 Signer lib
    public static OkHttpOAuth1Interceptor createSigningInfo(String keyStorePath, String keyStorePassword, String keystoreAlias,
                    String consumerKey) {

        PrivateKey signingKey = null;
        try {
            signingKey = AuthenticationUtils.loadSigningKey(PropertyFileReader.getProperty(keyStorePath),
                PropertyFileReader.getProperty(keystoreAlias), PropertyFileReader.getProperty(keyStorePassword));
            // System.out.println(signingKey);
        } catch (IOException | KeyStoreException | CertificateException | NoSuchAlgorithmException | UnrecoverableKeyException exception) {
            HttpLoggingInterceptor.Logger.DEFAULT.log(exception.toString());
        }
        // Gson gson = new Gson();
        // try {
        //     String request = gson.toJson(createRequest());
        //     String abc = OAuth.getAuthorizationHeader(URI.create("https://sandbox.api.mastercard.com/fld/confirmed-frauds/fraud-states"),
        //         "PUT", request, StandardCharsets.UTF_8, consumerKey, signingKey);
        //     System.out.println("\n\n\n" + abc);
        // } catch (Exception e) {
        //     // TODO Auto-generated catch block
        //     e.printStackTrace();
        // }
        return new OkHttpOAuth1Interceptor(PropertyFileReader.getProperty(consumerKey), signingKey);
    }

    // Encryption
    public static OkHttpFieldLevelEncryptionInterceptor createEncryptionConfig(String encryptionCertificatePath, String encryptionKey)
                    throws EncryptionException {

        return new OkHttpFieldLevelEncryptionInterceptor(EncryptionHelper
            .encryptionConfig(PropertyFileReader.getProperty(encryptionCertificatePath), PropertyFileReader.getProperty(encryptionKey)));
    }

    public static <T> T getResponseAsJavaObject(Class<T> clazz, String body) {

        T fraud = null;;
        Gson gson = new Gson();
        try {
            fraud = gson.fromJson(body, clazz);
        } catch (Exception ex) {
            // Check for null object.
            ex.printStackTrace();
        }
        return fraud;
    }

    public static <T> T returnResponseAndPrintOnColsole(String api, String url, String method, Response response, Class<T> clazz)
                    throws IOException {

        int httpCode = response.code();
        if (response.code() >= 400) {
            LoggerUtil.logResponse(api, url, method, httpCode, "", response.message());
            System.out.println("\nResponse payload - \n\tHttp Code - " + httpCode + "\n\tBody - " + response.message());
            return null;
        }
        String locationHeader = response.header("Location", "Not present");
        String body = response.body().string();

        LoggerUtil.logResponse(api, url, method, httpCode, locationHeader, body);

        System.out
            .println("\nResponse payload - \n\tHttp Code - " + httpCode + "\n\tLocation header - " + locationHeader + "\n\tBody - " + body);

        T fraud = ObjectUtility.getResponseAsJavaObject(clazz, body);
        return fraud;
    }

    public static void printJavaObjectOnColsole(Object obj) {

        System.out.println("\nJava object - \n" + obj);
    }
}
