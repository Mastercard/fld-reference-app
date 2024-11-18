package com.mastercard.fld.fraud.suspected.api.manage.issuer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.mastercard.developer.encryption.EncryptionException;
import com.mastercard.fld.api.fld.confirmed.model.CfcIndicator;
	//import com.mastercard.fld.api.fld.confirmed.model.MastercardFraudAllOf;
import com.mastercard.fld.api.fld.suspected.model.TransactionIdentifier;
import com.mastercard.fld.api.fld.suspected.ApiException;
import com.mastercard.fld.api.fld.suspected.api.SuspectedFraudManagementApi;
import com.mastercard.fld.api.fld.suspected.model.Fraud;
import com.mastercard.fld.api.fld.suspected.model.SuspectedFraudStateChange;
import com.mastercard.fld.fraud.constants.Constants;
import com.mastercard.fld.fraud.suspected.helper.RequestHelper;
import com.mastercard.fld.utility.ObjectUtility;

public class IssuerSuspectedToConfirm {

    private RequestHelper helper = new RequestHelper();

    public static void main(String[] args) {

        IssuerSuspectedToConfirm call = new IssuerSuspectedToConfirm();
        ObjectUtility.printJavaObjectOnColsole(call.confirmFraud(call.createRequest()));
    }

    public SuspectedFraudStateChange createRequest() {

        SuspectedFraudStateChange request = new SuspectedFraudStateChange();
        request.setAuditControlNumber("292328194169030");
        request.setRefId("ewqkd943-eabd-42y6-87wd-69c19792bdd6");
        request.setTimestamp("2024-03-27T20:34:37");
        request.setIcaNumber("3043");
        request.setOperationType("CONFIRM_FRAUD");
        request.setProviderId("10");
        request.setMemo("Request Description");
        request.setFraudPostedDate("20240327");
        request.setFraudTypeCode("04");
        request.setFraudSubTypeCode("K");
        request.setAccountDeviceType("1");
        request.setCardholderReportedDate("20240327");
        request.setAuthResponseCode("40");
        request.setAvsResponseCode("U");
        TransactionIdentifier ser = new TransactionIdentifier();
        ser.setSerialId("500000033");
        ser.setTraceId("500033");
        
        request.setTransactionIdentifiers(ser);
      
        return request;
    }

    public Fraud confirmFraud(SuspectedFraudStateChange request) {

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
