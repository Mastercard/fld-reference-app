package com.mastercard.fld.api.manage;

import java.io.IOException;

import com.mastercard.developer.encryption.EncryptionException;
import com.mastercard.fld.api.fld.ApiCallback;
import com.mastercard.fld.api.fld.ApiException;
import com.mastercard.fld.api.fld.api.ConfirmedFraudManagementApi;
import com.mastercard.fld.api.fld.model.UpdatedMastercardFraud;
import com.mastercard.fld.utility.LoggerUtil;
import com.mastercard.fld.utility.RequestHelper;

import okhttp3.Call;
import okhttp3.Response;

public class MastercardFraudChange {
	
	private RequestHelper helper = new RequestHelper();
	
	public static void main(String[] args) {
		MastercardFraudChange call = new MastercardFraudChange();
		call.submitFraudChange(call.createRequest());
	}
	
	public Response submitFraudChange(UpdatedMastercardFraud request) {
		ConfirmedFraudManagementApi fraudApi = null;
		Response response = null;
		ApiCallback callback = helper.getCallback();
		try {
			helper.initiateEncryptClient();
			fraudApi = new ConfirmedFraudManagementApi(helper.getClient());
			Call call = fraudApi.updateMastercardFraudCall(request, callback);
			response = helper.apiCall(call);
			
			LoggerUtil.logResponse(fraudApi.getApiClient().getBasePath() + "/mastercard-frauds", "put", response);
		} catch (ApiException | EncryptionException | IOException exception) {
			return null;
		}
		return response;
	}

	public UpdatedMastercardFraud createRequest() {
		UpdatedMastercardFraud request = new UpdatedMastercardFraud();
		request.setAuditControlNumber("292328194169030");
		request.setRefId("nnnd943-eabd-42y6-87wd-69c19792bded6");
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
