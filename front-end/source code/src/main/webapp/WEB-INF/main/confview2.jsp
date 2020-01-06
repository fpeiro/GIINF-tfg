<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<form action="confview2" id="cusform" method="post">
    <div class="card">
        <div class="card-content list no-hairlines-md">
            <ul>
                <li class="item-content item-input">
                    <div class="item-inner">
                        <div class="item-title item-label">${namemod}</div>
                        <div class="item-input-wrap">
                            <input type="text" name="newname" placeholder="${unnamedmod}" value="${eModel.name}"/>
                            <span class="input-clear-button"></span>
                        </div>
                    </div>
                </li>
            </ul>
        </div>
        <div class="card-footer"><a name="delete" dialog-data="${deletemod}" dialog-callback="${moddeleted}" class="link btn-link">${delmod}</a><input type="submit" name="chname" class="link btn-link" value="${savename}"/></div>
    </div>
    <div class="card">
        <div class="card-content list no-hairlines-md">
            <ul>
                <li>
                    <a href="confview8" class="item-link external">
                        <div class="item-content">
                            <div class="item-inner">
                                <div class="item-title">${sharewith}</div>
                                <div class="item-after">
                                    <c:if test="${fn:length(eModel.usuarios) == 0}">
                                        ${onlyyou}
                                    </c:if>
                                    <c:if test="${fn:length(eModel.usuarios) == 1}">
                                        ${fn:length(eModel.usuarios)} ${person}
                                    </c:if>
                                    <c:if test="${fn:length(eModel.usuarios) > 1}">
                                        ${fn:length(eModel.usuarios)} ${people}
                                    </c:if>
                                </div>
                            </div>
                        </div>
                    </a>
                </li>
            </ul>
        </div>
    </div>
    <div class="card">
        <div class="card-content list no-hairlines-md">
            <ul>
                <li>
                    <a href="confview3" class="item-link external">
                        <div class="item-content">
                            <div class="item-inner">
                                <div class="item-title">${operation}</div>
                                <div class="item-after">${edit}</div>
                            </div>
                        </div>
                    </a>
                </li>
            </ul>
        </div>
    </div>
</form>