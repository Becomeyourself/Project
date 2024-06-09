<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="dao.*,entity.*,java.util.*" %>
<%@ page import="dao.impl.GoodDaoImpl" %>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <style>
        *{
            font-family: 'Verdana';
        }
        li{
            list-style: none;
        }
        .box{
            width:1089px;
            height: 617px;
            background-color:#FFFFFF;
            margin: 0 auto;
        }
        .top{
            width:1089px;
            height: 42px;
            background-color: #D9D9D9;
            color: #58A5C3;
        }
        .topl{
            float: left;
            width:153px;
            height:42px;
            line-height: 42px;
            text-align: center;
            font-size: 12px;
        }
        .topr{
            margin-top: 14px;
            float:right;
            width:80px;
            height: 10px;
            color:#4A4747;
            font-size: 10px;
        }
        .left{
            float:left;
            width: 153px;
            height: 575px;
            background-color: #D9D9D9;
        }
        .left li{
            height: 34px;
            text-align: center;
            line-height: 34px;
            font-size: 12px;
            color: #868DA4;
            font-weight: 700;
            background-color: #D9D9D9;
        }
        .left li:hover {
            background-color: #EBF6F8;
        }
        .left .current {
            background-color: #A3DBE5;
        }
        .right{
            float:left;
            width: 936px;
            height: 575px;
            background-color: #EBF7F8;
        }
        .item{
            display: none;
        }
        .role1{
            margin-top: 42px;
            margin-left: 30px;
            margin-bottom: 26.6px;
        }
        .inquiry,
        .add1,.add2{
            background-color: #19A9FA;
            color:#FFFDFD;
            margin-left: 49.5px;
            width: 58.5px;
            height: 32px;
            line-height: 31px;
            text-align: center;
            font-size: 12px;
            border-radius: 5px;
        }
        .inquiry{
            float: left;
        }
        .role1 .add1,.role1 .add2{
            margin-left: 348px;
            width: 96px;
        }
        .role1 input{
            display: block;
            float: left;
            width: 185.5px;
            height: 26px;
        }
        .role11{
            margin-left: 30px;
        }
        .role11 .table1,.role11 .table2,.atable{
            width: 853.5px;
            text-align: center;
            font-size: 12px;
            color:#000;
            background-color: #FFFDFD;
            border: 1px solid #D9D9D9;
        }
        .add1:hover,.add2:hover,.editor:hover,.editor2:hover{
            font-weight: 700;
        }
        .roleadd,.roleeditor,
        .proleadd,.proleeditor{
            position: fixed;
            left: 45%;
            top:20%;
            width: 266px;
            height: 305px;
            background-color: #d7f4f4;
        }
        .rolename1,.rolename2,.rolemember1,.roleauthority1{
            margin-left: 17px;
            margin-top: 15px;
            font-size: 12px;
            color: rgba(0, 0, 0, 0.66);
        }
        .rolename2{
            margin-left: 29px;
            margin-top: 16px;
            margin-right: 21px;
            color: #000000;
        }
        .roleadd1,.roleadd2,.roleeditor1,.roleeditor2,.proleeditor1{
            font-size: 12px;
            color: rgba(0, 0, 0, 0.66);
            margin-left: 17px;
            margin-top: 35px;
        }
        .roleadd2,.roleeditor2{
            margin-top: 13px;
        }
        .sure,.back,.sure2,.back2,
        .sure3,.back3,.sure4,.back4{
            position: absolute;
            width: 64px;
            height: 23px;
            bottom: 14px;
            right: 17px;
            background-color: #19A9FA;
            border-radius: 5px;
            font-size: 12px;
            color: #FFFDFD;
            text-align: center;
            line-height: 23px;
        }
        .sure:hover,.back:hover,
        .sure2:hover,.back2:hover,.delete:hover,.delete2:hover,.inquiry:hover,
        .sure3:hover,.back3:hover,
        .sure4:hover,.back4:hover,.enter:hover{
            font-weight: 700;
        }
        .sure,.sure3{
            line-height: 19px;
            border: none;
        }
        .back,.back2,.back3,.back4{
            right:113px;
            background-color: #E76161;
        }
        .rolemember2,.roleauthority2{
            margin-left: 44px;
            margin-top: 26px;
        }
        .rolemember2 table,.roleauthority2 table{
            width: 149px;
            text-align: center;
            font-size: 12px;
            color:#000;
            background-color: #FFFDFD;
            border: 1px solid #D9D9D9;
        }
        .atable{
            margin-left: 30px;
            margin-top: 100.5px;
        }
        .enter a{
            text-decoration: none;
            color:#21AAF7;
        }
    </style>
</head>
<body>
<%  GoodDao gd = new GoodDaoImpl();
    List<Good> goods = gd.getAll("select * from good");
