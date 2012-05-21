package com.ivel

class UserController {

	def scaffold = true

	def index = {
		redirect(action: "list", params: params)
	}

	def search = {
	}

	def results = {
		def users = User.findAllByUserIdLike("%${params.userId}%")
		return [ users: users, term : params.userId ]
	}

	def advSearch = {
	}

	def advResults = {

		def profileProps = Profile.metaClass.properties*.name
		def profiles = Profile.withCriteria {
			"${params.queryType}" {

				params.each { field, value ->

					if (profileProps.grep(field) && value) {
						ilike(field, value)
					}
				}
			}
		}
		[ profiles : profiles ]
	}

	// direct binding of params
	def register = {
		if (params) {
			def user = new User(params)
			if (user.validate()) {
				user.save()
				flash.message = "Successfully Created User"
				redirect(uri: '/')
			} else {
				flash.message = "Error Registering User"
				return [ user: user ]
			}
		}
	}

	// binding using command
	def register2 = { UserRegistrationCommand urc ->
		if(urc) {
			if (urc.hasErrors()) {
				[user : urc]
			} else {
				def user = new User(urc.properties)
				user.profile = new Profile(urc.properties)
				if (user.save()) {
					flash.message = "Welcome aboard, ${urc.fullName ?: urc.userId}"
					redirect(uri: '/')
				} else {
					// maybe not unique userId?
					[user : urc]
				}
			}
		}
	}
	
	def profile = {
		if (params.id) {
			def user = User.findByUserId(params.id)
			if (user) {
				return [ profile : user.profile, userId : user.userId ]
			} else {
				response.sendError(404)
			}
		}
	}
}

class UserRegistrationCommand {
	String userId
	String password
	String passwordRepeat
	byte[] photo
	String fullName
	String bio
	String homepage
	String email
	String timezone
	String country
	String jabberAddress
	
	static constraints = {
		userId(size: 3..20)
		password(size: 6..8, blank: false,
				validator: { passwd, urc ->
					return passwd != urc.userId
				})
		passwordRepeat(nullable: false,
				validator: { passwd2, urc ->
					return passwd2 == urc.password
				})
		fullName(nullable: true)
		bio(nullable: true, maxSize: 1000)
		homepage(url: true, nullable: true)
		email(email: true, nullable: true)
		photo(nullable: true)
		country(nullable: true)
		timezone(nullable: true)
		jabberAddress(email: true, nullable: true)
	}
}

