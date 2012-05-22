class UrlMappings {

	static mappings = {
		"/$controller/$action?/$id?"{ constraints { // apply constraints here
			} }

		"/"(view:"/index")
		"500"(view:'/error')

		"/timeline/chuck_norris" {
			controller = "post"
			action = "timeline"
			id = "chuck_norris"
		}

		// alternatively, do it as a one-liner
		// "/timeline/chuck_norris"(controller:"post", action:"timeline", id:"chuck_norris")

		"/users/$id" {
			controller = "post"
			action = "timeline"
		}
	}
}
