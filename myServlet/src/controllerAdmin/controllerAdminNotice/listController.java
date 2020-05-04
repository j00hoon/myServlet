package controllerAdmin.controllerAdminNotice;

import model.noticeView;
import service.noticeService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@WebServlet("/admin/board/notice/list")
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
        List<noticeView> list = new ArrayList<>();
        list = ns.getNoticeViewList(field, query, page);
        int count = ns.getNoticeCount(field, query);

        req.setAttribute("list", list);
        req.setAttribute("count", count);
        req.getRequestDispatcher("/WEB-INF/view/admin/board/notice/list.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        String openIds[] = req.getParameterValues("open-id");
        String delIds[] = req.getParameterValues("del-id");

        String cmd = req.getParameter("cmd");
        String ids_ = req.getParameter("ids");
        // ids_의 빈 공백들을 제거하기 위해 trim을 사용한다
        String ids[] = ids_.trim().split(" ");

        noticeService ns = new noticeService();

        switch (cmd)
        {
            case "openAll":
                // 공개 체크된 목록
                List<String> oids = Arrays.asList(openIds);
                // 전체 글들의 목록
                List<String> cids = new ArrayList(Arrays.asList(ids));
                // 전체 목록에서 공개해야할 id 목록 ids[]들을 빼준다.

                // 이 결과가 close해야하는 id 목록이다.
                cids.removeAll(oids);

                ns.pubNoticeAll(oids, cids);

                break;
            case "DelAll":

                int delIdsArr[] = new int[delIds.length];

                for(int i = 0; i < delIds.length; i++)
                {
                    delIdsArr[i] = Integer.parseInt(delIds[i]);
                }// for

                int res = ns.deleteNoticeAll(delIdsArr);
                break;
        }// switch

        resp.sendRedirect("list");

    }
}
