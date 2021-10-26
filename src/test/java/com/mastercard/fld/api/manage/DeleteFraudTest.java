package com.mastercard.fld.api.manage;

import static org.junit.Assert.assertNotNull;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.mastercard.fld.api.fld.ApiException;
import com.mastercard.fld.api.fld.ApiResponse;
import com.mastercard.fld.api.fld.api.ConfirmedFraudManagementApi;
import com.mastercard.fld.api.fld.model.Fraud;

public class DeleteFraudTest {
	
	@Mock
	ConfirmedFraudManagementApi fraudApi;
	
	@InjectMocks
	DeleteFraud obj;
	
	@Before
    public void init() {
		MockitoAnnotations.initMocks(this);
		//fraudApi = EasyMock.createMock(ConfirmedFraudManagementApi.class);
	}

    @Test
    public void testCreateRequest() {
    	assertNotNull(DeleteFraud.createRequest());
    }
    
    @Test
    public void testDeleteFraud() throws ApiException {
    	Mockito.when(fraudApi.fraudStateWithHttpInfo(DeleteFraud.createRequest())).thenReturn(new ApiResponse<Fraud> (0, null));
    	
    	//EasyMock.expect(fraudApi.fraudStateWithHttpInfo(DeleteFraud.createRequest())).andReturn(new ApiResponse<Fraud> (0, null));
    	   	
    	assertNotNull(obj.deleteFraud(DeleteFraud.createRequest()));
    }
}
