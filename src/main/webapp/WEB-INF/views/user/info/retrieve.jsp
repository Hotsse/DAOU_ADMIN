<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<script type="text/javascript">
var writeUserInfo = function(){
	$.ajax({
		url : "${level2MenuPath}/write"
		, type : "post"
		, dataType : "html"
		, success : function(data) {
			alert("작업 접근 성공");
		}
		, error : function(xhr, status, error) {
			if(xhr.status == 401){
				alert("해당 작업에 대한 권한이 없습니다");
			}
		}
	});
}

var deleteUserInfo = function(){
	$.ajax({
		url : "${level2MenuPath}/delete"
		, type : "post"
		, dataType : "html"
		, success : function(data) {
			alert("작업 접근 성공");
		}
		, error : function(xhr, status, error) {
			if(xhr.status == 401){
				alert("해당 작업에 대한 권한이 없습니다.");
			}
		}
	});
}

var downloadUserInfo = function(){
	$.ajax({
		url : "${level2MenuPath}/download"
		, type : "post"
		, dataType : "html"
		, success : function(data) {
			alert("작업 접근 성공");
		}
		, error : function(xhr, status, error) {
			if(xhr.status == 401){
				alert("해당 작업에 대한 권한이 없습니다.");
			}
		}
	});
}
</script>

<h3>회원 조회</h3>

<h5>구분</h5>

<div class="row">
	<button type="button" class="btn btn-lg btn-primary" onclick="javascript:writeUserInfo();">
		<span class="glyphicon glyphicon-pencil"></span> 회원정보 등록/수정
	</button>
	
	<button type="button" class="btn btn-lg btn-danger" onclick="javascript:deleteUserInfo();">
		<span class="glyphicon glyphicon-trash"></span> 회원정보 삭제
	</button>
	
	<button type="button" class="btn btn-lg btn-warning" onclick="javascript:downloadUserInfo();">
		<span class="glyphicon glyphicon-download"></span> 회원정보 다운로드
	</button>
</div>
