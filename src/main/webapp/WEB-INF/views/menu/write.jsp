<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/paginator.tld" prefix="paginator" %>

<script>
	var submitInsertForm = function(){
		if(confirm("메뉴를 등록하시겠습니까?")){
			$("form[id=insertForm]").submit();			
		}		
	}
	
	$(document).ready(function(){
		$("input:radio[name=menuLv]").change(function(){
			level = $(this).val();
			
			if(level == 1){
				$("tr[id=parentMenuIdx]").addClass("collapse");
				$("select[name=parentMenuIdx]").val(0);
			}
			else if(level == 2){
				$("tr[id=parentMenuIdx]").removeClass("collapse");
			}
		});
	});
</script>

<h3 class="page-header">메뉴 관리 <small>등록</small>
	<div class="btn-group" role="group" style="float: right; margin-bottom: 10px;">
		<button type="button" class="btn btn-lg btn-default" onclick="moveLevel1MenuPath();">
			<span class="glyphicon glyphicon-chevron-left"></span> 돌아가기
		</button>
	</div>
</h3>

<form id="insertForm" method="post">
	<table id="tableDefault" class="table table-bordered">
		<colgroup>
			<col width="10%">
			<col width="90%">
		</colgroup>
		<tbody>
			<tr>
				<th>레벨 *</th>
				<td>
					<label><input type="radio" name="menuLv" value="1" ${menuInfo.menuLv == 1 ? "checked" : "" } /> 1레벨</label>&nbsp;
					<label><input type="radio" name="menuLv" value="2" ${menuInfo.menuLv == 2 ? "checked" : "" } /> 2레벨</label>
				</td>
			</tr>
			<tr id="parentMenuIdx" class="${menuInfo.menuLv == 1 ? "collapse" : "" }">
				<th>상위 메뉴 *</th>
				<td>
					<select class="form-control" name="parentMenuIdx">
						<option value="0">선택하십시오</option>
						<c:forEach var="list" items="${menuList}" varStatus="status">
						<option value="${list.menuIdx }" ${menuInfo.parentMenuIdx == list.menuIdx ? "selected" : "" }>${list.menuNm }</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<th>메뉴명 *</th>
				<td>
					<input type="text" class="form-control" name="menuNm" placeholder="메뉴명" value="${menuInfo.menuNm }" />
				</td>
			</tr>
			<tr>
				<th>경로 *</th>
				<td>
					<input type="text" class="form-control" name="menuPath" placeholder="메뉴명" value="${menuInfo.menuPath }" />
				</td>
			</tr>
			<tr>
				<th>정렬순서 *</th>
				<td>
					<input type="number" class="form-control" name="sortOrder" min="1" max="99999" value="${menuInfo.sortOrder }" />
				</td>
			</tr>
		</tbody>
	</table>
</form>

<div class="container">
	<div class="btn-group center-block" role="group" style="width:200px">
		<button type="button" class="btn btn-primary" onclick="submitInsertForm();">
			<span class="glyphicon glyphicon-save"></span> 저장
		</button>
	</div>
</div>