package controller;

import dao.BuyerDao;
import dao.SellDao;
import dao.SellerDao;
import dao.impl.BuyerDaoImpl;
import dao.impl.SellDaoImpl;
import dao.impl.SellerDaoImpl;
import entity.Buyer;
import entity.Sell;
import entity.Seller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "SellServlet", value = "/SellServlet")
public class SellServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public SellServlet() {
        super();

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        HttpSession session = request.getSession();
        PrintWriter out = response.getWriter();
        String action = request.getParameter("action");
        if (action.equals("add1")) {
            String a = request.getParameter("data1");
            String b = request.getParameter("data2");
            String c = request.getParameter("data3");
            String d = request.getParameter("data4");
            SellDao sd = new SellDaoImpl();
            sd.add(new Sell("2023-01-02",a,b,c,d));
            response.sendRedirect("waiter.jsp");
        }
        else if(action.equals("remove2")){
            String name = request.getParameter("name");
            SellDao sd = new SellDaoImpl();
            sd.remove(name);
            response.sendRedirect("boss.jsp");
        }
        else if(action.equals("remove1")){
            String name = request.getParameter("rname");
            SellDao sd = new SellDaoImpl();
            sd.remove(name);
            response.sendRedirect("boss.jsp");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
