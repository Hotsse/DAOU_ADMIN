<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" session="false" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<html>
<head>
<!-- #head -->
<tiles:insertAttribute name="head" />
<!--// #head -->
</head>
<body>
	<!-- #wrapper -->
	<div id="wrapper">
		<!-- #container -->
		<div id="container">
			<div class="page-content">
				<div class="row"></div>
				<div class="row">
					<div class="col-sm-12">
						<!-- #content -->
						<div id="content">
							<tiles:insertAttribute name="body" />
						</div>
						<!-- // #content -->
					</div>
				</div>
			</div>
		</div>
		<!--// #container -->
	</div>
	<!--// #wrapper -->
</body>
</html>