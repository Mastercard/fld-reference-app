package com.mastercard.fld.api.manage;

import com.mastercard.fld.api.fld.ApiException;
import com.mastercard.fld.api.fld.ApiResponse;
import com.mastercard.fld.api.fld.api.ConfirmedFraudManagementApi;
import com.mastercard.fld.api.fld.model.Fraud;
import com.mastercard.fld.api.fld.model.FraudDeleteAndConfirm;
import com.mastercard.fld.api.fld.model.FraudState;
import com.mastercard.fld.utility.LoggerUtil;
import com.mastercard.fld.utility.RequestHelper;

public class SuspendToConfirm {

	public RequestHelper helper = new RequestHelper();
	
	public static void main(String[] args) {
		SuspendToConfirm call = new SuspendToConfirm();
		call.confirmFraud(call.createRequest());
	}

	public ApiResponse<Fraud> confirmFraud(FraudDeleteAndConfirm request) {
		ApiResponse<Fraud> response = null;
		helper.initiateNonEncryptClient();
		ConfirmedFraudManagementApi fraudApi = helper.apiManageclient();
		try {
			response = fraudApi.fraudStateWithHttpInfo(request);
			LoggerUtil.logResponse(fraudApi.getApiClient().getBasePath() + "/fraud-states", "put", response);
		} catch (ApiException exception) {
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
		request.setMemo("Request Description");
		return request;
	}
}
