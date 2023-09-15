<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ include file="../layouts/header.jsp"%>
<%-- 개별 페이지 --%>
<h1>페이지 타이틀</h1>
<div>
<!-- 첫 페이지 이동 -->
<ul class="pagination justify-content-center">
	<c:if test="${pageMaker.cri.pageNum > 1 }">
		<li class="page-item">
		<a class="page-link" href="${cri.getLink(1)}"> 
			<i	class="fa-solid fa-backward-step"></i>
		</a></li>
	</c:if>

	<!-- 이전 페이지 이동 -->

	<c:if test="${pageMaker.prev}">
		<li class="page-item"><a class="page-link" href="${cri.getLink(pageMaker.startPage-1) }"> 
		<i class="fa-solid fa-angle-left"></i>
		</a></li>
	</c:if>

	<c:forEach begin="${pageMaker.startPage }" end="${pageMaker.endPage }"
		var="num">
		<li class="page-item ${pageMaker.cri.pageNum == num ? 'active' : '' }">
			<a class="page-link" href="${cri.getLink(num)}"> ${num }</a>
		</li>
	</c:forEach>

	<!--  ">" 표시 -->
	<c:if test="${pageMaker.next }">
		<li class="page-item"><a class="page-link"
			href="${cri.getLink(pageMaker.endPage+1)}"> <i
				class="fa-solid fa-angle-right"></i>
		</a></li>
	</c:if>

	<!-- ">|" 표시 -->
	<c:if test="${pageMaker.cri.pageNum < pageMaker.totalPage }">
		<li class="page-item"><a class="page-link"
			href="${cri.getLink(pageMaker.totalPage) }"> <i
				class="fa-solid fa-forward-step"></i>
		</a></li>
	</c:if>
</ul>
<div>
<%@ include file="../layouts/footer.jsp"%>