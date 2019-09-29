<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!-- JS -->
<script type="text/javascript">
var insertMenuAuth = function(){
	
	$form = $("form[id=insertForm]");
	
	// 유효성 체크
	if($form.find("select[name=deptIdx]").val() == ""){
		alert("부서를 선택해 주세요");
		return;
	}
	else if($form.find("select[name=roleIdx]").val() == ""){
		alert("직책을 선택해 주세요");
		return;
	}
	
	if( confirm("해당 권한을 등록하시겠습니까?") ) {
		$.ajax({
			url : "${level2MenuPath}/insertAuth"
			, type : "post"
			, data : $form.serialize()
			, dataType : "json"
			, success : function(data) {
				if( data.result == "OK" ) {
					alert("등록이 완료 되었습니다.");
				}
				else {
					alert("등록에 실패했습니다.");					
				}
				window.location.reload();
			}
			, error : function(xhr, status, error) {
				alert(error);
			}
		});
	}
	
}
var updateMenuAuth = function(menuIdx, deptIdx, roleIdx){
	
	isRetrieveYn = ($("input:checkbox[name=isRetrieveYn][data-dept=" + deptIdx + "][data-role=" + roleIdx + "]").is(":checked")) ? "Y" : "N";
	isWriteYn = ($("input:checkbox[name=isWriteYn][data-dept=" + deptIdx + "][data-role=" + roleIdx + "]").is(":checked")) ? "Y" : "N";
	isDeleteYn = ($("input:checkbox[name=isDeleteYn][data-dept=" + deptIdx + "][data-role=" + roleIdx + "]").is(":checked")) ? "Y" : "N";
	isDownloadYn = ($("input:checkbox[name=isDownloadYn][data-dept=" + deptIdx + "][data-role=" + roleIdx + "]").is(":checked")) ? "Y" : "N";
	
	if( confirm("해당 권한을 수정하시겠습니까?") ) {
		$.ajax({
			url : "${level2MenuPath}/updateAuth"
			, type : "post"
			, data : {
				menuIdx : menuIdx
				, deptIdx : deptIdx
				, roleIdx : roleIdx
				, isRetrieveYn : isRetrieveYn
				, isWriteYn : isWriteYn
				, isDeleteYn : isDeleteYn
				, isDownloadYn : isDownloadYn
			}
			, dataType : "json"
			, success : function(data) {
				if( data.result == "OK" ) {
					alert("수정이 완료 되었습니다.");
				}
				else {
					alert("수정에 실패했습니다.");
				}
				window.location.reload();
			}
			, error : function(xhr, status, error) {
				alert(error);
			}
		});
	}
}

var deleteMenuAuth = function(menuIdx, deptIdx, roleIdx){
	
	if( confirm("해당 권한을 삭제하시겠습니까?\n\n해당 작업은 복구가 불가능합니다") ) {
		$.ajax({
			url : "${level2MenuPath}/deleteAuth"
			, type : "post"
			, data : {
				menuIdx : menuIdx
				, deptIdx : deptIdx
				, roleIdx : roleIdx
			}
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
</script>

<!-- CONTENT -->
<!-- 제목 -->
<h3 class="page-header">메뉴 권한 관리
	<div class="btn-group" role="group" style="float: right; margin-bottom: 10px;">
		<button type="button" class="btn btn-lg btn-default" onclick="javascript:window.close();">
			종료 <span class="glyphicon glyphicon-remove"></span>
		</button>
	</div>
</h3>

<!-- 권한 추가 -->
<h5>권한 추가</h5>
	
<form id="insertForm" method="post" action="${level2MenuPath}/writeAuth">

	<input type="hidden" name="menuIdx" value="${param.menuIdx}" />
		
	<table id="tableDefault" class="table table-bordered">
		<colgroup>
			<col width="25%" />
			<col width="75%" />
		</colgroup>
		<tbody>
			<tr>
				<th>부서 *</th>
				<td>
					<select class="form-control" name="deptIdx">
						<option value="">부서 선택</option>
						<c:forEach var="list" items="${deptList }" varStatus="status">
						<option value="${list.deptIdx }">${list.deptNm }</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<th>직책 *</th>
				<td>
					<select class="form-control" name="roleIdx">
						<option value="">직책 선택</option>
						<c:forEach var="list" items="${roleList }" varStatus="status">
						<option value="${list.roleIdx }">${list.roleNm }</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<th>권한 내용 *</th>
				<td>
					<div class="form-check-inline">
						<label>
							<input type="checkbox" name="isRetrieveYn" class="form-check-input" value="Y" /> 조회
						</label>
						<label>
							<input type="checkbox" name="isWriteYn" class="form-check-input" value="Y" /> 등록/수정
						</label>
						<label>
							<input type="checkbox" name="isDeleteYn" class="form-check-input" value="Y" /> 삭제
						</label>
						<label>
							<input type="checkbox" name="isDownloadYn" class="form-check-input" value="Y" /> 다운로드
						</label>
					</div>
				</td>
			</tr>
		</tbody>
	</table>
</form>

<div class="container">
	<button type="button" class="btn btn-primary center-block" onclick="insertMenuAuth();">
		<span class="glyphicon glyphicon-save"></span> 저장
	</button>
</div>

<!-- 권한 리스트 -->
<h5>권한 리스트</h5>

<table id="tableDefault" class="table table-bordered table-hover">
	<colgroup>
		<col width="15%" />
		<col width="15%" />
		<col width="*" />
		<col width="10%" />
	</colgroup>
	<thead>
		<tr>
			<th>부서</th>
			<th>직책</th>
			<th>권한</th>
			<th>관리</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="list" items="${menuAuthList }" varStatus="status">
		<tr>
			<td>${list.deptNm }</td>
			<td>${list.roleNm }</td>
			<td>
				<div class="form-check-inline">
					<label>
						<input type="checkbox" name="isRetrieveYn" class="form-check-input"
							data-dept="${list.deptIdx }" data-role="${list.roleIdx }" value="Y" ${list.isRetrieveYn == 'Y' ? "checked" : "" } /> 조회
					</label>
					<label>
						<input type="checkbox" name="isWriteYn" class="form-check-input"
							data-dept="${list.deptIdx }" data-role="${list.roleIdx }" value="Y" ${list.isWriteYn == 'Y' ? "checked" : "" } /> 등록/수정
					</label>
					<label>
						<input type="checkbox" name="isDeleteYn" class="form-check-input"
							data-dept="${list.deptIdx }" data-role="${list.roleIdx }" value="Y" ${list.isDeleteYn == 'Y' ? "checked" : "" } /> 삭제
					</label>
					<label>
						<input type="checkbox" name="isDownloadYn" class="form-check-input"
							data-dept="${list.deptIdx }" data-role="${list.roleIdx }" value="Y" ${list.isDownloadYn == 'Y' ? "checked" : "" } /> 다운로드
					</label>
				</div>
			</td>
			<td>
				<div class="btn-group" role="group">
					<button type="button" class="btn btn-primary btn-xs" onclick="javascript:updateMenuAuth(${list.menuIdx}, ${list.deptIdx }, ${list.roleIdx });">저장</button>
					<button type="button" class="btn btn-danger btn-xs" onclick="javascript:deleteMenuAuth(${list.menuIdx}, ${list.deptIdx }, ${list.roleIdx });">삭제</button>
				</div>
			</td>
		</tr>
		</c:forEach>
	</tbody>
</table>