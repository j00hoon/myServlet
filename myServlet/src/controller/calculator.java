package controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/views/add")
public class calculator extends HttpServlet
{
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html; charset=utf-8");

        int num1 = 0;
        int num2 = 0;

        if(!req.getParameter("num1").equals(""))
        {
            num1 = Integer.parseInt(req.getParameter("num1"));
        }// if
        if(!req.getParameter("num2").equals(""))
        {
            num2 = Integer.parseInt(req.getParameter("num2"));
        }// if


        PrintWriter out = resp.getWriter();

        out.printf("Result : %d", num1 + num2);
        out.println("<div>");
        out.println("<input type=\"button\" onclick=\"location.href='../index.jsp'\" value=\"Home!\" />");
        out.println("</div>");

    }
}
