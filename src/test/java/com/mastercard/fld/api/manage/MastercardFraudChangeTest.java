package com.mastercard.fld.api.manage;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

public class MastercardFraudChangeTest {
	
	@Before
    public void init() {
    }

    @Test
    public void testCreateRequest() {
    	assertNotNull(MastercardFraudChange.createRequest());
    }
    
    @Test
    public void testSubmitFraudChange() {
    	MastercardFraudChange.submitFraudChange(MastercardFraudChange.createRequest());
    }

}
