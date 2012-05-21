package com.ivel

class TagController {

    def scaffold = true
	
    def index = {
        redirect(action: "list", params: params)
    }

}
