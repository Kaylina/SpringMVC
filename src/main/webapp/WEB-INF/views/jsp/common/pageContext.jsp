<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%
        String contextPath = request.getContextPath();
        pageContext.setAttribute("path", contextPath);
        
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
		pageContext.setAttribute("basePath", basePath);
    %>

