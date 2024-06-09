<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="dao.UserDao" %>
<%@ page import="dao.impl.UserDaoImpl" %>
<%@ page import="dao.UserRoleDao" %>
<%@ page import="dao.impl.UserRoleDaoImpl" %>

<%@ page import="entity.User" %>
<%@ page import="entity.UserRole" %>
<%@ page import="java.nio.charset.StandardCharsets" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    request.setCharacterEncoding("UTF-8");
    response.setContentType("text/html;charset=utf-8");
    String uact=request.getParameter("id");
    String upwd=request.getParameter("password");
    String udate=request.getParameter("date");
    String urole=request.getParameter("role");
    //String role = new String(urole.getBytes(StandardCharsets.ISO_8859_1),"UTF-8");

    UserDao ud = new UserDaoImpl();
    UserRoleDao ur = new UserRoleDaoImpl();
    int rs=0;
    try{
        ud.add(new User(uact,upwd,udate));
        ur.add(new UserRole(uact,urole));
        rs=1;
    }catch (Exception e){
        e.printStackTrace();
    }
    if (rs==1){
        //alert("恭喜用户注册成功!");
    }else {
        //alert("注册失败,可能用户名:"+uact+"已存在!");
    }
%>
<br/>
<input type="button" value="返回登录" onclick="window.open('index.jsp')">
<input type="button" value="返回注册" onclick="window.open('register.jsp')">
</body>
</html>

