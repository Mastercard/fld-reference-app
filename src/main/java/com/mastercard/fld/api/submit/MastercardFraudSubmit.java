package com.mastercard.fld.api.submit;

import java.util.ArrayList;
import java.util.List;

import com.mastercard.developer.encryption.EncryptionException;
import com.mastercard.fld.BaseClassUtil;
import com.mastercard.fld.api.fld.ApiException;
import com.mastercard.fld.api.fld.ApiResponse;
import com.mastercard.fld.api.fld.api.ConfirmedFraudSubmissionApi;
import com.mastercard.fld.api.fld.model.CfcIndicator;
import com.mastercard.fld.api.fld.model.Fraud;
import com.mastercard.fld.api.fld.model.MastercardFraud;
import com.mastercard.fld.api.fld.model.SafeFraudProvider;
import com.mastercard.fld.model.TransactionIdentifier;

public class MastercardFraudSubmit {

	public static void main(String[] args) {
		ApiResponse<Fraud> fraudResp = submitMastercardFraud(createRequest());
	}

	public static ApiResponse<Fraud> submitMastercardFraud(MastercardFraud request) {
		ConfirmedFraudSubmissionApi fraudApi = null;
		try {
			BaseClassUtil.setUpEncryptionEnv();
			fraudApi = new ConfirmedFraudSubmissionApi(BaseClassUtil.getClient());
			return fraudApi.submitMastercardFraudWithHttpInfo(request);
		} catch (ApiException | EncryptionException exception) {
			return null;
		}
	}

	public static MastercardFraud createRequest() {
		MastercardFraud request = new MastercardFraud();
		request.setRefId("ecb2d943-eabd-42y6-87wd-69c19792bdd6");
		request.setTimestamp("2021-05-24T20:34:37-06:00");
		request.setIcaNumber("1234");
		request.setProviderId(SafeFraudProvider.NUMBER_10);
		request.setCardNumber("5411050000000000037");
		request.setTransactionAmount("1234");
		request.setTransactionDate("20210527");
		request.setFraudPostedDate("20210527");
		request.setFraudTypeCode("01");
		request.setFraudSubTypeCode("K");
		request.setAccountDeviceType("1");
		request.setCardholderReportedDate("20210527");
		request.setCardInPossession("Y");
		request.setAvsResponseCode("U");
		request.setAuthResponseCode("00");
		request.setMemo("Request Description");
		List<Object> transactionIdentofoers = new ArrayList<>();
		TransactionIdentifier arn = new TransactionIdentifier(CfcIndicator.ARN.toString(), "01111110713000000040817");
		transactionIdentofoers.add(arn);
		request.setTransactionIdentifiers(transactionIdentofoers);
		return request;
	}
}
