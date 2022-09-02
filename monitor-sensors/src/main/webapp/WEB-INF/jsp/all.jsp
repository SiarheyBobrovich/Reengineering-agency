<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
    <head>
        <title>View sensors</title>
        <style>
               th {
                background: #496791; /* Цвет фона ячеек заголовка */
                color: #fff; /* Цвет текста */
               }
               td {
                background: #f5e8d0; /* Цвет фона ячеек */
                text-align: center; /* Выравниваем по центру */
               }
               td, th {
                padding: 5px 10px; /* Поля в ячейках */
                vertical-align: top; /* Выравниваем по верхнему краю */
               }
        </style>
    </head>
    <body>
        <form action=<%=request.getContextPath() + "/api/v1/user/sensors/view"%> method="GET">
                <p><input type="text" name="text"</p>
                <p><input type="submit" value="Search"></p>
        </form>

        <table>
            <thead>
                <tr>
                    <th>Uuid</th>
                    <th>Name</th>
                    <th>Model</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="sensor" items="${sensors}">
                    <tr>
                        <td><c:out value="${sensor.uuid}" escapeXml="false"/></td>
                        <td><c:out value="${sensor.name}" escapeXml="false"/></td>
                        <td><c:out value="${sensor.model}" escapeXml="false"/></td>
                    </tr>
               </c:forEach>
            </tbody>
        </table>
    </body>
</html>