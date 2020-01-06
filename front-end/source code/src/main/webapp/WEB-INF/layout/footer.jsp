<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div class="toolbar tabbar-labels toolbar-bottom-md">
    <div dialog-data="${safeexit}" class="toolbar-inner">
        <c:if test="${not fn:startsWith(view, 'confview')}">
            <a href="evalview1" class="tab-link tab-link-active external prev-load">
                <i class="icon f7-icons ios-only">home</i>
                <i class="icon f7-icons ios-only icon-ios-fill">home_fill</i>
                <i class="icon material-icons md-only">home</i>
                <span class="tabbar-label">${tab1}</span>
            </a>
            <a href="confview1" class="tab-link external prev-load">
                <i class="icon f7-icons ios-only">settings</i>
                <i class="icon f7-icons ios-only icon-ios-fill">settings_fill</i>
                <i class="icon material-icons md-only">settings</i>
                <span class="tabbar-label">${tab2}</span>
            </a>
        </c:if>
        <c:if test="${fn:startsWith(view, 'confview')}">
            <a href="evalview1" class="tab-link external prev-load">
                <i class="icon f7-icons ios-only">home</i>
                <i class="icon f7-icons ios-only icon-ios-fill">home_fill</i>
                <i class="icon material-icons md-only">home</i>
                <span class="tabbar-label">${tab1}</span>
            </a>
            <a href="confview1" class="tab-link tab-link-active external prev-load">
                <i class="icon f7-icons ios-only">settings</i>
                <i class="icon f7-icons ios-only icon-ios-fill">settings_fill</i>
                <i class="icon material-icons md-only">settings</i>
                <span class="tabbar-label">${tab2}</span>
            </a>
        </c:if>
    </div>
</div>