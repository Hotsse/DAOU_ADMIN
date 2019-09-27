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

<h3 class="page-header">관리자 활동 관리</h3>

<form id="searchForm" method="post">

	<div class="panel panel-default">
		<div class="panel-body">
			<div class="form-horizontal">
				<div class="form-group">
					<div class="col-sm-2">
						<select class="form-control" name="logAction">
							<option value="">활동 분류</option>
							<option value="RETRIEVE" ${param.logAction == 'RETRIEVE' ? "selected" : "" }>조회</option>
							<option value="WRITE"  ${param.logAction == 'WRITE' ? "selected" : "" }>등록/수정</option>
							<option value="DELETE"  ${param.logAction == 'DELETE' ? "selected" : "" }>삭제</option>
							<option value="DOWNLOAD"  ${param.logAction == 'DOWNLOAD' ? "selected" : "" }>다운로드</option>
							<option value="ADMIN"  ${param.logAction == 'ADMIN' ? "selected" : "" }>시스템 관리자</option>	
						</select>
					</div>
					<div class="col-sm-6">
						<div class="input-group">
							<input type="text" class="datepicker form-control" name="startDt" placeholder="조회 시작일" value="${param.startDt }" />
							<span class="input-group-addon gap">~</span>
							<input type="text" class="datepicker form-control" name="endDt" placeholder="조회 종료일" value="${param.endDt }" />
						</div>
					</div>
					<div class="col-sm-2">
				    	<input type="text" class="form-control" name="regId" placeholder="아이디 입력" value="${param.regId }" >
					</div>
					<div class="col-sm-2">
						<div class="btn-group" role="group">
							<button class="btn btn-primary" type="submit">
								<span class="glyphicon glyphicon-search"></span> 조회
							</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</form>

<table id="tableDefault" class="table table-bordered table-hover">
	<colgroup>
		<col width="5%" />
		<col width="10%" />
		<col width="10%" />
		<col width="*" />
		<col width="15%" />
		<col width="15%" />
	</colgroup>
	<thead>
		<tr>
			<th>Idx.</th>
			<th>아이디</th>
			<th>활동분류</th>
			<th>접근URI</th>
			<th>활동IP</th>
			<th>활동일시</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="list" items="${logList}" varStatus="status">
		<tr>
			<td>${list.logIdx }</td>
			<td>${list.regId }</td>
			<td>${list.logAction }</td>
			<td class="text-left">${list.logUri }</td>
			<td>${list.regIp }</td>
			<td>${list.regDt }</td>
		</tr>
		</c:forEach>
	</tbody>
</table>