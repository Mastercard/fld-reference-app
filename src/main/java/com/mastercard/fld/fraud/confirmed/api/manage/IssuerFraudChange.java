package com.mastercard.fld.fraud.confirmed.api.manage;

import java.io.IOException;

import com.mastercard.developer.encryption.EncryptionException;
import com.mastercard.fld.api.fld.confirmed.ApiException;
import com.mastercard.fld.api.fld.confirmed.api.ConfirmedFraudManagementApi;
import com.mastercard.fld.api.fld.confirmed.model.Fraud;
import com.mastercard.fld.api.fld.confirmed.model.UpdatedIssuerFraud;
import com.mastercard.fld.fraud.confirmed.helper.RequestHelper;
import com.mastercard.fld.fraud.constants.Constants;
import com.mastercard.fld.utility.ObjectUtility;

public class IssuerFraudChange {

    private RequestHelper helper = new RequestHelper();

    public static void main(String[] args) {

        IssuerFraudChange issuerFraudChange = new IssuerFraudChange();
        ObjectUtility.printJavaObjectOnColsole(
            issuerFraudChange.submitIssuerFraudChange(issuerFraudChange.createChangeRequest("504474824830976", "2742")));
    }

    public Fraud submitIssuerFraudChange(UpdatedIssuerFraud changeIssuer) {

        try {
            helper.initiateEncryptClient();
            ConfirmedFraudManagementApi manageApi = new ConfirmedFraudManagementApi(helper.getClient());
            return ObjectUtility.returnResponseAndPrintOnColsole(Constants.CONFIRMED_FRAUD_API,
                manageApi.getApiClient().getBasePath() + "/issuer-frauds", "put",
                helper.apiCall(manageApi.updateIssuerFraudCall(changeIssuer, helper.getCallback())), Fraud.class);
        } catch (ApiException | EncryptionException | IOException exception) {
            exception.printStackTrace();
            return null;
        }
    }

    public UpdatedIssuerFraud createChangeRequest(String acn, String ica) {

        UpdatedIssuerFraud request = new UpdatedIssuerFraud();
        request.setIcaNumber(ica);
        request.setRefId("khsib943-eakd-42y6-87wd-69i19514vjg2");
        request.setTimestamp("2024-09-13T08:46:28-05:00");
        request.setAcquirerId("2742");
        request.setAuditControlNumber(acn);
     //   request.setCardNumber("5587450000000001699");
        request.setFraudTypeCode("01");
        request.setMerchantCity("city1");
      /*  request.setFraudSubTypeCode("U");
        request.setCardProductCode("CIR");
        request.setMerchantId("6698696");
        request.setMerchantName("Chicago    ");
        request.setMerchantCountryCode("USA");
        request.setMerchantStateProvinceCode("AK  ");
        request.setMerchantPostalCode("78786");
        request.setMerchantCategoryCode("6010");
        request.setTerminalId("0");
        request.setCatLevelIndicator("2");
        request.setTerminalOperatingEnvironment("1");
        request.setTerminalCapabilityIndicator("0");
        request.setCardholderPresenceIndicator("0");
        request.setTerminalAttendanceIndicator("1");
        request.setCardPresenceIndicator("0");
        request.setCardInPossession("Y");
        request.setPosEntryMode("06");
        request.setCvcInvalidIndicator("M");
        request.setAvsResponseCode("A");
        request.setAuthResponseCode("40");
        request.setSecureCode("0");
        request.setAccountDeviceType("A");*/
        request.setMemo("Memo for FDC");
     //   request.setIssuerSCAExemption("01");
   //     request.setTransactionIndicator("MC01");
        return request;
    }
}
