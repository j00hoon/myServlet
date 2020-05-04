package controllerAdmin.controllerAdminNotice;

import model.notice;
import service.noticeService;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.util.Collection;


@MultipartConfig(
        fileSizeThreshold = 1024*1024,
        maxFileSize = 1024*1024*50,
        maxRequestSize = 1024*1024*50*5
)
@WebServlet("/admin/board/notice/reg")
public class regNoticeController extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        req.getRequestDispatcher("/WEB-INF/view/admin/board/notice/reg.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        String title = req.getParameter("title");
        String content = req.getParameter("content");
        String isOpen = req.getParameter("open");


        // Collection<Part>는 파일을 여러개 받을 경우에 사용
        Collection<Part> parts = req.getParts();
        // DB에 file을 넣기 위해서
        StringBuilder builder = new StringBuilder();
        for(Part p : parts)
        {
            if(!p.getName().equals("file"))
            {
                continue;
            }// if
            // 파일을 업로드 하지 않았을 경우, 그냥 continue
            if(p.getSize() == 0)
            {
                continue;
            }// if

            // filePart는 파일 하나만 받을 경우에 사용
            // name이 file인 경우
            //Part filePart = req.getPart("file");

            // 위의 for문에서 p변수에 담아서 사용하므로, 그대로 p를 넣어준다.
            Part filePart = p;
            String fileName = filePart.getSubmittedFileName();
            builder.append(fileName);
            builder.append(",");
            InputStream fis = filePart.getInputStream();

            String realPath = req.getServletContext().getRealPath("/upload");
            File path = new File(realPath);
            // 만약에 내가 미리 upload란 폴더를 만들지 않았다면,
            // 밑의 if문이 돌아가고
            if(!path.exists())
            {
                // 폴더를 생성하게 된다.
                // mkdir()과의 차이점은
                // mkdir은 경로상에서 마지막 폴더를 만든다.
                // 예를 들어, /member/upload 와 같은 경로라면, 마지막 upload 폴더만
                // mkdirs()는 경로상의 모든 폴더를 만들어준다.
                path.mkdirs();
            }// if
            // realPath변수로 실제 절대주소?위치?를 알아내고, fileName 변수를 붙인다.
            String filePath = realPath + File.separator + fileName;
            FileOutputStream fos = new FileOutputStream(filePath);


            // 1KB만큼씩 읽기 위해서 byte변수를 만든다.
            byte buf[] = new byte[1024];
            // size는 fis.read로 읽어온 데이터 byte의 개수를 return한다.
            int size = 0;

            while((size = fis.read(buf)) != -1)
            {
                // buf 안에 1KB 크기만큼 항상 read가 되면 문제가 없지만, 그럴 일은 없으므로
                // 0과 size를 써주는데, 이것의 의미는
                // 0번째부터 size만큼까지 읽겠다는 의미
                fos.write(buf, 0, size);
            }// while

            fos.close();
            fis.close();
        }// for

        // 위에서 file이름들을 ,로 구분하기로 했다.
        // 마지막 ,를 빼주기 위한 작업.
        builder.delete(builder.length() - 1, builder.length());


        boolean pub = false;
        noticeService ns = new noticeService();
        int cnt = 0;

        cnt = ns.getNoticeCount();

        if(isOpen != null)
        {
            pub = true;
        }// if

        notice n = new notice();
        n.setTitle(title);
        n.setContent(content);
        n.setPub(pub);
        n.setUserid("User11");
        n.setId(cnt + 1);
        n.setFiles(builder.toString());

        long millis = System.currentTimeMillis();
        Date date = new Date(millis);
        n.setRegdate(date);

        int res = ns.insertNotice(n);

        // redirect를 하게 되면, 현재 이 controller가 갖고 있는 url에서 마지막 부분인 'reg'를 빼고 그 자리에
        // 'list'를 넣고 url을 요청한다.
        resp.sendRedirect("list");

    }
}
