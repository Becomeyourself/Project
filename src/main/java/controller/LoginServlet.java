package controller;
import com.alibaba.fastjson.JSON;
import dao.LoginDao;
import dao.UserRoleDao;
import dao.impl.LoginDaoImpl;
import dao.impl.UserRoleDaoImpl;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "LoginServlet", value = "/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public LoginServlet() {
        super();

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");
        String uid = request.getParameter("id");
        String pwd = request.getParameter("password");
        String type = request.getParameter("role");
        LoginDao dao =  new LoginDaoImpl();
        UserRoleDao urd = new UserRoleDaoImpl();
        PrintWriter out = response.getWriter();
        if(dao.login(uid, pwd) && urd.getByUser(uid).contains(type)) {
            if(type.equals("管理员")){
                out.println(JSON.toJSONString(1));
            }
            else if(type.equals("科研人员")){
                out.println(JSON.toJSONString(2));
            }
            else if(type.equals("学生")){
                out.println(JSON.toJSONString(3));
            }
            else if(type.equals("老师")){
                out.println(JSON.toJSONString(4));
            }
            else if(type.equals("学校领导")){
                out.println(JSON.toJSONString(5));
            }
            else if(type.equals("校长")){
                out.println(JSON.toJSONString(6));
            }
        }
        else {
            out.println(JSON.toJSONString(9));
        }
    }

}
