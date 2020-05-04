package controllerAdmin.controllerAdminNotice;

import model.notice;
import service.noticeService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/board/notice/detail")
public class detailController extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        int id = Integer.parseInt(req.getParameter("id"));

        noticeService ns = new noticeService();
        notice n = ns.getNotice(id);

        req.setAttribute("n", n);

        req.getRequestDispatcher("/WEB-INF/view/admin/board/notice/detail.jsp").forward(req, resp);

    }
}
