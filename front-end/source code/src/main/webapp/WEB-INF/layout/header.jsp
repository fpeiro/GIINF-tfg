<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div class="navbar-inner">
    <div class="left">
        <a href="#" class="link icon-only panel-open text-color-white" data-panel="left">
            <i class="icon f7-icons ios-only">menu</i>
            <i class="icon material-icons md-only">menu</i>
        </a>
        <c:if test="${(view != 'evalview1' or not empty eModel.actual) and not fn:startsWith(view, 'confview')}">
            <a href="#" id="toback" class="link external text-color-white">
                <c:if test="${view != 'evalview1'}">
                    <i class="icon f7-icons ios-only">chevron_left</i>
                    <i class="icon material-icons md-only">chevron_left</i>
                </c:if>
                ${button1}
            </a>
        </c:if>
        <c:if test="${view == 'confview7'}">
            <a href="#" id="toback" class="link external text-color-white">
                <c:if test="${view != 'evalview1'}">
                    <i class="icon f7-icons ios-only">chevron_left</i>
                    <i class="icon material-icons md-only">chevron_left</i>
                </c:if>
                ${button1}
            </a>
        </c:if>
        <c:if test="${fn:startsWith(view, 'confview') and view != 'confview1' and view != 'confview7'}">
            <a href="#" id="toback2" dialog-data="${safeback}" class="link external text-color-white">
                <c:if test="${view != 'evalview1'}">
                    <i class="icon f7-icons ios-only">chevron_left</i>
                    <i class="icon material-icons md-only">chevron_left</i>
                </c:if>
                ${button1}
            </a>
        </c:if>
    </div>
    <div class="title sliding centered">${title}</div>
    <div class="right">
        <c:if test="${view != 'confview7'}">
            <a href="#" id="tonext" class="btn-link link external text-color-white">${button2}</a>
        </c:if>
    </div>
</div>