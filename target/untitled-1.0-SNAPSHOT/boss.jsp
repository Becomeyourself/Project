<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="dao.*,entity.*,java.util.*" %>
<%@ page import="dao.impl.IndataDaoImpl" %>
<%@ page import="dao.impl.BuyDaoImpl" %>
<%@ page import="dao.impl.SellDaoImpl" %>
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
        .role11 table,.atable{
            width: 853.5px;
            text-align: center;
            font-size: 12px;
            color:#000;
            background-color: #FFFDFD;
            border: 1px solid #D9D9D9;
        }
        table .member:hover,table .authority:hover,.add:hover,.add22:hover,
        table .role2:hover,table .member2:hover,table .authority2:hover,.editor:hover,.editor2:hover,.sure4:hover{
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
        .roleadd1,.roleadd2,.roleeditor1,.roleeditor2,.proleeditor1{
            font-size: 12px;
            color: rgba(0, 0, 0, 0.66);
            margin-left: 17px;
            margin-top: 47px;
        }
        .roleadd2,.roleeditor2{
            margin-top: 17px;
        }
        .rolename,.rolemember,.roleauthority,.roleadd,
        .prolename,.prolemember,.proleauthority,.proleadd{
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
        .roleadd1,.proleadd1{
            font-size: 12px;
            color: rgba(0, 0, 0, 0.66);
            margin-left: 27px;
            margin-top: 40px;
        }
        .roleadd2{
            margin-top: 24px;
        }
        .know1,.know2,.know3,.know21,.know22,.know23,
        .sure,.back,.sure2,.back2,.sure3,.sure4{
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
        .sure,.sure2,.sure3,.sure4{
            line-height: 19px;
            border: none;
        }
        .pass:hover,.pass2:hover,.know3:hover,
        .know21:hover,.know22:hover,.know23:hover,
        .sure:hover,.back:hover,
        .sure2:hover,.back2:hover,.delete:hover,.delete2:hover,.sure3:hover{
            font-weight: 700;
        }
        .back,.back2{
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
    </style>
</head>
<body>
<%IndataDao id = new IndataDaoImpl();
List<Indata> indatas = id.getAll("select * from indata");
BuyDao bd = new BuyDaoImpl();
List<Buy> buys = bd.getAll("select * from buys");
SellDao sd = new SellDaoImpl();
List<Sell> sells = sd.getAll("select * from sell");
%>
    <div class="box">
        <div class="top">
            <div class="topl">批发零售业务管理系统</div>
            <div class="topr"><img src="个人中心.png" width="10px">个人中心</div>
        </div>
        <div class="left">
            <li class="current">查看采购单</li>
            <li>查看销售单</li>
            <li>查看退货单</li>
        </div>
        <div class="right">
            <div class="item" style="display: block;">
                <div class="role11">
                    <table  cellpadding="10" >
                        <tr style="color: rgba(0,0,0,0.5)">
                            <td>编号</td><td>日期</td>
                            <td>货品信息</td><td>货品进货价格</td>
                            <td>总价格</td><td>仓库信息</td>
                            <td>供货商</td><td colspan="2">操作</td>
                        </tr>
                        <%int i = 1;
                            for(Indata indata : indatas){%>
                        <tr class="r1">
                            <td class="role"><%=i%></td><td><%=indata.date%></td>
                            <td><%=indata.goodname%></td><td><%=indata.inprice%></td>
                            <td><%=indata.price%></td><td><%=indata.storename%></td><td><%=indata.people%></td>
                            <%if(indata.check.equals("待付款")){%>
                            <td class="add" style="color:#21AAF7"><%=indata.check%></td>
                            <%}
                            else{%>
                            <td  style="color:#21AAF7"><%=indata.check%></td>
                            <%}%>
                            <td class="editor" style="color:#F72121">驳回</td>
                        </tr>
                        <%i++;}%>
                    </table>
                    <%for(Indata indata : indatas){%>
                    <form method="post" action="StoreServlet">
                        <input type="hidden" name="action" value="remove2" />
                        <input type="hidden" name="name7" value="<%=indata.goodname%>" />
                        <input type="hidden" name="storename7" value="<%=indata.storename%>" />
                    <div class="roleeditor" style="display: none;">
                            <div class="roleeditor1">
                                请填写驳回原因：
                             </div>
                             <div class="roleeditor2">
                                 <textarea name='roleexplain' style="width: 186px;height: 116px;margin-left:30px;margin-top:17px"></textarea>
                             </div>
                        <input class="sure2" type="submit" value="提交"/>
                    </div>
                    </form>
                    <%if(indata.check.equals("待付款")){%>
                    <form method="post" action="StoreServlet">
                        <input type="hidden" name="action" value="remove3" />
                        <input type="hidden" name="name5" value="<%=indata.goodname%>" />
                        <input type="hidden" name="storename5" value="<%=indata.storename%>" />
                    <div class="roleadd" style="display: none;">
                        <div class="roleadd1" style="margin-left: 69px;margin-top: 115px;">
                            您是否要为该采购单付款？
                         </div>
                        <div class="back">取消</div>
                        <input class="sure" type="submit" value="付款"/>
                    </div>
                    </form>
                    <%}%>
                    <%}%>
                </div>
            </div>
            <div class="item">
                <div class="role11">
                    <table  cellpadding="10">
                        <tr style="color: rgba(0,0,0,0.5)">
                            <tr style="color: rgba(0,0,0,0.5)">
                                <td>编号</td><td>日期</td>
                                <td>货品信息</td><td>客户地址</td>
                                <td>客户电话</td><td>货品销售总价</td>
                                <td colspan="2">操作</td>
                            </tr>
                        </tr>
                        <%  i = 1;
                            for(Sell s :sells){%>
                        <tr class="r1">
                            <td class="role"><%=i%></td><td><%=s.date%></td>
                            <td><%=s.name%></td><td><%=s.address%></td>
                            <td><%=s.telephone%></td><td><%=s.price%></td>
                            <td class="pass"><a style="color:#21AAF7;text-decoration: none"  href="SellServlet?action=remove1&rname=<%=s.name%>">通过</a></td>
                            <td class="editor2" style="color:#F72121">驳回</td>
                        </tr>
                        <%i++;}%>
                    </table>
                    <%for(Sell s :sells){%>
                    <form method="post" action="SellServlet">
                        <input type="hidden" name="action" value="remove2" />
                        <input type="hidden" name="name" value="<%=s.name%>" />
                    <div class="proleeditor" style="display: none;">
                        <div class="proleeditor1">
                            请填写驳回原因：
                         </div>
                         <div class="proleeditor2">
                             <textarea name='roleexplain' style="width: 186px;height: 116px;margin-left:30px;margin-top:17px"></textarea>
                         </div>
                        <input class="sure3" type="submit" value="提交"/>
                    </div>
                    </form>
                    <%}%>
                </div>
            </div>
            <div class="item">
                <div class="role11">
                    <table  cellpadding="10">
                        <tr style="color: rgba(0,0,0,0.5)">
                            <tr style="color: rgba(0,0,0,0.5)">
                                <td>编号</td><td>日期</td>
                                <td>货品信息</td><td>退货原因</td>
                                <td>货品总数</td><td>退货总价格</td>
                                <td colspan="2">操作</td>
                            </tr>
                        </tr>
                        <%i=1;
                            for(Buy b :buys){
                                if(!b.reason.equals("未退货") && b.check.equals("未审核")){
                        %>
                        <tr class="r1">
                            <td class="role"><%=i%></td><td><%=b.date%></td>
                            <td><%=b.name%></td><td><%=b.reason%></td>
                            <td><%=b.amount%></td><td><%=b.price%></td>
                            <td class="pass2"><a style="color:#21AAF7;text-decoration: none"  href="BuyServlet?action=return1&rname=<%=b.name%>">通过</a></td>
                            <td class="add22" style="color:#F72121">驳回</td>
                        </tr>
                        <%i++;}}%>
                    </table>
                    <%for(Buy b :buys){
                        if(!b.reason.equals("未退货") && b.check.equals("未审核")){%>
                    <form method="post" action="BuyServlet">
                        <input type="hidden" name="action" value="return2" />
                        <input type="hidden" name="name" value="<%=b.name%>" />
                    <div class="proleadd" style="display: none;">
                            <div class="proleadd1">
                                请填写驳回原因：
                            </div>
                            <div class="proleadd2">
                                <textarea name='roleexplain' style="width: 186px;height: 116px;margin-left:30px;margin-top:17px"></textarea>
                            </div>
                        <input class="sure4" type="submit" value="提交"/>
                    </div>
                    </form>
                    <%}}%>
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
        var add=document.querySelectorAll('.add');
        var roleadd=document.querySelectorAll('.roleadd');
        for(let i=0;i<add.length;i++) {
            add[i].onclick=function(){
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
        var editor2=document.querySelectorAll('.editor2');
        var proleeditor=document.querySelectorAll('.proleeditor');
        for(let i=0;i<editor2.length;i++) {
            editor2[i].onclick=function(){
                proleeditor[i].style.display='';
            }
        }
        var add22=document.querySelectorAll('.add22');
        var proleadd=document.querySelectorAll('.proleadd');
        for(let i=0;i<add22.length;i++) {
            add22[i].onclick=function(){
                proleadd[i].style.display='';
            }
        }
        // var username = document.querySelector('#name');
        // username.addEventListener("focus", function () {
        //     this.value = "";
        // })
        // var role=document.querySelector('.role');
        // var rolename=document.querySelector('.rolename');
        // var rolemember=document.querySelector('.rolemember');
        // var roleauthority=document.querySelector('.roleauthority');
        // var roleadd=document.querySelector('.roleadd');
        // role.onclick=function(){
        //     rolename.style.display='';
        // }
        // var member=document.querySelector('.member');
        // member.onclick=function(){
        //     rolemember.style.display='';
        // }
        // var authority=document.querySelector('.authority');
        // authority.onclick=function(){
        //     roleauthority.style.display='';
        // }
        // var add1=document.querySelector('.add1');
        // add1.onclick=function(){
        //     roleadd.style.display='';
        // }
        // var know1 =document.querySelector('.know1');
        // know1.onclick=function(){
        //     rolename.style.display='none';
        // }
        // var know2 =document.querySelector('.know2');
        // know2.onclick=function(){
        //     rolemember.style.display='none';
        // }
        // var know3 =document.querySelector('.know3');
        // know3.onclick=function(){
        //     roleauthority.style.display='none';
        // }
        // var back=document.querySelector('.back');
        // back.onclick=function(){
        //     roleadd.style.display='none';
        // }
        // var role2=document.querySelector('.role2');
        // var prolename=document.querySelector('.prolename');
        // var prolemember=document.querySelector('.prolemember');
        // var proleauthority=document.querySelector('.proleauthority');
        // var proleadd=document.querySelector('.proleadd');
        // role2.onclick=function(){
        //     prolename.style.display='';
        // }
        // var member2=document.querySelector('.member2');
        // member2.onclick=function(){
        //     prolemember.style.display='';
        // }
        // var authority2=document.querySelector('.authority2');
        // authority2.onclick=function(){
        //     proleauthority.style.display='';
        // }
        // var add2=document.querySelector('.add2');
        // add2.onclick=function(){
        //     proleadd.style.display='';
        // }
        // var know21 =document.querySelector('.know21');
        // know21.onclick=function(){
        //     prolename.style.display='none';
        // }
        // var know22 =document.querySelector('.know22');
        // know22.onclick=function(){
        //     prolemember.style.display='none';
        // }
        // var know23 =document.querySelector('.know23');
        // know23.onclick=function(){
        //     proleauthority.style.display='none';
        // }
        // var back2=document.querySelector('.back2');
        // back2.onclick=function(){
        //     proleadd.style.display='none';
        // }
        // var sure=document.querySelector('.sure');//
        // var xinzeng=document.querySelector('.xinzeng');//
        // sure.onclick=function(){
        //     xinzeng.style.display='';
        //     roleadd.style.display='none';
        // }//
        // var sure2=document.querySelector('.sure2');//
        // var xinzeng2=document.querySelector('.xinzeng2');//
        // sure2.onclick=function(){
        //     xinzeng2.style.display='';
        //     proleadd.style.display='none';
        // }//
        // var delete1=document.querySelector('.delete');//
        // var r1=document.querySelector('.r1');//
        // delete1.onclick=function(){
        //     r1.style.display='none';
        // }//
        // var delete2=document.querySelector('.delete2');//
        // var r2=document.querySelector('.r2');//
        // delete2.onclick=function(){
        //     r2.style.display='none';
        // }//

    </script>
</body>
</html>