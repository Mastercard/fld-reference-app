package com.mastercard.fld.api.manage;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

public class SuspendToConfirmTest {

	@Before
    public void init() {
		
    }

    @Test
    public void testCreateRequest() {
    	assertNotNull(SuspendToConfirm.createRequest());
    }
    
    @Test
    public void testConfirmFraud() {
    	SuspendToConfirm.confirmFraud(SuspendToConfirm.createRequest());
    }
}
