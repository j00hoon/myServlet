package service;

import model.notice;

import java.util.List;

public class noticeService
{

    public List<notice> getNoticeList()
    {
        return getNoticeList("title", "", 1);
    }

    public List<notice> getNoticeList(int page)
    {
        return getNoticeList("title", "", page);
    }

    // select * from notice order by regdate desc;
    public List<notice> getNoticeList(String field, String query, int page)
    {
        String sql = "select N.* from (" +
                " select @row_number:=@row_number + 1 as Num, notice.* " +
                " from notice, (select @row_number := 0) R " +
                "    order by regdate desc" +
                ") N" +
                " where N.Num between 1 and 5";

        return null;
    }

    public int getNoticeCount()
    {
        return getNoticeCount("title", "");
    }

    public int getNoticeCount(String field, String query)
    {
        String sql = "select N.* from (" +
                " select @row_number:=@row_number + 1 as Num, notice.* " +
                " from notice, (select @row_number := 0) R " +
                "    order by regdate desc" +
                ") N" +
                " where N.Num between 1 and 5";

        return 0;
    }

    public notice getNotice(int id)
    {
        String sql = "select * from notice where id=?";

        return null;
    }

    public notice getNextNotice(int id)
    {
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
                "   where id = 3) " +
                "   limit 1" +
                ");";
        return null;
    }

    public notice getPrevNotice(int id)
    {
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
                "   where id = 3) AND id != 3" +
                "   order by regdate desc" +
                "   limit 1" +
                ");";
        return null;
    }

}
