package com.mastercard.fld.api.submit;

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
import com.mastercard.fld.api.fld.api.ConfirmedFraudSubmissionApi;
import com.mastercard.fld.api.fld.model.Fraud;
import com.mastercard.fld.api.fld.model.MastercardFraud;
import com.mastercard.fld.utility.RequestHelper;

public class MastercardFraudSubmitTest {

	@InjectMocks
	MastercardFraudSubmit call;
	
	@Mock
	RequestHelper helper;
	
	@Mock
	ConfirmedFraudSubmissionApi fraudApi;
	
	@Mock
	ApiClient apiclient;
	
	MastercardFraud request;
	
	@Before
    public void init() {
		MockitoAnnotations.initMocks(this);
		MastercardFraudSubmit call = new MastercardFraudSubmit();
		request = call.createRequest();
    }

    @Test
    public void testCreateRequest() {
    	assertNotNull(request);
    }
    
    @Test
    public void testsubmitIssuerFraud() throws ApiException {
    	ApiResponse<Fraud> response = new ApiResponse<>(200, new HashMap<>());
		when(helper.apiSubmissionclient()).thenReturn(fraudApi);
		when(fraudApi.submitMastercardFraudWithHttpInfo(request)).thenReturn(response);
		when(apiclient.getBasePath()).thenReturn("https://sandbox.api.mastercard.com/fld/confirmed-frauds");
		when(fraudApi.getApiClient()).thenReturn(apiclient);
		call.submitMastercardFraud(request);
		
    }
}
