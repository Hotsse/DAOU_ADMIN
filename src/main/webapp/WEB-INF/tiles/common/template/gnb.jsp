<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" session="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!-- JS -->
<script type="text/javascript">
var moveLevel1MenuPath = function() {
	window.location.href="${level1MenuPath}/index";
}

var moveLevel2MenuPath = function() {
	window.location.href="${level2MenuPath}";
}
</script>

<c:if test="${redirectMsg ne null }">
<script type="text/javascript">
$(document).ready(function(){
	alert("${redirectMsg}");
});
</script>
</c:if>

<!-- CONTENT -->
<nav class="navbar navbar-default">
	<div class="container-fluid">
		<div class="navbar-header">
			<a class="navbar-brand" href="/home/main" style="background:url('https://img.eduwill.net/resources/daou/logo.png') !important; background-size: cover !important;">
				<!--  -->
			</a>
		</div>
		<div id="navbar" class="navbar-collapse collapse">
			<ul class="nav navbar-nav">
				<c:forEach items="${level1MenuList}" var="level1Menu" varStatus="status">
				<li <c:if test="${level1Menu.isActive eq 'Y' }">class='active'</c:if>>
					<a href="${level1Menu.menuPath}">
						${level1Menu.menuNm}
					</a>
				</li>
				</c:forEach>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li>
					<a href="/login/logout"><span class="glyphicon glyphicon-log-out"></span> ${member.userNm}&nbsp;</a>
				</li>	
			</ul>
		</div>
	</div>
</nav>