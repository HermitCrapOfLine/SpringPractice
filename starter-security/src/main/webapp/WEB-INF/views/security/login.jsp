<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ include file="../layouts/header.jsp"%>
<%-- 개별 페이지 --%>
<div style="width:500px" class="mx-auto">
<h1>
	<i class="fa-solid fa-person-walking-arrow-right"></i> 로그인
</h1>

	<c:if test="${param.error == 'true'}">
		<div class="error">사용자 ID 또는 비밀번호가 일치하지 않습니다.</div>
	</c:if>
	<form action="/security/login" method="post">
		<input type="hidden" name="${_csrf.parameterName }"	value="${_csrf.token }" />


		<div class="form-group">
			<label for="username">사용자 ID :</label> 
			<input type="text" class="form-control" name="username">
		</div>


		<div class="form-group">
			<label for="password">비밀번호 :</label> 
			<input type="password" id="password" class="form-control" name="password">
		</div>

		<div class="form-group form-check">
			<label class="form-check-label"> 
			<input	class="form-check-input" type="checkbox" name="remember">
				로그인 유지
			</label>
		</div>
		
		<button type="submit" class="btn btn-primary btn-block">
			<i class="fa-solid fa-person-walking-arrow-right"></i>
				로그인
		</button>
		
		<button type="#" class="btn btn-primary btn-block">
			<i class="fa-solid fa-file-signature"></i>
				회원가입
		</button>

	</form>
</div>
		<%@ include file="../layouts/footer.jsp"%>