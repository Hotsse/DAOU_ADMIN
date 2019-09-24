<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" session="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!-- CSS -->
<style>
.form-control-feedback {
  position: absolute;
  top: 8px;
  right: 5px;
  z-index: 2;
  display: block;
  text-align: center;
  pointer-events: none;
}
#ulAutoMenu li.active {
	background : #ccc;
	font-weight: bold;
}
#ulAutoMenu li:hover {
	background : #ccc;
	cursor : pointer;
	font-weight: bold;
}
</style>

<!-- JS -->
<script type="text/javascript">
var moveLevel1MenuPath = function() {
	window.location.href="${level1MenuPath}/index";
}

var moveLevel2MenuPath = function() {
	window.location.href="${level2MenuPath}";
}
</script>


<!-- CONTENT -->
<nav class="navbar navbar-default">
	<div class="container-fluid">
		<div class="navbar-header">
			<!--
			<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
				<span class="sr-only">Toggle navigation</span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>
			-->
			
			<a class="navbar-brand" href="/home/main">
				<!-- <img src="#" /> -->
			</a>
		</div>
		<div id="navbar" class="navbar-collapse collapse">
			<ul class="nav navbar-nav">
				<c:forEach items="${level1MenuList}" var="level1Menu" varStatus="status">
					<li <c:if test="${level1Menu.isActive eq 'Y' }">class='active'</c:if> style="margin:0px !important;">
						<a href="${level1Menu.menuPath}">
							${level1Menu.menuNm}
						</a>
					</li>
				</c:forEach>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li><a href="/login/logout"><span class="glyphicon glyphicon-log-out"></span> ${member.userNm}&nbsp;</a></li>	
			</ul>
		</div>
	</div>
</nav>