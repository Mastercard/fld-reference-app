package com.mastercard.fld.api.manage;

import com.mastercard.fld.BaseClassUtil;
import com.mastercard.fld.api.fld.ApiException;
import com.mastercard.fld.api.fld.ApiResponse;
import com.mastercard.fld.api.fld.api.ConfirmedFraudManagementApi;
import com.mastercard.fld.api.fld.model.Fraud;
import com.mastercard.fld.api.fld.model.FraudDeleteAndConfirm;
import com.mastercard.fld.api.fld.model.FraudState;
import com.mastercard.fld.api.fld.model.SafeFraudProvider;

public class DeleteFraud {

	public static void main(String[] args) {
		ApiResponse<Fraud> fraudResp = deleteFraud(createRequest());
	}

	public static ApiResponse<Fraud> deleteFraud(FraudDeleteAndConfirm request) {
		BaseClassUtil.setUpEnv();
		ConfirmedFraudManagementApi fraudApi = new ConfirmedFraudManagementApi(BaseClassUtil.getClient());
		try {
			return fraudApi.fraudStateWithHttpInfo(request);
		} catch (ApiException exception) {
			return null;
		}
	}

	public static FraudDeleteAndConfirm createRequest() {
		FraudDeleteAndConfirm request = new FraudDeleteAndConfirm();
		request.setRefId("ecb2d943-eabd-42y6-87wd-69c19792bdd6");
		request.setTimestamp("2021-07-04T20:34:37-06:00");
		request.setAuditControlNumber("292328194169030");
		request.setIcaNumber("3043");
		request.setProviderId(SafeFraudProvider.NUMBER_10);
		request.setOperationType(FraudState.FDD);
		request.setMemo("Request Description");
		return request;
	}
}
