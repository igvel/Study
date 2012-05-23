package com.ivel

import grails.test.*

class PostControllerTests extends ControllerUnitTestCase {
	protected void setUp() {
		super.setUp()
	}

	protected void tearDown() {
		super.tearDown()
	}

	void testShow() {
		mockDomain(User, [
			new User(userId: "glen"),
			new User(userId: "peter") ])
		
		this.controller.params.id = "peter"
		def model = this.controller.show()
		assertEquals "peter", model["viewUser"]?.userId
	}
}
