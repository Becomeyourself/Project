<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="dao.*,entity.*,java.util.*" %>
<%@ page import="dao.impl.PowerDaoImpl" %>
<%@ page import="dao.impl.RoleDaoImpl" %>
<%@ page import="dao.impl.UserDaoImpl" %>
<%@ page import="dao.impl.UserRoleDaoImpl" %>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="jquery.js"></script>
    <title>权限管理系统</title>
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
        .prolename,.prolemember,.proleauthority,.proleadd,.roleassign{
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
        .sure,.back,.back2,.back3{
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
        .know1:hover,.know2:hover,.know3:hover,
        .know21:hover,.know22:hover,.know23:hover,
        .sure:hover,.back:hover,
        .back2:hover,.assign:hover,.back3:hover,.inquiry:hover{
            font-weight: 700;
        }
        .back,.back2,.back3{
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
<% PowerDao pd = new PowerDaoImpl();
    List<Power> powers = pd.getAll("select * from 权限管理");
    RoleDao rd = new RoleDaoImpl();
    List<Role> roles = rd.getAll("select * from 角色管理");
    UserDao ud = new UserDaoImpl();
    List<User> users = ud.getAll("select * from 用户信息");
    UserRoleDao urd = new UserRoleDaoImpl();
    List<UserRole> urs = urd.getAll("select * from 用户角色关联表");
%>
<div class="box">
    <div class="top">
        <div class="topl">权限管理系统</div>
        <div class="topr"><img src="../img/个人中心.png" width="10px">个人中心</div>
    </div>
    <div class="left">
        <li class="current">角色管理</li>
        <li>用户管理</li>
        <li>权限管理</li>
    </div>
    <div class="right">
        <div class="item" style="display: block;">
            <div class="role1">
                <input type="search" value="请输入角色名称" id='name' style="color:rgba(0,0,0,0.25)">
                <div class="inquiry" onclick="search1()">查询</div>
                <div class="add1">+添加角色</div>
            </div>
            <div class="role11">
                <table cellpadding="10" >
                    <tr style="color: rgba(0,0,0,0.5)">
                        <td>角色名称</td><td>角色说明</td><td colspan="3">操作</td>
                    </tr>
                    <% for(Role r : roles){
                        String rname = r.getName();
                        String rinform = r.getInform();%>
                    <tr class="rolelist">
                        <td class="role"><%=rname%></td><td><%=rinform%></td>
                        <td class="member" style="color:  #21AAF7">角色成员</td>
                        <td class="authority" style="color:#3A9D32">角色权限</td>
                        <td><a style="color:#F72121;text-decoration: none" onclick="return confirm('确定要删除<%=rname%>角色吗？')" href="RoleServlet?action=delete1&rname=<%=rname%>">删除</a></td>
                    </tr>
                    <%
                        }%>
                </table>
                <% for(Role r : roles){
                    String rname = r.getName();
                    String power = r.getPower();
                    List<String> us = urd.getByRole(rname);

                %>
                <div class="rolename" style="display: none;">
                    <div class="rolename1">以下是对该角色的详细说明：</div>
                    <div class="rolename2">角色：<%=rname%><br>
                        他具有的所有权限，包括<%=power%>
                    </div>
                    <div class="know1">我知道了</div>
                </div>

                <div class="rolemember" style="display: none;">
                    <div class="rolemember1">下面列出该角色具有的全部成员：</div>
                    <div class="rolemember2">
                        <table>
                            <tr>
                                <td style="color: rgba(0, 0, 0, 0.25)">成员账号</td>
                            </tr>
                            <%for(String s : us){
                            %>
                            <tr><td><%=s%></td></tr>
                            <%}%>
                        </table>
                    </div>
                    <div class="know2">我知道了</div>
                </div>
                <% String[] ps = power.split("/");
                %>
                <div class="roleauthority" style="display: none;">
                    <div class="roleauthority1">下面列出该角色具有的全部权限：</div>
                    <div class="roleauthority2">
                        <table>
                            <tr>
                                <td style="color: rgba(0, 0, 0, 0.25)">权限名称</td>
                            </tr>
                            <%for(int i = 0;i<ps.length;i++){%>
                            <tr><td><%=ps[i]%></td></tr>
                            <%}%>
                        </table>
                    </div>
                    <div class="know3">我知道了</div>
                </div>
                <%}%>
                <div class="roleadd" style="display: none;">
                    <form method="post" action="RoleServlet" onsubmit="return check()">
                        <input type="hidden" name="action" value="add1" />
                            <div class="roleadd1">
                                角色名称<input type="text" id='rolename' name='rolename' style="width: 120px;height: 17px;margin-left:20px">
                            </div>
                            <div id="r1" style="color:  #F72121;font-size: 10px"></div>
                            <div class="roleadd2">
                                角色说明<input type="text" id='roleexplain' name='roleexplain' style="width: 120px;height: 17px;margin-left:20px">
                            </div>
                            <div id="r2" style="color:  #F72121;font-size: 10px"></div>
                            <div class="roleadd2">
                                角色权限<input type="text" id='rolepower' name='rolepower' style="width: 120px;height: 17px;margin-left:20px">
                            </div>
                            <div class="back">返回</div>
                            <input class="sure" type="submit" value="确定"/>
                    </form>
                </div>
            </div>
        </div>
        <div class="item">
            <div class="role1">
                <input type="search" value="请输入用户名称" id='name2' style="color:rgba(0,0,0,0.25)">
                <div class="inquiry" onclick="search2()">查询</div>
                <div class="add2">+添加用户</div>
            </div>
            <div class="role11">
                <table cellpadding="10">
                    <tr style="color: rgba(0,0,0,0.5)">
                        <td>用户账号</td><td colspan="4">操作</td>
                    </tr>
                    <% for(User u : users){
                        String uname = u.getName();
                    %>
                    <tr class="rolelist2">
                        <td class="role2"><%=uname%></td>
                        <td class="member2" style="color:  #21AAF7">用户角色</td>
                        <td class="authority2" style="color:#3A9D32">用户权限</td>
                        <td><a style="color:#F72121;text-decoration: none" onclick="return confirm('确定要删除<%=uname%>用户吗？')" href="UserServlet?action=delete1&uname=<%=uname%>">删除</a></td>
                        <td class="assign" style="color:#3A329D">分配角色</td>
                    </tr>
                    <%
                        }%>
                    </tr>
                </table>
                <%for(User u : users){%>
                <div class="prolename" style="display: none;">
                    <div class="rolename1">以下是对该账户的详细说明：</div>
                    <div class="rolename2">
                        账号：<%=u.getName()%><br>
                        密码：<%=u.getPassword()%><br>
                        注册时间：<%=u.getDate()%>
                    </div>
                    <div class="know21">我知道了</div>
                </div>
                <div class="prolemember" style="display: none;">
                    <div class="rolemember1">下面列出该用户所属的全部角色：</div>
                    <%List<String> rs = urd.getByUser(u.getName());%>
                    <div class="rolemember2">
                        <table>
                            <tr>
                                <td style="color: rgba(0, 0, 0, 0.25)">角色名称</td>
                            </tr>
                            <%for(String s : rs){%>
                            <tr><td><%=s%></td></tr>
                            <%}%>
                        </table>
                    </div>
                    <div class="know22">我知道了</div>
                </div>
                <%}%>
                <%for(User u : users){%>
                <div class="proleauthority" style="display: none;">
                    <div class="roleauthority1">下面列出该用户具有的全部权限：</div>
                    <div class="roleauthority2">
                        <table>
                            <%List<String> rs = urd.getByUser(u.getName());
                            List<Role> roles2 = new ArrayList<>();
                                for(String s : rs){
                                    Role r = rd.getByName(s);
                                    roles2.add(r);
                                }
                                List<String> ps = new ArrayList<>();
                                for(Role r : roles2){
                                    String[] ps2 = r.getPower().split("/");
                                    for(String s : ps2){
                                        if(!ps.contains(s)){
                                            ps.add(s);
                                        }
                                    }
                                }
                                for(String s : ps){%>
                            <tr>
                                <td><%=s%></td>
                            </tr>
                            <%}%>
                        </table>
                    </div>
                    <div class="know23">我知道了</div>
                </div>
                <%}%>
                <%for(User u : users){%>
                <div class="roleassign" style="display: none;">
                    <form method="post" action="UserServlet">
                        <input type="hidden" name="action" value="assign1" />
                        <div class="roleauthority1">
                            请选择你要给该用户分配的角色：
                        </div>
                    <% List<String> r3 = urd.getByUser(u.getName());
                    %>
                        <div class="roleauthority2">
                            <table>
                            <%for(Role r : roles){
                                if(!r3.contains(r.getName())){
                                    String a = r.getName();
                            %>
                                <tr>
                                    <td><input name='rassign' value="<%=a+","+u.getName()%>" type="checkbox"></td><td><%=a%></td>
                                </tr>
                            <%}}%>
                            </table>
                        </div>
                        <div class="back3">返回</div>
                        <input class="sure" type="submit" value="确定"/>
                    </form>
                </div>
                <%}%>
                <div class="proleadd" style="display: none;">
                    <form method="post" action="UserServlet" onsubmit="return check2()">
                        <input type="hidden" name="action" value="add1" />
                        <div class="roleadd1">
                            用户账号<input type="text" id='rolename2' name='rolename2' style="width: 120px;height: 17px;margin-left:20px">
                        </div>
                        <div id="r3" style="color:  #F72121;font-size: 10px"></div>
                        <div class="roleadd2">
                            用户密码<input type="text" id='roleexplain2' name='roleexplain2' style="width: 120px;height: 17px;margin-left:20px">
                        </div>
                        <div id="r4" style="color:  #F72121;font-size: 10px"></div>
                        <div class="back2">返回</div>
                        <input class="sure" type="submit" value="确定"/>
                    </form>
                </div>
            </div>
        </div>
        <div class="item">
            <table cellpadding="10" class="atable">
                <tr style="color: rgba(0,0,0,0.5)">
                    <td>权限名称</td><td>权限说明</td>
                </tr>
                <% for(Power p : powers){
                    String pname = p.getName();
                    String pinform = p.getInform();
                %>
                <tr>
                    <td><%=pname%></td>
                    <td><%=pinform%></td>
                </tr>
                <%
                    }%>
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
    var username2 = document.querySelector('#name2');
    username2.addEventListener("focus", function () {
        this.value = "";
    })
    var role=document.querySelectorAll('.role');
    var rolename=document.querySelectorAll('.rolename');
    var rolemember=document.querySelectorAll('.rolemember');
    var roleauthority=document.querySelectorAll('.roleauthority');
    var roleadd=document.querySelector('.roleadd');
    for(let i=0;i<role.length;i++) {
        role[i].onclick=function(){
            rolename[i].style.display='';
        }
    }
    var member=document.querySelectorAll('.member');
    for(let i=0;i<member.length;i++) {
        member[i].onclick=function(){
            rolemember[i].style.display='';
        }
    }
    var authority=document.querySelectorAll('.authority');
    for(let i=0;i<authority.length;i++) {
        authority[i].onclick=function(){
            roleauthority[i].style.display='';
        }
    }
    var add1=document.querySelector('.add1');
    add1.onclick=function(){
        roleadd.style.display='';
    }
    var know1 =document.querySelectorAll('.know1');
    for(let i=0;i<know1.length;i++) {
        know1[i].onclick=function(){
            rolename[i].style.display='none';
        }
    }
    var know2 =document.querySelectorAll('.know2');
    for(let i=0;i<know2.length;i++) {
        know2[i].onclick=function(){
            rolemember[i].style.display='none';
        }
    }
    var know3 =document.querySelectorAll('.know3');
    for(let i=0;i<know3.length;i++) {
        know3[i].onclick=function(){
            roleauthority[i].style.display='none';
        }
    }
    var back=document.querySelector('.back');
    back.onclick=function(){
        roleadd.style.display='none';
    }
    var roleassign=document.querySelectorAll('.roleassign');
    var assign=document.querySelectorAll('.assign');
    for(let i=0;i<assign.length;i++) {
        assign[i].onclick=function(){
            roleassign[i].style.display='';
        }
    }
    var role2=document.querySelectorAll('.role2');
    var prolename=document.querySelectorAll('.prolename');
    var prolemember=document.querySelectorAll('.prolemember');
    var proleauthority=document.querySelectorAll('.proleauthority');
    var proleadd=document.querySelector('.proleadd');
    for(let i=0;i<role2.length;i++) {
        role2[i].onclick=function(){
            prolename[i].style.display='';
        }
    }
    var member2=document.querySelectorAll('.member2');
    for(let i=0;i<member2.length;i++) {
        member2[i].onclick=function(){
            prolemember[i].style.display='';
        }
    }
    var authority2=document.querySelectorAll('.authority2');
    for(let i=0;i<authority2.length;i++) {
        authority2[i].onclick=function(){
            proleauthority[i].style.display='';
        }
    }
    var add2=document.querySelector('.add2');
    add2.onclick=function(){
        proleadd.style.display='';
    }
    var know21 =document.querySelectorAll('.know21');
    for(let i=0;i<know21.length;i++) {
        know21[i].onclick=function(){
            prolename[i].style.display='none';
        }
    }
    var know22 =document.querySelectorAll('.know22');
    for(let i=0;i<know22.length;i++) {
        know22[i].onclick=function(){
            prolemember[i].style.display='none';
        }
    }
    var know23 =document.querySelectorAll('.know23');
    for(let i=0;i<know23.length;i++) {
        know23[i].onclick=function(){
            proleauthority[i].style.display='none';
        }
    }
    var back2=document.querySelector('.back2');
    back2.onclick=function(){
        proleadd.style.display='none';
    }
    var back3=document.querySelectorAll('.back3');
    for(let i=0;i<back3.length;i++) {
        back3[i].onclick=function(){
            roleassign[i].style.display='none';
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
    function check() {
        var irolename=document.getElementById("rolename").value;
        var iroleexplain=document.getElementById("roleexplain").value;
        if(irolename==""){
            // alert("角色名称不能为空");
            $("#r1").html("*角色名称不能为空");
            return false;
        }
        if(iroleexplain==""){
            $("#r2").html("*角色说明不能为空");
            // alert("角色说明不能为空");
            return false;
        }
        return true;
    }
    var crolename=document.getElementById("rolename");
    var croleexplain=document.getElementById("roleexplain");
    crolename.onclick=function(){
        $("#r1").html("");
    }
    croleexplain.onclick=function(){
        $("#r2").html("");
    }
    function check2() {
        var irolename2=document.getElementById("rolename2").value;
        var iroleexplain2=document.getElementById("roleexplain2").value;
        if(irolename2==""||irolename2.length>64){
            $("#r3").html("*用户账号不能为空，最大长度为64个字符");
            return false;
        }
        if(iroleexplain2==""||iroleexplain2.length>6){
            $("#r4").html("*用户密码不能为空，最大长度为6");
            return false;
        }
        return true;
    }
    var crolename2=document.getElementById("rolename2");
    var croleexplain2=document.getElementById("roleexplain2");
    crolename2.onclick=function(){
        $("#r3").html("");
    }
    croleexplain2.onclick=function(){
        $("#r4").html("");
    }
</script>
</body>
</html>