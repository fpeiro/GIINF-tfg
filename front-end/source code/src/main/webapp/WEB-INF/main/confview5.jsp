<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<form action="confview5" id="cusform" method="post">
    <div class="list tablet-inset">
        <ul>
            <c:set var="counter" value="0"/>
            <c:forEach items="${eModel.getCategorias()}" var="cat" varStatus="c">
                <c:if test="${fn:length(cat.factores) > 0}">
                    <c:set var="condition" value="false"/>
                    <c:forEach items="${cat.factores}" var="item" varStatus="i">
                        <li class="swipeout deleted-callback covert" dialog-data="${facdeleted}" cat-id="${c.index}" fac-id="${i.index}" name="${cat.name}" id="rm_${counter}" style="display: none">
                            <div class="item-content swipeout-content">
                                <div class="item-media">
                                    <a class="swout" name="rm_${counter}">
                                        <i class="icon f7-icons ios-only color-gray">more_round_fill</i>
                                        <i class="icon material-icons md-only color-gray">more_horiz</i>
                                    </a>
                                </div>
                                <div class="item-inner">
                                    <c:if test="${item.name != ''}">${item.name}</c:if>
                                    <c:if test="${item.name == ''}">${unnamedfac}</c:if>
                                    </div>
                                </div>
                                <div class="swipeout-actions-right">
                                    <a cat-id="${c.index}" fac-id="${i.index}" class="color-orange swipeout-edit">${edit}</a>
                                <a data-confirm="${renamefac}" cat-id="${c.index}" fac-id="${i.index}" class="swipeout-rename" style="display: none"></a>
                                <a data-confirm="${deletefac}" class="swipeout-delete">${delete}</a>
                            </div>
                        </li>
                        <c:set var="counter" value="${counter+1}"/>
                    </c:forEach>
                </c:if>
                <c:if test="${fn:length(cat.factores) == 0}">
                    <%--<c:set var="condition" value="true"/>--%>
                </c:if>
            </c:forEach>
        </ul>
        <input type="hidden" name="addInput"/>
    </div>
    <c:if test="${condition == 'true'}">
        <div class="login-screen-title middle">${addfac}</div>
    </c:if>
    <div class="fab fab-center-bottom">
        <button id="addform" class="btn-circle">
            <i class="icon f7-icons ios-only color-white">add</i>
            <i class="icon material-icons md-only color-white">add</i>
        </button>
    </div>
</form>