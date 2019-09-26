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
			<button type="submit" class="btn btn-lg btn-primary btn-block">로그인</button>
		</div>
	</form>
</div>