<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@include file="../layouts/header.jsp"%>

<script>
	// DOM 준비가 끝나면
	$(document).ready(function() {
		let actionForm = $('#actionForm');

		$('a.page-link').on('click', function(e) {
			e.preventDefault(); // a태그 혹은 체크할 때 주로 사용하는 event 핸들러
			actionForm.find('input[name="pageNum"]') // 자식에서 find를 찾는다.
			.val($(this).attr('href'));
			actionForm.submit();
		});

		$('.move').on('click', function(e) {
			e.preventDefault();
			actionForm.append('<input type="hidden" name="bno"/>');
			actionForm.find('input[name="bno"]').val($(this).attr('href'));
			actionForm.attr('action', '/board/get');
			actionForm.submit();
		});
	});
</script>

<h1 class="page-header">
	<i class="fas fa-list"></i> 게시글 목록
</h1>

<table class="table table-striped table-hover">
	<thead>
		<tr>
			<th style="width: 60px">No</th>
			<th>제목</th>
			<th style="width: 100px">작성자</th>
			<th style="width: 130px">등록일</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="board" items="${list}">
			<tr>
				<td>${board.bno}</td>
				<td><a class="move" href="${board.bno}">${board.title}</a></td>
				<td>${board.writer}</td>
				<td><fmt:formatDate pattern="yyyy-MM-dd"
						value="${board.regDate}" /></td>
			</tr>
		</c:forEach>
	</tbody>

</table>


<div class="text-right">
	<a href="register" class="btn btn-primary"> <i class="far fa-edit"></i>
		글쓰기
	</a>
</div>

<!-- 첫 페이지 이동 -->
<ul class="pagination justify-content-center">
	<c:if test="${pageMaker.cri.pageNum > 1 }">
		<li class="page-item"><a class="page-link" href="1"> <i
				class="fa-solid fa-backward-step"></i>
		</a></li>
	</c:if>

	<!-- 이전 페이지 이동 -->

	<c:if test="${pageMaker.prev}">
		<li class="page-item"><a class="page-link"
			href="${pageMaker.startPage-1 }"> <i
				class="fa-solid fa-angle-left"></i>
		</a></li>
	</c:if>

	<c:forEach begin="${pageMaker.startPage }" end="${pageMaker.endPage }"
		var="num">
		<li class="page-item ${pageMaker.cri.pageNum == num ? 'active' : '' }">
			<a class="page-link" href=${num }> ${num }</a>
		</li>
	</c:forEach>

	<!--  ">" 표시 -->
	<c:if test="${pageMaker.next }">
		<li class="page-item"><a class="page-link"
			href="${pageMaker.endPage+1 }"> <i
				class="fa-solid fa-angle-right"></i>
		</a></li>
	</c:if>

	<!-- ">|" 표시 -->
	<c:if test="${pageMaker.cri.pageNum < pageMaker.totalPage }">
		<li class="page-item"><a class="page-link"
			href="${pageMaker.totalPage }"> <i
				class="fa-solid fa-forward-step"></i>
		</a></li>
	</c:if>
</ul>

<form id="actionForm" action="/board/list" method="get">
	<input type="hidden" name="pageNum" value="${pageMaker.cri.pageNum }"/>
	<input type="hidden" name="amount" value="${pageMaker.cri.amount }"/>
</form>

<%@include file="../layouts/footer.jsp"%>