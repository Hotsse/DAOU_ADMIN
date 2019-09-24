<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" session="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<div class="list-group">

	<c:forEach items="${level2MenuList}" var="level2Menu" varStatus="status">

		<c:choose>
			<%-- 중메뉴 --%>
			<c:when test="${level2Menu.menuPath eq ''}">
				<span class="list-group-item"><strong>${level2Menu.menuNm}</strong></span>
			</c:when>
			<%-- 소메뉴 --%>
			<c:otherwise>
				<a href="${level2Menu.menuPath}" class="list-group-item<c:if test="${level2Menu.isActive eq 'Y' }"> active</c:if>">
					${level2Menu.menuNm}
				</a>
			</c:otherwise>
		</c:choose>

	</c:forEach>

</div>