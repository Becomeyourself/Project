<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form name="reg" action="doregister.jsp" method="post">
    <table>
        <tbody>
        <tr>
            <td>用户名:</td>
            <td><input type="text" name="id"/></td>
        </tr>
        <tr>
            <td>密码:</td>
            <td><input type="password" name="password"/></td>
        </tr>
        <tr>
            <td>重复密码:</td>
            <td><input type="password" name="rpassword"/></td>
        </tr>
        <tr>
            <td>注册日期:</td>
            <td><input type="date" name="date"/></td>
        </tr>
        <tr>
            <td>用户身份</td>
            <td><input type="text" name="role"/></td>
        </tr>
        </tbody>
    </table>
    <input type="button" value="注册" onclick="doregister()">
</form>
<script>
    function doregister(){
        if (document.reg.id.value==null){
            alert("用户名不能为空！");
            return;
        }
        if (document.reg.password.value===document.reg.rpassword.value){
            reg.submit();
        }else{
            alert("两次密码输入不一致！");
        }
    }
</script>
</body>
</html>
