package controller;

import dao.BuyerDao;
import dao.SellerDao;
import dao.impl.BuyerDaoImpl;
import dao.impl.SellerDaoImpl;
import entity.Buyer;
import entity.Seller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "SellerServlet", value = "/SellerServlet")
public class SellerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public SellerServlet() {
        super();

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        HttpSession session = request.getSession();
        PrintWriter out = response.getWriter();
        String action = request.getParameter("action");
        if (action.equals("delete1")) {
            String name = request.getParameter("sname");
            SellerDao sd = new SellerDaoImpl();
            sd.remove(name);
            response.sendRedirect("inform.jsp");
        } else if (action.equals("add1")) {
            String name = request.getParameter("rolename4");
            String inform = request.getParameter("roleconnect4");
            String method = request.getParameter("rolepay4");
            String type = request.getParameter("roletype4");
            String address = request.getParameter("rolead4");
            SellerDao sd = new SellerDaoImpl();
            sd.add(new Seller(name,inform,method,type,address));
            response.sendRedirect("inform.jsp");
        }
        else if(action.equals("editor1")){
            String name = request.getParameter("prolename");
            String inform = request.getParameter("proleconnect");
            String method = request.getParameter("prolepay");
            String type = request.getParameter("proletype");
            String address = request.getParameter("prolead");
            SellerDao sd = new SellerDaoImpl();
            sd.remove(name);
            sd.add(new Seller(name,inform,method,type,address));
            response.sendRedirect("inform.jsp");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
