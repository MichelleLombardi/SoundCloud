package mlob.org.routes;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mlob.org.libs.JDBC;
import mlob.org.libs.JSonG;
import mlob.org.objs.SessionUser;

/**
 * Servlet implementation class Perfil
 */
@WebServlet("/perfil")
public class Perfil extends HttpServlet {
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
    
    String tabla = "" +
            "SELECT FROM media (" +
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
    public Perfil() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
