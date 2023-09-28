<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<%@ include file="../layouts/header.jsp"%>


<!-- 날짜 Format -> moment.min.js -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.29.4/moment.min.js">
</script>

<script src="/resources/js/rest.js"></script>
<script src="/resources/js/comment.js"></script>
<script src="/resources/js/reply.js"></script>

<script>
// 댓글 기본 URL 상수 - 전역 상수

const COMMENT_URL = '/api/board/${param.bno}/comment/';
const REPLY_URL = '/api/board/${param.bno}/reply/';

	$(document).ready(async function() {
 
		$('.remove').click(function() {
			// 클릭 이벤트 핸들러 함수
			if (!confirm('정말 삭제하시겠습니까?'))
				return;
			document.forms.removeForm.submit();
		});

		let bno = ${param.bno}; // 글번호
		let writer = '${username}'; // 작성자 (로그인 유지)
		
		loadComments(bno, writer); // 댓글 목록 불러오기 
		
		// 댓글 추가 버튼 처리
		$('.comment-add-btn').click(function(e) {
			createComment(bno, writer);
		});
		
		$('.comment-list').on('click', '.comment-update-show-btn', showUpdateComment);
		
		// 수정 확인 버튼 클릭
		$('.comment-list').on('click', '.comment-update-btn', function (e){
			const el = $(this).closest('.comment');
			updateComment(el, writer);
		});
		
		$('.comment-list').on('click', '.comment-update-cancel-btn', 
				cancelCommentUpdate);
		
		$('.comment-list').on('click', '.comment-delete-btn', deleteComment);

		$('.comment-list').on('click', '.reply-add-show-btn', function(e){
			showReplyAdd($(this), writer);
		});
		
		
		// writer 매개변수가 필요하면 function(e)을 사용한다
		$('.comment-list').on('click', '.reply-add-btn', function(e){
			addReply($(this), writer);
		});
		
		$('.comment-list').on('click', '.reply-add-cancel-btn', cancelReply);
		
		$('.comment-list').on('click', '.reply-update-show-btn', function(e){
			showUpdateReply($(this));
		});
		
		// 답글 수정 등록
		$('.comment-list').on('click', '.reply-update', function(e){
			updateReply($(this));
		});
		
		// 답글 수정 취소
		$('.comment-list').on('click', '.reply-update-cancel', cancelReplyUpdate);
		
		// 답글 삭제
		$('.comment-list').on('click', '.reply-delete-btn', deleteReply);
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

<div class="my-4">${board.content}</div>

<div class="mt-4">
	<a href="${cri.getLink('list') }" class="btn btn-primary list"> <i
		class="fas fa-list"></i>목록
	</a>

	<c:if test="${username == board.writer }">
		<a href="${cri.getLinkWithBno('modify', board.bno) }"
			class="btn btn-primary modify"> <i class="far fa-edit"></i> 수정
		</a>
		<a href="#" class="btn btn-danger remove"> <i
			class="fas fa-trash-alt"></i>삭제
		</a>
	</c:if>
</div>


<!-- 새 댓글 작성 -->
<c:if test="${username != board.writer }">
	<div class="bg-light p-2 rounded my-5">
		<div>${username == null ? '댓글을 작성하려면 먼저 로그인하세요' : '댓글 작성' }
		</div>
			<div>
				<textarea class="form-control new-comment-content" rows="3"
					${username == null ? 'disabled' : '' } }></textarea>
				<div class="text-right">
					<button class="btn btn-primary btn-sm my-2 comment-add-btn"
						${username == null ? 'disabled' : '' } >
						<i class="fa-regular fa-comment"></i> 확인
					</button>
				</div>
			</div>
		</div>
	</c:if>
		


<div class="my-5">
	<i class="fa-regular fa-comments"></i>
	댓글 목록
	<hr>
	<div class="comment-list">
	
	
	</div>
</div>





<form action="remove" method="post" name="removeForm">
	<input type="hidden" name="${_csrf.parameterName }"
		value="${_csrf.token }" /> <input type="hidden" name="bno"
		value="${board.bno}" /> <input type="hidden" name="pageNum"
		value="${cri.pageNum}" /> <input type="hidden" name="amount"
		value="${cri.amount}" /> <input type="hidden" name="type"
		value="${cri.type}" /> <input type="hidden" name="keyword"
		value="${cri.keyword}" />
</form>


<%@ include file="../layouts/footer.jsp"%>