package com.ivel

class ShopController {

	def index = {  redirect(action: "order") }

	def orderFlow = {
		displayProducts {
			on("next") {
				// capture products
			}.to("enterAddress")
			on("cancel").to("finish")
		}
		enterAddress {
			on("next") {
				// capture address
			}.to("enterPayment")
			on("previous").to("displayProducts")
		}
		enterPayment {
			on("next") {
				// capture payment details
			}.to("validateCard")
			on("previous").to("enterAddress")
		}
		validateCard {
			action {
				// do some logic here
				if (params.validCard) {
					log.debug "Valid Card!!"
					valid()
				} else {
					log.debug "Invalid Card!!"
					invalid()
				}
			}
			on("valid").to("orderComplete")
			on("invalid").to("enterPayment")
		}
		orderComplete {
			// display order
			on("finished").to("finish")
		}
		finish {
			redirect(controller:"homePage", action: "index")
		}
	}
}
