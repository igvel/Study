package hubbub

class UtilTagLib {

	static namespace = 'h'

	// conditional tag
	def lameBrowser = { attrs, body ->
		if(request.getHeader('User-Agent')  =~ attrs.userAgent ) {
			out << body()
		}
	}

	/** 
	 * iteration tag
	 * <h:eachFollower in="${following}">
		<img src="
		<g:createLink action="tiny" controller="image"
		id="${followUser.userId}"/>
		" alt="${followUser.userId}"/>
		</h:eachFollower>
	*/
	def eachFollower = { attrs, body ->
		def followers = attrs.followers
		followers?.each { follower ->
			body(followUser: follower)
		}
	}

	// sample of reusing existing tags as methods
	// <h:tinyThumbnail userId="${followUser.userId}"/>
	def tinyThumbnail = { attrs ->
		def userId = attrs.userId
		out << "<img src='"
		out << g.createLink(action: "tiny",
				controller: "image", id: userId)
		out << "' alt='${userId}'"
	}
}
