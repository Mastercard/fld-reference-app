package com.mastercard.fld.api.manage;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.mastercard.fld.api.fld.ApiClient;
import com.mastercard.fld.api.fld.ApiException;
import com.mastercard.fld.api.fld.ApiResponse;
import com.mastercard.fld.api.fld.api.ConfirmedFraudManagementApi;
import com.mastercard.fld.api.fld.model.Fraud;
import com.mastercard.fld.api.fld.model.UpdatedIssuerFraud;
import com.mastercard.fld.utility.RequestHelper;

public class IssuerFraudChangeTest {
	
	@InjectMocks
	IssuerFraudChange call;
	
	@Mock
	RequestHelper helper;
	
	@Mock
	ConfirmedFraudManagementApi fraudApi;
	
	@Mock
	ApiClient apiclient;
	
	UpdatedIssuerFraud request;
	
	@Before
    public void init() {
		MockitoAnnotations.initMocks(this);
		IssuerFraudChange call = new IssuerFraudChange();
		request = call.createChangeRequest("292328194169030", "2742");
    }

    @Test
    public void testCreateRequest() {
    	assertNotNull(request);
    }
    
    @Test
    public void testSubmitIssuerFraudChange() throws ApiException {
    	ApiResponse<Fraud> response = new ApiResponse<>(200, new HashMap<>());
		when(helper.apiManageclient()).thenReturn(fraudApi);
		when(fraudApi.updateIssuerFraudWithHttpInfo(request)).thenReturn(response);
		when(apiclient.getBasePath()).thenReturn("https://sandbox.api.mastercard.com/fld/confirmed-frauds");
		when(fraudApi.getApiClient()).thenReturn(apiclient);
		call.submitIssuerFraudChange(request);	
    }

}
