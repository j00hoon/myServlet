package testController;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/views/notice-reg")
public class noticeReg extends HttpServlet
{
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html; charset=utf-8");

        PrintWriter out = resp.getWriter();

        String title = req.getParameter("title");
        String content = req.getParameter("content");

        out.println(title);
        out.println(content);

    }
}
