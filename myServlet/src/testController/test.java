package testController;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/views/test")
public class test extends HttpServlet
{
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html; charset=utf-8");

        PrintWriter out = resp.getWriter();

        int cnt = 1;
        String cnt_str = req.getParameter("cnt");

        if(cnt_str != null && !cnt_str.equals(""))
        {
            cnt = Integer.parseInt(req.getParameter("cnt"));
        }// if

        for(int i = 0; i < cnt; i++)
        {
            out.println("Hello ~~~ <br>");
        }// for
    }
}
