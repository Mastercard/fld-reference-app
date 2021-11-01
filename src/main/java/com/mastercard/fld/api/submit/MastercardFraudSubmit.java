package com.mastercard.fld.api.submit;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.mastercard.developer.encryption.EncryptionException;
import com.mastercard.fld.api.fld.ApiCallback;
import com.mastercard.fld.api.fld.ApiException;
import com.mastercard.fld.api.fld.api.ConfirmedFraudSubmissionApi;
import com.mastercard.fld.api.fld.model.CfcIndicator;
import com.mastercard.fld.api.fld.model.MastercardFraud;
import com.mastercard.fld.api.fld.model.SafeFraudProvider;
import com.mastercard.fld.api.fld.model.TransactionIdentifier;
import com.mastercard.fld.utility.LoggerUtil;
import com.mastercard.fld.utility.RequestHelper;

import okhttp3.Call;
import okhttp3.Response;

public class MastercardFraudSubmit {
	
	private RequestHelper helper = new RequestHelper();
	
	public static void main(String[] args) {
		MastercardFraudSubmit call = new MastercardFraudSubmit();
		call.submitMastercardFraud(call.createRequest());
	}

	public Response submitMastercardFraud(MastercardFraud request) {
		ConfirmedFraudSubmissionApi fraudApi = null;
		ApiCallback callback = helper.getCallback();
		Response response = null;
		
		try {
			helper.initiateEncryptClient();
			fraudApi = new ConfirmedFraudSubmissionApi(helper.getClient());
			Call call = fraudApi.submitMastercardFraudCall(request, callback);
			response = helper.apiCall(call);
			LoggerUtil.logResponse(fraudApi.getApiClient().getBasePath() + "/mastercard-frauds", "post", response);
		} catch (ApiException | EncryptionException | IOException exception) {
			return null;
		}
		return response;
	}	

	public Response updateMastercardFraudCall(Call call)throws IOException {
		return call.execute();
	}

	public MastercardFraud createRequest() {
		MastercardFraud request = new MastercardFraud();
		request.setRefId("ecb2d943-eabd-42y6-87wd-69c19792bdd6");
		request.setTimestamp("2021-05-24T20:34:37-06:00");
		request.setIcaNumber("1234");
		request.setProviderId(SafeFraudProvider.NUMBER_10);
		request.setCardNumber("5411050000000000037");
		request.setTransactionAmount("1234");
		request.setTransactionDate("20210727");
		request.setFraudPostedDate("20210527");
		request.setFraudTypeCode("01");
		request.setFraudSubTypeCode("K");
		request.setAccountDeviceType("1");
		request.setCardholderReportedDate("20210527");
		request.setCardInPossession("Y");
		request.setAvsResponseCode("U");
		request.setAuthResponseCode("00");
		request.setMemo("Request Description");
		List<TransactionIdentifier> transactionIdentofoers = new ArrayList<>();
		TransactionIdentifier arn = new TransactionIdentifier();
		arn.setCfcKey(CfcIndicator.ARN);
		arn.setCfcValue("01111110713000000040817");
		transactionIdentofoers.add(arn);
		request.setTransactionIdentifiers(transactionIdentofoers);
		return request;
	}
}
