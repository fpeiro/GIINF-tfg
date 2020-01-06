<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<form action="confview9" id="cusform" method="post">
    <div class="row no-gap">
        <div class="col-100 tablet-50">
            <div class="block-title">${category}</div>
            <div class="card">
                <div class="card-content list no-hairlines-md">
                    <ul id="outerdiv">
                        <li class="item-content item-input">
                            <div class="item-inner">
                                <div class="item-title item-label">${namecat}</div>
                                <div class="item-input-wrap">
                                    <input type="text" placeholder="${unnamedcat}" name="catname" value="${eModel.getCatName()}"/>
                                    <span class="input-clear-button"></span>
                                </div>
                            </div>
                        </li>
                        <li class="item-content item-input">
                            <div class="item-inner">
                                <div class="item-title item-label">${operation}</div>
                                <div id="operplace" class="item-input-wrap centered-input2">
                                    <c:if test="${fn:length(eModel.transOper()) > 0}">
                                        <c:forEach items="${eModel.transOper()}" var="oper" varStatus="op">
                                            <div class="chip">
                                                <div class="chip-label">${oper}</div><a class="chip-delete" chip-number="${op.index}"></a>
                                            </div>
                                        </c:forEach>
                                    </c:if>
                                    <c:if test="${fn:length(eModel.transOperation()) == 0}">
                                        ${emptyoper}
                                    </c:if>
                                </div>
                            </div>
                        </li>
                    </ul>
                </div>
                <input type="hidden" id="operinput"/>
                <input type="hidden" name="opvalue" id="operinputh" value="${eModel.getOper()}"/>
                <div class="card-footer"><input type="submit" id="clear-btn" class="link btn-link" value="${clop}"/><input type="submit" class="link btn-link" value="${savecat}"/></div>   
            </div>
        </div>

        <div class="col-100 tablet-50">
            <div class="block-title">${operators}</div>
            <div class="block block-strong tablet-inset">
                <div class="row no-gap">
                    <div class="col-50 tablet-50">
                        <button class="btn-circle-small">+</button>
                        <button class="btn-circle-small">-</button>
                        <button class="btn-circle-small">×</button>
                        <button class="btn-circle-small">÷</button>
                        <button class="btn-circle-small">(</button>
                        <button class="btn-circle-small">)</button>
                    </div>
                    <div class="col-50 tablet-50">
                        <div class="row no-gap">
                            <div class="col-100 tablet-100">
                                <button class="btn-circle-small">7</button>
                                <button class="btn-circle-small">8</button>
                                <button class="btn-circle-small">9</button>
                            </div>
                        </div>
                        <div class="row no-gap">
                            <div class="col-100 tablet-100">
                                <button class="btn-circle-small">4</button>
                                <button class="btn-circle-small">5</button>
                                <button class="btn-circle-small">6</button>
                            </div>
                        </div>
                        <div class="row no-gap">
                            <div class="col-100 tablet-100">
                                <button class="btn-circle-small">1</button>
                                <button class="btn-circle-small">2</button>
                                <button class="btn-circle-small">3</button>
                            </div>
                        </div>
                        <div class="row no-gap">
                            <div class="col-100 tablet-100">
                                <button class="btn-circle-small">0</button>
                                <button class="btn-circle-small">.</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="block-title">${operands}</div>
    <div class="list tablet-inset">
        <ul>
            <div class="row no-gap">
                <c:forEach items="${eModel.getFactors()}" var="item" varStatus="i">
                    <div class="col-100 tablet-33">
                        <li>
                            <a class="item-link item-content op-content">
                                <div class="item-inner">
                                    <div class="item-title op-name" op-id="fac_${eModel.selectCat}_${i.index}">
                                        ${item.name}
                                    </div>
                                    <div class="item-after">Factor</div>
                                </div>
                            </a>
                        </li>
                    </div>
                </c:forEach>
            </div>
        </ul>
    </div>
</form>