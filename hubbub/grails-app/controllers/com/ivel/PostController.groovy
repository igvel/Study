package com.ivel

class PostController {

	def scaffold = true

	def postService

	def index = {
		if (!params.id)
			params.id = "chuck_norris"
		redirect(action: 'timeline', params: params)
	}

	def timeline = {
		def user = User.findByUserId(params.id)
		[ user : user ]
	}

	def addPost = {
		try {
			def newPost = postService.createPost(params.id, params.content)
			flash.message = "Added new post: ${newPost.content}"
		} catch (PostException pe) {
			flash.message = pe.message
			log.error "Failed to add new post", pe
		}
		redirect(action: 'timeline', id: params.id)
	}
}
