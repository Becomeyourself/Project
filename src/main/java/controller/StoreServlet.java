package controller;

import dao.BuyerDao;
import dao.GoodDao;
import dao.IndataDao;
import dao.StoreDao;
import dao.impl.BuyerDaoImpl;
import dao.impl.GoodDaoImpl;
import dao.impl.IndataDaoImpl;
import dao.impl.StoreDaoImpl;
import entity.Buyer;
import entity.Good;
import entity.Indata;
import entity.Store;
import util.DBUtil3;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "StoreServlet", value = "/StoreServlet")
public class StoreServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public StoreServlet() {
        super();

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        HttpSession session = request.getSession();
        PrintWriter out = response.getWriter();
        String action = request.getParameter("action");
        if(action.equals("delete1")){
            String name = request.getParameter("gnumber");
            String storename = request.getParameter("snumber");
            StoreDao sd = new StoreDaoImpl();
            sd.remove(name,storename);
            response.sendRedirect("store1.jsp?storeID="+storename.substring(2));
        }
        else if(action.equals("add1")){
            String storename = request.getParameter("name2");
            String name = request.getParameter("rolename2");
            String inform = request.getParameter("price2");
            String method = request.getParameter("amount2");
            String type = request.getParameter("outprice2");
            String address = request.getParameter("people2");
            String number = request.getParameter("number2");
            StoreDao sd = new StoreDaoImpl();
            sd.add(new Store("仓库"+storename,number,name,inform,method,type,address));
            response.sendRedirect("store1.jsp?storeID="+storename);
        }
        else if(action.equals("editor1")){
            String storename = request.getParameter("name");
            String name = request.getParameter("rolename");
            String inform = request.getParameter("price");
            String method = request.getParameter("amount");
            String type = request.getParameter("outprice");
            String address = request.getParameter("people");
            String number = request.getParameter("number");
            StoreDao sd = new StoreDaoImpl();
            sd.remove(number,"仓库"+storename);
            sd.add(new Store("仓库"+storename,number,name,inform,method,type,address));
            response.sendRedirect("store1.jsp?storeID="+storename);
        }
        else if(action.equals("move1")){
            String storename = request.getParameter("name");
            String number = request.getParameter("number");
            String data = request.getParameter("data");
            String to = request.getParameter("to");
            StoreDao sd = new StoreDaoImpl();
            Store s = sd.getByNumber(number,storename);
            data = data.substring(0,data.length()-1);
            Double data2 = Double.valueOf(s.number.substring(0,s.number.length()-1));
            data2 -= Double.valueOf(data);
            sd.remove(number,storename);
            s.number = String.valueOf(data2)+"吨";
            sd.add(s);
            Store s2 = sd.getByNumber(number,to);
            if(s2!= null){
                sd.remove(number,to);
                data2 = Double.valueOf(s2.number.substring(0,s.number.length()-1));
                data2 += Double.valueOf(data);
                s2.number = String.valueOf(data2)+"吨";
                sd.add(s2);
            }
            else{
                s.storename = to;
                s.number = data+"吨";
                sd.add(s);
            }
            response.sendRedirect("store1.jsp?storeID="+storename.charAt(2));
        }
        else if(action.equals("in1")){
            String storename = request.getParameter("name3");
            String date = "2023-06-27";
            String number = request.getParameter("number3");
            StoreDao sd = new StoreDaoImpl();
            Store s = sd.getByNumber(number,storename);
            Double x = Double.valueOf(s.inprice.substring(0,s.inprice.length()-3));
            Double y = Double.valueOf(request.getParameter("indata").substring(0,request.getParameter("indata").length()-1));
            String s2 = String.valueOf(x*y/5);
            Indata in = new Indata(date,s.goodname,s.inprice,s2+"万",storename,s.people);
            IndataDao id = new IndataDaoImpl();
            id.add(in);
            response.sendRedirect("store1.jsp?storeID="+storename.charAt(2));
        }
        else if(action.equals("out1")){
            String storename = request.getParameter("name4");
            String number = request.getParameter("number4");
            String outdata = request.getParameter("outdata");
            StoreDao sd = new StoreDaoImpl();
            Store s = sd.getByNumber(number,storename);
            sd.remove(number,storename);
            Double x = Double.valueOf(s.number.substring(0,s.number.length()-1));
            x -= Double.valueOf(outdata.substring(0,outdata.length()-1));
            s.number = String.valueOf(x)+"吨";
            sd.add(s);
            response.sendRedirect("store1.jsp?storeID="+storename.charAt(2));
        }
        else if(action.equals("remove2")){
            String name = request.getParameter("name7");
            String storename = request.getParameter("storename7");
            IndataDao id = new IndataDaoImpl();
            id.remove(name,storename);
            response.sendRedirect("boss.jsp");
        }
        else if(action.equals("remove3")){
            String name = request.getParameter("name5");
            String storename = request.getParameter("storename5");
            IndataDao id = new IndataDaoImpl();
            List<Indata> indatas = id.getAll("select * from indata");
            Indata a =null;
            for(Indata in: indatas){
                if(in.goodname.equals(name) && in.storename.equals(storename)){
                    a = in;
                }
            }
            id.remove(name,storename);
            a.check = "已付款";
            Double x = Double.valueOf(a.price.substring(0,a.price.length()-1))/Double.valueOf(a.inprice.substring(0,a.inprice.length()-3))*5;
            StoreDao sd = new StoreDaoImpl();
            Store s = sd.getByNamea(name,storename);
            id.add(a);
            sd.remove(s.goodnumber,s.storename);
            x += Double.valueOf(s.number.substring(0,s.number.length()-1));
            s.number = String.valueOf(x)+"吨";
            sd.add(s);
            response.sendRedirect("boss.jsp");
        }
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}


