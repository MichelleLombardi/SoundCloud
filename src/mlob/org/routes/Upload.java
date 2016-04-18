package mlob.org.routes;

import mlob.org.libs.JDBC;
import mlob.org.libs.JSonG;
import mlob.org.objs.SessionUser;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;

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

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        out = response.getWriter();
        response.setHeader("Content-Type", "application/json");
        JSonG json = new JSonG();

        HttpSession session = request.getSession();
        SessionUser user = (SessionUser) session.getAttribute("user");

        Part file = request.getPart("song");
        String namesong = request.getParameter("namesong");

        InputStream filecontent = file.getInputStream();
        OutputStream os = null;

        String dir = System.getProperty("user.dir") + "/" + user.getId() + "/" + namesong + ".txt";

        try {
            os = new FileOutputStream(dir);
            int read = 0;
            byte[] bytes = new byte[1024];

            while ((read = filecontent.read(bytes)) != -1) {
                os.write(bytes, 0, read);
            }

            json.add("url", "");

            jdbc.executeQuery("INSERT INTO media (" +
                    "   id_media, " +
                    "   url_media, " +
                    "   created_at_media, " +
                    "   descripcion_media, " +
                    "   views_media, " +
                    "   likes_media, " +
                    "   name_media, " +
                    "   tags_media" +
                    ") VALUES (
                        )");

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
