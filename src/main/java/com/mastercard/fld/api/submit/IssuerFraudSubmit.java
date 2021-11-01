package com.mastercard.fld.api.submit;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.mastercard.developer.encryption.EncryptionException;
import com.mastercard.fld.api.fld.ApiCallback;
import com.mastercard.fld.api.fld.ApiException;
import com.mastercard.fld.api.fld.api.ConfirmedFraudSubmissionApi;
import com.mastercard.fld.api.fld.model.CfcIndicator;
import com.mastercard.fld.api.fld.model.IssuerFraud;
import com.mastercard.fld.api.fld.model.TransactionIdentifier;
import com.mastercard.fld.utility.LoggerUtil;
import com.mastercard.fld.utility.RequestHelper;

import okhttp3.Call;
import okhttp3.Response;

public class IssuerFraudSubmit {

	private RequestHelper helper = new RequestHelper();
	
	public static void main(String[] args) {
		IssuerFraudSubmit call = new IssuerFraudSubmit();
		call.submitIssuerFraud(call.createRequest());
	}

	public Response submitIssuerFraud(IssuerFraud additionIssuer) {
		ApiCallback callback = helper.getCallback();
		ConfirmedFraudSubmissionApi submissionApi = null;
		Response response = null;
		try {
			helper.initiateEncryptClient();
			submissionApi = new ConfirmedFraudSubmissionApi(helper.getClient());
			Call call = submissionApi.submitIssuerFraudCall(additionIssuer, callback);
			response = helper.apiCall(call);
			LoggerUtil.logResponse(submissionApi.getApiClient().getBasePath() + "/issuer-frauds", "post", response);
		} catch (ApiException | EncryptionException | IOException exception) {
			return null;
		}
		return response;
	}
	
	public IssuerFraud createRequest() {
		IssuerFraud request = new IssuerFraud();

		request.setRefId("ecb2d943-eabd-42y6-87wd-69c19792vjg1");
		request.setTimestamp("2021-07-04T01:34:37-06:00");
		request.setIcaNumber("2742");
		request.setAcquirerId("2742");

		List<TransactionIdentifier> transactionIdentofoers = new ArrayList<>();
		TransactionIdentifier trc = new TransactionIdentifier();
		trc.setCfcKey(CfcIndicator.TRC);
		trc.setCfcValue("500011");
		transactionIdentofoers.add(trc);
		
		TransactionIdentifier ser = new TransactionIdentifier();
		ser.setCfcKey(CfcIndicator.SER);
		ser.setCfcValue("500000011");
		transactionIdentofoers.add(ser);
		
		request.setTransactionIdentifiers(transactionIdentofoers);

		request.setCardNumber("5587450000000009197");
		request.setFraudTypeCode("01");
		request.setFraudSubTypeCode("U");
		request.setCardProductCode("CIR");
		request.setTransactionDate("20200215");
		request.setFraudPostedDate("20210704");
		request.setSettlementDate("20210213");
		request.setCardholderReportedDate("20210213");
		request.setTransactionAmount("3500");
		request.setTransactionCurrencyCode("840");
		request.setBillingAmount("3500");
		request.setBillingCurrencyCode("840");
		request.setMerchantName("1234");
		request.setMerchantCity("city");
		request.setMerchantId("6698696");
		request.setMerchantCategoryCode("6010");
		request.setMerchantStateProvinceCode("CO");
		request.setMerchantCountryCode("USA");
		request.setMerchantPostalCode("78786");
		request.setTerminalAttendanceIndicator("1");
		request.setTerminalId("0");
		request.setTerminalCapabilityIndicator("0");
		request.setTerminalOperatingEnvironment("1");
		request.setAccountDeviceType("A");
		request.setCardholderPresenceIndicator("0");
		request.setCardPresenceIndicator("0");
		request.setCardInPossession("Y");
		request.setCvcInvalidIndicator("M");
		request.setCatLevelIndicator("2");
		request.setPosEntryMode("06");
		request.setAvsResponseCode("A");
		request.setAuthResponseCode("40");
		request.setSecureCode("0");
		request.setAcquirerRoutingTransitNumber("1111111111");
		request.setIssuerRoutingTransitNumber("1111111111");
		request.setMemo("Fraud Addition");
		return request;
	}
}
