<%@page contentType="text/html;utf-8" pageEncoding="utf-8" isELIgnored="false" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>受限资源</title>
</head>
<body>

<h1>系统主页V1</h1>
<h1><shiro:principal/></h1>
<a href="${pageContext.request.contextPath}/user/logout">退出登录</a>
<ul>
    <shiro:hasAnyRoles name="user,admin">
        <li><a href="#">用户管理</a></li>
        <ul>
            <shiro:hasPermission name="user:add:*">
                <li><a href="${pageContext.request.contextPath}/order/save">添加</a></li>
            </shiro:hasPermission>
            <shiro:hasPermission name="user:modify:*">
                <li><a href="#">修改</a></li>
            </shiro:hasPermission>
            <shiro:hasPermission name="user:delete:*">
                <li><a href="#">删除</a></li>
            </shiro:hasPermission>
            <shiro:hasPermission name="user:query:*">
                <li><a href="#">查询</a></li>
            </shiro:hasPermission>
        </ul>
    </shiro:hasAnyRoles>
    <%--仅具有admin角色用户权限可以看到--%>
    <shiro:hasRole name="admin">
        <li><a href="#">商品管理</a></li>
        <li><a href="#">订单管理</a></li>
        <li><a href="#">物流管理</a></li>
    </shiro:hasRole>


</ul>

</body>
</html>