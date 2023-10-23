<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ include file="../layouts/header.jsp"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%-- 개별 페이지 --%>
<h1>페이지 타이틀</h1>

<div class="panel-body">
	<form:form modelAttribute="basketballVO" role="form">
		<input type="hidden" name="_csrf" value="${_csrf.token }">
		<div class="form-group">
			<form:label path="p3"> 3점</form:label>
			<form:input path="p3" cssClass="form-control" />
		</div>
		<div class="form-group">
			<form:label path="p2"> 2점</form:label>
			<form:input path="p2" cssClass="form-control" />
		</div>
		<div class="form-group">
			<form:label path="trb"> 트레블링</form:label>
			<form:input path="trb" cssClass="form-control" />
		</div>
		<div class="form-group">
			<form:label path="ast"> 도움</form:label>
			<form:input path="ast" cssClass="form-control" />
		</div>
		<div>
			<form:label path="stl"> 스틸</form:label>
			<form:input path="stl" cssClass="form-control" />
		</div>
		<div class="form-group">
			<form:label path="blk"> 블락</form:label>
			<form:input path="blk" cssClass="form-control" />
		</div>
		
		<div class="form-group">
			<label> 결과</label>
			<div>${result }</div>
		</div>
		
		<button type="submit" class="btn btn-primary list">
			<i class="fas fa-check"></i> 확인
		</button>
		<button type="reset" class="btn btn-primary">
			<i class="fas fa-undo"></i> 취소
		</button>
		
	</form:form>
</div>


<%@ include file="../layouts/footer.jsp"%>