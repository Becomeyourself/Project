<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="power.css">
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
        .role11 table,.atable{
            width: 853.5px;
            text-align: center;
            font-size: 12px;
            color:#000;
            background-color: #FFFDFD;
            border: 1px solid #D9D9D9;
        }
        table .role:hover,table .member:hover,table .authority:hover,.add1:hover,
        table .role2:hover,table .member2:hover,table .authority2:hover,.add2:hover{
            font-weight: 700;
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
        .roleadd1,.roleadd2{
            font-size: 12px;
            color: rgba(0, 0, 0, 0.66);
            margin-left: 27px;
            margin-top: 40px;
        }
        .roleadd2{
            margin-top: 24px;
        }
        .know1,.know2,.know3,.know21,.know22,.know23,
        .sure,.back,.sure2,.back2{
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
        .know1:hover,.know2:hover,.know3:hover,
        .know21:hover,.know22:hover,.know23:hover,
        .sure:hover,.back:hover,
        .sure2:hover,.back2:hover{
            font-weight: 700;
        }
        .back,.back2{
            right:113px;
            background-color: #e76161;
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
        <div class="topl">论文管理系统</div>
        <div class="topr"><img src="img/个人中心.png" width="10px">个人中心</div>
    </div>
    <div class="left">
        <li class="current">角色管理</li>
        <li>用户管理</li>
        <li>权限管理</li>
    </div>
    <div class="right">
        <div class="item" style="display: block;">
            <div class="role1">
                <input type="text" value="请输入角色名称" id='name' style="color:rgba(0,0,0,0.25)">
                <div class="inquiry">查询</div>
                <div class="add1">+添加角色</div>
            </div>
            <div class="role11">
                <table cellpadding="10" >
                    <tr style="color: rgba(0,0,0,0.5)">
                        <td>角色名称</td><td>角色说明</td><td colspan="3">操作</td>
                    </tr>
                    <tr>
                        <td class="role">管理员</td><td>具有系统所有权限</td>
                        <td class="member" style="color:  #21AAF7">角色成员</td>
                        <td class="authority" style="color:#3A9D32">角色权限</td>
                        <td style="color:#F72121">删除</td>
                    </tr>
                    <tr>
                        <td>角色2</td><td>角色说明2</td>
                        <td style="color:  #21AAF7">角色成员</td>
                        <td style="color:#3A9D32">角色权限</td>
                        <td style="color:#F72121">删除</td>
                    </tr>
                    <tr>
                        <td>角色3</td><td>角色说明3</td>
                        <td style="color:  #21AAF7">角色成员</td>
                        <td style="color:#3A9D32">角色权限</td>
                        <td style="color:#F72121">删除</td>
                    </tr>
                </table>
                <div class="rolename" style="display: none;">
                    <div class="rolename1">以下是对该角色的详细说明：</div>
                    <div class="rolename2">角色：管理员<br>
                        他具有系统的所有权限，如增加用户，
                        删除用户，它也可以对论文进行增删改查
                        .....</div>
                    <div class="know1">我知道了</div>
                </div>
                <div class="rolemember" style="display: none;">
                    <div class="rolemember1">下面列出该角色具有的全部成员：</div>
                    <div class="rolemember2">
                        <table>
                            <tr>
                                <td style="color: rgba(0, 0, 0, 0.25)">成员账号</td>
                            </tr>
                            <tr><td>10086</td></tr>
                            <tr><td>账号2</td></tr>
                            <tr><td>账号3</td></tr>
                        </table>
                    </div>
                    <div class="know2">我知道了</div>
                </div>
                <div class="roleauthority" style="display: none;">
                    <div class="roleauthority1">下面列出该角色具有的全部权限：</div>
                    <div class="roleauthority2">
                        <table>
                            <tr>
                                <td style="color: rgba(0, 0, 0, 0.25)">权限名称</td>
                            </tr>
                            <tr><td>修改订单信息</td></tr>
                            <tr><td>权限2</td></tr>
                            <tr><td>权限3</td></tr>
                        </table>
                    </div>
                    <div class="know3">我知道了</div>
                </div>
                <div class="roleadd" style="display: none;">
                    <div class="roleadd1">
                        角色名称<input type="text" id='rolename' style="width: 120px;height: 17px;margin-left:20px">
                    </div>
                    <div class="roleadd2">
                        角色说明<input type="text" id='roleexplain' style="width: 120px;height: 17px;margin-left:20px">
                    </div>
                    <div class="back">返回</div>
                    <div class="sure">确定</div>
                </div>
            </div>
        </div>
        <div class="item">
            <div class="role1">
                <input type="text" value="请输入角色名称" id='name' style="color:rgba(0,0,0,0.25)">
                <div class="inquiry">查询</div>
                <div class="add2">+添加用户</div>
            </div>
            <div class="role11">
                <table cellpadding="10">
                    <tr style="color: rgba(0,0,0,0.5)">
                        <td>用户账号</td><td colspan="3">操作</td>
                    </tr>
                    <tr>
                        <td class="role2">10086</td>
                        <td class="member2" style="color:  #21AAF7">用户角色</td>
                        <td class="authority2" style="color:#3A9D32">用户权限</td>
                        <td style="color:#F72121">删除</td>
                    </tr>
                    <tr>
                        <td>用户账号2</td>
                        <td style="color:  #21AAF7">用户角色</td>
                        <td style="color:#3A9D32">用户权限</td>
                        <td style="color:#F72121">删除</td>
                    </tr>
                    <tr>
                        <td>用户账号3</td>
                        <td style="color:  #21AAF7">用户角色</td>
                        <td style="color:#3A9D32">用户权限</td>
                        <td style="color:#F72121">删除</td>
                    </tr>
                </table>
                <div class="prolename" style="display: none;">
                    <div class="rolename1">以下是对该账户的详细说明：</div>
                    <div class="rolename2">
                        账号：10086<br>
                        密码：123456<br>
                        注册时间：2022-02-09
                    </div>
                    <div class="know21">我知道了</div>
                </div>
                <div class="prolemember" style="display: none;">
                    <div class="rolemember1">下面列出该用户所属的全部角色：</div>
                    <div class="rolemember2">
                        <table>
                            <tr>
                                <td style="color: rgba(0, 0, 0, 0.25)">角色名称</td>
                            </tr>
                            <tr><td>管理员</td></tr>
                            <tr><td>角色2</td></tr>
                            <tr><td>角色3</td></tr>
                        </table>
                    </div>
                    <div class="know22">我知道了</div>
                </div>
                <div class="proleauthority" style="display: none;">
                    <div class="roleauthority1">下面列出该用户具有的全部权限：</div>
                    <div class="roleauthority2">
                        <table>
                            <tr>
                                <td style="color: rgba(0, 0, 0, 0.25)">权限名称</td>
                            </tr>
                            <tr><td>修改订单信息</td></tr>
                            <tr><td>权限2</td></tr>
                            <tr><td>权限3</td></tr>
                        </table>
                    </div>
                    <div class="know23">我知道了</div>
                </div>
                <div class="proleadd" style="display: none;">
                    <div class="roleadd1">
                        用户账号<input type="text" id='rolename' style="width: 120px;height: 17px;margin-left:20px">
                    </div>
                    <div class="roleadd2">
                        用户密码<input type="password" id='roleexplain' style="width: 120px;height: 17px;margin-left:20px">
                    </div>
                    <div class="back2">返回</div>
                    <div class="sure2">确定</div>
                </div>
            </div>
        </div>
        <div class="item">
            <table cellpadding="10" class="atable">
                <tr style="color: rgba(0,0,0,0.5)">
                    <td>权限名称</td><td>权限说明</td>
                </tr>
                <tr>
                    <td>管理员权限</td>
                    <td>可以对所有角色的信息进行管理</td>
                </tr>
                <tr>
                    <td>权限2</td>
                    <td>权限说明2</td>
                </tr>
                <tr>
                    <td>权限3</td>
                    <td>权限说明3</td>
                </tr>
            </table>
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
    var role=document.querySelector('.role');
    var rolename=document.querySelector('.rolename');
    var rolemember=document.querySelector('.rolemember');
    var roleauthority=document.querySelector('.roleauthority');
    var roleadd=document.querySelector('.roleadd');
    role.onclick=function(){
        rolename.style.display='';
    }
    var member=document.querySelector('.member');
    member.onclick=function(){
        rolemember.style.display='';
    }
    var authority=document.querySelector('.authority');
    authority.onclick=function(){
        roleauthority.style.display='';
    }
    var add1=document.querySelector('.add1');
    add1.onclick=function(){
        roleadd.style.display='';
    }
    var know1 =document.querySelector('.know1');
    know1.onclick=function(){
        rolename.style.display='none';
    }
    var know2 =document.querySelector('.know2');
    know2.onclick=function(){
        rolemember.style.display='none';
    }
    var know3 =document.querySelector('.know3');
    know3.onclick=function(){
        roleauthority.style.display='none';
    }
    var back=document.querySelector('.back');
    back.onclick=function(){
        roleadd.style.display='none';
    }
    var role2=document.querySelector('.role2');
    var prolename=document.querySelector('.prolename');
    var prolemember=document.querySelector('.prolemember');
    var proleauthority=document.querySelector('.proleauthority');
    var proleadd=document.querySelector('.proleadd');
    role2.onclick=function(){
        prolename.style.display='';
    }
    var member2=document.querySelector('.member2');
    member2.onclick=function(){
        prolemember.style.display='';
    }
    var authority2=document.querySelector('.authority2');
    authority2.onclick=function(){
        proleauthority.style.display='';
    }
    var add2=document.querySelector('.add2');
    add2.onclick=function(){
        proleadd.style.display='';
    }
    var know21 =document.querySelector('.know21');
    know21.onclick=function(){
        prolename.style.display='none';
    }
    var know22 =document.querySelector('.know22');
    know22.onclick=function(){
        prolemember.style.display='none';
    }
    var know23 =document.querySelector('.know23');
    know23.onclick=function(){
        proleauthority.style.display='none';
    }
    var back2=document.querySelector('.back2');
    back2.onclick=function(){
        proleadd.style.display='none';
    }
</script>
</body>
</html>
