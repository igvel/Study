package hubbub

import com.grailsrocks.functionaltest.*

class PostListFunctionalTests extends BrowserTestCase {
    void testSomeWebsiteFeature() {
        // Here call get(uri) or post(uri) to start the session
        // and then use the custom assertXXXX calls etc to check the response
        //
		get('/post/timeline/chuck_norris')
        assertStatus 200
        assertContentContains 'What are you hacking on right now?'
	}
}
