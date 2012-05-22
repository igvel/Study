package com.ivel

class PostController {

	def int MAX_ENTRIES_PER_PAGE = 5

	static navigation = [
		[group:'tabs', action:'timeline', title: 'My Timeline', order: 0],
		[action: 'global', title: 'Global Timeline', order: 1]
	]

	def scaffold = true

	def postService

	def index = {
		if (!params.id)
			params.id = "chuck_norris"
		redirect(action: 'timeline', params: params)
	}

	def timeline = {
		if (!params.max)
			params.max = MAX_ENTRIES_PER_PAGE
		if (!params.offset)
			params.offset = 0

		def user = User.findByUserId(params.id)
		def postCount = Post.countByUser(user)
		def posts = Post.findAllByUser(user, params)
		return [user: user, posts: posts, postCount: postCount]
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
