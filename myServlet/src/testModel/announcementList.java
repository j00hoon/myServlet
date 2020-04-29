package testModel;

public class announcementList
{
    private int num;
    private String title;
    private String user;
    private  String date;
    private int count;

    public announcementList(int num, String title, String user, String date, int count) {
        this.num = num;
        this.title = title;
        this.user = user;
        this.date = date;
        this.count = count;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
