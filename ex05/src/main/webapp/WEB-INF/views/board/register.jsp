<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<%@ include file="../layouts/header.jsp"%>
<!-- summernote -->
<link rel="stylesheet"
	href="/resources/css/summernote/summernote-lite.min.css">
<script src="/resources/js/summernote/summernote-lite.min.js"></script>
<script src="/resources/js/summernote/lang/summernote-ko-KR.min.js"></script>
<script>
	$(document).ready(function() {
		$('#content').summernote({
			height : 300, // 에디터높이
			focus : true, // 에디터로딩후포커스를맞출지여부
			lang : "ko-KR",// 한글설정
		});
		
		const attaches = $('[name="files"]');
		const attachList = $('#attach-list');
		
		attaches.change(function (e) {
			let fileList = '';
			for (let file of this.files) {
				let fileStr = `
				<div>
					<i class="fa-solid fa-file"></i>
					\${file.name}(\${file.size.formatBytes()})
				</div>`;
				fileList += fileStr;
			}
			attachList.html(fileList);	
		});
		
	});
	// 기본글꼴설정
	$('#content').summernote('fontName', 'Arial');
</script>
<%--개별페이지--%>
<h1 class="page-header">
	<i class="far fa-edit"></i>Board Register
</h1>
<div class="panel panel-default">
	<div class="panel-heading">board Register</div>
	<div class="panel-body">
		<form:form modelAttribute="board" role="form"
		action="?_csrf=${_csrf.token }"
		enctype="multipart/form-data">
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		<form:hidden path="bno" />
		<form:hidden path="writer" value="${username}" />
			<div class="form-group">
				<form:label path="title">Title</form:label>
				<form:input path="title" cssClass="form-control" />
				<!-- name은 BoardVO에 있는 멤버변수 이름을 사용 -->
				<form:errors path="title" cssClass="error" />
			</div>
			
			<div class="form-group">
				<label for="attaches">첨부파일</label>
				<div id="attach-list" class="my-1"></div>
				<input type="file" class="form-control" multiple
					name="files"/>
			</div>
			
			<div class="form-group">
				<form:label path="content">Content</form:label>
				<form:textarea path="content" cssClass="form-control" id="content" />
				<form:errors path="content" cssClass="error" />
			</div>

			<button type="submit" class="btn btn-primary">
				<i class="fas fa-undo"></i>확인
			</button>
			<button type="reset" class="btn btn-primary">
				<i class="fas fa-undo"></i>취소
			</button>
			<a href="javascript:history.back()" class="btn btn-primary"> <i
				class="fas fa-list"></i>목록
			</a>
		</form:form>
	</div>
</div>
<%@ include file="../layouts/footer.jsp"%>