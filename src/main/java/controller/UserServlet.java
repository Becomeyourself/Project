package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.RoleDao;
import dao.UserDao;
import dao.UserRoleDao;
import dao.impl.RoleDaoImpl;
import dao.impl.UserDaoImpl;
import dao.impl.UserRoleDaoImpl;
import entity.Role;
import entity.User;
import entity.UserRole;


@WebServlet(name = "UserServlet", value = "/UserServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public UserServlet() {
        super();
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	doPost(request, response);
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		String action = request.getParameter("action");
		if(action.equals("delete1")){
			String uname = request.getParameter("uname");
			UserDao ud = new UserDaoImpl();
			ud.remove(uname);
			UserRoleDao urd = new UserRoleDaoImpl();
			urd.removeUser(uname);
			response.sendRedirect("power.jsp");
		}
		else if(action.equals("add1")){
			String uname = request.getParameter("rolename2");
			String pwd = request.getParameter("roleexplain2");
			UserDao ud = new UserDaoImpl();
			ud.add(new User(uname,pwd,"2023-06-06"));
			response.sendRedirect("power.jsp");
		}
		else if(action.equals("assign1")){
			String[] roles = request.getParameterValues("rassign");
			UserRoleDao urd = new UserRoleDaoImpl();
			for(String s : roles){
				String[] ab = s.split(",");
				urd.add(new UserRole(ab[1],ab[0]));
			}
			response.sendRedirect("power.jsp");
		}
	}
}