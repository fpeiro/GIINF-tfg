<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="/WEB-INF/layout/frheader.jsp"/>
    </head>
    <body class="color-theme-gray">
        <div id="app" class="login-screen-content bg-stat-image">
            <div class="login-screen-title">${appname}</div>
            <form:form action="${view}" method="post" modelAttribute="udto">
                <c:if test="${requestScope['javax.servlet.forward.query_string'] == 'error=notmatch'}">
                    <div class="list">
                        <div class="block-footer text-color-red">
                            ${notmatch}
                        </div>
                    </div>
                </c:if>
                <c:if test="${requestScope['javax.servlet.forward.query_string'] == 'error=notav'}">
                    <div class="list">
                        <div class="block-footer text-color-red">
                            ${notav}
                        </div>
                    </div>
                </c:if>
                <c:if test="${requestScope['javax.servlet.forward.query_string'] == 'error=notmatch2'}">
                    <div class="list">
                        <div class="block-footer text-color-red">
                            ${notmatch2}
                        </div>
                    </div>
                </c:if>
                <c:if test="${requestScope['javax.servlet.forward.query_string'] == 'error=banned'}">
                    <div class="list">
                        <div class="block-footer text-color-red">
                            ${banned}
                        </div>
                    </div>
                </c:if>
                <c:if test="${view == 'recover'}">
                    <div class="list">
                        <div class="block-footer">
                            ${recovermsg}
                        </div>
                    </div>
                </c:if>
                <div class="list">
                    <ul>
                        <li class="item-content item-input">
                            <div class="item-inner">
                                <div class="item-label">${usertitle}</div>
                                <div class="item-input-wrap">
                                    <form:input type="text" path="nick" placeholder="${userinput}"/>
                                    <span class="input-clear-button"></span>
                                </div>
                            </div>
                        </li>
                        <c:if test="${view == 'recover'}">
                            <li class="item-content item-input">
                                <div class="item-inner">
                                    <div class="item-title item-label">${codetitle}</div>
                                    <div class="item-input-wrap">
                                        <form:input type="text" path="codigo" placeholder="${codeinput}"/>
                                    </div>
                                </div>
                            </li>
                        </c:if>
                        <li class="item-content item-input">
                            <div class="item-inner">
                                <div class="item-title item-label">
                                    <c:if test="${view != 'recover'}">
                                        ${passtitle}
                                    </c:if>
                                    <c:if test="${view == 'recover'}">
                                        ${npasstitle}
                                    </c:if>
                                </div>
                                <div class="item-input-wrap">
                                    <c:if test="${view != 'recover'}">
                                        <form:input type="password" path="password" placeholder="${passinput}"/>
                                    </c:if>
                                    <c:if test="${view == 'recover'}">
                                        <form:input type="password" path="password" placeholder="${npassinput}"/>
                                    </c:if>
                                </div>
                            </div>
                        </li>
                    </ul>
                </div>
                <div class="list">
                    <ul>
                        <li><button type="submit" class="btn-link list-button">
                                <c:if test="${view == 'signin'}">
                                    ${signin}
                                </c:if>
                                <c:if test="${view == 'login'}">
                                    ${login}
                                </c:if>
                                <c:if test="${view == 'recover'}">
                                    ${passchg}
                                </c:if>
                            </button></li>
                    </ul>
                    <div class="block-footer">
                        <p>
                            <c:if test="${view == 'signin'}">
                                <a href="login" class="external">${alreadyreg}</a>
                            </c:if>
                            <c:if test="${view == 'login'}">
                                <a href="recover" class="external">${forgpass}</a> | <a href="signin" class="external">${needacc}</a>
                            </c:if>
                            <c:if test="${view == 'recover'}">
                                <a href="signin" class="external">${needacc}</a>
                            </c:if>
                        </p>
                        <!--<p><a href="index" class="external">${closesignin}</a></p>-->
                    </div>
                </div>
            </form:form>
        </div>
        <jsp:include page="/WEB-INF/layout/frfooter.jsp"/>
    </body>
</html>