<%@page import="client.classes.Modelo.TipoDeDatos"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>
<c:set var="dataValues" value="<%=TipoDeDatos.values()%>"/>

<form:form action="confview6" id="cusform" method="post" modelAttribute="xfactor">
    <div class="card">
        <div class="card-content list no-hairlines-md">
            <ul id="outerdiv">
                <li class="item-content item-input">
                    <div class="item-inner">
                        <div class="item-title item-label">${namefac}</div>
                        <div class="item-input-wrap">
                            <form:input type="text" placeholder="${unnamedfac}" path="name" value="${xfactor.name}"/>
                            <span class="input-clear-button"></span>
                        </div>
                    </div>
                </li>
                <li class="item-content item-input">
                    <div class="item-inner">
                        <div class="item-title item-label">${typefac}</div>
                        <div class="item-input-wrap centered-input">
                            <div class="segmented">
                                <c:forEach items="${dataValues}" var="xdata">
                                    <a class="button sm-opc2 btn-responsive <c:if test="${xdata == xfactor.dataType}">button-active</c:if>" style="width: auto" realval="${xdata}">
                                        <c:if test="${xdata == 'BOOLEAN'}">${BTYPE}</c:if>
                                        <c:if test="${xdata == 'PERCENTAGE'}">${PTYPE}</c:if>
                                        <c:if test="${xdata == 'OPTION'}">${OTYPE}</c:if>
                                        <c:if test="${xdata == 'NUMBER'}">${NTYPE}</c:if>
                                        </a>
                                </c:forEach>
                            </div>
                            <form:input type="hidden" class="sm-opcv" path="dataType" value="${xfactor.dataType}"/>
                        </div>
                    </div>
                </li>
                <c:forEach items="${xfactor.valores}" var="xval" varStatus="xv">
                    <c:if test="${xfactor.dataType != 'OPTION'}">
                        <li class="item-input swipeout sm-opcd disabled" name="${xv.index}" style="display: none" id="rm_${xv.index}">
                        </c:if>
                        <c:if test="${xfactor.dataType == 'OPTION'}">
                        <li class="item-input swipeout sm-opcd" name="${xv.index}" id="rm_${xv.index}">
                        </c:if>
                        <div class="item-content swipeout-content">
                            <div class="item-media">
                                <a class="swout" name="rm_${xv.index}">
                                    <i class="icon f7-icons ios-only color-gray">more_round_fill</i>
                                    <i class="icon material-icons md-only color-gray">more_horiz</i>
                                </a>
                            </div>
                            <div class="item-inner">
                                <div class="item-title item-label">${optnum} ${xv.index + 1}</div>
                                <div class="item-input-wrap rm-xval">
                                    <c:if test="${xval != ''}">${xval} (${xfactor.scores[xv.index]})</c:if>
                                    <c:if test="${xval == ''}">${unnamedopt} (${xfactor.scores[xv.index]})</c:if>
                                    </div>
                                </div>
                            </div>
                            <div class="swipeout-actions-right">
                                <a data-confirm="${revalueopt}" class="color-blue swipeout-revalue">${revalue}</a>
                            <a data-confirm="${renameopt}" class="color-orange swipeout-rename3">${rename}</a>
                            <a data-confirm="${deleteopt}" class="swipeout-delete">${delete}</a>
                        </div>
                        <form:input type="hidden" class="rm-input" path="valores[${xv.index}]" value="${xval}"/>
                        <c:if test="${xfactor.dataType == 'OPTION'}">
                            <form:input type="hidden" class="rm-input2" path="scores[${xv.index}]" value="${xfactor.scores[xv.index]}"/>
                        </c:if>
                    </li>
                </c:forEach>
                <c:if test="${xfactor.dataType != 'BOOLEAN'}">
                    <li class="item-input swipeout sm-opcd2 disabled" name="0" style="display: none" id="rm_no">
                    </c:if>
                    <c:if test="${xfactor.dataType == 'BOOLEAN'}">
                    <li class="item-input swipeout sm-opcd2" name="0" id="rm_no">
                    </c:if>
                    <div class="item-content swipeout-content">
                        <div class="item-media">
                            <a class="swout" name="rm_no">
                                <i class="icon f7-icons ios-only color-gray">more_round_fill</i>
                                <i class="icon material-icons md-only color-gray">more_horiz</i>
                            </a>
                        </div>
                        <div class="item-inner">
                            <div class="item-title item-label">${optnum} 1</div>
                            <div class="item-input-wrap">${no} (${xfactor.scores[0]})</div>
                        </div>
                    </div>
                    <div class="swipeout-actions-right">
                        <a data-confirm="${revalueopt}" class="color-blue swipeout-revalue">${revalue}</a>
                    </div>
                    <c:if test="${xfactor.dataType == 'BOOLEAN'}">
                        <form:input type="hidden" class="rm-input2" path="scores[0]" value="${xfactor.scores[0]}"/>
                    </c:if>
                </li>
                <c:if test="${xfactor.dataType != 'BOOLEAN'}">
                    <li class="item-input swipeout sm-opcd2 disabled" name="1" style="display: none" id="rm_yes">
                    </c:if>
                    <c:if test="${xfactor.dataType == 'BOOLEAN'}">
                    <li class="item-input swipeout sm-opcd2" name="1" id="rm_yes">
                    </c:if>
                    <div class="item-content swipeout-content">
                        <div class="item-media">
                            <a class="swout" name="rm_yes">
                                <i class="icon f7-icons ios-only color-gray">more_round_fill</i>
                                <i class="icon material-icons md-only color-gray">more_horiz</i>
                            </a>
                        </div>
                        <div class="item-inner">
                            <div class="item-title item-label">${optnum} 2</div>
                            <div class="item-input-wrap">${yes} (${xfactor.scores[1]})</div>
                        </div>
                    </div>
                    <div class="swipeout-actions-right">
                        <a data-confirm="${revalueopt}" class="color-blue swipeout-revalue">${revalue}</a>
                    </div>
                    <c:if test="${xfactor.dataType == 'BOOLEAN'}">
                        <form:input type="hidden" class="rm-input2" path="scores[1]" value="${xfactor.scores[1]}"/>
                    </c:if>
                </li>
                <c:if test="${xfactor.dataType != 'PERCENTAGE' && xfactor.dataType != 'NUMBER'}">
                    <li class="item-input swipeout sm-opcd3 disabled" name="0" style="display: none" id="rm_min">
                    </c:if>
                    <c:if test="${xfactor.dataType == 'PERCENTAGE' || xfactor.dataType == 'NUMBER'}">
                    <li class="item-input swipeout sm-opcd3" name="0" id="rm_min">
                    </c:if>
                    <div class="item-content swipeout-content">
                        <div class="item-media">
                            <a class="swout" name="rm_min">
                                <i class="icon f7-icons ios-only color-gray">more_round_fill</i>
                                <i class="icon material-icons md-only color-gray">more_horiz</i>
                            </a>
                        </div>
                        <div class="item-inner">
                            <div class="item-title item-label">${optnum} 1</div>
                            <div class="item-input-wrap">${min} (${xfactor.scores[0]})</div>
                        </div>
                    </div>
                    <div class="swipeout-actions-right">
                        <a data-confirm="${revalueopt}" class="color-blue swipeout-revalue">${revalue}</a>
                    </div>
                    <c:if test="${xfactor.dataType == 'PERCENTAGE' || xfactor.dataType == 'NUMBER'}">
                        <form:input type="hidden" class="rm-input2" path="scores[0]" value="${xfactor.scores[0]}"/>
                    </c:if>
                </li>
                <c:if test="${xfactor.dataType != 'PERCENTAGE' && xfactor.dataType != 'NUMBER'}">
                    <li class="item-input swipeout sm-opcd3 disabled" name="1" style="display: none" id="rm_max">
                    </c:if>
                    <c:if test="${xfactor.dataType == 'PERCENTAGE' || xfactor.dataType == 'NUMBER'}">
                    <li class="item-input swipeout sm-opcd3" name="1" id="rm_max">
                    </c:if>
                    <div class="item-content swipeout-content">
                        <div class="item-media">
                            <a class="swout" name="rm_max">
                                <i class="icon f7-icons ios-only color-gray">more_round_fill</i>
                                <i class="icon material-icons md-only color-gray">more_horiz</i>
                            </a>
                        </div>
                        <div class="item-inner">
                            <div class="item-title item-label">${optnum} 2</div>
                            <div class="item-input-wrap">${max} (${xfactor.scores[1]})</div>
                        </div>
                    </div>
                    <div class="swipeout-actions-right">
                        <a data-confirm="${revalueopt}" class="color-blue swipeout-revalue">${revalue}</a>
                    </div>
                    <c:if test="${xfactor.dataType == 'PERCENTAGE' || xfactor.dataType == 'NUMBER'}">
                        <form:input type="hidden" class="rm-input2" path="scores[1]" value="${xfactor.scores[1]}"/>
                    </c:if>
                    <c:if test="${xfactor.dataType != 'PERCENTAGE' && xfactor.dataType != 'NUMBER'}">
                    <li class="item-input swipeout sm-opcd3 disabled" name="2" style="display: none" id="rm_step">
                    </c:if>
                    <c:if test="${xfactor.dataType == 'PERCENTAGE' || xfactor.dataType == 'NUMBER'}">
                    <li class="item-input swipeout sm-opcd3" name="2" id="rm_step">
                    </c:if>
                    <div class="item-content swipeout-content">
                        <div class="item-media">
                            <a class="swout" name="rm_step">
                                <i class="icon f7-icons ios-only color-gray">more_round_fill</i>
                                <i class="icon material-icons md-only color-gray">more_horiz</i>
                            </a>
                        </div>
                        <div class="item-inner">
                            <div class="item-title item-label">${optnum} 3</div>
                            <div class="item-input-wrap">${step} (${xfactor.scores[2]})</div>
                        </div>
                    </div>
                    <div class="swipeout-actions-right">
                        <a data-confirm="${revalueopt}" class="color-blue swipeout-revalue">${revalue}</a>
                    </div>
                    <c:if test="${xfactor.dataType == 'PERCENTAGE' || xfactor.dataType == 'NUMBER'}">
                        <form:input type="hidden" class="rm-input2" path="scores[2]" value="${xfactor.scores[2]}"/>
                    </c:if>
                </li>
            </ul>
        </div>
        <div class="card-footer"><input type="button" id="addform" class="link btn-link sm-opcd maintain addopt <c:if test="${xfactor.dataType != 'OPTION'}">disabled</c:if>" value="${newopt}"/><input type="submit" id="saveed" name="saveed" class="link btn-link" value="${savefac}"/></div>
        </div>
</form:form>