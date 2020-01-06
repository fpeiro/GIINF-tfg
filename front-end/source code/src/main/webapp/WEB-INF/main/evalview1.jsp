<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>

<form:form action="evalview1" id="cusform" method="post" modelAttribute="eModel">
    <c:if test="${empty eModel.actual}">
        <c:if test="${fn:length(eModel.evaluaciones) > 0}">
            <div class="login-screen-title middle">${chooseev}</div>
        </c:if>
        <c:if test="${fn:length(eModel.evaluaciones) == 0}">
            <div class="login-screen-title middle">${createev}</div>
        </c:if>
    </c:if>
    <c:if test="${not empty eModel.actual}">
        <div class="card">
            <div class="card-content list no-hairlines-md">
                <ul>
                    <li class="item-content item-input">
                        <div class="item-inner">
                            <div class="item-title item-label">${nameev}</div>
                            <div class="item-input-wrap">
                                <form:input type="text" placeholder="${unnamedev}" path="selectName" value="${eModel.selectName}"/>
                                <span class="input-clear-button"></span>
                            </div>
                        </div>
                    </li>
                </ul>
            </div>
            <div class="card-footer"><a name="delete" dialog-data="${deleteev}" dialog-callback="${evdeleted}" class="link btn-link">${delev}</a><input type="submit" name="chname" class="link btn-link" value="${savename}"/></div>
        </div>
        <div class="card">
            <div class="row no-gap">
                <div id='myChart' class="col-100 tablet-50"></div>
                <div class="col-100 tablet-50">
                    <div class="row no-gap">
                        <c:if test="${fn:length(eModel.actual.getResParciales()) > 0}">
                            <c:forEach items="${eModel.actual.getResParciales()}" var="item" varStatus="i">
                                <div class="col">
                                    <p class="alignres">
                                        ${eModel.getCategorias().get(i.index).getName()}
                                        <br/>
                                        <font class="login-screen-title">${item}</font>
                                    </p>
                                </div>
                            </c:forEach>
                        </c:if>
                        <c:if test="${fn:length(eModel.actual.getResParciales()) == 0}">
                            <div class="col">
                                <p class="alignres">
                                    ${emptyres}
                                </p>
                            </div>
                        </c:if>
                    </div>
                    <div class="row no-gap">
                        <div class="col">
                            <p class="alignres">
                                ${score}
                                <br/>
                                <font class="login-screen-title">
                                <c:if test="${not empty eModel.actual.getPuntuacion()}">
                                    ${eModel.actual.getPuntuacion()}
                                </c:if>
                                <c:if test="${empty eModel.actual.getPuntuacion()}">
                                    ${emptyscore}
                                </c:if>
                                </font>
                            </p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </c:if>
    <form:input id="selevId" type="hidden" path="selectEv"/>
</form:form>