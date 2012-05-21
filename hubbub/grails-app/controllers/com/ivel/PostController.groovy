package com.ivel

class PostController {
	
	def scaffold = true
	
	def index = {
		redirect(action: "list", params: params)
	}

}
