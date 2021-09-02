package com.mastercard.fld.api.submit;

import java.util.ArrayList;
import java.util.List;

import com.mastercard.developer.encryption.EncryptionException;
import com.mastercard.fld.BaseClassUtil;
import com.mastercard.fld.api.fld.ApiException;
import com.mastercard.fld.api.fld.ApiResponse;
import com.mastercard.fld.api.fld.api.ConfirmedFraudManagementApi;
import com.mastercard.fld.api.fld.api.ConfirmedFraudSubmissionApi;
import com.mastercard.fld.api.fld.model.CfcIndicator;
import com.mastercard.fld.api.fld.model.Fraud;
import com.mastercard.fld.api.fld.model.IssuerFraud;
import com.mastercard.fld.api.fld.model.UpdatedIssuerFraud;
import com.mastercard.fld.model.TransactionIdentifier;

public class IssuerFraudSubmit {

	public static void main(String[] args) {
		ApiResponse<Fraud> fraudResp = submitIssuerFraud(createRequest());
	}

	public static ApiResponse<Fraud> submitIssuerFraud(IssuerFraud additionIssuer) {
		ConfirmedFraudSubmissionApi submissionApi = null;
		try {
			BaseClassUtil.setUpEncryptionEnv();
			submissionApi = new ConfirmedFraudSubmissionApi(BaseClassUtil.getClient());
			return submissionApi.submitIssuerFraudWithHttpInfo(additionIssuer);
		} catch (ApiException | EncryptionException exception) {
			return null;
		}
	}

	public static ApiResponse<Fraud> sendIssuerFDCFraud(UpdatedIssuerFraud changeIssuer) {
		ConfirmedFraudManagementApi manageApi = null;
		try {
			BaseClassUtil.setUpEncryptionEnv();
			manageApi = new ConfirmedFraudManagementApi(BaseClassUtil.getClient());
			return manageApi.updateIssuerFraudWithHttpInfo(changeIssuer);
		} catch (ApiException | EncryptionException exception) {
			return null;
		}
	}

	public static IssuerFraud createRequest() {
		IssuerFraud request = new IssuerFraud();

		request.setRefId("ecb2d943-eabd-42y6-87wd-69c19792vjg1");
		request.setTimestamp("2021-07-04T01:34:37-06:00");
		request.setIcaNumber("2742");
		request.setAcquirerId("2742");

		List<Object> transactionIdentofoers = new ArrayList<>();
		TransactionIdentifier trc = new TransactionIdentifier(CfcIndicator.TRC.getValue(), "500011");
		TransactionIdentifier ser = new TransactionIdentifier(CfcIndicator.SER.getValue(), "500000011");
		transactionIdentofoers.add(trc);
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
		request.setMerchantId("6698696");
		request.setMerchantName("1234");
		request.setMerchantCity("city");
		request.setMerchantStateProvinceCode("CO");
		request.setMerchantCountryCode("USA");
		request.setMerchantPostalCode("78786");
		request.setMerchantCategoryCode("6010");
		request.setTerminalAttendanceIndicator("1");
		request.setTerminalId("0");
		request.setTerminalOperatingEnvironment("1");
		request.setTerminalCapabilityIndicator("0");
		request.setCardholderPresenceIndicator("0");
		request.setCardPresenceIndicator("0");
		request.setCardInPossession("Y");
		request.setCatLevelIndicator("2");
		request.setPosEntryMode("06");
		request.setCvcInvalidIndicator("M");
		request.setAvsResponseCode("A");
		request.setAuthResponseCode("40");
		request.setSecureCode("0");
		request.setAccountDeviceType("A");
		request.setAcquirerRoutingTransitNumber("1111111111");
		request.setIssuerRoutingTransitNumber("1111111111");
		request.setMemo("Fraud Addition");
		return request;
	}
}
