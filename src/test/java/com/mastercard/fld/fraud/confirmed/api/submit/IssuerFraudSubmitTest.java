package com.mastercard.fld.fraud.confirmed.api.submit;

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

import com.mastercard.fld.api.fld.confirmed.ApiCallback;
import com.mastercard.fld.api.fld.confirmed.ApiClient;
import com.mastercard.fld.api.fld.confirmed.ApiException;
import com.mastercard.fld.api.fld.confirmed.api.ConfirmedFraudSubmissionApi;
import com.mastercard.fld.api.fld.confirmed.model.IssuerFraud;
import com.mastercard.fld.fraud.confirmed.helper.RequestHelper;

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
            .protocol(Protocol.HTTP_1_1)
            .code(200)
            .message("")
            .body(ResponseBody.create(MediaType.parse("application/json"),
                "{\"timestamp\":\"2023-07-15T04:38:24-05:00\",\"refId\":\"ecb2d943-eabd-42y6-87wd-69c19792vjg1\",\"responseCode\":\"200\",\"responseMessage\":\"Failure\",\"errorDetails\":{\"Errors\":{\"Error\":[{\"ReasonCode\":\"60128\",\"Description\":\"CIT/MIT value does not exist in the system\"},{\"ReasonCode\":\"60012\",\"Description\":\"Timestamp value in request should be same as current date.\"},{\"ReasonCode\":\"60027\",\"Description\":\"Fraud posted date cannot be before current date -1.\"},{\"ReasonCode\":\"60025\",\"Description\":\"Transaction date cannot be 18 months before current date.\"},{\"ReasonCode\":\"60039\",\"Description\":\"Cardholder reported date cannot be 18 months before current date.\"},{\"ReasonCode\":\"60013\",\"Description\":\"Duplicate Reference ID ecb2d943-eabd-42y6-87wd-69c19792vjg1 for the initiator and service combination.\"}]}}}"))
            .build();
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
            .protocol(Protocol.HTTP_1_1)
            .code(200)
            .message("")
            .body(ResponseBody.create(MediaType.parse("application/json"), "aaa"))
            .build();
        when(helper.getCallback()).thenReturn(callback);
        when(helper.getClient()).thenReturn(apiclient);
        when(apiclient.getBasePath()).thenReturn("https://sandbox.api.mastercard.com/fld/confirmed-frauds");
        when(fraudApi.submitIssuerFraudCall(Mockito.any(), Mockito.any())).thenReturn(call);
        Mockito.doThrow(new IOException()).when(helper).apiCall(Mockito.any());
        issuerFraudSubmit.submitIssuerFraud(request);
    }
}