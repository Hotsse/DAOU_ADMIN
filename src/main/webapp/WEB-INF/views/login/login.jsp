<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<div class="col-sm-4 col-sm-offset-4">
	<form id="loginForm" action="/login/login" method="post">
		<h3 class="form-signin-heading text-danger">DAOU Admin Site(샘플)</h3>
		<div class="form-group">
			<input type="text" name="userId" class="form-control" placeholder="아이디" required autofocus />
		</div>
		<div class="form-group">
			<input type="password" name="userPw" class="form-control" placeholder="패스워드" required />
		</div>
		<div class="form-group">
			<button class="btn btn-lg btn-primary btn-block" onclick="javscript:tryLogin();">로그인</button>
		</div>
		<div class="form-group">
			<!--
			<p class="form-control-static text-danger">
				&lt;주의&gt;
				<br>AD 계정 로그인은 내부에서만 가능합니다.
				<br>기존 관리자 계정이 존재하는 상태에서만 AD 계정 로그인이 가능합니다.
				<br>기존 관리자 계정과 AD 계정의 연결은 계정 신청서에 별도로 기재하여 신청하셔야합니다. (담당: 개발팀 장연호 팀장)
			</p>
			-->
		</div>

	</form>
</div>