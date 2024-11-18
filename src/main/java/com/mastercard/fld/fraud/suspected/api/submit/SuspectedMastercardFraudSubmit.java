package com.mastercard.fld.fraud.suspected.api.submit;

import java.io.IOException;

import com.mastercard.developer.encryption.EncryptionException;
import com.mastercard.fld.api.fld.suspected.ApiException;
import com.mastercard.fld.api.fld.suspected.api.SuspectedFraudSubmissionApi;
import com.mastercard.fld.api.fld.suspected.model.Fraud;
import com.mastercard.fld.api.fld.suspected.model.SuspectedFraud;
import com.mastercard.fld.api.fld.suspected.model.TransactionIdentifier;
import com.mastercard.fld.fraud.constants.Constants;
import com.mastercard.fld.fraud.suspected.helper.RequestHelper;
import com.mastercard.fld.utility.ObjectUtility;

import okhttp3.Call;
import okhttp3.Response;

public class SuspectedMastercardFraudSubmit {

    private RequestHelper helper = new RequestHelper();

    public static void main(String[] args) {

        SuspectedMastercardFraudSubmit call = new SuspectedMastercardFraudSubmit();
        ObjectUtility.printJavaObjectOnColsole(call.submitMastercardFraud(call.createRequest()));
    }

    public Fraud submitMastercardFraud(SuspectedFraud request) {

        SuspectedFraudSubmissionApi fraudApi = null;
        try {
            helper.initiateEncryptClient();
            fraudApi = new SuspectedFraudSubmissionApi(helper.getClient());
            return ObjectUtility.returnResponseAndPrintOnColsole(Constants.SUSPECTED_FRAUD_API,
                fraudApi.getApiClient().getBasePath() + "/mastercard-frauds", "post",
                helper.apiCall(fraudApi.submitSuspectedFraudCall(request, helper.getCallback())), Fraud.class);
        } catch (ApiException | IOException | EncryptionException exception) {
            exception.printStackTrace();
            return null;
        }
    }

    public Response updateMastercardFraudCall(Call call) throws IOException {

        return call.execute();
    }

    public SuspectedFraud createRequest() {

        SuspectedFraud request = new SuspectedFraud();
        request.setRefId("ecb2dl16-ea7d-u2y6-87wd-69c19792bdd6");
        request.setTimestamp("2024-03-27T14:12:37");
        request.setIcaNumber("1076");
        request.setProviderId("10");
        request.setCardNumber("5505130000000000302");
        request.setTransactionAmount("2100");
        request.setTransactionDate("20231007");
        request.setFraudPostedDate("20240327");
        request.setFraudTypeCode("04");
        request.setAccountDeviceType("1");
        request.setCardholderReportedDate("20240327");
        request.setCardInPossession("Y");
        request.setMemo("Request Description");
        TransactionIdentifier arn = new TransactionIdentifier();
        arn.setAcqRefNum("15505130816110008000998");
        arn.setBanknetRefNum("858001");
        // arn.setSerialId("");
        // arn.traceId("");
        request.setTransactionIdentifiers(arn);
        return request;
    }
}
