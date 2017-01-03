<%@page pageEncoding="utf-8" isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<ul id="menu">
	<c:choose>
		<c:when test="${currentModule==0 }">
			<li><a href="${pageContext.request.contextPath }/login/index.do" class="index_on"></a></li>
		</c:when>
		<c:otherwise>
			<li><a href="${pageContext.request.contextPath }/login/index.do" class="index_off"></a></li>
		</c:otherwise>
	</c:choose>
	
	<c:forEach var="m" items="${userModules }">
	
		<c:if test="${m.module_id==1 }">
			<c:choose>
				<c:when test="${currentModule==1 }">
					<li><a href="${pageContext.request.contextPath }/role/findRole.do" class="role_on"></a></li>
				</c:when>
				<c:otherwise>
					<li><a href="${pageContext.request.contextPath }/role/findRole.do" class="role_off"></a></li>
				</c:otherwise>
			</c:choose>
		</c:if>
		
		<c:if test="${m.module_id==2 }">
			<c:choose>
				<c:when test="${currentModule==2 }">
					<li><a href="${pageContext.request.contextPath }/admin/findAdmin.do" class="admin_on"></a></li>
				</c:when>
				<c:otherwise>
					<li><a href="${pageContext.request.contextPath }/admin/findAdmin.do" class="admin_off"></a></li>
				</c:otherwise>
			</c:choose>
		</c:if>
		
		<c:if test="${m.module_id==3 }">
			<c:choose>
				<c:when test="${currentModule==3 }">
					<li><a href="${pageContext.request.contextPath }/cost/findCost.do" class="fee_on"></a></li>
				</c:when>
				<c:otherwise>
					<li><a href="${pageContext.request.contextPath }/cost/findCost.do" class="fee_off"></a></li>
				</c:otherwise>
			</c:choose>
		</c:if>
		
		<c:if test="${m.module_id==4 }">
			<c:choose>
				<c:when test="${currentModule==4 }">
					<li><a href="${pageContext.request.contextPath }/account/findAccount.do" class="account_on"></a></li>
				</c:when>
				<c:otherwise>
					<li><a href="${pageContext.request.contextPath }/account/findAccount.do" class="account_off"></a></li>
				</c:otherwise>
			</c:choose>
		</c:if>
		
		<c:if test="${m.module_id==5 }">
			<c:choose>
				<c:when test="${currentModule==5 }">
					<li><a href="${pageContext.request.contextPath }/service/findService.do" class="service_on"></a></li>
				</c:when>
				<c:otherwise>
					<li><a href="${pageContext.request.contextPath }/service/findService.do" class="service_off"></a></li>
				</c:otherwise>
			</c:choose>
		</c:if>
		
		<c:if test="${m.module_id==1 }">
			<c:choose>
				<c:when test="">
					
				</c:when>
				<c:otherwise>
					
				</c:otherwise>
			</c:choose>
		</c:if>
		
	</c:forEach>
	
    
    
    
    
    <!--  
    <li><a href="../bill/bill_list.html" class="bill_off"></a></li>
    <li><a href="../report/report_list.html" class="report_off"></a></li>
    <li><a href="../user/user_info.html" class="information_off"></a></li>
    <li><a href="../user/user_modi_pwd.html" class="password_off"></a></li>
	-->
	
</ul>  