package com.mastercard.fld.api.manage;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
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
import com.mastercard.fld.api.fld.model.UpdatedMastercardFraud;
import com.mastercard.fld.utility.RequestHelper;

public class MastercardFraudChangeTest {
	
	@InjectMocks
	MastercardFraudChange call;
	
	@Mock
	RequestHelper helper;
	
	@Mock
	ConfirmedFraudManagementApi fraudApi;
	
	@Mock
	ApiClient apiclient;
	
	UpdatedMastercardFraud request;
	
	@Before
    public void init() {
		MockitoAnnotations.initMocks(this);
		MastercardFraudChange call = new MastercardFraudChange();
		request = call.createRequest();
    }

    @Test
    public void testCreateRequest() {
    	assertNotNull(request);
    }
    
    @Test
    public void testSubmitFraudChange() throws ApiException {
    	ApiResponse<Fraud> response = new ApiResponse<>(200, new HashMap<>());
		when(helper.apiManageclient()).thenReturn(fraudApi);
		when(fraudApi.updateMastercardFraudWithHttpInfo(request)).thenReturn(response);
		when(apiclient.getBasePath()).thenReturn("https://sandbox.api.mastercard.com/fld/confirmed-frauds");
		when(fraudApi.getApiClient()).thenReturn(apiclient);
		call.submitFraudChange(request);
		assertTrue(true);
		
    }

}
