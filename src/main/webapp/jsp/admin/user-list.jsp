<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="fa">
<head>
    <title>کاربران-ادمین</title>
    <jsp:include page="/jsp/css-import.jsp"></jsp:include>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="initial-scale=1, maximum-scale=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta charset="UTF-8">
</head>
<body dir="rtl">
<div class="container-fluid">
    <table id="user-table" class="table table-responsive-sm table-striped m-auto table-hover">
        <thead>
        <tr>
            <th>شناسه</th>
            <th>نام</th>
            <th>نام خانوادگی</th>
            <th>نام کاربری</th>
            <th>رمز عبور</th>
            <th>سطح دسترسی</th>
            <th>نوع حساب</th>
            <th>وضعیت فعال</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="user" items="${userList}">
            <c:if test="${not user.active}">
                <tr style="background-color: hotpink">
            </c:if>

            <c:if test="${user.active}">
                <tr style="background-color: lightgreen">
            </c:if>
            <td>${user.id}</td>
            <td>${user.firstname}</td>
            <td>${user.lastname}</td>
            <td>${user.userName}</td>
            <td>${user.password}</td>
            <td>${user.role.role}</td>
            <td>${user.privateAccount}</td>
            <td>${user.active}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <jsp:include page="/jsp/js-import.jsp"></jsp:include>
</body>
</html>
