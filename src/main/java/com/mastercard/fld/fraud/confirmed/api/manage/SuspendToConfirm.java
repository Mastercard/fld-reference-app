package com.mastercard.fld.fraud.confirmed.api.manage;

import java.io.IOException;

import com.mastercard.fld.api.fld.confirmed.ApiException;
import com.mastercard.fld.api.fld.confirmed.api.ConfirmedFraudManagementApi;
import com.mastercard.fld.api.fld.confirmed.model.Fraud;
import com.mastercard.fld.api.fld.confirmed.model.FraudDeleteAndConfirm;
import com.mastercard.fld.api.fld.confirmed.model.FraudState;
import com.mastercard.fld.fraud.confirmed.helper.RequestHelper;
import com.mastercard.fld.fraud.constants.Constants;
import com.mastercard.fld.utility.ObjectUtility;

public class SuspendToConfirm {

    private RequestHelper helper = new RequestHelper();

    public static void main(String[] args) {

        SuspendToConfirm call = new SuspendToConfirm();
        ObjectUtility.printJavaObjectOnColsole(call.confirmFraud(call.createRequest()));
    }

    public Fraud confirmFraud(FraudDeleteAndConfirm request) {

        helper.initiateNonEncryptClient();
        try {
            ConfirmedFraudManagementApi fraudApi = new ConfirmedFraudManagementApi(helper.getClient());
            return ObjectUtility.returnResponseAndPrintOnColsole(Constants.CONFIRMED_FRAUD_API,
                fraudApi.getApiClient().getBasePath() + "/fraud-states", "put",
                helper.apiCall(fraudApi.fraudStateCall(request, helper.getCallback())), Fraud.class);
        } catch (ApiException | IOException exception) {
            exception.printStackTrace();
            return null;
        }
    }

    public FraudDeleteAndConfirm createRequest() {

        FraudDeleteAndConfirm request = new FraudDeleteAndConfirm();
        request.setAuditControlNumber("124847553614848");
        request.setRefId("azq3d943-eabd-42y6-87wd-69c19792bdd6");
        request.setTimestamp("2024-09-13T08:53:07-05:00");
        request.setIcaNumber("2742");
        request.setOperationType(FraudState.FDE);
        request.setProviderId("10");
        request.setMemo("Request Description");
        return request;
    }
}
