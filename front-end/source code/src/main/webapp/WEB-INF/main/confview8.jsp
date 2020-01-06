<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>

<form:form action="confview8" id="cusform" method="post" modelAttribute="eModel">
    <c:if test="${fn:length(nicks) > 0}">
        <div class="list tablet-inset">
            <ul>
                <c:forEach items="${nicks}" var="item">
                    <c:if test="${item != usuario.nick}">
                        <li>
                            <label class="item-checkbox item-content item-ucheck">
                                <c:if test="${eModel.usuarios.contains(item)}">
                                    <form:checkbox path="usuarios" value="${item}" checked="checked"/>
                                </c:if>
                                <c:if test="${!eModel.usuarios.contains(item)}">
                                    <form:checkbox path="usuarios" value="${item}"/>
                                </c:if>
                                <i class="icon icon-checkbox"></i>
                                <div class="item-inner">
                                    <div class="item-title">${item}</div>
                                </div>
                            </label>
                        </li>
                    </c:if>
                    <c:if test="${item == usuario.nick}">
                        <li>
                            <label class="item-checkbox item-content item-ucheck disabled">
                                <input type="checkbox" checked/>
                                <i class="icon icon-checkbox"></i>
                                <div class="item-inner">
                                    <div class="item-title">${you}</div>
                                </div>
                            </label>
                        </li>
                    </c:if>
                </c:forEach>
            </ul>
        </div>
    </c:if>
    <c:if test="${fn:length(nicks) == 0}">
        <div class="login-screen-title middle">${emptyus}</div>
    </c:if>
</form:form>