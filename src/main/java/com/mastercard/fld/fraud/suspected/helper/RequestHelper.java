package com.mastercard.fld.fraud.suspected.helper;

import java.io.IOException;

import com.mastercard.developer.encryption.EncryptionException;
import com.mastercard.fld.api.fld.suspected.ApiCallback;
import com.mastercard.fld.api.fld.suspected.ApiClient;
import com.mastercard.fld.fraud.suspected.BaseClassUtil;

import okhttp3.Call;
import okhttp3.Response;

public class RequestHelper {

    public void initiateNonEncryptClient() {

        BaseClassUtil.setUpEnv();
    }

    public void initiateEncryptClient() throws EncryptionException {

        BaseClassUtil.setUpEncryptionEnv();
    }

    public ApiClient getClient() {

        return BaseClassUtil.getClient();
    }

    public ApiCallback getCallback() {

        return BaseClassUtil.getCallback();
    }

    public Response apiCall(Call call) throws IOException {

        return call.execute();
    }
}
