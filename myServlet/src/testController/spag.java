package testController;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/views/spagSP2")
public class spag extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        int num = 0;
        String numStr = req.getParameter("num");

        if(numStr != null && !numStr.equals(""))
        {
            num = Integer.parseInt(numStr);
        }// if

        /*String result = "";

        if(num % 2 != 0)
        {
            result = "Odd";
        }// if
        else
        {
            result = "Even";
        }// else*/

        String names[] = {"baik", "seunghoon"};
        Map<String, Object> notice = new HashMap<>();
        notice.put("id", 1);
        notice.put("title", "Expression Language!!!");

        // redirect : 현재 작업 내용을 보존하지 않고, 아예 새로운 요청
        // forward : 현재 작업 내용을 가지고, 요청

        // forward로 spagSP2.jsp와 연결한다.
        req.setAttribute("result", num);
        req.setAttribute("names", names);
        req.setAttribute("notice", notice);

        RequestDispatcher view = req.getRequestDispatcher("spagSP2.jsp");
        view.forward(req, resp);
    }
}
