package com.mastercard.fld.api.manage;

import com.mastercard.developer.encryption.EncryptionException;
import com.mastercard.fld.BaseClassUtil;
import com.mastercard.fld.api.fld.ApiException;
import com.mastercard.fld.api.fld.ApiResponse;
import com.mastercard.fld.api.fld.api.ConfirmedFraudManagementApi;
import com.mastercard.fld.api.fld.model.Fraud;
import com.mastercard.fld.api.fld.model.UpdatedMastercardFraud;
import com.mastercard.fld.utility.LoggerUtil;

public class MastercardFraudChange {
	public static void main(String[] args) {
		submitFraudChange(createRequest());
	}

	public static ApiResponse<Fraud> submitFraudChange(UpdatedMastercardFraud request) {
		ApiResponse<Fraud> response = null;
		ConfirmedFraudManagementApi fraudApi = null;
		try {
			BaseClassUtil.setUpEncryptionEnv();
			fraudApi = new ConfirmedFraudManagementApi(BaseClassUtil.getClient());
			response = fraudApi.updateMastercardFraudWithHttpInfo(request);

			LoggerUtil.logResponse(fraudApi.getApiClient().getBasePath() + "/mastercard-frauds", "put", response);
		} catch (ApiException | EncryptionException exception) {
			return null;
		}
		return response;
	}

	public static UpdatedMastercardFraud createRequest() {
		UpdatedMastercardFraud request = new UpdatedMastercardFraud();
		request.setAuditControlNumber("292328194169030");
		request.setRefId("nnnd943-eabd-42y6-87wd-69c19792bdd6");
		request.setTimestamp("2021-05-24T20:34:37-06:00");
		request.setIcaNumber("3043");
		request.setFraudTypeCode("01");
		request.setFraudSubTypeCode("K");
		request.setAccountDeviceType("1");
		request.setCardInPossession("Y");
		request.setFraudPostedDate("20210529");
		request.setMemo("Request Description");
		request.setCardholderReportedDate("20210528");
		return request;
	}
}
