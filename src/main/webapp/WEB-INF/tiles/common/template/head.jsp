<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" session="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setBundle var="commonProperties" basename="properties/common" />

<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>DAOU Admin</title>

<!-- bootstrap css -->
<link href="//img.eduwill.net/resources/ngene/live/vendors/bootstrap/css/bootstrap.css" rel="stylesheet">

<!-- custom css -->  
<link href="//img.eduwill.net/resources/daou/css/styles.css" rel="stylesheet">
<link href="//img.eduwill.net/resources/daou/css/common.css" rel="stylesheet">
<link href="//img.eduwill.net/resources/daou/css/custom.css" rel="stylesheet">

<!-- jQuery -->
<script type="text/javascript" src="//img.eduwill.net/resources/daou/js/jquery-1.8.3.min.js"></script>

<!-- bootstrap js -->
<script src="//img.eduwill.net/resources/daou/js/bootstrap.min.js"></script>

<c:if test="${redirectMsg ne null }">
<script type="text/javascript">
$(document).ready(function(){
	alert("${redirectMsg}");
});
</script>
</c:if>