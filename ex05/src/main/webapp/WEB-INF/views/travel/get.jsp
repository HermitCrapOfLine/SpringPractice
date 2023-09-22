<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<%@ include file="../layouts/header.jsp"%>


<script>
	$(document).ready(function() {

		$('.remove').click(function() {
			// 클릭 이벤트 핸들러 함수
			if (!confirm('정말 삭제하시겠습니까?'))
				return;

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
	<i class="fa-solid fa-car"></i> ${travel.title}
</h1>
<div class="d-flex justify-content-between">
	<div>
	 <i class="fa-solid fa-road"></i> ${travel.region}
	</div>
	<div><i class="fa-solid fa-headset"></i> ${travel.phone}</div>
</div>



<hr>

<div>${travel.description}</div>


<div class="mt-4"><i class="fa-solid fa-vihara"></i>주소: ${travel.address}</div>

<div id="map" style="width: 100%; height: 300px; background: gray">

</div>

<div class="mt-4">
	<a href="${cri.getLink('list') }" class="btn btn-primary list"> <i
		class="fas fa-list"></i>목록
	</a> 
	<sec:authorize access="hasRole('MANAGER')">
	<a href="${cri.getLink('modify')}&no=${travel.no}"
		class="btn btn-primary modify"> <i class="far fa-edit"></i> 수정
	</a> <a href="#" class="btn btn-danger remove"> <i
		class="fas fa-trash-alt"></i>삭제
	</a>
	</sec:authorize>
</div>


<form action="remove" method="post" name="removeForm">
<input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }" />
	<input type="hidden" name="no" value="${travel.no}" /> 
	<input type="hidden" name="pageNum" value="${cri.pageNum}" /> 
	<input type="hidden" name="amount" value="${cri.amount}" /> 
	<input type="hidden" name="type" value="${cri.type}" /> 
	<input type="hidden" name="keyword" value="${cri.keyword}" />
</form>

<script src="//dapi.kakao.com/v2/maps/sdk.js?appkey=b66fbde391b88480d9050ef5798db4ee&libraries=services"></script>
<script>

	
	
	let geocoder = new kakao.maps.services.Geocoder();
	let address ='${travel.address}';
	
	geocoder.addressSearch(address, function(result,status){
		if (status === kakao.maps.services.Status.OK){
			// 배열의 첫 번째 위치로 이동
			let coords = new kakao.maps.LatLng(result[0].y, result[0].x);
			
			let mapContainer = document.getElementById('map'); // 지도를 표시할 div
			let mapOption = {
					center: coords, // 중심좌표
					level: 3 // 지도의 확대 레벨
			};
			
			let map = new kakao.maps.Map(mapContainer, mapOption);
			
			var marker = new kakao.maps.Marker({
				map: map,
				position: coords
			});
			
			// 지도의 중심을 결과값으로 받은 위치로 이동시킵니다.
			// map.setCenter(coords) 위에서 중심이 잡혔기 때문에 움직일 필요 없다.
		} else{
			alert('잘못된 주소입니다.');
		}
	});

	
</script>

<%@ include file="../layouts/footer.jsp"%>