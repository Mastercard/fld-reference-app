package com.mastercard.fld.fraud.confirmed.api.manage;

import java.io.IOException;

import com.google.gson.JsonSyntaxException;
import com.mastercard.fld.api.fld.confirmed.ApiException;
import com.mastercard.fld.api.fld.confirmed.api.ConfirmedFraudManagementApi;
import com.mastercard.fld.api.fld.confirmed.model.Fraud;
import com.mastercard.fld.api.fld.confirmed.model.FraudDeleteAndConfirm;
import com.mastercard.fld.api.fld.confirmed.model.FraudState;
import com.mastercard.fld.fraud.confirmed.helper.RequestHelper;
import com.mastercard.fld.fraud.constants.Constants;
import com.mastercard.fld.utility.ObjectUtility;

public class DeleteFraud {

    private RequestHelper helper = new RequestHelper();

    public static void main(String[] args) throws JsonSyntaxException {

        DeleteFraud call = new DeleteFraud();
        ObjectUtility.printJavaObjectOnColsole(call.deleteFraud(call.createRequest()));
    }

    public Fraud deleteFraud(FraudDeleteAndConfirm request) {

        helper.initiateNonEncryptClient();
        ConfirmedFraudManagementApi fraudApi = new ConfirmedFraudManagementApi(helper.getClient());
        try {
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
        request.setTimestamp("2024-09-13T08:50:50-05:00");
        request.setAuditControlNumber("504474824830976");
        request.setRefId("aab34943-e1sd-42y6-87wd-69c19792bdd6");
        request.setIcaNumber("2742");
        request.setProviderId("10");
        request.setOperationType(FraudState.FDD);
        request.setMemo("Request Description");
        return request;
    }
}
