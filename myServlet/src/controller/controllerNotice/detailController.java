package controller.controllerNotice;

import model.notice;
import service.noticeService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/notice/detail")
public class detailController extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        int id = Integer.parseInt(req.getParameter("id"));

        noticeService ns = new noticeService();
        notice n = ns.getNotice(id);

        req.setAttribute("n", n);


        /*
        Connection con = null;
        //Statement st = null;
        ResultSet rs = null;

        String url = "jdbc:mysql://localhost:3307/myservlet";
        String user = "root";
        String password = "password";

        try
        {
            Class.forName("com.mysql.jdbc.Driver");

            con = DriverManager.getConnection(url + "?useSSL=false", user, password);
            //st = con.createStatement();
            //rs = st.executeQuery(sql);
            // String sql = "select * from notice where id=" + id;

            // or 밑에 PreparedStatement를 이용하는 방법도 있다
            // ?를 채우는 방법
            // Statement st대신에 사용
            String sql = "select * from notice where id=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, id);

            rs = pst.executeQuery();
            // sql문을 실행하면, record들을 갖고오고, 그 record를 사용하기 위해서는 next()를 해야한다.
            rs.next();

            int id_ = rs.getInt("ID");
            String title = rs.getString("TITLE");
            Date regdate = rs.getDate("REGDATE");
            String userid = rs.getString("USER_ID");
            int hit = rs.getInt("HIT");
            String files = rs.getString("FILES");
            String content = rs.getString("CONTENT");

            notice not = new notice(id_, title, regdate, userid, hit, files, content);
            req.setAttribute("n", not);

            rs.close();
            //pst.close();
            con.close();

        }// try
        catch(ClassNotFoundException e)
        {
            System.err.println("JDBC Error!!! Class not found !!! " + e.getMessage());
            e.printStackTrace();
        }// catch
        catch(SQLException e)
        {
            System.err.println("SQL Exception !!! " + e.getMessage());
            e.printStackTrace();
        }// catch

         */

        req.getRequestDispatcher("/WEB-INF/view/notice/detail.jsp").forward(req, resp);

    }
}
