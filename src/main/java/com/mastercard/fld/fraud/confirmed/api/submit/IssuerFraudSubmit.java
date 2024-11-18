package com.mastercard.fld.fraud.confirmed.api.submit;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.mastercard.developer.encryption.EncryptionException;
import com.mastercard.fld.api.fld.confirmed.ApiException;
import com.mastercard.fld.api.fld.confirmed.api.ConfirmedFraudSubmissionApi;
import com.mastercard.fld.api.fld.confirmed.model.*;
import com.mastercard.fld.fraud.confirmed.helper.RequestHelper;
import com.mastercard.fld.fraud.constants.Constants;
import com.mastercard.fld.utility.ObjectUtility;

public class IssuerFraudSubmit {

    private RequestHelper helper = new RequestHelper();

    public static void main(String[] args) throws IOException {

        IssuerFraudSubmit call = new IssuerFraudSubmit();
        ObjectUtility.printJavaObjectOnColsole(call.submitIssuerFraud(call.createPayload()));
    }

    public Fraud submitIssuerFraud(IssuerFraud additionIssuer) {

        try {
            helper.initiateEncryptClient();
            ConfirmedFraudSubmissionApi submissionApi = new ConfirmedFraudSubmissionApi(helper.getClient());
            return ObjectUtility.returnResponseAndPrintOnColsole(Constants.CONFIRMED_FRAUD_API,
                submissionApi.getApiClient().getBasePath() + "/issuer-frauds", "post",
                helper.apiCall(submissionApi.submitIssuerFraudCall(additionIssuer, helper.getCallback())), Fraud.class);
        } catch (ApiException | EncryptionException | IOException exception) {
            exception.printStackTrace();
            return null;
        }
    }

    public IssuerFraud createPayload() throws IOException {

        String filepath = System.getProperty("user.dir") + "/src/main/resources/confirmed/IssuerFraud.json";


        // MastercardFraudBuilder mastercardFraudBuilder = MastercardFraudBuilder.builder().
        Gson gson = new Gson();
        IssuerFraud myClass = gson.fromJson(new FileReader(filepath), IssuerFraud.class);
        // myClass.setTimestamp(java.time.OffsetDateTime.now().format(java.time.format.DateTimeFormatter.ISO_OFFSET_DATE_TIME));
        myClass.setTimestamp("2024-09-13T08:44:48-05:00");
        System.out.println("My class " + myClass.toString());
        return myClass;

    }

    public IssuerFraud createRequest() {

        IssuerFraud request = new IssuerFraud();

        request.setRefId("985kxk9a-5l2t-kt8a-cta9-eqw7fvref87r");
        request.setTimestamp("2024-03-12T20:34:37-06:00");
        request.setIcaNumber("2742");
        request.setAcquirerId("5313");
 
        List<TransactionIdentifier> transactionIdentofoers = new ArrayList<>();
        TransactionIdentifier acn = new TransactionIdentifier();
        acn.setCfcKey(CfcIndicator.ARN);
        acn.setCfcValue("01111111007000000011327");
        transactionIdentofoers.add(acn);
        TransactionIdentifier brn = new TransactionIdentifier();
        brn.setCfcKey(CfcIndicator.BRN);
        brn.setCfcValue("756QR7");
        transactionIdentofoers.add(brn);
 
        request.setTransactionIdentifiers(transactionIdentofoers);
 
        request.setCardNumber("5587450000000001699");
        request.setFraudTypeCode("00");
        request.setFraudSubTypeCode("K");
        request.setCardProductCode("MCC");
        request.setTransactionDate("20231007");
        request.setFraudPostedDate("20240311");
        request.setSettlementDate("20240311");
        request.setCardholderReportedDate("20240311");
        request.setTransactionAmount("2300");
        request.setTransactionCurrencyCode("840");
        request.setBillingAmount("2100");
        request.setBillingCurrencyCode("840");
        request.setMerchantName("Webuto Infotech");
        request.setMerchantCity("New York");
        request.setMerchantId("TEST DE42");
        request.setMerchantCategoryCode("6012");
        request.setMerchantStateProvinceCode("CA ");
        request.setMerchantCountryCode("USA");
        request.setMerchantPostalCode("78786 ");
        request.setTerminalAttendanceIndicator("0");
        request.setTerminalId("T1234567");
        request.setTerminalCapabilityIndicator("0");
        request.setTerminalOperatingEnvironment("1");
        request.setAccountDeviceType("1");
        request.setCardholderPresenceIndicator("4");
        request.setCardPresenceIndicator("1");
        request.setCardInPossession("Y");
        request.setCvcInvalidIndicator("*");
        request.setCatLevelIndicator("9");
        
        request.setElectronicCommerceIndicator("21");
        
        request.setPosEntryMode("09");
        request.setAvsResponseCode("U");
        request.setAuthResponseCode("00");
        request.setSecureCode("2");
        request.setAcquirerRoutingTransitNumber("1111111111");
        request.setIssuerRoutingTransitNumber("1111111111");
        request.setMemo("Fraud Addition");
        request.setIssuerSCAExemption("04");
        request.setTransactionIndicator("M101");
        return request;
         }
      
}
