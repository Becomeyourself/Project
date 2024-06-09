package controller;

import dao.BuyerDao;
import dao.GoodDao;
import dao.impl.BuyerDaoImpl;
import dao.impl.GoodDaoImpl;
import entity.Buyer;
import entity.Good;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "GoodServlet", value = "/GoodServlet")
public class GoodServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public GoodServlet() {
        super();

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        HttpSession session = request.getSession();
        PrintWriter out = response.getWriter();
        String action = request.getParameter("action");
        if(action.equals("delete1")){
            String name = request.getParameter("gname");
            GoodDao gd = new GoodDaoImpl();
            gd.remove(name);
            response.sendRedirect("store.jsp");
        }
        else if(action.equals("add1")){
            String name = request.getParameter("rolename2");
            String inform = request.getParameter("roleexplain2");
            GoodDao gd = new GoodDaoImpl();
            gd.add(new Good(name,inform));
            response.sendRedirect("store.jsp");
        }
        else if(action.equals("editor1")){
            String name = request.getParameter("rolename");
            String inform = request.getParameter("roleexplain");
            GoodDao gd = new GoodDaoImpl();
            gd.remove(name);
            gd.add(new Good(name,inform));
            response.sendRedirect("store.jsp");
        }
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}

