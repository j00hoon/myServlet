package service;

import model.notice;
import model.noticeView;

import java.sql.*;
import java.util.ArrayList;

import java.util.Arrays;
import java.util.List;

public class noticeService
{

    public int removeNoticeAll(int[] idArr)
    {
        return 0;
    }












    public int pubNoticeAll(int oids[], int cids[])
    {
        // 두 가지 for문은 int배열로 받은 변수들을
        // ArrayList collection에 넣는 과정
        // 넣는 이유는, 넣은 후에 밑에 오버로딩 함수에 값을 전달한다.
        // 그리고 그 오버로딩 함수에서 과정을 거쳐서 CSV 형태(~, ~, ~, ~, ...)로
        // 마지막 오버로딩 함수를 호출하기 위해

        List<String> oList = new ArrayList<>();
        for(int i = 0; i < oids.length; i++)
        {
            oList.add(String.valueOf(oids[i]));
        }// for

        List<String> cList = new ArrayList<>();
        for(int i = 0; i < cids.length; i++)
        {
            cList.add(String.valueOf(cids[i]));
        }// for

        return pubNoticeAll(oList, cList);
    }

    public int pubNoticeAll(List<String> oids, List<String> cids)
    {
        String oidsCSV = String.join(",", oids);
        String cidsCSV = String.join(",", cids);

        return pubNoticeAll(oidsCSV, cidsCSV);
    }

    // 20, 30, 43, 56, ... -> 이런 형태로 변수들을 보내겠다는 소리
    public int pubNoticeAll(String oidsCSV, String cidsCSV)
    {
        String sql = "UPDATE NOTICE SET PUB = 1 WHERE ID IN (?);";

        return 0;
    }


















    public int insertNotice(notice n)
    {
        int res = 0;

        String url = "jdbc:mysql://localhost:3307/myservlet";
        String user = "root";
        String password = "password";

        String sql = "insert into notice(ID, TITLE, CONTENT, USER_ID, REGDATE, PUB, FILES) values (?, ?, ?, ?, ?, ?, ?)";

        try
        {
            Class.forName("com.mysql.jdbc.Driver");

            Connection con = DriverManager.getConnection(url + "?useSSL=false", user, password);
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, n.getId());
            st.setString(2, n.getTitle());
            st.setString(3, n.getContent());
            st.setString(4, n.getUserid());
            st.setDate(5, n.getRegdate());
            st.setBoolean(6, n.getPub());
            st.setString(7, n.getFiles());

            res = st.executeUpdate();

            System.out.println("Connection succeed");

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

        return res;
    }

    public int deleteNotice(int id)
    {
        return 0;
    }

    public int updateNotice(notice n)
    {
        return 0;
    }

    public List<notice> getNoticeNewestList()
    {
        return null;
    }


















    public List<noticeView> getNoticeList()
    {
        return getNoticeViewList("title", "", 1);
    }

    public List<noticeView> getNoticeList(int page)
    {
        return getNoticeViewList("title", "", page);
    }

