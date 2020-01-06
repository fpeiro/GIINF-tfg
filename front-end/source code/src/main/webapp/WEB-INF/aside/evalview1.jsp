<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:if test="${fn:length(eModel.evaluaciones) > 0}">
    <div class="list media-list">
        <ul>
            <c:forEach var="x" begin="0" end="${fn:length(eModel.evaluaciones)}">
                <c:forEach items="${eModel.evaluaciones}" var="evalm" varStatus="i" begin="${fn:length(eModel.evaluaciones) - x}" end="${fn:length(eModel.evaluaciones) - x}">
                    <c:if test="${i.index != eModel.selectEv}">
                        <li>
                            <a class="item-link item-content setev" name="${i.index}">

                                <div class="item-inner">
                                    <div class="item-title-row">
                                        <div class="item-title">
                                            <c:if test="${evalm.value.name != ''}">
                                                ${evalm.value.name}
                                            </c:if>
                                            <c:if test="${evalm.value.name == ''}">
                                                ${unnamedev}
                                            </c:if>
                                        </div>
                                        <div class="item-after">${evalm.value.getPrettyTime(locale)}</div>
                                    </div>
                                    <div class="item-subtitle">${score}: 
                                        <c:if test="${not empty evalm.value.getPuntuacion()}">
                                            ${evalm.value.getPuntuacion()}
                                        </c:if>
                                        <c:if test="${empty evalm.value.getPuntuacion()}">
                                            ${emptyscore}
                                        </c:if>
                                    </div>
                                    <div class="item-text">
                                        <div class="row" style="font-size: 14px">
                                            <c:forEach items="${evalm.value.getResParciales()}" var="item" varStatus="i">
                                                <div class="col-50">${category} ${i.index + 1}: ${item}</div>
                                            </c:forEach>
                                        </div>
                                    </div>
                                </div>
                            </a>
                        </li>
                    </c:if>
                    <c:if test="${i.index == eModel.selectEv}">
                        <li class="bg-color-aquamarine">
                            <a class="item-link item-content setev" name="${i.index}">

                                <div class="item-inner">
                                    <div class="item-title-row">
                                        <div class="item-title text-color-white">
                                            <c:if test="${evalm.value.name != ''}">
                                                ${evalm.value.name}
                                            </c:if>
                                            <c:if test="${evalm.value.name == ''}">
                                                ${unnamedev}
                                            </c:if>
                                        </div>
                                        <div class="item-after text-color-white">${evalm.value.getPrettyTime(locale)}</div>
                                    </div>
                                    <div class="item-subtitle text-color-white">${score}: 
                                        <c:if test="${not empty evalm.value.getPuntuacion()}">
                                            ${evalm.value.getPuntuacion()}
                                        </c:if>
                                        <c:if test="${empty evalm.value.getPuntuacion()}">
                                            ${emptyscore}
                                        </c:if>
                                    </div>
                                    <div class="item-text">
                                        <div class="row text-color-white" style="font-size: 14px">
                                            <c:forEach items="${evalm.value.getResParciales()}" var="item" varStatus="i">
                                                <div class="col-50">${category} ${i.index + 1}: ${item}</div>
                                            </c:forEach>
                                        </div>
                                    </div>
                                </div>
                            </a>
                        </li>
                    </c:if>
                </c:forEach>
            </c:forEach>
        </ul>
    </div>
</c:if>
<c:if test="${fn:length(eModel.evaluaciones) == 0}">
    <div class="login-screen-title little-size middle">${emptyev}</div>
</c:if>