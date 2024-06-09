<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
        .reserve{
            background-color: #FFFDFD;
            font-size: 12px;
            color:#21AAF7;
            border: none;
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
        .add1:hover,.add2:hover,.editor:hover,.editor2:hover,.reserve:hover{
            font-weight: 700;
        }
        .roleadd,.roleeditor,
        .instock,.outstock{
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
        .roleadd1,.roleadd2,.roleeditor1,.instock1,.outstock1{
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
        .sure4:hover,.back4:hover{
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
        .atable{
            margin-left: 30px;
            margin-top: 100.5px;
        }
    </style>
</head>
<body>
    <div class="box">
        <div class="top">
            <div class="topl">批发零售业务管理系统</div>
            <div class="topr"><img src="个人中心.png" width="10px">个人中心</div>
        </div>
        <div class="left">
            <li class="current">普通销售管理</li>
        </div>
        <div class="right">
            <div class="item" style="display: block;">
                <div class="role11">
                    <table class="table1" cellpadding="10" cellspacing="0" >
                        <form method="post" action="SellServlet">
                            <input type="hidden" name="action" value="add1" />
                        <tr style="color: rgba(0,0,0,0.5)">
                            <td>编号</td><td>日期</td>
                            <td>货品信息</td><td>客户地址</td>
                            <td>客户电话</td><td>货品销售总价</td>
                            <td>操作</td>
                        </tr>
                        <tr class="r1">
                            <td class="role">1</td>
                            <td>2023-01-02</td>
                            <td><input type="text" name="data1" style="width: 70px;"/></td>
                            <td><input type="text" name="data2" style="width: 70px;"/></td>
                            <td><input type="text" name="data3" style="width: 70px;"/></td>
                            <td><input type="text" name="data4" style="width: 70px;"/></td>
                            <td><input class="reserve" type="submit" value="提交"/></td>
                        </tr>
                        </form>
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
        // var roleadd=document.querySelector('.roleadd');
        // var add1=document.querySelector('.add1');
        // add1.onclick=function(){
        //     roleadd.style.display='';
        // }
        // var back=document.querySelector('.back');
        // back.onclick=function(){
        //     roleadd.style.display='none';
        // }
        // var editor=document.querySelector('.editor');
        // var roleeditor=document.querySelector('.roleeditor');
        // editor.onclick=function(){
        //     roleeditor.style.display='';
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
        // var in1=document.querySelector('.in');
        // var out1=document.querySelector('.out');
        // var instock=document.querySelector('.instock');
        // var outstock=document.querySelector('.outstock');
        // in1.onclick=function(){
        //    instock.style.display='';
        // }
        // out1.onclick=function(){
        //    outstock.style.display='';
        // }
        // var back2=document.querySelector('.back2');
        // back2.onclick=function(){
        //     instock.style.display='none';
        // }
        // var back4=document.querySelector('.back4');
        // back4.onclick=function(){
        //     outstock.style.display='none';
        // }
    </script>
</body>
</html>