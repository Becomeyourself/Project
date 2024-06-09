package controller;

import dao.*;
import dao.impl.*;
import entity.Buy;
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

@WebServlet(name = "BuyServlet", value = "/BuyServlet")
public class BuyServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public BuyServlet() {
        super();

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        HttpSession session = request.getSession();
        PrintWriter out = response.getWriter();
        String action = request.getParameter("action");
        if (action.equals("buy1")) {
            String name = request.getParameter("goodname");
            Double x = Double.valueOf(request.getParameter("data"));
            BuyDao bd = new BuyDaoImpl();
            StoreDao sd = new StoreDaoImpl();
            Double y = sd.getByName(name);
            y = x*y;
            bd.add(new Buy("2023-01-02",name,"未退货",String.valueOf(x),String.valueOf(y),"未审核","未驳回"));
            response.sendRedirect("client.jsp");
        } else if (action.equals("buy2")) {
            String name = request.getParameter("name");
            String reson = request.getParameter("roleexplain");
            BuyDao bd = new BuyDaoImpl();
            Buy b = bd.getByName(name);
            bd.remove(name);
            b.reason = reson;
            bd.add(b);
            response.sendRedirect("client.jsp");
        }
        else if(action.equals("return2")){
            String name = request.getParameter("name");
            String reson2 = request.getParameter("roleexplain");
            BuyDao bd = new BuyDaoImpl();
            Buy b = bd.getByName(name);
            bd.remove(name);
            b.reson2 = reson2;
            b.check = "已审核";
            bd.add(b);
            response.sendRedirect("boss.jsp");
        }
        else if(action.equals("return1")){
            String name = request.getParameter("rname");
            BuyDao bd = new BuyDaoImpl();
            Buy b = bd.getByName(name);
            bd.remove(name);
            b.check = "已审核";
            bd.add(b);
            response.sendRedirect("boss.jsp");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
