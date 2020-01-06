<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<form action="confview7" id="cusform" method="post">
    <c:if test="${fn:length(usuarios) > 0}">
        <div class="list tablet-inset">
            <ul>
                <c:forEach items="${usuarios}" var="item" varStatus="i">
                    <li class="swipeout" id="rm_${i.index}">
                        <div class="item-content swipeout-content">
                            <div class="item-media">
                                <a class="swout" name="rm_${i.index}">
                                    <i class="icon f7-icons ios-only color-gray">more_round_fill</i>
                                    <i class="icon material-icons md-only color-gray">more_horiz</i>
                                </a>
                            </div>
                            <div class="item-inner">
                                ${item.nick}
                            </div>
                        </div>
                        <div class="swipeout-actions-right">
                            <a class="color-orange swipeout-alert" data-alert="${codemsg}" name="${item.codigo}">${obtncode}</a>
                            <c:if test="${item.disabled == false}">
                                <a class="color-red swipeout-disable" data-alert="${dismsg}" dialog-data="${disuser}" name="${i.index}">${disable}</a>
                            </c:if>
                            <c:if test="${item.disabled == true}">
                                <a class="color-red swipeout-disable" data-alert="${enmsg}" dialog-data="${enuser}" name="${i.index}">${enable}</a>
                            </c:if>
                        </div>
                    </li>
                </c:forEach>
            </ul>
        </div>
    </c:if>
    <c:if test="${fn:length(usuarios) == 0}">
        <div class="login-screen-title middle">${emptyus}</div>
    </c:if>
</form>