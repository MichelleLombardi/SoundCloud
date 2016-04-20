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

@WebServlet("/search")
public class Search extends HttpServlet {
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

    String search = "" +
            "SELECT " +
            "   app_user.id_app_user," +
            "   media.id_media, " +
            "   name_app_user," +
            "   lastname_app_user," +
            "   name_media," +
            "   descripcion_media," +
            "   created_at_media," +
            "   views_media," +
            "   tags_media " +
            "FROM media " +
            "   INNER JOIN user_media " +
            "       ON media.id_media = user_media.id_media" +
            "   INNER JOIN app_user" +
            "       ON user_media.id_app_user = app_user.id_app_user " +
            "WHERE " +
            "   CONCAT(name_app_user, ' ', lastname_app_user ) LIKE ? OR" +
            "   name_media LIKE ? OR" +
            "   tags_media LIKE ? " +
            "ORDER BY " +
            "   views_media DESC " +
            "LIMIT 1";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        out = response.getWriter();
        response.setHeader("Content-Type", "application/json");
        JSonG json = new JSonG();

        String data = "%" + request.getParameter("data").trim() + "%";

        // Buscar canciones por nombre, apellido del usuario,
        // o por nombre o genero de la cancion
        Object[][] table = jdbc.executeQuery(search, data, data, data);

        json.add("arr", table);

        out.print(json.toString());

    }
}
