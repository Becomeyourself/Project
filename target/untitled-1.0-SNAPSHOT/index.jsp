<%@ page import="dao.RoleDao" %>
<%@ page import="dao.impl.RoleDaoImpl" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="jquery.js"></script>
    <title>登录页面</title>
    <style>
        .login{
            position: fixed;
            left: 40%;
            top:20%;
            width: 266px;
            height: 305px;
            background-color: #d7f4f4;
        }
        .id,.password,.role{
            font-size: 12px;
            color: rgba(0, 0, 0, 0.66);
            margin-left: 52px;
            margin-top: 40px;
        }
        /*.sure{
            position: absolute;
            width: 64px;
            height: 23px;
            bottom: 43px;
            left:38%;
            background-color: #19A9FA;
            border-radius: 5px;
            font-size: 12px;
            color: #FFFDFD;
            text-align: center;
            line-height: 19px;
            border: none;
        }
        .register{
            font-size: 12px;
           !* color: rgba(0, 0, 0, 0.66);*!
            margin-left: 110px;
            margin-top: 90px;
            width: 64px;
            height: 23px;
            bottom: 43px;
            left:38%;
            background-color: #19A9FA;
            border-radius: 5px;
            font-size: 12px;
            color: #FFFDFD;
            text-align: center;
            line-height: 19px;
            border: none;
        }*/
        .sure {
            position: absolute;
            width: 64px;
            height: 23px;
            background-color: #19A9FA;
            border-radius: 5px;
            font-size: 12px;
            color: #FFFDFD;
            text-align: center;
            line-height: 19px;
            border: none;
            /* 移除与位置相关的样式 */
            bottom: 43px;
            left:20%;
            margin-top: 90px;
        }

        .register {
            position: absolute;
            width: 64px;
            height: 23px;
            background-color: #19A9FA;
            border-radius: 5px;
            font-size: 12px;
            color: #FFFDFD;
            text-align: center;
            line-height: 19px;
            border: none;
            /* 移除与位置相关的样式 */
            bottom: 43px;
            right:20%;
            margin-top: 90px;
        }
        .sure:hover{
            font-weight: 700;
        }
    </style>
</head>
<body>
<div class="login">
    <form id="f1">
        <div class="id">
            账号：<input type="text" name='id' style="width: 120px;height: 17px">
        </div>
        <div class="password">
            密码：<input type="password" name='password' style="width: 120px;height: 17px">
        </div>
        <div class="role">
            角色：
            <select  name="role" style="width: 120px;height: 17px">
                <option>管理员</option>
                <option>科研人员</option>
                <option>学生</option>
                <option>老师</option>
                <option>学校领导</option>
                <option>校长</option>
            </select>
        </div>
        <button class="sure" type="button" onclick="login1();">登录</button>
        <input class="register" type="button" value="注册" onclick="window.open('register.jsp')">
    </form>
</div>
<script>
    function login1() {
        $.ajax({
            type:"POST",
            url:"LoginServlet",
            data:$("#f1").serialize(),
            success:function(data){
                console.log(data);
                if(data==1){
                    alert("登录成功");
                    window.location.href="http://localhost:8080/untitled_war_exploded/power.jsp";
                }
                else if(data==2){
                    alert("登录成功");
                    window.location.href="http://localhost:8080/untitled_war_exploded/inform.jsp";
                }
                else if(data==3){
                    alert("登录成功");
                    window.location.href="http://localhost:8080/untitled_war_exploded/store.jsp";
                }
                else if(data==4){
                    alert("登录成功");
                    window.location.href="http://localhost:8080/untitled_war_exploded/waiter.jsp";
                }
                else if(data==5){
                    alert("登录成功");
                    window.location.href="http://localhost:8080/untitled_war_exploded/client.jsp";
                }
                else if(data==6){
                    alert("登录成功");
                    window.location.href="http://localhost:8080/untitled_war_exploded/boss.jsp";
                }
                else {

                    alert("登录失败");

                }
            },
            error:function(){
                alert("Error!");
            }
        });
    }
</script>
</body>
</html>
