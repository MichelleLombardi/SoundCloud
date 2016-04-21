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

@WebServlet("/like")
public class Like extends HttpServlet {
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

    private String postLike = "" +
            "INSERT INTO likes (" +
            "   id_app_user, " +
            "   id_media, " +
            "   likes_likes" +
            ") " +
            "VALUES (" +
            "   ?," +
            "   ?," +
            "   ?" +
            ")";

    private String toogleLike = "" +
            "UPDATE likes SET " +
            "   likes_likes = ? " +
            "WHERE " +
            "   id_app_user = ? AND" +
            "   id_media = ?";

    private String getLike = "" +
            "SELECT likes_likes " +
            "FROM likes " +
            "WHERE " +
            "   id_app_user = ? AND " +
            "   id_media = ?" +
            "LIMIT 1";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        out = response.getWriter();
        response.setHeader("Content-Type", "application/json");

        JSonG json = new JSonG();
        int id_media = Integer.parseInt(request.getParameter("id_media"));
        Boolean like = Boolean.valueOf(request.getParameter("like"));

        HttpSession session = request.getSession();
        SessionUser user = (SessionUser) session.getAttribute("user");

        System.out.println(user.getId());

        Object[][] table = jdbc.executeQuery(getLike, user.getId(), id_media);

        // Si hay un dato para hacer like verificamos el estado
        if( (table.length - 1) == 1 ) {
            jdbc.execute(toogleLike, like, user.getId(), id_media);
            json.add("like", like);
        }
        else {
            jdbc.execute(postLike, user.getId(), id_media, like);
            json.add("like", like);
        }

        out.print(json);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        out = response.getWriter();
        response.setHeader("Content-Type", "application/json");
        JSonG json = new JSonG();

        int id_media = Integer.parseInt(request.getParameter("id_media"));

        HttpSession session = request.getSession();
        SessionUser user = (SessionUser) session.getAttribute("user");

        System.out.println(user.getId());
        System.out.println(id_media);

        Object[][] table = jdbc.executeQuery(getLike, user.getId(), id_media);

        // Si hay un dato para hacer like verificamos el estado
        if( (table.length - 1) == 1 ) {
            Boolean like = (Boolean) table[1][0];

            json.add("like", like);
        }
        else {
            json.add("like", false);
        }

        out.print(json);

    }
}
