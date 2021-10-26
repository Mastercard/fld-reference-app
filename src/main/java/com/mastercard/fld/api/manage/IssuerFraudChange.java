package com.mastercard.fld.api.manage;

import com.mastercard.developer.encryption.EncryptionException;
import com.mastercard.fld.api.fld.ApiException;
import com.mastercard.fld.api.fld.ApiResponse;
import com.mastercard.fld.api.fld.api.ConfirmedFraudManagementApi;
import com.mastercard.fld.api.fld.model.Fraud;
import com.mastercard.fld.api.fld.model.UpdatedIssuerFraud;
import com.mastercard.fld.utility.LoggerUtil;
import com.mastercard.fld.utility.RequestHelper;

public class IssuerFraudChange {

	public RequestHelper helper = new RequestHelper();
	
	public static void main(String[] args) {
		IssuerFraudChange call = new IssuerFraudChange();
		call.submitIssuerFraudChange(call.createChangeRequest("292328194169030", "2742"));
	}

	public ApiResponse<Fraud> submitIssuerFraudChange(UpdatedIssuerFraud changeIssuer) {
		ConfirmedFraudManagementApi manageApi = null;
		ApiResponse<Fraud> response = null;
		try {
			helper.initiateEncryptClient();
			manageApi = helper.apiManageclient();
			response = manageApi.updateIssuerFraudWithHttpInfo(changeIssuer);
			
			LoggerUtil.logResponse(manageApi.getApiClient().getBasePath() + "/issuer-frauds", "put", response);
		} catch (ApiException | EncryptionException exception) {
			return null;
		}
		return response;
	}

	public UpdatedIssuerFraud createChangeRequest(String acn, String ica) {
		UpdatedIssuerFraud request = new UpdatedIssuerFraud();
		request.setIcaNumber(ica);
		request.setRefId("jes2b943-eabd-42y6-87wd-69c19592vjg2");
		request.setTimestamp("2021-07-04T01:34:37-06:00");
		request.setAcquirerId("2742");
		request.setAuditControlNumber(acn);
		request.setCardNumber("5587450000000009197");
		request.setFraudTypeCode("01");
		request.setMerchantCity("city");
		request.setFraudSubTypeCode("U");
		request.setCardProductCode("CIR");
		request.setMerchantId("6698696");
		request.setMerchantName("1234");
		request.setMerchantCountryCode("USA");
		request.setMerchantStateProvinceCode("CO");
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
		request.setAccountDeviceType("A");
		request.setMemo("Fraud change");
		return request;
	}
}
