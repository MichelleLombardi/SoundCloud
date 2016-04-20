package mlob.org.routes;

import mlob.org.libs.JDBC;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Servlet implementation class Audio
 */
@WebServlet("/streaming")
public class Streaming extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final long EXPIRE_TIME = 1000 * 60 * 60 * 24;

    // Conexion a postgres
    private JDBC jdbc = new JDBC(
            JDBC.JDBC_POSTGRESQL,        // JDBC
            "localhost",          // Host
            "postgres",               // User
            "masterkey",            // Pass
            "soundcloud",     // DataBase
            JDBC.PORT_POSTGRESQL         // Port
    );

    String getUrl = "" +
            "SELECT " +
            "   url_media " +
            "FROM media " +
            "WHERE " +
            "   id_media = ?";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int song = Integer.parseInt(request.getParameter("song"));

        Object[][] table = jdbc.executeQuery(getUrl, song);

        String url = String.valueOf(table[1][0]);

        String filename = url.split("/")[2];

        String audioFilename = URLDecoder.decode(filename, "UTF-8");

        String dir = System.getProperty("user.dir");

        Path audio = Paths.get(dir, url);

        System.out.println(audio);

        int length = (int) Files.size(audio);
        int start = 0;
        int end = length - 1;

        String range = request.getHeader("Range");


        String rangeValues = range.substring(range.indexOf("=") + 1, range.length());
        String rangeSplit [] = rangeValues.split("-");

        start = rangeSplit[0].isEmpty() ? start : Integer.valueOf(rangeSplit[0]);
        start = start < 0 ? 0 : start;

        end = rangeSplit.length == 1 ? end : Integer.valueOf(rangeSplit[1]);
        end = end > length - 1 ? length - 1 : end;

        System.out.println("range: " + range);
        System.out.println("start: " + start);
        System.out.println("end: " + end);

        response.reset();
        response.setBufferSize(1024);
        response.setDateHeader("Last-Modified", Files.getLastModifiedTime(audio).toMillis());
        response.setHeader("Content-Disposition", String.format("inline;filename=\"%s\"", audioFilename));
        response.setHeader("Accept-Ranges", "bytes");
        response.setHeader("Content-Range", String.format("bytes %s-%s/%s", start, end, length));
        response.setDateHeader("Expires", System.currentTimeMillis() + EXPIRE_TIME);
        response.setContentType(Files.probeContentType(audio));
        response.setStatus(HttpServletResponse.SC_PARTIAL_CONTENT);

        BufferedInputStream input = new BufferedInputStream(new FileInputStream (audio.toString()), 1024);
        BufferedOutputStream output = new BufferedOutputStream(response.getOutputStream(), 1024);

        byte [] buffer = new byte[1024];
        int count;
        while ((count = input.read(buffer)) > 0) {
            output.write(buffer, 0, count);
        }
        input.close();
        output.close();
    }

}
