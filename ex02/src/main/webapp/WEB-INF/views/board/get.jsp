<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ include file="../layouts/header.jsp"%>


<script>
	$(document).ready(function() {

		$('.list').click(function() {
			document.forms.listForm.submit();
		});

		$('.modify').click(function() {
			document.forms.modifyForm.submit();
		});

		$('.remove').click(function() {
			// 클릭 이벤트 핸들러 함수
			if (!confirm('정말 삭제하시겠습니까?'))
				return;

			// form을 얻어서 submit() 호출
			//console.log(document.forms);
			document.forms.removeForm.submit();

		});

	});
</script>

<c:if test="${result=='success'}">
	<script>
		alert("수정이 완료되었습니다!");
	</script>
</c:if>


<h1 class="page-header">
	<i class="far fa-file-alt"></i> ${board.title}
</h1>
<div class="d-flex justify-content-between">
	<div>
		<i class="fas fa-user"></i> ${board.writer }
	</div>
	<div>
		<i class="fas fa-user"></i>
		<fmt.formatDate pattern="yyyy-MM-dd" value="${board.regDate}" />
	</div>
</div>

<hr>

<div>${board.content}</div>


<div class="mt-4">
	<a href="#" class="btn btn-primary list"> <i class="fas fa-list"></i>
		목록
	</a> <a href="#" class="btn btn-primary modify"> <i class="far fa-edit"></i>
		수정
	</a> <a href="#" class="btn btn-danger remove"> <i
		class="fas fa-trash-alt"></i> 삭제
	</a>
</div>




<form id="listForm" action="/board/list" method="get">
	<input type="hidden" name="pageNum" value="${cri.pageNum}" /> <input
		type="hidden" name="amount" value="${cri.amount}" />
</form>
<form id="modifyForm" action="/board/modify" method="get">
	<input type="hidden" id="bno" name="bno" value="${board.bno}" /> <input
		type="hidden" name="pageNum" value="${cri.pageNum}" /> <input
		type="hidden" name="amount" value="${cri.amount}" />
</form>
<form action="remove" method="post" name="removeForm">
	<input type="hidden" name="bno" value="${board.bno}" /> <input
		type="hidden" name="pageNum" value="${cri.pageNum}" /> <input
		type="hidden" name="amount" value="${cri.amount}" />
</form>


<%@ include file="../layouts/footer.jsp"%>