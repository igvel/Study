package com.ivel

import grails.test.*

class UserTests extends GrailsUnitTestCase {
	protected void setUp() {
		super.setUp()
	}

	protected void tearDown() {
		super.tearDown()
	}

	void testConstraints() {
		def will = new User(userId: "william")
		mockForConstraintsTests(User, [ will ])
		
		def testUser = new User()
		assertFalse testUser.validate()
		assertEquals "nullable", testUser.errors["userId"]
		assertEquals "nullable", testUser.errors["password"]
		
		testUser = new User(userId: "william", password: "william")
		assertFalse testUser.validate()
		assertEquals "unique", testUser.errors["userId"]
		assertEquals "validator", testUser.errors["password"]
		
		testUser = new User(userId: "glen", password: "passwd")
		assertTrue testUser.validate()
	}
}
