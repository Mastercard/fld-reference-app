package com.mastercard.fld.fraud.suspected.api.manage;

import java.io.IOException;

import com.mastercard.fld.api.fld.suspected.ApiException;
import com.mastercard.fld.api.fld.suspected.api.SuspectedFraudManagementApi;
import com.mastercard.fld.api.fld.suspected.model.Fraud;
import com.mastercard.fld.fraud.constants.Constants;
import com.mastercard.fld.fraud.suspected.helper.RequestHelper;
import com.mastercard.fld.utility.ObjectUtility;

public class SuspectedFraudStatus {

    private RequestHelper helper = new RequestHelper();

    public static void main(String[] args) {

        SuspectedFraudStatus call = new SuspectedFraudStatus();
        ObjectUtility.printJavaObjectOnColsole(call.fraudStatus("3043", "aab34943-eabd-42y6-87wd-69c19792bdd6", "292328194169030"));
    }

    public Fraud fraudStatus(String ica, String refId, String acn) {

        helper.initiateNonEncryptClient();
        SuspectedFraudManagementApi fraudApi = new SuspectedFraudManagementApi(helper.getClient());
        try {
            return ObjectUtility.returnResponseAndPrintOnColsole(Constants.SUSPECTED_FRAUD_API,
                fraudApi.getApiClient().getBasePath() + "/fraud-statuses/icas/" + ica + "?ref_id=" + refId + "&acn=" + acn, "get",
                helper.apiCall(fraudApi.fraudRequestStatusCall(ica, refId, acn, helper.getCallback())), Fraud.class);
        } catch (ApiException | IOException exception) {
            exception.printStackTrace();
            return null;
        }
    }
}
