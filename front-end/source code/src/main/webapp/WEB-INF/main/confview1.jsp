<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div class="list tablet-inset">
    <ul>
        <li>
            <a class="item-link sm-lang">
                <div class="item-content">
                    <div class="item-inner">
                        <div class="item-title">${language}</div>
                        <div class="item-after sm-chosen">
                            <c:if test="${locale == 'en'}">${english}</c:if>
                            <c:if test="${locale != 'en'}">${spanish}</c:if>
                            </div>
                        </div>
                    </div>
                </a>
            </li>
        </ul>
    </div>
    <div class="list tablet-inset">
        <ul>
            <li>
                <a class="item-link external sm-model">
                    <div class="item-content">
                        <div class="item-inner">
                            <div class="item-title">${curmodel}</div>
                        <div class="item-after">
                            <c:if test="${fn:length(modelos) == 0}">
                                ${any}
                            </c:if>
                            <c:if test="${fn:length(modelos) != 0 and eModel.name != ''}">
                                ${eModel.name}
                            </c:if>
                            <c:if test="${fn:length(modelos) != 0 and eModel.name == ''}">
                                ${unnamedmod}
                            </c:if>
                        </div>
                    </div>
                </div>
            </a>
        </li>
    </ul>
</div>
<c:if test="${usuario.admin == true}">
    <div class="list tablet-inset">
        <ul>
            <li>
                <a href="confview7" class="item-link external">
                    <div class="item-content">
                        <div class="item-inner">
                            ${userman}
                        </div>
                    </div>
                </a>
            </li>
        </ul>
    </div>
</c:if>
<form action="confview1" id="cusform" method="post">
    <div class="sheet-modal smart-select-sheet modal-in" id="changemodel" style="display: none">
        <div class="toolbar toolbar-inner">
            <div class="left">
                <c:if test="${fn:length(modelos) != 0}">
                    <a href="confview2" class="link external">${edit}</a>
                </c:if>
            </div>
            <div class="right">
                <a class="link sm-model">${done}</a>
            </div>
        </div>
        <c:if test="${fn:length(modelos) == 0}">
            <div class="login-screen-title little-size middle">${emptymod}</div>
        </c:if>
        <c:if test="${fn:length(modelos) != 0}">
            <div class="sheet-modal-inner list">
                <ul>
                    <c:forEach items="${modelos}" var="item" varStatus="i">
                        <li>
                            <label class="item-radio item-content">
                                <input type="radio" name="curmodel" <c:if test="${item.value.activo == true}">checked</c:if>/>
                                    <i class="icon-radio"></i>
                                    <div class="item-inner sm-submit" name="${i.index}">
                                    <c:if test="${item.value.name != ''}">
                                        ${item.value.name}
                                    </c:if>
                                    <c:if test="${item.value.name == ''}">
                                        ${unnamedmod}
                                    </c:if>
                                </div>
                            </label>
                        </li>
                    </c:forEach>
                </ul>
            </div>
        </c:if>
    </div>
</form>
<div class="sheet-modal smart-select-sheet modal-in" id="changelang" style="display: none">
    <div class="toolbar toolbar-inner">
        <div class="left"></div>
        <div class="right">
            <a class="link sm-lang">${done}</a>
        </div>
    </div>
    <div class="sheet-modal-inner list">
        <ul>
            <li>
                <label class="item-radio item-content">
                    <input type="radio" name="curlang" <c:if test="${locale != 'en'}">checked</c:if>>
                        <i class="icon-radio"></i>
                        <div send-data="${spanishcode}" class="item-inner sm-langv">${spanish}</div>
                </label>
            </li>
            <li>
                <label class="item-radio item-content">
                    <input type="radio" name="curlang" <c:if test="${locale == 'en'}">checked</c:if>>
                        <i class="icon-radio"></i>
                        <div send-data="${englishcode}" class="item-inner sm-langv">${english}</div>
                </label>
            </li>
        </ul>
    </div>
</div>
<!--<div class="login-screen-title middle">${nosettings}</div>-->