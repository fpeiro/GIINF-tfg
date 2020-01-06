<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>

<form:form action="evalview2" id="cusform" method="post" modelAttribute="eval">
    <c:if test="${fn:length(eval.factors) > 0}">
        <c:forEach items="${eval.factors}" var="map" varStatus="m">
            <div class="outer list tablet-inset listarev" name="${m.index}" <c:if test="${m.index != 0}">style="display:none"</c:if>>
                    <ul>
                    <c:forEach items="${map.value}" var="prin" varStatus="p">
                        <li>
                            <div class="item-content">
                                <div class="item-inner">
                                    <div class="item-title">
                                        <c:if test="${prin.key != ''}">${prin.key}</c:if>
                                        <c:if test="${prin.key == ''}">${unnamedfac}</c:if>
                                        </div>
                                    <c:if test="${prin.value.getClass().simpleName == 'Boolean'}">
                                        <label class="toggle">
                                            <input type="checkbox" data-group="${prin.key}" <c:if test="${prin.value == true}">checked</c:if>/>
                                                <span class="toggle-icon"></span>
                                            <form:checkbox path="factors['${map.key}']['${prin.key}']" data-group="${prin.key}"/>
                                        </label>
                                    </c:if>
                                    <c:if test="${prin.value.getClass().simpleName == 'Double'}">
                                        <label id="outerNode" class="toggle range-knob-active-state"  style="width: auto; top: 5px">
                                            <form:input type="range" class="slider" path="factors['${map.key}']['${prin.key}']" value="${prin.value}" min="${eModel.categorias[m.index].factores[p.index].scores[0]}" max="${eModel.categorias[m.index].factores[p.index].scores[1]}" step="${eModel.categorias[m.index].factores[p.index].scores[2]}"/>
                                            <div class="range-knob-label knobslider" style="display: none"></div>
                                        </label>
                                    </c:if>
                                    <c:if test="${prin.value.getClass().simpleName == 'SimpleEntry'}">
                                        <div class="segmented">
                                            <c:forEach items="${prin.value.key}" var="opc" varStatus="o">
                                                <a class="button sm-opc <c:if test="${prin.value.value == opc}">button-active</c:if>" style="width: auto">
                                                    <c:if test="${opc != ''}">${opc}</c:if>
                                                    <c:if test="${opc == ''}">${unnamedopt}</c:if>
                                                    </a>
                                            </c:forEach>
                                        </div>
                                        <form:input type="hidden" class="sm-opcv" path="factors['${map.key}']['${prin.key}']"/>
                                    </c:if>
                                    <c:if test="${prin.value.getClass().simpleName == 'Integer'}">
                                        <div class="segmented">
                                            <a class="button sm-minus button-active" style="width: auto">-</a>
                                            <a class="button sm-value" style="width: auto">${prin.value}</a>
                                            <a class="button sm-plus button-active" style="width: auto">+</a>
                                        </div>
                                        <form:input type="hidden" class="sm-stepv" path="factors['${map.key}']['${prin.key}']" data-min="${eModel.categorias[m.index].factores[p.index].scores[0]}" data-max="${eModel.categorias[m.index].factores[p.index].scores[1]}" data-step="${eModel.categorias[m.index].factores[p.index].scores[2]}"/>
                                    </c:if>
                                </div>
                            </div>
                        </li>
                    </c:forEach>
                </ul>
            </div>
        </c:forEach>
    </c:if>
    <c:if test="${fn:length(eval.factors) == 0}">
        <div class="login-screen-title middle">${emptyppl}</div>
    </c:if>
</form:form>