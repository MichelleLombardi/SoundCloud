package mlob.org.routes;

import mlob.org.libs.JDBC;
import mlob.org.libs.JSonG;
import mlob.org.objs.SessionUser;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

@MultipartConfig
@WebServlet("/upload")
public class Upload extends HttpServlet {
    private PrintWriter out = null;

    /*// Conexion a db4free
    private JDBC jdbc = new JDBC(
            JDBC.JDBC_MYSQL,        // JDBC
            "db4free.org",          // Host
            "brutal",               // User
            "masterkey",            // Pass
            "soundcloudveinte",     // DataBase
            JDBC.PORT_MYSQL         // Port
    );*/

    // Conexion a postgres
    private JDBC jdbc = new JDBC(
            JDBC.JDBC_POSTGRESQL,        // JDBC
            "localhost",          // Host
            "postgres",               // User
            "masterkey",            // Pass
            "soundcloud",     // DataBase
            JDBC.PORT_POSTGRESQL         // Port
    );
    
    String upload = "" +
            "INSERT INTO media (" +
            "   id_media, " +
            "   url_media, " +
            "   created_at_media, " +
            "   descripcion_media, " +
            "   views_media, " +
            "   name_media, " +
            "   tags_media" +
            ") VALUES (" +
            "   ((SELECT COUNT(*) FROM media) + 1), " +
            "   ?, " +
            "   ?, " +
            "   ?, " +
            "   ?, " +
            "   ?, " +
            "   ?, " +
            ")";
            
    String getMediaId = "" +
            "SELECT id_media FROM media " +
            " WHERE " +
            "  url_media = ?, " +
            "  created_at_media = ?, " +
            "  descripcion_media = ?, " +
            "  views_media = ?, " +
            "  name_media = ?, " +
            "  tags_media = ?, ";
            
    String createUserMedia = "" +
            "INSERT INTO media (" +
            "   id_media, " +
            "   id_app_user, " +
            ") VALUES (" +
            "   ?, " +
            "   ? " +
            ")";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        out = response.getWriter();
        response.setHeader("Content-Type", "application/json");
        JSonG json = new JSonG();

        HttpSession session = request.getSession();
        SessionUser user = (SessionUser) session.getAttribute("user");

        Part file = request.getPart("song");
        String namesong = request.getParameter("namesong");
        String descpsong = request.getParameter("descpsong");
        String tagsong = request.getParameter("tagsong");
        Calendar calendar = Calendar.getInstance();
        Date now = calendar.getTime();
        Timestamp timestamp = new Timestamp(now.getTime());

        InputStream filecontent = file.getInputStream();
        OutputStream os = null;

        String url = "/" + user.getId() + "/" + namesong + ".mp3";

        try {
            os = new FileOutputStream(System.getProperty("user.dir") + url);
            int read = 0;
            byte[] bytes = new byte[1024];

            while ((read = filecontent.read(bytes)) != -1) {
                os.write(bytes, 0, read);
            }

            jdbc.execute(upload, url, timestamp, descpsong, 0, namesong, tagsong);
            
            Object[][] table = jdbc.executeQuery(getMediaId, url, timestamp, descpsong, 0, namesong, tagsong);
            
            
            json.add("url", url);

        } catch (Exception e) {
            e.printStackTrace();
            json.add("error", "No se pudo subir el archivo");
        } finally {
            if (filecontent != null) {
                filecontent.close();
            }
            if (os != null) {
                os.close();
            }
        }

        out.print(json.toString());

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private String getFileName(Part part) {
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(
                        content.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }
}
