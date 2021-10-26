package com.mastercard.fld.api.submit;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

public class MastercardFraudSubmitTest {

	@Before
    public void init() {
    }

    @Test
    public void testCreateRequest() {
    	assertNotNull(MastercardFraudSubmit.createRequest());
    }
    
    @Test
    public void testsubmitMastercardFraud() {
    	MastercardFraudSubmit.submitMastercardFraud(MastercardFraudSubmit.createRequest());
    }
}
