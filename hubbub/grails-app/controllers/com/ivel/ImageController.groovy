package com.ivel

class ImageController {

	// this is for storing image into DB
	def upload = { PhotoUploadCommand puc ->
		def user = User.findByUserId(puc.userId)
		user.profile.photo = puc.photo
		redirect(controller: 'user', action: 'profile', id: puc.userId) 
	}

	def form = {
		// pass through to upload form
		[ userList : User.list() ]
	}

	def view = {
		// pass through to "view photo" page
	}

	def renderImage = {
		def user = User.findByUserId(params.id)
		if (user?.profile?.photo) {
			response.setContentLength(user.profile.photo.length)
			response.outputStream.write(user.profile.photo)
		} else {
			response.sendError(404)
		}
	}

	// this is for raw file uploading
	def rawUpload = {
		// a Spring MultiPartFile
		def mpf = request.getFile('photo')
		if (!mpf?.empty && mpf.size < 1024*200) {
			mpf.transferTo(new File(
					"/hubbub/images/${params.userId}/mugshot.gif"
					))
		}
	}
}

class PhotoUploadCommand {
	byte[] photo
	String userId
}