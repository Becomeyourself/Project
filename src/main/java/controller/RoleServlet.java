package controller;

import dao.RoleDao;
import dao.UserDao;
import dao.UserRoleDao;
import dao.impl.RoleDaoImpl;
import dao.impl.UserDaoImpl;
import dao.impl.UserRoleDaoImpl;
import entity.Role;
import entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "RoleServlet", value = "/RoleServlet")
public class RoleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public RoleServlet() {
        super();
        
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		String action = request.getParameter("action");
		if(action.equals("delete1")){
			String rname = request.getParameter("rname");
			RoleDao rd = new RoleDaoImpl();
			rd.remove(rname);
			UserRoleDao urd = new UserRoleDaoImpl();
			urd.removerRole(rname);
			response.sendRedirect("power.jsp");
		}
		else if(action.equals("add1")){
			String rname = request.getParameter("rolename");
			String rinform = request.getParameter("roleexplain");
			String rpower = request.getParameter("rolepower");
			RoleDao rd = new RoleDaoImpl();
			rd.add(new Role(rname,rinform,rpower));
			response.sendRedirect("power.jsp");
		}
		else if(action.equals("search1")){
			String rname = request.getParameter("name");
			response.sendRedirect("power.jsp");
		}
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	doPost(request,response);
	}
}
