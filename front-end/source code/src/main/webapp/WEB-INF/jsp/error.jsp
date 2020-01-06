<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="/WEB-INF/layout/frheader.jsp"/>
    </head>
    <body class="color-theme-gray">
        <div id="app" class="login-screen-content bg-stat-image">
            <div class="login-screen-title">${appname}</div>
            <div class="list">
                <div class="block-footer">
                    <c:if test="${requestScope['javax.servlet.forward.query_string'] == 'error=lostcon'}">
                        <p class="little-size">${notfound}</p>
                        <p>${lostcon}</p>
                    </c:if>
                    <c:if test="${requestScope['javax.servlet.forward.query_string'] == 'error=inveval'}">
                        <p class="little-size">${ietitle}</p>
                        <p>${inveval}</p>
                    </c:if>
                    <c:if test="${requestScope['javax.servlet.forward.query_string'] == 'error=unknown'}">
                        <p class="little-size">${untitle}</p>
                        <p>${unmsg}</p>
                    </c:if>
                    <p><a href="${appbase}" class="external">${goback}</a></p>
                </div>
            </div>
        </div>
        <jsp:include page="/WEB-INF/layout/frfooter.jsp"/>
    </body>
</html>