%>
    <div class="box">
        <div class="top">
            <div class="topl">批发零售业务管理系统</div>
            <div class="topr"><img src="个人中心.png" width="10px">个人中心</div>
        </div>
        <div class="left">
            <li class="current">货品档案</li>
            <li>库存</li>
        </div>
        <div class="right">
            <div class="item" style="display: block;">
                <div class="role1">
                    <input type="text" value="请输入货品名称" id='name' style="color:rgba(0,0,0,0.25)">
                    <div class="inquiry" onclick="search1()">查询</div>
                    <div class="add1">+添加货品</div>
                </div>
                <div class="role11">
                    <table class="table1" cellpadding="10" >
                        <tr style="color: rgba(0,0,0,0.5)">
                            <td>货品名称</td><td>货品介绍</td><td colspan="2">操作</td>
                        </tr>
                        <% for(Good g : goods){
                            String gname = g.goodname;
                            String ginform = g.goodinform;%>
                        <tr class="rolelist">
                            <td class="role"><%=gname%></td><td><%=ginform%></td>
                            <td class="editor" style="color:#3A9D32">编辑</td>
                            <td><a style="color:#F72121;text-decoration: none" onclick="return confirm('确定要删除<%=gname%>货品吗？')" href="GoodServlet?action=delete1&gname=<%=gname%>">删除</a></td>
                        </tr>
                        <%
                            }%>
                    </table>
                    <% for(Good g : goods){%>
                    <div class="roleeditor" style="display: none;">
                        <form method="post" action="GoodServlet">
                        <input type="hidden" name="action" value="editor1" />
                        <div class="roleeditor1">
                            货品名称<input type="text" name='rolename' value ="<%=g.goodname%>" style="width: 120px;height: 17px;margin-left:20px">
                         </div>
                         <div class="roleeditor2">
                             货品介绍<input type="text" name='roleexplain' value ="<%=g.goodinform%>" style="width: 120px;height: 17px;margin-left:20px">
                         </div>
                        <div class="back3">返回</div>
                        <input class="sure3" type="submit" value="确定"/>
                        </form>
                    </div>
                    <%}%>
                    <div class="roleadd" style="display: none;">
                        <form method="post" action="GoodServlet">
                            <input type="hidden" name="action" value="add1" />
                        <div class="roleadd1">
                            货品名称<input type="text" name='rolename2' style="width: 120px;height: 17px;margin-left:20px">
                         </div>
                         <div class="roleadd2">
                             货品介绍<input type="text" name='roleexplain2' style="width: 120px;height: 17px;margin-left:20px">
                         </div>
                        <div class="back">返回</div>
                            <input class="sure" type="submit" value="确定"/>
                        </form>
                    </div>
                </div>
            </div>
            <div class="item">
                <div class="role11" style="margin-top: 100.73px;">
                    <table class="table2" cellpadding="10">
                        <tr style="color: rgba(0,0,0,0.5)">
                            <td>仓库名称</td><td>操作</td>
                        </tr>
                        <tr class="r1">
                            <td class="role">仓库1</td>
                            <td class="enter"><a href="store1.jsp?storeID=1" onclick="return alert('欢迎进入仓库！')">进入</a></td>
                        </tr>
                        <tr>
                            <td class="role">仓库2</td>
                            <td class="enter"><a href="store1.jsp?storeID=2" onclick="return alert('欢迎进入仓库！')">进入</a></td>
                        </tr>
                    </table>
                </div>
            </div>
        </div>
    </div>
    <script>
        var left =document.querySelector('.left');
        var lis=left.querySelectorAll('li');
        var items=document.querySelectorAll('.item');
        for(var i=0;i<lis.length;i++) {
            lis[i].setAttribute('index',i);
            lis[i].onclick=function(){
                for(var i=0;i<lis.length;i++) {
                    lis[i].className='';
                }
                this.className='current';
                var index=this.getAttribute('index');
                for(var i=0;i<items.length;i++) {
                    items[i].style.display='none';
                }
                items[index].style.display='block';
            }
        }
        var username = document.querySelector('#name');
        username.addEventListener("focus", function () {
            this.value = "";
        })
        var roleadd=document.querySelectorAll('.roleadd');
        var add1=document.querySelectorAll('.add1');
        for(let i=0;i<add1.length;i++) {
            add1[i].onclick=function(){
                roleadd[i].style.display='';
            }
        }
        var back=document.querySelectorAll('.back');
        for(let i=0;i<back.length;i++) {
            back[i].onclick=function(){
                roleadd[i].style.display='none';
            }
        }
        var editor=document.querySelectorAll('.editor');
        var roleeditor=document.querySelectorAll('.roleeditor');
        for(let i=0;i<editor.length;i++) {
            editor[i].onclick=function(){
                roleeditor[i].style.display='';
            }
        }
        var back3=document.querySelectorAll('.back3');
        for(let i=0;i<back3.length;i++) {
            back3[i].onclick=function(){
                roleeditor[i].style.display='none';
            }
        }
        var rolelist=document.querySelectorAll('.rolelist');
        var role=document.querySelectorAll('.role');
        function search1() {
            let name = document.getElementById('name');
            for (let i = 0; i < rolelist.length; i++) {
                rolelist[i].style.display = 'none';
            }
            for (let i = 0; i < rolelist.length; i++) {
                if (name.value === role[i].textContent) {
                    rolelist[i].style.display = '';
                }
            }
            if(name.value===""){
                for (let i = 0; i < rolelist.length; i++) {
                    rolelist[i].style.display = '';
                }
            }
        }
    </script>
</body>
</html>