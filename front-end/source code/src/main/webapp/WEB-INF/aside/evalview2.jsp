<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:if test="${fn:length(eModel.getCategorias()) > 0}">
    <div class="list links-list">
        <ul>
            <c:forEach items="${eModel.getCategorias()}" var="item" varStatus="i">
                <c:if test="${item.name != ''}">
                    <li>
                        <a class="<c:if test="${i.index == eval.selectCat}">bg-color-aquamarine text-color-white</c:if> criteriorev" name="${i.index}">
                            <c:if test="${item.name != 'unnamed'}">${item.name}</c:if>
                            <c:if test="${item.name == 'unnamed'}">${unnamedcat}</c:if>
                        </a>
                    </li>
                </c:if>
            </c:forEach>
        </ul>
    </div>
</c:if>
<c:if test="${fn:length(eModel.getCategorias()) == 0}">
    <div class="login-screen-title little-size middle">${emptycat}</div>
</c:if>