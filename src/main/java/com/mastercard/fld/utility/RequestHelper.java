package com.mastercard.fld.utility;

import com.mastercard.developer.encryption.EncryptionException;
import com.mastercard.fld.BaseClassUtil;
import com.mastercard.fld.api.fld.api.ConfirmedFraudManagementApi;
import com.mastercard.fld.api.fld.api.ConfirmedFraudSubmissionApi;

public class RequestHelper {

	public void initiateNonEncryptClient() {
		BaseClassUtil.setUpEnv();
	}
	
	public void initiateEncryptClient() throws EncryptionException {
		BaseClassUtil.setUpEncryptionEnv();
	}
	
	public ConfirmedFraudManagementApi apiManageclient() {
		return new ConfirmedFraudManagementApi(BaseClassUtil.getClient());
	}
	
	public ConfirmedFraudSubmissionApi apiSubmissionclient() {
		return new ConfirmedFraudSubmissionApi(BaseClassUtil.getClient());
	}
}
