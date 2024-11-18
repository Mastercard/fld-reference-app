
package com.mastercard.fld.fraud.confirmed.api.manage;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import com.mastercard.fld.api.fld.confirmed.ApiCallback;
import com.mastercard.fld.api.fld.confirmed.ApiClient;
import com.mastercard.fld.api.fld.confirmed.ApiException;
import com.mastercard.fld.api.fld.confirmed.api.ConfirmedFraudManagementApi;
import com.mastercard.fld.fraud.confirmed.helper.RequestHelper;

import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class FraudStatusTest {

    @Spy
    FraudStatus fraudStatusObj;

    @Mock
    RequestHelper helper;

    @Mock
    ConfirmedFraudManagementApi fraudApi;

    @Mock
    ApiClient apiclient;

    @Mock
    ApiCallback callback;

    @Mock
    Call call;

    @Before
    public void init() {

        MockitoAnnotations.initMocks(this);
    }

    /*
     * @Test public void fraudStatus() throws ApiException, IOException { Response
     * response = new Response.Builder().request(new
     * Request.Builder().url("http://url.com").build())
     * .protocol(Protocol.HTTP_2).code(200).message("")
     * .body(ResponseBody.create(MediaType.parse("application/json"),
     * "{\"timestamp\":\"2023-07-16T08:52:28-05:00\",\"refId\":\"aab34943-eabd-42y6-87wd-69c19792bdd6\",\"responseCode\":\"200\",\"responseMessage\":\"Failure\",\"auditControlNumber\":\"292328194169030\",\"errorDetails\":{\"Errors\":{\"Error\":[{\"ReasonCode\":\"60127\",\"Description\":\"Record searched could not be found. Correct the input parameter and resubmit\"}]}}}"
     * )) .build();
     * 
     * when(helper.getCallback()).thenReturn(callback);
     * when(helper.getClient()).thenReturn(apiclient);
     * when(apiclient.getBasePath()).thenReturn(
     * "https://sandbox.api.mastercard.com/fld/fraud-statuses/icas/");
     * when(fraudApi.getApiClient()).thenReturn(apiclient);
     * when(fraudApi.fraudRequestStatusCall(Mockito.any(), Mockito.any(),
     * Mockito.any(), Mockito.any())) .thenReturn(call);
     * Mockito.doReturn(response).when(helper).apiCall(Mockito.any()); Fraud fraud =
     * fraudStatusObj.fraudStatus("ica", "refId", "acn"); assertNotNull(fraud);
     * assertThrows(IllegalStateException.class, runnable) }
     */

    @Test
    public void fraudStatusFailuer() throws ApiException, IOException {

        Response response = new Response.Builder().request(new Request.Builder().url("http://url.com").build())
            .protocol(Protocol.HTTP_2)
            .code(200)
            .message("")
            .body(ResponseBody.create(MediaType.parse("application/json"), "aaa"))
            .build();

        when(helper.getCallback()).thenReturn(callback);
        when(helper.getClient()).thenReturn(apiclient);
        when(apiclient.getBasePath()).thenReturn("https://sandbox.api.mastercard.com/fld/fraud-statuses/icas/");
        when(fraudApi.getApiClient()).thenReturn(apiclient);
        when(fraudApi.fraudRequestStatusCall(Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(call);
        Mockito.doThrow(new IOException()).when(helper).apiCall(Mockito.any());
        try {
            fraudStatusObj.fraudStatus("ica", "refId", "acn");
        } catch (Exception ex) {
            assertNotNull(ex);
        }
    }
}
