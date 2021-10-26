package com.mastercard.fld.api.manage;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

public class IssuerFraudChangeTest {
	
	@Before
    public void init() {
    }

    @Test
    public void testCreateRequest() {
    	assertNotNull(IssuerFraudChange.createChangeRequest("",""));
    }
    
    @Test
    public void testSubmitIssuerFraudChange() {
    	IssuerFraudChange.submitIssuerFraudChange(IssuerFraudChange.createChangeRequest("",""));
    }

}
