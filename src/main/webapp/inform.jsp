<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="dao.BuyerDao" %>
<%@ page import="dao.impl.BuyerDaoImpl" %>
<%@ page import="dao.SellerDao" %>

<%@ page import="dao.impl.SellerDaoImpl" %>

<%@ page import="entity.Buyer" %>
<%@ page import="java.util.List" %>
<%@ page import="entity.Seller" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    v<script src="jquery.js"></script>
    <title>个人论文管理系统</title>
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
        .role11 .table1,.role11 .table2{
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
        .roleadd1,.proleadd1,.roleeditor1,.proleeditor1{
            font-size: 12px;
            color: rgba(0, 0, 0, 0.66);
            margin-left: 17px;
            margin-top: 35px;
        }
        .roleadd2{
            margin-top: 13px;
        }
        .roleadd1 tr,.proleadd1 tr,.roleeditor tr,.proleeditor tr{
            height: 35px;
            text-align: right;
        }
        .sure,.back,.back2,
        .back3,.back4{
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
        .sure{
            line-height: 19px;
            border: none;
        }
        .sure:hover,.back:hover,
        .back2:hover,.delete:hover,.delete2:hover,.inquiry:hover,
        .back3:hover,
        .back4:hover{
            font-weight: 700;
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
    </style>
</head>
<body>
<% BuyerDao bd = new BuyerDaoImpl();
List<Buyer> buyers = bd.getAll("select * from buyer");
SellerDao sd = new SellerDaoImpl();
List<Seller> sellers = sd.getAll("select * from seller");

%>
    <div class="box">
        <div class="top">
            <div class="topl">信息管理系统</div>
            <div class="topr"><img src="个人中心.png" width="10px">个人中心</div>
        </div>
        <div class="left">
            <li class="current">论文基本信息管理</li>
            <li>论文基本信息管理</li>
        </div>
        <div class="right">
            <div class="item" style="display: block;">
                <div class="role1"  style="display: flex; align-items: center;">
                    <select  id="op1" style="width: 120px;height: 17px; margin-right:10px; ">
                        <option>论文名称</option>
                        <option>刊物</option>
                        <option>发表日期</option>
                        <option>文件路径</option>
                        <option>工作量分数</option>
                        <option>老板</option>
                        <option>类别</option>
                    </select>
                    <input type="text" value="请输入论文名称" id='name' style="color:rgba(0,0,0,0.25);margin-right:10px;">
                    <div class="inquiry" style="margin-right: 10px;" onclick="search1()">查询</div>
                    <div class="add1" style="margin-left: 10px;" >+添加论文</div>
                </div>
                <div class="role11">
                    <table class="table1" cellpadding="10" >
                        <tr style="color: rgba(0,0,0,0.5)">
                            <td>论文名称</td><td>刊物</td><td>发表日期</td><td>文件路径</td><td>工作量分数</td><td>类别</td><td colspan="2">操作</td>
                        </tr>
                        <% for(Buyer b : buyers){%>
                        <tr class="rolelist">
                            <td class="role"><%=b.getName()%></td><td class="kan"><%=b.getTelephone()%></td>
                            <td class="riqi"><%=b.getMethod()%></td><td  class="lujing"><%=b.getType()%></td>
                            <td class="fenshu"><%=b.getAddress()%></td><td class="leibie"><%=b.getCategory()%></td>
                            <td class="editor" style="color:#3A9D32">编辑</td>
                            <td><a style="color:#F72121;text-decoration: none" onclick="return confirm('确定要删除<%=b.getName()%>客户吗？')" href="BuyerServlet?action=delete1&bname=<%=b.getName()%>">删除</a></td>
                        </tr>
                        <%}%>
                    </table>
                    <% for(Buyer b : buyers){%>
                    <div class="roleeditor" style="display: none;">
                        <form method="post" action="BuyerServlet">
                            <input type="hidden" name="action" value="editor1" />
                            <table class="roleeditor1">
                                <tr>
                                    <td>论文名称</td>
                                    <td><input type="text" name='rolename' value="<%=b.getName()%>" style="width: 120px;height: 17px;margin-left: 19px;"/></td>
                                </tr>
                                <tr>
                                    <td>刊物</td>
                                    <td><input type="text" name='roleconnect' value="<%=b.getTelephone()%>" style="width: 120px;height: 17px;margin-left: 19px;"/></td>
                                </tr>
                                <tr>
                                    <td>发表日期</td>
                                    <td>
                                        <select name='rolepay' style="width: 128px;height: 24px;margin-left: 19px;">
                                            <option value="1"<%if(b.getMethod().equals("微信支付")) out.print("selected=\"true\"");%>>微信支付</option>
                                            <option value="2"<%if(b.getMethod().equals("支付宝")) out.print("selected=\"true\"");%>>支付宝</option>
                                            <option value="3"<%if(b.getMethod().equals("银行卡")) out.print("selected=\"true\"");%>>银行卡</option>
                                        </select>
                                    </td>
                                </tr>
                                <tr>
                                    <td>文件路径</td>
                                    <td><input type="text" name='roletype' value="<%=b.getType()%>" style="width: 120px;height: 17px;margin-left: 19px;"/></td>
                                </tr>
                                <tr>
                                    <td>工作量分数</td>
                                    <td><input type="text" name='rolead' value="<%=b.getAddress()%>" style="width: 120px;height: 17px;margin-left: 19px;"/></td>
                                </tr>
                                <tr>
                                    <td>类别</td>
                                    <td><input type="text" name='roleca' value="<%=b.getCategory()%>" style="width: 120px;height: 17px;margin-left: 19px;"/></td>
                                </tr>
                            </table>
                            <div class="back3">返回</div>
                            <input class="sure" type="submit" value="确定"/>
                        </form>
                    </div>
                    <%}%>
                    <div class="roleadd" style="display: none;">
                        <form method="post" action="BuyerServlet">
                            <input type="hidden" name="action" value="add1" />
                             <table class="roleadd1">
                                <tr>
                                    <td>论文名称</td>
                                    <td><input type="text" name='rolename2' style="width: 120px;height: 17px;margin-left: 19px;"/></td>
                                </tr>
                                <tr>
                                    <td>刊物</td>
                                    <td><input type="text"  name="roleconnect2" style="width: 120px;height: 17px;margin-left: 19px;"/></td>
                                </tr>
                                <tr>
                                    <td>发表日期</td>
                                    <td>
                                        <select style="width: 128px;height: 24px;margin-left: 19px;" name="rolepay2">
                                            <option>1</option>
                                            <option>2</option>
                                            <option>3</option>
                                        </select>
                                    </td>
                                </tr>
                                <tr>
                                    <td>文件路径</td>
                                    <%--<td>
                                        <select style="width: 128px;height: 24px;margin-left: 19px;" name="roletype2">
                                            <option>批发</option>
                                            <option>零售</option>
                                        </select>
                                    </td>--%>
                                    <td><input type="text"  name="roletype2" style="width: 120px;height: 17px;margin-left: 19px;"/></td>
                                </tr>
                                <tr>
                                    <td>工作量分数</td>
                                    <td><input type="textarea"  name="rolead2" style="width: 120px;height: 17px;margin-left: 19px;"/></td>
                                </tr>
                                 <tr>
                                     <td>类别</td>
                                     <td><input type="textarea"  name="roleca2" style="width: 120px;height: 17px;margin-left: 19px;"/></td>
                                 </tr>
                        </table>
                        <div class="back">返回</div>
                        <input class="sure" type="submit" value="确定"/>
                        </form>
                    </div>
                </div>
            </div>
            <div class="item">
                <div class="role1">
                    <input type="text" value="请输入lunwen名称" id='name2' style="color:rgba(0,0,0,0.25)">
                    <div class="inquiry" onclick="search2()">查询</div>
                    <div class="add2">+添加论文</div>
                </div>
                <div class="role11">
                    <table class="table2" cellpadding="10">
                        <tr style="color: rgba(0,0,0,0.5)">
                            <td>论文名称</td><td>刊物</td><td>发表日期</td><td>文件路径</td><td>工作量分数</td><td colspan="2">操作</td>
                        </tr>
                        <%for(Seller s : sellers){%>
                        <tr class="rolelist2">
                            <td class="role2"><%=s.getName()%></td><td><%=s.getTelephone()%></td>
                            <td><%=s.getMethod()%></td><td><%=s.getType()%></td>
                            <td><%=s.getAddress()%></td>
                            <td class="editor2" style="color:#3A9D32">编辑</td>
                            <td><a style="color:#F72121;text-decoration: none" onclick="return confirm('确定要删除<%=s.getName()%>供货商吗？')" href="SellerServlet?action=delete1&sname=<%=s.getName()%>">删除</a></td>
                        </tr>
                        <%}%>
                    </table>
                    <% for(Seller s : sellers){%>
                    <div class="proleeditor" style="display: none;">
                        <form method="post" action="SellerServlet">
                            <input type="hidden" name="action" value="editor1" />
                            <table class="proleeditor1">
                                <tr>
                                    <td>论文名称</td>
                                    <td><input type="text" name='prolename' value="<%=s.getName()%>" style="width: 120px;height: 17px;margin-left: 19px;"/></td>
                                </tr>
                                <tr>
                                    <td>刊物</td>
                                    <td><input type="text" name='proleconnect' value="<%=s.getTelephone()%>" style="width: 120px;height: 17px;margin-left: 19px;"/></td>
                                </tr>
                                <tr>
                                    <td>发表日期</td>
                                    <td>
                                        <select name='prolepay' style="width: 128px;height: 24px;margin-left: 19px;">
                                            <option value="1"<%if(s.getMethod().equals("1")) out.print("selected=\"true\"");%>>微信支付</option>
                                            <option value="2"<%if(s.getMethod().equals("2")) out.print("selected=\"true\"");%>>支付宝</option>
                                            <option value="3"<%if(s.getMethod().equals("3")) out.print("selected=\"true\"");%>>银行卡</option>
                                        </select>
                                    </td>
                                </tr>
                                <tr>
                                    <td>文件路径</td>
                                    <td>
                                        <select name='proletype' style="width: 128px;height: 24px;margin-left: 19px;">
                                            <option value="本地"<%if(s.getType().equals("本地")) out.print("selected=\"true\"");%>>本地</option>
                                            <option value="外地"<%if(s.getType().equals("外地")) out.print("selected=\"true\"");%>>外地</option>
                                        </select>
                                    </td>
                                </tr>
                                <tr>
                                    <td>工作量分数</td>
                                    <td><input type="textarea" name='prolead' value="<%=s.getAddress()%>" style="width: 120px;height: 17px;margin-left: 19px;"/></td>
                                </tr>
                            </table>
                            <div class="back4">返回</div>
                            <input class="sure" type="submit" value="确定"/>
                        </form>
                    </div>
                    <%}%>
                    <div class="proleadd" style="display: none;">
                        <form method="post" action="SellerServlet">
                            <input type="hidden" name="action" value="add1" />
                            <table class="proleadd1">
                            <tr>
                                <td>论文名称</td>
                                <td><input type="text" name='rolename4' style="width: 120px;height: 17px;margin-left: 19px;"/></td>
                            </tr>
                            <tr>
                                <td>刊物</td>
                                <td><input type="text" name="roleconnect4" style="width: 120px;height: 17px;margin-left: 19px;"/></td>
                            </tr>
                            <tr>
                                <td>发表日期</td>
                                <td>
                                    <select style="width: 128px;height: 24px;margin-left: 19px;" name="rolepay4">
                                        <option>1</option>
                                        <option>2</option>
                                        <option>3</option>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td>文件路径</td>
                                <td>
                                    <select style="width: 128px;height: 24px;margin-left: 19px;" name="roletype4">
                                        <option>1</option>
                                        <option>2</option>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td>工作量分数</td>
                                <td><input type="textarea" name="rolead4" style="width: 120px;height: 17px;margin-left: 19px;"/></td>
                            </tr>
                        </table>
                        <div class="back2">返回</div>
                        <input class="sure" type="submit" value="确定"/>
                        </form>
                    </div>
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
        var username2 = document.querySelector('#name2');
        username2.addEventListener("focus", function () {
            this.value = "";
        })
        var roleadd=document.querySelector('.roleadd');
        var add1=document.querySelector('.add1');
        add1.onclick=function(){
            roleadd.style.display='';
        }
        var back=document.querySelector('.back');
        back.onclick=function(){
            roleadd.style.display='none';
        }
        var proleadd=document.querySelector('.proleadd');
        var add2=document.querySelector('.add2');
        add2.onclick=function(){
            proleadd.style.display='';
        }
        var back2=document.querySelector('.back2');
        back2.onclick=function(){
            proleadd.style.display='none';
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
        var editor2=document.querySelectorAll('.editor2');
        var proleeditor=document.querySelectorAll('.proleeditor');
        for(let i=0;i<editor2.length;i++) {
            editor2[i].onclick=function(){
                proleeditor[i].style.display='';
            }
        }
        var back4=document.querySelectorAll('.back4');
        for(let i=0;i<back4.length;i++) {
            back4[i].onclick=function(){
                proleeditor[i].style.display='none';
            }
        }
        var rolelist=document.querySelectorAll('.rolelist');
        var role=document.querySelectorAll('.role');
        var kanwu=document.querySelectorAll('.kan');
        var riqi=document.querySelectorAll('.riqi');
        var lujing=document.querySelectorAll('.lujing');
        var fenshu=document.querySelectorAll('.fenshu');
        var leibie=document.querySelectorAll('.leibie');
        function search1() {
            let name = document.getElementById('name');
            let op1=document.getElementById('op1');
            for (let i = 0; i < rolelist.length; i++) {
                rolelist[i].style.display = 'none';
            }

            if(name.value===""){
                for (let i = 0; i < rolelist.length; i++) {
                    rolelist[i].style.display = '';
                }
            }
            else {
                if(op1.value=="论文名称"){
                    for (let i = 0; i < rolelist.length; i++) {
                        if (name.value === role[i].textContent) {
                            rolelist[i].style.display = '';
                        }
                    }
                }
                else if(op1.value=="刊物"){
                    for (let i = 0; i < rolelist.length; i++) {
                        if (name.value === kanwu[i].textContent) {
                            rolelist[i].style.display = '';
                        }
                    }
                }
                else if(op1.value=="发表日期"){
                    for (let i = 0; i < rolelist.length; i++) {
                        if (name.value === riqi[i].textContent) {
                            rolelist[i].style.display = '';
                        }
                    }
                }
                else if(op1.value=="文件路径"){
                    console.log(lujing);
                    for (let i = 0; i < rolelist.length; i++) {
                        if (name.value === lujing[i].textContent) {
                            rolelist[i].style.display = '';
                        }
                    }
                }
                else if(op1.value=="工作量分数"){
                    for (let i = 0; i < rolelist.length; i++) {
                        if (name.value === fenshu[i].textContent) {
                            rolelist[i].style.display = '';
                        }
                    }
                }
                else if(op1.value=="类别"){
                    for (let i = 0; i < rolelist.length; i++) {
                        if (name.value === leibie[i].textContent) {
                            rolelist[i].style.display = '';
                        }
                    }
                }
            }
        }
        var rolelist2=document.querySelectorAll('.rolelist2');
        var role2=document.querySelectorAll('.role2');
        function search2() {
            let name2 = document.getElementById('name2');
            for (let i = 0; i < rolelist2.length; i++) {
                rolelist2[i].style.display = 'none';
            }
            for (let i = 0; i < rolelist2.length; i++) {
                if (name2.value === role2[i].textContent) {
                    rolelist2[i].style.display = '';
                }
            }
            if (name2.value === "") {
                for (let i = 0; i < rolelist2.length; i++) {
                    rolelist2[i].style.display = '';
                }
            }
        }
    </script>
</body>
</html>