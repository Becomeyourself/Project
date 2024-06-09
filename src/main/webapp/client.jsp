<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="dao.*,entity.*,java.util.*" %>
<%@ page import="dao.impl.GoodDaoImpl" %>
<%@ page import="dao.impl.SellDaoImpl" %>
<%@ page import="dao.impl.BuyDaoImpl" %>
<%@ page import="dao.impl.StoreDaoImpl" %>
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
            margin-top: 100.73px;
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
            margin-top: 47px;
        }
        .roleadd2,.roleeditor2{
            margin-top: 17px;
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
        .sure4:hover,.back4:hover,.reserve:hover{
            font-weight: 700;
        }
        .sure3{
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
        .reserve{
            background-color: #FFFDFD;
            font-size: 12px;
            color:#F72121;
            border: none;
        }
    </style>
</head>
<body>
<% GoodDao gd = new GoodDaoImpl();
List<Good> goods = gd.getAll("select * from good");
StoreDao srd = new StoreDaoImpl();
BuyDao bd = new BuyDaoImpl();
List<Buy> buys = bd.getAll("select * from buys");
%>
    <div class="box">
        <div class="top">
            <div class="topl">批发零售业务管理系统</div>
            <div class="topr"><img src="个人中心.png" width="10px">个人中心</div>
        </div>
        <div class="left">
            <li class="current">退货申请</li>
            <li>选购货品</li>
        </div>
        <div class="right">
            <div class="item" style="display: block;">
                <div class="role11">
                    <table class="table1" cellpadding="10" >
                        <tr style="color: rgba(0,0,0,0.5)">
                            <td>编号</td><td>日期</td>
                            <td>货品信息</td><td>货品总数</td>
                            <td>退款总价格</td><td colspan="2">操作</td>
                        </tr>
                        <%  int i = 1;
                            for(Buy b : buys){%>
                        <tr class="r1">
                            <td class="role"><%=i%></td><td><%=b.date%></td>
                            <td><%=b.name%></td><td><%=b.amount%></td>
                            <td><%=b.price%>元</td>
                            <td class="editor" style="color:#F72121">申请退货</td>
                            <%if(b.check.equals("已审核")){%>
                            <td class="add11" style="color:#2137F7;"><%=b.check%></td>
                            <%}
                            else{%>
                            <td class="add22" style="color:#000000"><%=b.check%></td>
                            <%}%>
                        </tr>
                        <%i++;}%>
                    </table>
                    <%for(Buy b : buys){%>
                    <div class="roleeditor" style="display: none;">
                        <form method="post" action="BuyServlet">
                            <input type="hidden" name="action" value="buy2" />
                            <input type="hidden" name="name" value="<%=b.name%>" />
                        <div class="roleeditor1">
                            请填写退货原因：
                         </div>
                         <div class="roleeditor2">
                             <textarea name='roleexplain' style="width: 186px;height: 116px;margin-left:30px"></textarea>
                         </div>
                            <input class="sure3" type="submit" value="提交"/>
                        </form>
                    </div>
                    <%}%>
                    <%for(Buy b : buys){
                        if(b.check.equals("已审核")){
                    %>
                    <div class="roleadd" style="display: none;">
                        <%if(b.reson2.equals("未驳回")){%>
                        <div class="roleadd1" style="width: 191px;margin-left: 33px;">
                            您的退货单已通过！钱款在7个工作日内打到账上
                        </div>
                        <div class="roleadd2">
                        </div>
                        <%}
                        else{%>
                        <div class="roleadd1" style="margin-left: 33px;">
                            您的退货单被驳回！驳回原因如下：
                        </div>
                        <div class="roleadd2" style="width: 135px;margin-left: 41px;">
                            <%=b.reson2%>
                        </div>
                        <%}%>
                        <div class="sure">我知道了</div>
                    </div>
                    <%}}%>
                </div>
            </div>
            <div class="item">
                <div class="role11" style="margin-top: 100.73px;">
                    <table class="table2" cellpadding="10">
                        <tr style="color: rgba(0,0,0,0.5)">
                            <td>编号</td>
                            <td>货品信息</td><td>货品总数(斤)</td>
                            <td>单价</td><td colspan="2">操作</td>
                        </tr>
                        <%for(Good g :goods){%>
                        <form method="post" action="BuyServlet">
                            <input type="hidden" name="action" value="buy1" />
                            <input type="hidden" name="goodname" value="<%=g.goodname%>" />
                        <tr class="r1">
                            <td class="role">1</td>
                            <td><%=g.goodname%></td>
                            <td><input type="text" name="data" style="width: 56px;"/></td>
                            <td><%=srd.getByName(g.goodname)%></td>
                            <td><input class="reserve" type="submit" value="购买"/></td>
                        </tr>
                        </form>
                        <%}%>
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
        // var roleadd=document.querySelector('.roleadd');
        // var add1=document.querySelector('.add1');
        // add1.onclick=function(){
        //     roleadd.style.display='';
        // }
        // var back=document.querySelector('.back');
        // back.onclick=function(){
        //     roleadd.style.display='none';
        // }
        var editor=document.querySelectorAll('.editor');
        var roleeditor=document.querySelectorAll('.roleeditor');
        for(let i=0;i<editor.length;i++) {
            editor[i].onclick=function(){
                roleeditor[i].style.display='';
            }
        }
        var add11=document.querySelectorAll('.add11');
        var roleadd=document.querySelectorAll('.roleadd');
        for(let i=0;i<add11.length;i++) {
            add11[i].onclick=function(){
                roleadd[i].style.display='';
            }
        }
        // var add22=document.querySelectorAll('.add22');
        // var proleadd=document.querySelectorAll('.proleadd');
        // for(let i=0;i<add22.length;i++) {
        //     add22[i].onclick=function(){
        //         proleadd[i].style.display='';
        //     }
        // }
        var sure=document.querySelectorAll('.sure');
        // var sure2=document.querySelectorAll('.sure2');
        for(let i=0;i<sure.length;i++) {
            sure[i].onclick=function(){
                roleadd[i].style.display='none';
            }
        }
        // for(let i=0;i<sure2.length;i++) {
        //     sure2[i].onclick=function(){
        //         proleadd[i].style.display='none';
        //     }
        // }
        // var back3=document.querySelector('.back3');
        // back3.onclick=function(){
        //     roleeditor.style.display='none';
        // }
        // var editor2=document.querySelector('.editor2');
        // var proleeditor=document.querySelector('.proleeditor');
        // editor2.onclick=function(){
        //    proleeditor.style.display='';
        // }
        // var back4=document.querySelector('.back4');
        // back4.onclick=function(){
        //     proleeditor.style.display='none';
        // }
    </script>
</body>
</html>