    // select * from notice order by regdate desc;
    public List<noticeView> getNoticeViewList(String field/* title or user_id */, String query, int page)
    {
        List<noticeView> list = new ArrayList<>();

        String sql = "select N.* from (" +
                " select @row_number:=@row_number + 1 as Num, notice_view.* " +
                " from notice_view, (select @row_number := 0) R " +
                " where " + field + " like ? " +
                "    order by regdate desc" +
                ") N" +
                " where N.Num between ? and ?";

        // 첫 번째 ?
        // 1, 6, 11, 16 -> an = a + (n - 1) * d
        // an = 1 + (page - 1) * 5

        // 두 번째 ?
        // 5, 10, 15, 20 -> an = 5 + (page - 1) * 5 = 5 * page



        String url = "jdbc:mysql://localhost:3307/myservlet";
        String user = "root";
        String password = "password";

        try
        {
            Class.forName("com.mysql.jdbc.Driver");

            Connection con = DriverManager.getConnection(url + "?useSSL=false", user, password);
            PreparedStatement st = con.prepareStatement(sql);
            // 위의 ?에 원하는 값 및 식을 넣는 작업
            // field를 안 하는 이유는 string을 넣는 것이므로 실제 sql문은 -> where 'title' ... 이렇게 '' 가 붙는다
            st.setString(1, "%" + query + "%");
            st.setInt(2, 1 + (page - 1) * 5);
            st.setInt(3, 5 * page);

            // PreparedStatement에서 이미 sql을 집어넣었으므로, ResultSet에서는 sql 넣는 작업을 반드시 빼야한다
            ResultSet rs = st.executeQuery();

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
                int hitCount = rs.getInt("Hit_Count");
                boolean pub = rs.getBoolean("PUB");

                noticeView notView = new noticeView(id_, title, regdate, userid, hit, files, content, pub, hitCount);
                list.add(notView);
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

        return list;
    }














    public List<noticeView> getNoticeViewPubList(String field, String query, int page)
    {
        List<noticeView> list = new ArrayList<>();

        String sql = "select N.* from (" +
                " select @row_number:=@row_number + 1 as Num, notice_view.* " +
                " from notice_view, (select @row_number := 0) R " +
                " where " + field + " like ? " +
                "    order by regdate desc" +
                ") N" +
                " where PUB = 1 AND N.Num between ? and ?";

        // 첫 번째 ?
        // 1, 6, 11, 16 -> an = a + (n - 1) * d
        // an = 1 + (page - 1) * 5

        // 두 번째 ?
        // 5, 10, 15, 20 -> an = 5 + (page - 1) * 5 = 5 * page



        String url = "jdbc:mysql://localhost:3307/myservlet";
        String user = "root";
        String password = "password";

        try
        {
            Class.forName("com.mysql.jdbc.Driver");

            Connection con = DriverManager.getConnection(url + "?useSSL=false", user, password);
            PreparedStatement st = con.prepareStatement(sql);
            // 위의 ?에 원하는 값 및 식을 넣는 작업
            // field를 안 하는 이유는 string을 넣는 것이므로 실제 sql문은 -> where 'title' ... 이렇게 '' 가 붙는다
            st.setString(1, "%" + query + "%");
            st.setInt(2, 1 + (page - 1) * 5);
            st.setInt(3, 5 * page);

            // PreparedStatement에서 이미 sql을 집어넣었으므로, ResultSet에서는 sql 넣는 작업을 반드시 빼야한다
            ResultSet rs = st.executeQuery();

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
                int hitCount = rs.getInt("Hit_Count");
                boolean pub = rs.getBoolean("PUB");

                noticeView notView = new noticeView(id_, title, regdate, userid, hit, files, content, pub, hitCount);
                list.add(notView);
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

        return list;
    }



















    public int getNoticeCount()
    {
        return getNoticeCount("user_id", "");
    }

    public int getNoticeCount(String field, String query)
    {
        int count = 0;

        String sql = "select count(N.id) count from (" +
                " select @row_number:=@row_number + 1 as Num, notice.* " +
                " from notice, (select @row_number := 0) R " +
                " where " + field + " like ? " +
                "    order by regdate desc" +
                ") N";

        String url = "jdbc:mysql://localhost:3307/myservlet";
        String user = "root";
        String password = "password";

        try
        {
            Class.forName("com.mysql.jdbc.Driver");

            Connection con = DriverManager.getConnection(url + "?useSSL=false", user, password);
            PreparedStatement st = con.prepareStatement(sql);

            st.setString(1, "%" + query + "%");

            // PreparedStatement에서 이미 sql을 집어넣었으므로, ResultSet에서는 sql 넣는 작업을 반드시 빼야한다
            ResultSet rs = st.executeQuery();

            System.out.println("Connection succeed");

            if(rs.next())
            {
                count = rs.getInt("count");
            }// if

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

        return count;
    }


















    public notice getNotice(int id)
    {
        notice not = null;
        String sql = "select * from notice where id=?";

        String url = "jdbc:mysql://localhost:3307/myservlet";
        String user = "root";
        String password = "password";

        try
        {
            Class.forName("com.mysql.jdbc.Driver");

            Connection con = DriverManager.getConnection(url + "?useSSL=false", user, password);
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, id);

            // PreparedStatement에서 이미 sql을 집어넣었으므로, ResultSet에서는 sql 넣는 작업을 반드시 빼야한다
            ResultSet rs = st.executeQuery();

            System.out.println("Connection succeed");

            if(rs.next())
            {
                int id_ = rs.getInt("ID");
                String title = rs.getString("TITLE");
                Date regdate = rs.getDate("REGDATE");
                String userid = rs.getString("USER_ID");
                int hit = rs.getInt("HIT");
                String files = rs.getString("FILES");
                String content = rs.getString("CONTENT");
                boolean pub = rs.getBoolean("PUB");

                not = new notice(id_, title, regdate, userid, hit, files, content, pub);

            }// if

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

        return not;
    }






























    public notice getNextNotice(int id)
    {
        notice not = null;

        String sql = "select N.* from (" +
                "select @row_number:=@row_number + 1 as Num, notice.* " +
                " from notice, (select @row_number := 0) R " +
                "    order by regdate desc" +
                ") N" +
                " where N.id = (" +
                " select id " +
                "    from notice" +
                "    where regdate > (select regdate " +
                "   from notice" +
                "   where id = ?) " +
                "   limit 1" +
                ");";

        String url = "jdbc:mysql://localhost:3307/myservlet";
        String user = "root";
        String password = "password";

        try
        {
            Class.forName("com.mysql.jdbc.Driver");

            Connection con = DriverManager.getConnection(url + "?useSSL=false", user, password);
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, id);

            // PreparedStatement에서 이미 sql을 집어넣었으므로, ResultSet에서는 sql 넣는 작업을 반드시 빼야한다
            ResultSet rs = st.executeQuery();

            System.out.println("Connection succeed");

            if(rs.next())
            {
                int id_ = rs.getInt("ID");
                String title = rs.getString("TITLE");
                Date regdate = rs.getDate("REGDATE");
                String userid = rs.getString("USER_ID");
                int hit = rs.getInt("HIT");
                String files = rs.getString("FILES");
                String content = rs.getString("CONTENT");
                boolean pub = rs.getBoolean("PUB");

                not = new notice(id_, title, regdate, userid, hit, files, content, pub);

            }// if

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

        return not;
    }









    public notice getPrevNotice(int id)
    {
        notice not = null;

        String sql = "select N.* from (" +
                "   select @row_number:=@row_number + 1 as Num, notice.*" +
                "   from notice, (select @row_number := 0) R" +
                "    order by regdate desc" +
                ") N" +
                "   where N.id = (" +
                "   select id " +
                "    from notice" +
                "    where regdate <= (select regdate" +
                "   from notice" +
                "   where id = ?) AND id != ?" +
                "   order by regdate desc" +
                "   limit 1" +
                ");";

        String url = "jdbc:mysql://localhost:3307/myservlet";
        String user = "root";
        String password = "password";

        try
        {
            Class.forName("com.mysql.jdbc.Driver");

            Connection con = DriverManager.getConnection(url + "?useSSL=false", user, password);
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, id);
            st.setInt(2, id);

            // PreparedStatement에서 이미 sql을 집어넣었으므로, ResultSet에서는 sql 넣는 작업을 반드시 빼야한다
            ResultSet rs = st.executeQuery();

            System.out.println("Connection succeed");

            if(rs.next())
            {
                int id_ = rs.getInt("ID");
                String title = rs.getString("TITLE");
                Date regdate = rs.getDate("REGDATE");
                String userid = rs.getString("USER_ID");
                int hit = rs.getInt("HIT");
                String files = rs.getString("FILES");
                String content = rs.getString("CONTENT");
                boolean pub = rs.getBoolean("PUB");

                not = new notice(id_, title, regdate, userid, hit, files, content, pub);

            }// if

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

        return not;
    }























    public int deleteNoticeAll(int[] delIdsArr)
    {
        int res = 0;

        String url = "jdbc:mysql://localhost:3307/myservlet";
        String user = "root";
        String password = "password";
        String params = "";

        for(int i = 0; i < delIdsArr.length; i++)
        {
            params += delIdsArr[i];

            if(i < delIdsArr.length - 1)
            {
                params += ", ";
            }// if
        }// for
        String sql = "delete from notice where id in (" + params + ")";

        try
        {
            Class.forName("com.mysql.jdbc.Driver");

            Connection con = DriverManager.getConnection(url + "?useSSL=false", user, password);
            Statement st = con.createStatement();

            res = st.executeUpdate(sql);

            System.out.println("Connection succeed");

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

        return res;
    }


}
