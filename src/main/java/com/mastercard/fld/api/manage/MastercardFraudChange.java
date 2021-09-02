package com.mastercard.fld.api.manage;

import com.mastercard.developer.encryption.EncryptionException;
import com.mastercard.fld.BaseClassUtil;
import com.mastercard.fld.api.fld.ApiException;
import com.mastercard.fld.api.fld.ApiResponse;
import com.mastercard.fld.api.fld.api.ConfirmedFraudManagementApi;
import com.mastercard.fld.api.fld.model.Fraud;
import com.mastercard.fld.api.fld.model.UpdatedMastercardFraud;

public class MastercardFraudChange {
	public static void main(String[] args) {
		ApiResponse<Fraud> fraudResp = submitFraudChange(createRequest());
	}

	public static ApiResponse<Fraud> submitFraudChange(UpdatedMastercardFraud request) {
		ConfirmedFraudManagementApi fraudApi = null;
		try {
			 BaseClassUtil.setUpEncryptionEnv();
			 fraudApi = new ConfirmedFraudManagementApi(BaseClassUtil.getClient());
			return fraudApi.updateMastercardFraudWithHttpInfo(request);
		} catch (ApiException | EncryptionException exception) {
			return null;
		}
	}

	public static UpdatedMastercardFraud createRequest() {
		UpdatedMastercardFraud request = new UpdatedMastercardFraud();
		request.setRefId("ecb2d943-eabd-42y6-87wd-69c19792bdd6");
		request.setTimestamp("2021-05-24T20:34:37-06:00");
		request.setAuditControlNumber("292328194169030");
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
