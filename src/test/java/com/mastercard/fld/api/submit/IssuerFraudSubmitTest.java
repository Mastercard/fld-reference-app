package com.mastercard.fld.api.submit;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

public class IssuerFraudSubmitTest {

	@Before
    public void init() {
		
    }

    @Test
    public void testCreateRequest() {
    	assertNotNull(IssuerFraudSubmit.createRequest());
    }
    
    @Test
    public void testSubmitIssuerFraud() {
    	IssuerFraudSubmit.submitIssuerFraud(IssuerFraudSubmit.createRequest());
    }
}
