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

public class DeleteFraud {
	
	private RequestHelper helper = new RequestHelper();
	
	public static void main(String[] args) {
		DeleteFraud call = new DeleteFraud();
		call.deleteFraud(call.createRequest());
	}

	public Response deleteFraud(FraudDeleteAndConfirm request) {
		ApiCallback callback = helper.getCallback();
		Response response = null;
		helper.initiateNonEncryptClient();
		ConfirmedFraudManagementApi fraudApi = new ConfirmedFraudManagementApi(helper.getClient());
		try {
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
		request.setTimestamp("2021-07-04T20:34:37-06:00");
		request.setAuditControlNumber("292328194169030");
		request.setRefId("aab34943-eabd-42y6-87wd-69c19792bdd6");
		request.setIcaNumber("3043");
		request.setProviderId(SafeFraudProvider.NUMBER_10);
		request.setOperationType(FraudState.FDD);
		request.setMemo("Request Description");
		return request;
	}
}
