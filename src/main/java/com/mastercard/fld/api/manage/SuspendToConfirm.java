package com.mastercard.fld.api.manage;

import java.io.IOException;

import com.mastercard.fld.api.fld.ApiCallback;
import com.mastercard.fld.api.fld.ApiException;
import com.mastercard.fld.api.fld.api.ConfirmedFraudManagementApi;
import com.mastercard.fld.api.fld.model.FraudDeleteAndConfirm;
import com.mastercard.fld.api.fld.model.FraudState;
import com.mastercard.fld.api.fld.model.SafeFraudProvider;
import com.mastercard.fld.utility.LoggerUtil;
import com.mastercard.fld.utility.RequestHelper;

import okhttp3.Call;
import okhttp3.Response;

public class SuspendToConfirm {

	private RequestHelper helper = new RequestHelper();
	
	public static void main(String[] args) {
		SuspendToConfirm call = new SuspendToConfirm();
		call.confirmFraud(call.createRequest());
	}

	public Response confirmFraud(FraudDeleteAndConfirm request) {
		ApiCallback callback = helper.getCallback();
		helper.initiateNonEncryptClient();
		ConfirmedFraudManagementApi fraudApi = null;
		Response response = null;
		try {
			fraudApi = new ConfirmedFraudManagementApi(helper.getClient());
			Call call = fraudApi.fraudStateCall(request, callback);
			response = helper.apiCall(call);
			LoggerUtil.logResponse(fraudApi.getApiClient().getBasePath() + "/fraud-states", "put", response);
		} catch (ApiException | IOException exception) {
			return null;
		}
		return response;
	}

	public FraudDeleteAndConfirm createRequest() {
		FraudDeleteAndConfirm request = new FraudDeleteAndConfirm();
		request.setAuditControlNumber("292328194169030");
		request.setRefId("ewq3d943-eabd-42y6-87wd-69c19792bdd6");
		request.setTimestamp("2021-05-24T20:34:37-06:00");
		request.setIcaNumber("3043");
		request.setOperationType(FraudState.FDE);
		request.setProviderId(SafeFraudProvider.NUMBER_10);
		request.setMemo("Request Description");
		return request;
	}
}
