<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<form action="confview4" id="cusform" method="post">
    <c:if test="${fn:length(eModel.getCategorias()) > 0}">
        <div class="list tablet-inset">
            <ul>
                <c:forEach items="${eModel.getCategorias()}" var="item" varStatus="i">
                    <c:if test="${item.name != ''}">
                        <li class="swipeout deleted-callback" dialog-data="${catdeleted}" cat-id="${i.index}" id="rm_${i.index}">
                            <div class="item-content swipeout-content">
                                <div class="item-media">
                                    <a class="swout" name="rm_${i.index}">
                                        <i class="icon f7-icons ios-only color-gray">more_round_fill</i>
                                        <i class="icon material-icons md-only color-gray">more_horiz</i>
                                    </a>
                                </div>
                                <div class="item-inner">
                                    <c:if test="${item.name != 'unnamed'}">${item.name}</c:if>
                                    <c:if test="${item.name == 'unnamed'}">${unnamedcat}</c:if>
                                    </div>
                                </div>
                                <div class="swipeout-actions-right">
                                <a class="color-orange swipeout-edit2" name="${i.index}">${edit}</a>
                                <a data-confirm="${renamecat}" cat-id="${i.index}" class="color-orange swipeout-rename" style="display: none">${rename}</a>
                                <a data-confirm="${deletecat}" class="swipeout-delete">${delete}</a>
                            </div>
                        </li>
                    </c:if>
                </c:forEach>
            </ul>
        </div>
    </c:if>
    <c:if test="${fn:length(eModel.getCategorias()) == 0}">
        <div class="login-screen-title middle">${addcat}</div>
    </c:if>
    <div class="fab fab-center-bottom">
        <button id="addform" class="btn-circle">
            <i class="icon f7-icons ios-only color-white">add</i>
            <i class="icon material-icons md-only color-white">add</i>
        </button>
    </div>
</form>