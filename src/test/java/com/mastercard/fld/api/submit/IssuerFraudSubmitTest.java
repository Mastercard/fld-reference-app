package com.mastercard.fld.api.submit;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import com.mastercard.fld.api.fld.ApiCallback;
import com.mastercard.fld.api.fld.ApiClient;
import com.mastercard.fld.api.fld.ApiException;
import com.mastercard.fld.api.fld.api.ConfirmedFraudSubmissionApi;
import com.mastercard.fld.api.fld.model.IssuerFraud;
import com.mastercard.fld.utility.RequestHelper;

import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class IssuerFraudSubmitTest {

	@InjectMocks
	@Spy
	IssuerFraudSubmit issuerFraudSubmit;
	
	@Mock
	RequestHelper helper;
	
	@Mock
	ConfirmedFraudSubmissionApi fraudApi;
	
	@Mock
	ApiClient apiclient;
	
	@Mock
	ApiCallback callback;

	@Mock
	Call call;
	
	IssuerFraud request;
	
	@Before
    public void init() {
		MockitoAnnotations.initMocks(this);
		IssuerFraudSubmit call = new IssuerFraudSubmit();
		request = call.createRequest();
    }

    @Test
    public void testCreateRequest() {
    	assertNotNull(request);
    }
    
    @Test
    public void testsubmitIssuerFraud() throws ApiException, IOException {
    	Response response = new Response.Builder().request(new Request.Builder().url("http://url.com").build())
				.protocol(Protocol.HTTP_1_1).code(200).message("")
				.body(ResponseBody.create(MediaType.parse("application/json"), "aaa")).build();
    	when(helper.getCallback()).thenReturn(callback);
		when(helper.getClient()).thenReturn(apiclient);
		when(apiclient.getBasePath()).thenReturn("https://sandbox.api.mastercard.com/fld/confirmed-frauds");
		when(fraudApi.submitIssuerFraudCall(Mockito.any(), Mockito.any())).thenReturn(call);
		Mockito.doReturn(response).when(helper).apiCall(Mockito.any());
		issuerFraudSubmit.submitIssuerFraud(request);
    }
    
    @Test
    public void testsubmitIssuerFraudFailure() throws ApiException, IOException {
    	Response response = new Response.Builder().request(new Request.Builder().url("http://url.com").build())
				.protocol(Protocol.HTTP_1_1).code(200).message("")
				.body(ResponseBody.create(MediaType.parse("application/json"), "aaa")).build();
    	when(helper.getCallback()).thenReturn(callback);
		when(helper.getClient()).thenReturn(apiclient);
		when(apiclient.getBasePath()).thenReturn("https://sandbox.api.mastercard.com/fld/confirmed-frauds");
		when(fraudApi.submitIssuerFraudCall(Mockito.any(), Mockito.any())).thenReturn(call);
		Mockito.doThrow(new IOException()).when(helper).apiCall(Mockito.any());
		issuerFraudSubmit.submitIssuerFraud(request);
    }
}
