<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<script type="text/javascript">
var moveInsertForm = function(menuIdx){
	if(menuIdx){
		window.location.href="${level2MenuPath}/write?menuIdx=" + menuIdx;
	}
	else{
		window.location.href="${level2MenuPath}/write";		
	}	
}

var deleteMenu = function(menuIdx){
	if( confirm("해당 메뉴를 삭제하시겠습니까?\n\n해당 작업은 복구가 불가능합니다") ) {
		$.ajax({
			url : "${level2MenuPath}/delete"
			, type : "post"
			, data : { menuIdx : menuIdx }
			, dataType : "json"
			, success : function(data) {
				if( data.result == "OK" ) {
					alert("삭제가 완료 되었습니다.");
				}
				else {
					alert("삭제에 실패했습니다.");
				}				
				window.location.reload();
			}
			, error : function(xhr, status, error) {
				alert(error);
			}
		});
	}
}

var openMenuAuthPopup = function(menuIdx){	
	window.open("${level2MenuPath}/popupAuth?menuIdx=" + menuIdx, "popupAuth", "width=500,height=650");	
}
</script>

<h3 class="page-header">메뉴 관리
	<div class="btn-group" role="group" style="float: right; margin-bottom: 10px;">
		<button type="button" class="btn btn-lg btn-default" onclick="moveInsertForm();">
			<span class="glyphicon glyphicon-pencil"></span> 등록
		</button>
	</div>
</h3>
<table id="tableDefault" class="table table-bordered table-hover">
	<colgroup>
		<col width="5%" />
		<col width="15%" />
		<col width="*" />
		<col width="5%" />
		<col width="5%" />
		<col width="5%" />
		<col width="20%" />	
		<col width="15%" />	
	</colgroup>
	<thead>
		<tr>
			<th>메뉴 번호</th>
			<th>이름</th>
			<th>경로</th>
			<th>레벨</th>
			<th>상위메뉴</th>
			<th>정렬 순서</th>
			<th>등록일/등록자<br/>수정일/수정자</th>
			<th>관리</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="list" items="${menuList}" varStatus="status">
		<tr <c:if test="${list.menuLv == 1 }">style="background:DarkGray;"</c:if>>
			<td>${list.menuIdx }</td>
			<td class="text-left"><c:forEach var="i" begin="2" end="${list.menuLv }">ㄴ</c:forEach>${list.menuNm }</td>
			<td class="text-left">${list.menuPath }</td>
			<td>${list.menuLv }</td>
			<td>${list.parentMenuIdx }</td>
			<td>${list.sortOrder }</td>
			<td>
				${list.regDt} / ${list.regId}<br/>
				${list.modDt} / ${list.modId}
			</td>
			<td>
				<div class="btn-group" role="group">
					<button type="button" class="btn btn-secondary btn-xs" onclick="javascript:openMenuAuthPopup(${list.menuIdx});">권한</button>
					<button type="button" class="btn btn-default btn-xs" onclick="javascript:moveInsertForm(${list.menuIdx});">수정</button>
					<button type="button" class="btn btn-danger btn-xs" onclick="javascript:deleteMenu(${list.menuIdx });">삭제</button>
				</div>
			</td>
		</tr>
		</c:forEach>
	</tbody>
</table>