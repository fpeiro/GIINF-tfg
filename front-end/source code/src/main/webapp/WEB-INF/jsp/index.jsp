<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <meta charset="UTF-8">
    <head>
        <jsp:include page="/WEB-INF/layout/frheader.jsp"/>
    </head>
    <body class="color-theme-gray">
        <div id="app">
            <div class="panel panel-left panel-reveal page">
                <div class="navbar">
                    <jsp:include page="/WEB-INF/layout/nav.jsp"/>
                </div>
                <div class="page-content">
                    <jsp:include page="/WEB-INF/aside/${view}.jsp"/>
                </div>
            </div>
            <div class="view views tabs ios-edges">
                <jsp:include page="/WEB-INF/layout/footer.jsp"/>
                <div id="view-home" class="view view-main tab tab-active">
                    <div class="navbar bg-color-aquamarine text-color-white">
                        <jsp:include page="/WEB-INF/layout/header.jsp"/>
                    </div>
                    <div id="main-page" class="page page-content">
                        <jsp:include page="/WEB-INF/main/${view}.jsp"/>
                    </div>
                </div>
                <div id="view-settings" class="view tab"></div>
            </div>
        </div>
        <jsp:include page="/WEB-INF/layout/frfooter.jsp"/>
    </body>
</html>