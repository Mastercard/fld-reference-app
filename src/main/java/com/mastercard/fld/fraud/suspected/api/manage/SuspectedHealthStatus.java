package com.mastercard.fld.fraud.suspected.api.manage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mastercard.fld.api.fld.suspected.ApiException;
import com.mastercard.fld.api.fld.suspected.Pair;
import com.mastercard.fld.fraud.suspected.helper.RequestHelper;

import okhttp3.Call;
import okhttp3.Response;

public class SuspectedHealthStatus {

    public static void main(String[] args) {

        RequestHelper helper = new RequestHelper();
        helper.initiateNonEncryptClient();
        try {
            Call call = buildGetHttpCall(helper, "/health");
            Response response = call.execute();
            System.out.println("\nResponse " + new String(response.body().bytes()));
        } catch (ApiException | IOException e) {
            e.printStackTrace();
        }
    }

    public static Call buildGetHttpCall(RequestHelper helper, String endPointString) throws ApiException {

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, String> localVarCookieParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();
        String[] localVarAuthNames = new String[] {};

        return helper.getClient()
            .buildCall(helper.getClient().getBasePath(), endPointString, "GET", localVarQueryParams, localVarCollectionQueryParams, null,
                localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, helper.getCallback());
    }
}
