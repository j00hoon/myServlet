package model;


import java.sql.Date;

public class notice
{
    private int id;
    private String title;
    private Date regdate;
    private String userid;
    private int hit;
    private String files;
    private String content;
    private boolean pub;

    public notice() {
    }

    public notice(int id, String title, Date regdate, String userid, int hit, String files, String content, boolean pub)
    {
        this.id = id;
        this.title = title;
        this.regdate = regdate;
        this.userid = userid;
        this.hit = hit;
        this.files = files;
        this.content = content;
        this.pub = pub;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getRegdate() {
        return regdate;
    }

    public void setRegdate(Date regdate) {
        this.regdate = regdate;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public int getHit() {
        return hit;
    }

    public void setHit(int hit) {
        this.hit = hit;
    }

    public String getFiles() {
        return files;
    }

    public void setFiles(String files) {
        this.files = files;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean getPub() {
        return pub;
    }

    public void setPub(boolean pub) {
        this.pub = pub;
    }

    @Override
    public String toString()
    {
        return "notice{" +
                "title='" + title + '\'' +
                ", regdate=" + regdate +
                ", userid='" + userid + '\'' +
                ", hit=" + hit +
                ", files='" + files + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
