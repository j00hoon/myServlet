package controller;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/views/cal3")
public class calculator3 extends HttpServlet
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

            // Cookie는 특정 url의 값만 저장을 할 수도 있고, --> setPath(~~)을 이용
            // WAS가 종료된 후에도 값을 저장할 수 있다. --> setMaxAge(~~)을 이용
            Cookie valueCookie = new Cookie("value", String.valueOf(v));
            Cookie opCookie = new Cookie("op", op);

            valueCookie.setPath("/views/cal2");
            // 60seconds * 5 means 5 minutes
            valueCookie.setMaxAge(60 * 5);
            opCookie.setPath("/views/cal2");

            resp.addCookie(valueCookie);
            resp.addCookie(opCookie);

            resp.sendRedirect("/views/calculator2.html");

        }// else


    }
}
