package com.mastercard.fld.fraud.suspected.api.manage.issuer;

import java.io.IOException;

import com.mastercard.developer.encryption.EncryptionException;
import com.mastercard.fld.api.fld.suspected.ApiException;
import com.mastercard.fld.api.fld.suspected.api.SuspectedFraudManagementApi;
import com.mastercard.fld.api.fld.suspected.model.Fraud;
import com.mastercard.fld.api.fld.suspected.model.SuspectedFraudChange;
import com.mastercard.fld.fraud.constants.Constants;
import com.mastercard.fld.fraud.suspected.helper.RequestHelper;
import com.mastercard.fld.utility.ObjectUtility;

public class IssuerSuspectedMastercardFraudChange {

    private RequestHelper helper = new RequestHelper();

    public static void main(String[] args) {

        IssuerSuspectedMastercardFraudChange call = new IssuerSuspectedMastercardFraudChange();
        ObjectUtility.printJavaObjectOnColsole(call.submitFraudChange(call.createRequest()));
    }

    public SuspectedFraudChange createRequest() {

        SuspectedFraudChange request = new SuspectedFraudChange();
        request.setAuditControlNumber("292328194169030");
        request.setRefId("nnnd943-eabd-82y6-87wd-69c19792bded6");
        request.setTimestamp("2024-03-27T20:34:37");
        request.setIcaNumber("3043");
        request.setFraudTypeCode("01");
        request.setAccountDeviceType("1");
        request.setCardInPossession("Y");
        request.setFraudPostedDate("20240327");
        request.setProviderId("10");
        request.setMemo("Request Description");
        request.setCardholderReportedDate("20240327");
        return request;
    }

    public Fraud submitFraudChange(SuspectedFraudChange request) {

        try {
            helper.initiateEncryptClient();
            SuspectedFraudManagementApi fraudApi = new SuspectedFraudManagementApi(helper.getClient());
            return ObjectUtility.returnResponseAndPrintOnColsole(Constants.SUSPECTED_FRAUD_API,
                fraudApi.getApiClient().getBasePath() + "/mastercard-frauds", "put",
                helper.apiCall(fraudApi.updateSuspectedFraudCall(request, helper.getCallback())), Fraud.class);
        } catch (ApiException | EncryptionException | IOException exception) {
            exception.printStackTrace();
            return null;
        }
    }
}
