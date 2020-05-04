package controller.controllerNotice;

import model.noticeView;
import service.noticeService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/notice/list")
public class listController extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        String field_ = req.getParameter("f");
        String query_ = req.getParameter("q");
        String page_ = req.getParameter("p");

        String field = "title";
        if(field_ != null && !field_.equals(""))
        {
            field = field_;
        }// if

        String query = "";
        if(query_ != null && !query_.equals(""))
        {
            query = query_;
        }// if

        int page = 1;
        if(page_ != null && !page_.equals(""))
        {
            page = Integer.parseInt(page_);
        }//

        noticeService ns = new noticeService();
        List<noticeView> list = ns.getNoticeViewPubList(field, query, page);
        int count = ns.getNoticeCount(field, query);

        req.setAttribute("list", list);
        req.setAttribute("count", count);
        req.getRequestDispatcher("/WEB-INF/view/notice/list.jsp").forward(req, resp);
    }
}
