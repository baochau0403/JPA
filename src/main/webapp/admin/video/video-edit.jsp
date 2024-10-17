<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<form action="${pageContext.request.contextPath }/admin/video/update"
	method="post">
	<input type="text" id="videoid" name="videoid"
		value="${video.videoid}" hidden="hidden"><br> <label
		
		for="videoname">Video name:</label><br> <input type="text"
		id="videoname" name="videoname" value="${video.videoname}"><br>
		
		 <label for="title">Title:</label>
    <input type="text" id="title" name="title" value="${video.title}" required/><br/><br/>
    
    
	  <label for="description">Description:</label>
    <textarea id="description" name="description" required>${video.description}</textarea><br/><br/>

    <label for="file">Select New File:</label>
    <input type="file" id="file" name="file"/><br/>
    <small>Current file: ${video.filename}</small><br/><br/>

    <input type="submit" value="Update Video"/>
</form>
<a href="${pageContext.request.contextPath}/admin/videos">Back to Video List</a>
</body>
</html>

</form>
