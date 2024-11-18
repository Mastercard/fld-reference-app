package com.mastercard.fld.fraud.confirmed.api.submit;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.mastercard.developer.encryption.EncryptionException;
import com.mastercard.fld.api.fld.confirmed.ApiException;
import com.mastercard.fld.api.fld.confirmed.api.ConfirmedFraudSubmissionApi;
import com.mastercard.fld.api.fld.confirmed.model.CfcIndicator;
import com.mastercard.fld.api.fld.confirmed.model.Fraud;
import com.mastercard.fld.api.fld.confirmed.model.MastercardFraud;
import com.mastercard.fld.api.fld.confirmed.model.TransactionIdentifier;
import com.mastercard.fld.fraud.confirmed.helper.RequestHelper;
import com.mastercard.fld.fraud.constants.Constants;
import com.mastercard.fld.utility.ObjectUtility;

import okhttp3.Call;
import okhttp3.Response;

public class MastercardFraudSubmit {

    private RequestHelper helper = new RequestHelper();

    public static void main(String[] args) throws IOException {

        MastercardFraudSubmit call = new MastercardFraudSubmit();
        ObjectUtility.printJavaObjectOnColsole(call.submitMastercardFraud(call.createPayload()));
    }

    public Fraud submitMastercardFraud(MastercardFraud request) {

        try {
            helper.initiateEncryptClient();
            ConfirmedFraudSubmissionApi fraudApi = new ConfirmedFraudSubmissionApi(helper.getClient());
            return ObjectUtility.returnResponseAndPrintOnColsole(Constants.CONFIRMED_FRAUD_API,
                fraudApi.getApiClient().getBasePath() + "/mastercard-frauds", "post",
                helper.apiCall(fraudApi.submitMastercardFraudCall(request, helper.getCallback())), Fraud.class);
        } catch (ApiException | IOException | EncryptionException exception) {
            exception.printStackTrace();
            return null;
        }
    }

    public MastercardFraud createPayload() throws IOException {

        String filepath = System.getProperty("user.dir") + "/src/main/resources/confirmed/config.json";


        // MastercardFraudBuilder mastercardFraudBuilder = MastercardFraudBuilder.builder().
        Gson gson = new Gson();
        MastercardFraud myClass = gson.fromJson(new FileReader(filepath), MastercardFraud.class);
       // myClass.setTimestamp(java.time.OffsetDateTime.now().format(java.time.format.DateTimeFormatter.ISO_OFFSET_DATE_TIME));
        myClass.setTimestamp("2024-07-24T05:48:47-05:00");
        System.out.println("My class " + myClass.toString());
        return myClass;

    }



    public Response updateMastercardFraudCall(Call call) throws IOException {

        return call.execute();
    }

    public MastercardFraud createRequest() {

        MastercardFraud request = new MastercardFraud();
        request.setRefId("wkd2d1a6-5pblo-16l6-ukwd-00m1p0g9dc5");
        request.setTimestamp("2024-07-24T20:34:37-06:00");
        request.setIcaNumber("2742");
        request.setProviderId("10");
        request.setCardNumber("5587450000000001699");
        request.setTransactionAmount("2100");
        request.setTransactionDate("20231007");
        request.setFraudPostedDate("20240305");
        request.setFraudTypeCode("04");
        request.setFraudSubTypeCode("K");
        request.setAccountDeviceType("1");
        request.setCardholderReportedDate("20240305");
        request.setCardInPossession("Y");
        request.setAvsResponseCode("U");
        request.setAuthResponseCode("00");
        request.setMemo("Request Description");
        List<TransactionIdentifier> transactionIdentofoers = new ArrayList<>();
        TransactionIdentifier arn = new TransactionIdentifier();
		arn.setCfcKey(CfcIndicator.ARN); arn.setCfcValue("15487050713330003230075");
		transactionIdentofoers.add(arn);
        //Serial ID
        TransactionIdentifier ser = new TransactionIdentifier();
        ser.setCfcKey(CfcIndicator.ARN);
        ser.setCfcValue("01111111007000000033297");
        transactionIdentofoers.add(ser);
        //Trace ID
        TransactionIdentifier trc = new TransactionIdentifier();
        trc.setCfcKey(CfcIndicator.BRN);
        trc.setCfcValue("756QR7");
        transactionIdentofoers.add(trc);
        
        request.setTransactionIdentifiers(transactionIdentofoers);
        request.setIssuerSCAExemption("06");
        return request;
    }
}
