<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="appbase" value="${pageContext.request.contextPath}"/>

<!-- Latest compiled and minified Javascript -->
<script src="${appbase}/js/framework7.min.js"></script>
<script src="${appbase}/js/jquery-3.3.1.min.js"></script>
<script src="${appbase}/js/routes.js"></script>
<script>
    var chartnames = '<c:forEach items="${eModel.categorias}" var="categ">${categ.name},</c:forEach>'
            .slice(0, -1).split(',');
    localStorage.setItem('chartnames', JSON.stringify(chartnames));
    localStorage.setItem('chartvalues', JSON.stringify(${eModel.actual.getResParciales()}));
    localStorage.setItem('cancel', '${cancel}');
</script>
<script src="${appbase}/js/app.js"></script>
<script src="${appbase}/js/zingchart.min.js"></script>
<script src="${appbase}/js/charts.js"></script>