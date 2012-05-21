package com.ivel

class ProfileController {
	
	def scaffold = true
	
	def index = {
		redirect(action: "list", params: params)
	}

}
