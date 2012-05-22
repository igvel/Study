<html>
<head>
<title>Timeline for ${user.profile.fullName}
</title>
<meta name="layout" content="main" />
</head>
<body>
	<h:lameBrowser userAgent="MSIE">
		<p>Dude, Firefox really is better. No, really. </p>
	</h:lameBrowser>
	<div id="newPost">
		<h3>
			What is
			${user.profile.fullName}
			hacking on right now?
		</h3>
		<g:if test="${flash.message}">
			<div class="flash">
				${flash.message}
			</div>
		</g:if>
		<p>
			<g:form action="addPost" id="${params.id}">
				<g:textArea id='postContent' name="content" rows="3" cols="50" />
				<br />
				<g:submitButton name="post" value="Post" />
			</g:form>
		</p>
	</div>
	<div class="allPosts">
		<g:each in="${posts}" var="post">
			<div class="postEntry">
				<div class="postText">
					${post.content}
				</div>
				<div class="postDate">
					<h:dateFromNow date="${post.dateCreated}"/>
				</div>
			</div>
		</g:each>
		<g:paginate total="${postCount}" id="${params.id}"/>
	</div>
</body>
</html>