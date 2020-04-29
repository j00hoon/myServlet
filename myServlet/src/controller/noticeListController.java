package controller;

import model.notice;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet("/notice/list")
public class noticeListController extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;

        List<notice> list = new ArrayList<>();

        String url = "jdbc:mysql://localhost:3307/myservlet";
        String user = "root";
        String password = "password";
        String sql = "select * from notice";

        try
        {
            Class.forName("com.mysql.jdbc.Driver");

            con = DriverManager.getConnection(url + "?useSSL=false", user, password);
            st = con.createStatement();
            rs = st.executeQuery(sql);
            System.out.println("Connection succeed");

            while(rs.next())
            {
                int id_ = rs.getInt("ID");
                String title = rs.getString("TITLE");
                Date regdate = rs.getDate("REGDATE");
                String userid = rs.getString("USER_ID");
                int hit = rs.getInt("HIT");
                String files = rs.getString("FILES");
                String content = rs.getString("CONTENT");

                notice not = new notice(id_, title, regdate, userid, hit, files, content);
                list.add(not);
            }// while

            rs.close();
            st.close();
            con.close();
        }
        catch(ClassNotFoundException e)
        {
            System.err.println("JDBC Error!!! " + e.getMessage());
            e.printStackTrace();
        }
        catch(SQLException e)
        {
            System.err.println("Connection Failed : " + e.getMessage());
            e.printStackTrace();
        }

        req.setAttribute("list", list);
        req.getRequestDispatcher("/WEB-INF/view/notice/list.jsp").forward(req, resp);
    }
}
