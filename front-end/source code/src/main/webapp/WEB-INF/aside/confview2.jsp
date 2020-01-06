<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div class="list links-list">
    <ul>
        <li>
            <a href="confview2" class="link external bg-color-aquamarine text-color-white">${general}</a>
        </li>
        <li>
            <a href="confview4" class="link external">${categories}</a>
        </li>
    </ul>
</div>
<c:if test="${fn:length(eModel.getCategorias()) > 0}">
    <div class="block-title">${factors}</div>
    <div class="list links-list">
        <ul>
            <c:forEach items="${eModel.getCategorias()}" var="item" varStatus="i">
                <c:if test="${item.name != ''}">
                    <li>
                        <a class="setcat2" name="${i.index}" realval="${item.name}">
                            <c:if test="${item.name != 'unnamed'}">${item.name}</c:if>
                            <c:if test="${item.name == 'unnamed'}">${unnamedcat}</c:if>
                            </a>
                        </li>
                </c:if>
            </c:forEach>
        </ul>
    </div>
</c:if>