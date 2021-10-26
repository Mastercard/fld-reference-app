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
import com.mastercard.fld.api.fld.model.FraudDeleteAndConfirm;
import com.mastercard.fld.utility.RequestHelper;

public class SuspendToConfirmTest {

	@InjectMocks
	SuspendToConfirm call;
	
	@Mock
	RequestHelper helper;
	
	@Mock
	ConfirmedFraudManagementApi fraudApi;
	
	@Mock
	ApiClient apiclient;
	
	FraudDeleteAndConfirm request;
	
	@Before
    public void init() {
		MockitoAnnotations.initMocks(this);
		SuspendToConfirm call = new SuspendToConfirm();
		request = call.createRequest();
    }

    @Test
    public void testCreateRequest() {
    	assertNotNull(request);
    }
    
    @Test
    public void testConfirmFraud() throws ApiException {
    	ApiResponse<Fraud> response = new ApiResponse<>(200, new HashMap<>());
		when(helper.apiManageclient()).thenReturn(fraudApi);
		when(fraudApi.fraudStateWithHttpInfo(request)).thenReturn(response);
		when(apiclient.getBasePath()).thenReturn("https://sandbox.api.mastercard.com/fld/confirmed-frauds");
		when(fraudApi.getApiClient()).thenReturn(apiclient);
		call.confirmFraud(request);
		assertNotNull(response);
		
    }
}
