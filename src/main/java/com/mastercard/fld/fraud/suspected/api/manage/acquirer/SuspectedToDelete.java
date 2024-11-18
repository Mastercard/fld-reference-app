package com.mastercard.fld.fraud.suspected.api.manage.acquirer;

import java.io.IOException;

import com.mastercard.developer.encryption.EncryptionException;
import com.mastercard.fld.api.fld.suspected.ApiException;
import com.mastercard.fld.api.fld.suspected.api.SuspectedFraudManagementApi;
import com.mastercard.fld.api.fld.suspected.model.Fraud;
import com.mastercard.fld.api.fld.suspected.model.SuspectedFraudStateChange;
import com.mastercard.fld.fraud.constants.Constants;
import com.mastercard.fld.fraud.suspected.helper.RequestHelper;
import com.mastercard.fld.utility.ObjectUtility;

public class SuspectedToDelete {

    private RequestHelper helper = new RequestHelper();

    public static void main(String[] args) {

        SuspectedToDelete call = new SuspectedToDelete();
        ObjectUtility.printJavaObjectOnColsole(call.deleteFraud(call.createRequest()));
    }

    public Fraud deleteFraud(SuspectedFraudStateChange request) {

        try {
            helper.initiateEncryptClient();
            SuspectedFraudManagementApi fraudApi = new SuspectedFraudManagementApi(helper.getClient());
            return ObjectUtility.returnResponseAndPrintOnColsole(Constants.SUSPECTED_FRAUD_API,
                fraudApi.getApiClient().getBasePath() + "/fraud-states", "put",
                helper.apiCall(fraudApi.fraudStateCall(request, helper.getCallback())), Fraud.class);
        } catch (ApiException | IOException | EncryptionException exception) {
            exception.printStackTrace();
            return null;
        }
    }

    public SuspectedFraudStateChange createRequest() {

        SuspectedFraudStateChange request = new SuspectedFraudStateChange();
        request.setAuditControlNumber("292328194169030");
        request.setRefId("ewq3d943-eabd-42y6-87wd-69c19792bdd7");
        request.setTimestamp("2024-03-27T20:34:37");
        request.setIcaNumber("3043");
        request.setOperationType("DELETE");
        request.setProviderId("20");
        request.setMemo("Request Description");
        return request;
    }
}
