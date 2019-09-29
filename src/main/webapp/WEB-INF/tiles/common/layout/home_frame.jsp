<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" session="true"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<html>
<head>
<!-- #head -->
<tiles:insertAttribute name="head" />
<!--// #head -->
</head>
<body>
	<!-- #wrapper -->
	<div id="wrapper">
		<!-- #header -->
		<div id="header">
			<tiles:insertAttribute name="gnb" />
		</div>
		<!--// #header -->
		<div class="clearfix visible-xs-block"></div>
		<!-- #container -->
		<div id="container">
			<div class="page-content">
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
		<!-- #footer -->
		<div id="footer">
		</div>
		<!--// #footer -->
	</div>
	<!--// #wrapper -->
</body>
</html>