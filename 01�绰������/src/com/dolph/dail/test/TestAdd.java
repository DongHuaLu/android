package com.dolph.dail.test;

import android.test.AndroidTestCase;

import com.dolph.dail.services.AddServices;

public class TestAdd extends AndroidTestCase {

	public void testAdd() throws Exception {
		AddServices add = new AddServices();
		int result = add.add(10, 8);
		assertEquals(18, result);
	}

}