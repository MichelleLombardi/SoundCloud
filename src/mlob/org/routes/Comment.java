package mlob.org.routes;

import mlob.org.libs.JDBC;
import mlob.org.libs.JSonG;
import mlob.org.objs.SessionUser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        out = response.getWriter();
        response.setHeader("Content-Type", "application/json");
        JSonG json = new JSonG();

        HttpSession session = request.getSession();
        SessionUser user = (SessionUser) session.getAttribute("user");

        String text_comments = request.getParameter("text_comments");
        String id_media = request.getParameter("id_media");

        Calendar calendar = Calendar.getInstance();
        Date now = calendar.getTime();
        Timestamp created_at_comments = new Timestamp(now.getTime());

        jdbc.execute(postComment, user.getName(), text_comments, created_at_comments, id_media);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
