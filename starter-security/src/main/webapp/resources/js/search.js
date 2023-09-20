 	$(document).ready(function() {

		let searchForm = $('#searchForm');
		
		$('#searchForm button').on('click', function(e) { 
			let type =searchForm.find('option:selected');
			if (!type.val()) {
				alert('검색종류를 선택하세요');
				return false;
			}

			let keyword = searchForm.find('input[name="keyword"]');
			if (!keyword.val()) { //필수요소 체크
				keyword.focus();
				alert('키워드를 입력하세요');
				return false;
			}

			searchForm.find('input[name="pageNum"]').val('1');
			e.preventDefault();
			searchForm.submit();
		});

	});