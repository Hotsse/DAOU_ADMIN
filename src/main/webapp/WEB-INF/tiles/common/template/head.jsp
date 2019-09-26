<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" session="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setBundle var="commonProperties" basename="properties/common" /><!-- common.properties 객체화 -->

<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Eduwill E/P Manager</title>

<script>document.domain = "eduwill.net";</script>

<!-- Bootstrap -->
<link href="//img.eduwill.net/resources/ngene/live/vendors/bootstrap/css/bootstrap.css" rel="stylesheet">
<!-- link href="/bootstrap/bootstrap.css" rel="stylesheet" -->
<link href="//img.eduwill.net/resources/ngene/live/vendors/bootstrap-datetimepicker/css/bootstrap-datetimepicker.css" rel="stylesheet">

<!-- Datatables -->
<link href="//img.eduwill.net/resources/ngene/live/vendors/datatables/css/dataTables.bootstrap.min.css" rel="stylesheet">
<link href="//img.eduwill.net/resources/ngene/live/vendors/datatables/buttons/css/buttons.bootstrap.min.css" rel="stylesheet">

<!-- Fullcalendar -->
<link href="//img.eduwill.net/resources/ngene/live/vendors/fullcalendar/fullcalendar.min.css" rel="stylesheet">
<link href="//img.eduwill.net/resources/ngene/live/vendors/fullcalendar/fullcalendar.print.css" rel="stylesheet" media="print">

<!-- styles -->  
<link href="//img.eduwill.net/resources/daou/css/styles.css" rel="stylesheet">
<link href="//img.eduwill.net/resources/daou/css/common.css" rel="stylesheet">
<link href="//img.eduwill.net/resources/daou/css/custom.css" rel="stylesheet">

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
  <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
<![endif]-->

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script type="text/javascript" src="//img.eduwill.net/resources/ngene/live/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="//img.eduwill.net/resources/ngene/live/js/jquery-ui.js"></script>
<script type="text/javascript" src="//img.eduwill.net/resources/ngene/live/js/jquery.form.js"></script>

<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="//img.eduwill.net/resources/ngene/live/vendors/bootstrap/js/bootstrap.min.js"></script>
<!-- minor bug fixed 
<script src="/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
-->
<script src="//img.eduwill.net/resources/ngene/live/vendors/bootstrap-datetimepicker/js/bootstrap-datetimepicker.js"></script>
<script src="//img.eduwill.net/resources/ngene/live/vendors/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.ko.js"></script>

<!-- Datatables -->
<script src="//img.eduwill.net/resources/ngene/live/vendors/datatables/js/jquery.dataTables.min.js"></script>
<script src="//img.eduwill.net/resources/ngene/live/vendors/datatables/js/dataTables.bootstrap.min.js"></script>
<script src="//img.eduwill.net/resources/ngene/live/vendors/datatables/buttons/js/dataTables.buttons.min.js"></script>
<script src="//img.eduwill.net/resources/ngene/live/vendors/datatables/buttons/js/buttons.bootstrap.min.js"></script>
<script src="//img.eduwill.net/resources/ngene/live/vendors/datatables/buttons/js/buttons.flash.min.js"></script>
<script src="//img.eduwill.net/resources/ngene/live/vendors/datatables/buttons/js/buttons.html5.min.js"></script>
<script src="//img.eduwill.net/resources/ngene/live/vendors/datatables/buttons/js/buttons.print.min.js"></script>