package qotd

class QuoteController {
	def scaffold = true
	
	def quoteService
	
    def index = { 
		redirect(action: home)
	}
	
	def home = {
		render "<h1>Real programmers do not sleep!</h1>"
	}
	
	def random = {
		def randomQuote = quoteService.getRandomQuote()
		[ quote : randomQuote]
	}
	
	def ajaxRandom = {
		def randomQuote = quoteService.getRandomQuote()
		response.outputStream <<  "<q>${randomQuote.content}</q>" + "<p>${randomQuote.author}</p>"
	}
}
