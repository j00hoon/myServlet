package testController;

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

//        int num1 = 0;
//        int num2 = 0;
        String op = req.getParameter("btn");
        String numArr[] = req.getParameterValues("num");
        int res = 0;

        for(int i = 0; i < numArr.length; i++)
        {
            int num = Integer.parseInt(numArr[i]);
            if(op.equals("Add"))
            {
                res += num;
            }// if
            else if(op.equals("Sub"))
            {
                res -= num;
            }// else if
        }// for

//        if(!req.getParameter("num1").equals(""))
//        {
//            num1 = Integer.parseInt(req.getParameter("num1"));
//        }// if
//        if(!req.getParameter("num2").equals(""))
//        {
//            num2 = Integer.parseInt(req.getParameter("num2"));
//        }// if


        PrintWriter out = resp.getWriter();


//        if(op.equals("Add"))
//        {
//            res = num1 + num2;
//        }// if
//        else if(op.equals("Sub"))
//        {
//            res = num1 - num2;
//        }// else if

        out.printf("Result : %d", res);
        out.println("<div>");
        out.println("<input type=\"button\" onclick=\"location.href='../index.jsp'\" value=\"Home!\" />");
        out.println("</div>");

    }
}
