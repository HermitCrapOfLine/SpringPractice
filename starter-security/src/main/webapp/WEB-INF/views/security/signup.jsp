<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<%@ include file="../layouts/header.jsp"%>
<%-- 개별 페이지 --%>

<div style="width:500px" class="mx-auto">
	<h1>회원 가입</h1>

	<form:form modelAttribute="member">
		<div class="form-group">
			<form:label path="username"> 사용자 ID: </form:label>
			<form:input path="username" cssClass="form-control" />
			<form:errors path="username" cssClass="error" />
		</div>

		<div class="form-group">
			<form:label path="password"> 비밀번호 : </form:label>
			<form:input path="password" cssClass="form-control" />
			<form:errors path="password" cssClass="error" />
		</div>

		<div class="form-group">
			<form:label path="password2"> 비밀번호 확인: </form:label>
			<form:input path="password2" cssClass="form-control" />
			<form:errors path="password2" cssClass="error" />
		</div>
		
		<div class="form-group">
		<form:label path="email"> 이메일: </form:label>
		<form:input path="email" cssClass="form-control" />
		<form:errors path="email" cssClass="error" />
	</div>
	
		<div class="text-center">
			<button type="submit" class="btn btn-primary btn-block">확인</button>
		</div>
	</form:form>


</div>



<%@ include file="../layouts/footer.jsp"%>
