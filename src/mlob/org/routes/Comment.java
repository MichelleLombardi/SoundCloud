package mlob.org.routes;

import mlob.org.libs.JDBC;
import mlob.org.libs.JSonG;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

@WebServlet("/comment")
public class Comment extends HttpServlet {
    private PrintWriter out = null;

    // Conexion a postgres
    private JDBC jdbc = new JDBC(
            JDBC.JDBC_POSTGRESQL,        // JDBC
            "localhost",          // Host
            "postgres",               // User
            "masterkey",            // Pass
            "soundcloud",     // DataBase
            JDBC.PORT_POSTGRESQL         // Port
    );

    private String postComment = "" +
            "INSERT INTO " +
            "comments (" +
            "   id_comments, " +
            "   username_comments, " +
            "   text_comments, " +
            "   created_at_comments, " +
            "   id_media" +
            ") VALUES (" +
            "   (SELECT COUNT(*) FROM comments) + 1," +
            "   ?," +
            "   ?," +
            "   ?," +
            "   ?" +
            ")";

    private String getComment = "" +
            "SELECT " +
            "   username_comments," +
            "   text_comments," +
            "   created_at_comments " +
            "FROM " +
            "   comments " +
            "WHERE " +
            "   id_media = ? " +
            "ORDER BY" +
            "   created_at_comments";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        out = response.getWriter();
        response.setHeader("Content-Type", "application/json");
        JSonG json = new JSonG();

        String username_comments = request.getParameter("username_comments");
        String text_comments = request.getParameter("text_comments");
        String id_media = request.getParameter("id_media");

        Calendar calendar = Calendar.getInstance();
        Date now = calendar.getTime();
        Timestamp created_at_comments = new Timestamp(now.getTime());

        jdbc.execute(postComment, username_comments, text_comments, created_at_comments, Integer.parseInt(id_media));

        json.add("created_at_comments", created_at_comments);

        out.print(json.toString());

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        out = response.getWriter();
        response.setHeader("Content-Type", "application/json");
        JSonG json = new JSonG();

        String id_media = request.getParameter("id_media");

        Object[][] table = jdbc.executeQuery(getComment, Integer.parseInt(id_media));

        json.add("arr", table);

        out.print(json.toString());
    }
}
