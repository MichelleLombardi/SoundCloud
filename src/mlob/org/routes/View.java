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



@WebServlet("/view")
public class View extends HttpServlet {
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

    String incrementView = "" +
            "UPDATE media " +
            "SET views_media = (views_media + 1)";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        out = response.getWriter();
        response.setHeader("Content-Type", "application/json");
        JSonG json = new JSonG();

        String id_media = request.getParameter("id_media");

        System.out.println(id_media);
        
        jdbc.execute(incrementView);

        json.add("status", "OK")
        	.add("id_media", id_media);

        out.println(json.toString());

    }
}
