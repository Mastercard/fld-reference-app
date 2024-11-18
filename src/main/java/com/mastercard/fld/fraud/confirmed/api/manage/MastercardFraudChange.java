package com.mastercard.fld.fraud.confirmed.api.manage;

import java.io.FileReader;
import java.io.IOException;

import com.google.gson.Gson;
import com.mastercard.developer.encryption.EncryptionException;
import com.mastercard.fld.api.fld.confirmed.ApiException;
import com.mastercard.fld.api.fld.confirmed.api.ConfirmedFraudManagementApi;
import com.mastercard.fld.api.fld.confirmed.model.Fraud;
import com.mastercard.fld.api.fld.confirmed.model.MastercardFraud;
import com.mastercard.fld.api.fld.confirmed.model.UpdatedMastercardFraud;
import com.mastercard.fld.fraud.confirmed.helper.RequestHelper;
import com.mastercard.fld.fraud.constants.Constants;
import com.mastercard.fld.utility.ObjectUtility;

public class MastercardFraudChange {

    private RequestHelper helper = new RequestHelper();

    public static void main(String[] args) throws IOException {

        MastercardFraudChange call = new MastercardFraudChange();
        ObjectUtility.printJavaObjectOnColsole(call.submitFraudChange(call.createPayload()));
    }

    public Fraud submitFraudChange(UpdatedMastercardFraud request) {

        try {
            helper.initiateEncryptClient();
            ConfirmedFraudManagementApi fraudApi = new ConfirmedFraudManagementApi(helper.getClient());
            return ObjectUtility.returnResponseAndPrintOnColsole(Constants.CONFIRMED_FRAUD_API,
                fraudApi.getApiClient().getBasePath() + "/mastercard-frauds", "put",
                helper.apiCall(fraudApi.updateMastercardFraudCall(request, helper.getCallback())), Fraud.class);
        } catch (ApiException | EncryptionException | IOException exception) {
            exception.printStackTrace();
            return null;
        }
    }

    public UpdatedMastercardFraud createPayload() throws IOException {

        String filepath = System.getProperty("user.dir") + "/src/main/resources/confirmed/update.json";


        // MastercardFraudBuilder mastercardFraudBuilder = MastercardFraudBuilder.builder().
        Gson gson = new Gson();
        UpdatedMastercardFraud myClass = gson.fromJson(new FileReader(filepath), UpdatedMastercardFraud.class);
        // myClass.setTimestamp(java.time.OffsetDateTime.now().format(java.time.format.DateTimeFormatter.ISO_OFFSET_DATE_TIME));
        myClass.setTimestamp("2024-04-24T02:01:32-05:00");
        System.out.println("My class " + myClass.toString());
        return myClass;

    }



    public UpdatedMastercardFraud createRequest() {

        UpdatedMastercardFraud request = new UpdatedMastercardFraud();
        request.setAuditControlNumber("936348180837376");
        request.setRefId("nnnd943-eabd-42y6-87wd-69c19792bdea0");
        request.setTimestamp("2024-03-05T20:34:37-06:00");
        request.setIcaNumber("1076");
        request.setFraudTypeCode("01");
        request.setFraudSubTypeCode("K");
        request.setAccountDeviceType("1");
        request.setCardInPossession("Y");
        request.setFraudPostedDate("20230816");
        request.setMemo("Request Description");
        request.setCardholderReportedDate("20240305");
        request.setIssuerSCAExemption("04");
        return request;
    }
}
