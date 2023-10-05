<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ include file="../layouts/header.jsp"%>
<!-- 이미지 크기 조정 -->
<style>
.thumb-images > a {
    width:20%;
}
.thumb-images img {  /* .thumb-images의 자손인 img만 적용 */
    width: 100%;
    height: 150px;
    padding: 2px;
    object-fit: cover;
} 
.image-panel {
    width: 300px;
    height: 270px;
}
.image-panel > img {
    width: 300px;
    height: 200px;
}
.image-panel > .sm-images > img {
    width: 20%;
    height: 60px;
    object-fit: cover;
    cursor: pointer;
}

</style>
<c:if test="${result=='success' }">
    <script>
        alert("수정을 완료했습니다.")
    </script>
</c:if>
<script src="https://cdn.jsdelivr.net/npm/@fancyapps/ui@5.0/dist/fancybox/fancybox.umd.js"></script>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/@fancyapps/ui@5.0/dist/fancybox/fancybox.css"/>
<script>
    $(document).ready(function() {
        
        $('.remove').click(function() { // 얘는 post로 움직여야 됨
        // 클릭 이벤트 핸들러 함수
        if(!confirm("정말 삭제하시겠습니까?")) return; // 취소버튼 누르면 바로 리턴
        // form을 얻어서 submit() 호출
        document.forms.removeForm.submit();
        });
        
        // Fancybox 이미지 관리
        Fancybox.bind('[data-fancybox="gallery"]', { 
            // Your custom options
        });
        
        // 작은 이미지 클릭 시 큰 이미지 영역에 출력
        $('.sm-images > img').mouseenter(function(e) {
            let src = $(this).attr('src');
            $('.image-panel > img').attr('src', src);
        });
        
    });
</script>
<h1 class="page-header"> <!-- 제목 -->
    <i class="fa-solid fa-location-dot"></i> ${travel.title}
</h1>
<hr>
<div>
    <i class="fa-solid fa-map"></i> 권역: ${travel.region}
</div>
<div>
    <i class="fa-solid fa-location-crosshairs"></i> 주소: ${travel.address}
</div>
<div>
    <i class="fa-solid fa-phone"></i> 전화: ${travel.phone}
</div>
<hr>

<hr>
<!-- 상세 내용 + 이미지 -->
<div class="clearfix">
    <div class="image-panel float-left mr-3">
        <img src="${travel.image}">
        <div class="sm-images mt-1 d-flex">
            <c:forEach var="image" items="${travel.images}">
                <img src="${image}">
            </c:forEach>
        </div>
    </div>
    ${travel.description}
</div>
<hr>
<!-- 상세보기 이미지 -->
<div class="thumb-images my-5 d-flex">
    <c:forEach var="image" items="${travel.images}">
        <a href="${image}" data-fancybox="gallery">
            <img src="${image}">
        </a>
    </c:forEach>
</div>  

<!-- 지도 -->
<div id="map" style="width:100%; height:400px; background:gray"></div>


<hr>
<div class="mt-4">
    <a href="${cri.getLink('list')}" class="btn btn-primary list"> <!-- href 링크 지우고  #으로 쓴 다음 마지막에 list로 스크립트 연결 -->
        <i class="fas fa-list"></i> 목록</a> 
        
    <sec:authorize access="hasRole('ROLE_MANAGER')"> <!-- 매니저 권한 가진 사람만 가능 -->
        <a href="${cri.getLink('modify')}&no=${travel.no}" class="btn btn-primary modify">
            <i class="fas fa-edit"></i> 수정</a> 
            
        <a href="#" class="btn btn-danger remove">
            <i class="fas fa-trash-alt"></i> 삭제</a>
    </sec:authorize>
    
</div>
<!-- 삭제 Form -->
<form action="remove" method="post" name="removeForm">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
    <input type="hidden" name="bno" value="${travel.no }" />    
    <input type="hidden" name="pageNum" value="${cri.pageNum }" />
    <input type="hidden" name="amount" value="${cri.amount}" />
    <input type="hidden" name="type" value="${cri.type }" />
    <input type="hidden" name="keyword" value="${cri.keyword}" />
</form>





<!-- Kakao Map API 등록 -->
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=1d3200cdac5443c1aa08492f07588b53&libraries=services"></script> <!-- kakao map api & Geocoder api -->
<script>
    let geocoder = new kakao.maps.services.Geocoder(); // Geocoder라고 주소를 위도 경도로 바꿔주는 api
    let address = '${travel.address}';
    
    geocoder.addressSearch(address, function(result, status) { // ${travel.address}를 위도 경도로 바꿔줌
        if(status === kakao.maps.services.Status.OK) {
            // 배열의 첫번째 위치로 이동
            let coords = new kakao.maps.LatLng(result[0].y, result[0].x); // 해당 위치의 좌표 얻음
            
            // 지도 제어 코딩
            let mapContainer = document.getElementById('map'); // 지도를 표시할 div
            let mapOption = {
                //  center: new kakao.maps.LatLng(coords), // 위에서 이미 let coords에 넣어서 아래처럼 줄여야 됨
                    center: coords, // 중심 좌표
                    level: 5 // 지도의 확대 레벨
            };
            
            let map = new kakao.maps.Map(mapContainer, mapOption); // Map(생성자-element, 옵션객체-지도를 어떻게 구성할꺼냐)
            
            let marker = new kakao.maps.Marker({
                map: map,
                position: coords
            });
            
            // 지도의 중심을 결과값으로 받은 위치로 이동
            // map.setCenter(coords); // center: new kakao.maps.LatLng(coords)에서 이미 중심으로 이동됨
        } else {
            alert('잘못된 주소입니다.');
        }
    });
    
</script>
<%@ include file="../layouts/footer.jsp"%>