package model;


import java.sql.Date;

public class noticeView extends notice
{
    private int hitCount;

    public noticeView() {
    }

    public noticeView(int id, String title, Date regdate, String userid, int hit, String files, String content, boolean pub, int hitCount)
    {
        super(id, title, regdate, userid, hit, files, content, pub);
        this.hitCount = hitCount;
    }

    public int getHitCount() {
        return hitCount;
    }

    public void setHitCount(int hitCount) {
        this.hitCount = hitCount;
    }
}
