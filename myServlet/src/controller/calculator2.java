package controller;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/views/cal2")
public class calculator2 extends HttpServlet
{
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html; charset=utf-8");

        String op = req.getParameter("opBtn");
        String num = req.getParameter("value");
        int v = 0;

        ServletContext application = req.getServletContext();
        HttpSession session = req.getSession();
        PrintWriter out = resp.getWriter();

        if(!num.equals(""))
        {
            v = Integer.parseInt(num);
        }// if

        if(op.equals("="))
        {
            //int n1 = (int) application.getAttribute("value");
            int n1 = (int) session.getAttribute("value");
            int n2 = v;
            //String opAddOrSub = (String) application.getAttribute("op");
            String opAddOrSub = (String) session.getAttribute("op");
            int res = 0;

            if(opAddOrSub.equals("+"))
            {
                res = n1 + n2;
            }// if
            else if(opAddOrSub.equals("-"))
            {
                res = n1 - n2;
            }// else if

            out.printf("Result : %d", res);
            out.println("<div>");
            out.println("<input type=\"button\" onclick=\"location.href='../index.jsp'\" value=\"Home!\" />");
            out.println("</div>");

        }// if
        else
        {
            //application.setAttribute("value", v);
            //application.setAttribute("op", op);
            session.setAttribute("value", v);
            session.setAttribute("op", op);
        }// else


    }
}
