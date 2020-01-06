<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="list media-list">
    <ul>
        <li>
            <div class="item-content">
                <div class="item-media"><img src="images/erdtqth.png" width="44"/></div>
                <div class="item-inner">
                    <div class="item-title-row">
                        <div class="item-title">
                            <c:if test="${conectado == false}">
                                ${unloguser}
                            </c:if>
                            <c:if test="${conectado == true}">
                                ${usuario.nick}
                            </c:if>
                        </div>
                    </div>
                    <div class="item-subtitle">
                        <c:if test="${usuario.admin == false}">
                            ${user}
                        </c:if>
                        <c:if test="${usuario.admin == true}">
                            ${admin}
                        </c:if>
                    </div>
                </div>
            </div>
        </li>
    </ul>
</div>

<div class="list links-list">
    <ul>
        <li><a dialog-data="${safelogout}" class="logout">${logout}</a></li>
    </ul>
</div>