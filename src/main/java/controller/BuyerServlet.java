package controller;

import dao.BuyerDao;
import dao.impl.BuyerDaoImpl;
import entity.Buyer;
import dao.cateDao;
import dao.impl.cateDaolmpl;
import entity.cate;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "BuyerServlet", value = "/BuyerServlet")
public class BuyerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public BuyerServlet() {
        super();

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        HttpSession session = request.getSession();
        PrintWriter out = response.getWriter();
        String action = request.getParameter("action");
        if(action.equals("delete1")){
            String name = request.getParameter("bname");
            BuyerDao bd = new BuyerDaoImpl();
            bd.remove(name);
            response.sendRedirect("inform.jsp");
        }
        else if(action.equals("add1")){
            String name = request.getParameter("rolename2");
            String inform = request.getParameter("roleconnect2");
            String method = request.getParameter("rolepay2");
            String type = request.getParameter("roletype2");
            String address = request.getParameter("rolead2");
            String category=request.getParameter("roleca2");
            BuyerDao bd = new BuyerDaoImpl();
            bd.add(new Buyer(name,inform,method,type,address,category));
            response.sendRedirect("inform.jsp");
        }
        else if(action.equals("editor1")){
            String name = request.getParameter("rolename");
            String inform = request.getParameter("roleconnect");
            String method = request.getParameter("rolepay");
            String type = request.getParameter("roletype");
            String address = request.getParameter("rolead");
            String category=request.getParameter("roleca");
            cateDao cd = new cateDaolmpl();
            cate cat=cd.getByName(category);
            BuyerDao bd = new BuyerDaoImpl();
            if(cat==null) {

            }
            else{
                bd.remove(name);
                bd.add(new Buyer(name,inform,method,type,address,category));
            }
            response.sendRedirect("inform.jsp");

        }
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}

