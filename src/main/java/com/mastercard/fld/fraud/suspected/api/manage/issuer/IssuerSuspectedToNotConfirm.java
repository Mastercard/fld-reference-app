package com.mastercard.fld.fraud.suspected.api.manage.issuer;

import java.io.IOException;

import com.mastercard.developer.encryption.EncryptionException;
import com.mastercard.fld.api.fld.suspected.ApiException;
import com.mastercard.fld.api.fld.suspected.api.SuspectedFraudManagementApi;
import com.mastercard.fld.api.fld.suspected.model.Fraud;
import com.mastercard.fld.api.fld.suspected.model.SuspectedFraudStateChange;
import com.mastercard.fld.fraud.constants.Constants;
import com.mastercard.fld.fraud.suspected.helper.RequestHelper;
import com.mastercard.fld.utility.ObjectUtility;

public class IssuerSuspectedToNotConfirm {

    private RequestHelper helper = new RequestHelper();

    public static void main(String[] args) {

        IssuerSuspectedToNotConfirm call = new IssuerSuspectedToNotConfirm();
        ObjectUtility.printJavaObjectOnColsole(call.confirmNotFraud(call.createRequest()));
    }

    public SuspectedFraudStateChange createRequest() {

        SuspectedFraudStateChange request = new SuspectedFraudStateChange();
        request.setAuditControlNumber("292328194169030");
        request.setRefId("c0112b25-4363-4df2-896d-fa7ae1dc8524");
        request.setTimestamp("2024-03-27T20:34:37");
        request.setIcaNumber("2742");
        request.setOperationType("NOT_FRAUD");
        request.setNotFraudTypeCode("00");
        request.setProviderId("10");
        request.setMemo("Request Description");
        return request;
    }

    public Fraud confirmNotFraud(SuspectedFraudStateChange request) {

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
}
