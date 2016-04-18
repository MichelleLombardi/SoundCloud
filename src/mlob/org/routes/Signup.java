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
import java.sql.Date;

@WebServlet("/signup")
public class Signup extends HttpServlet {
    private PrintWriter out = null;
    
   /*
    // Conexion a db4free
    private JDBC jdbc = new JDBC(
            JDBC.JDBC_MYSQL,        // JDBC
            "db4free.org",          // Host
            "brutal",               // User
            "masterkey",            // Pass
            "soundcloudveinte",     // DataBase
            JDBC.PORT_MYSQL         // Port
    );
    */
    
    // Conexion a postgres
    private JDBC jdbc = new JDBC(
            JDBC.JDBC_POSTGRESQL,        // JDBC
            "localhost",          // Host
            "postgres",               // User
            "masterkey",            // Pass
            "soundcloud",     // DataBase
            JDBC.PORT_POSTGRESQL         // Port
    );
    
    private String countQuery = "" +
            "SELECT " +
            "   COUNT(*)" +
            "FROM " +
            "   app_user " +
            "WHERE " +
            "   email_app_user = ?";

    private String authQuery = "" +
            "SELECT " +
            "   id_app_user AS id," +
            "   name_app_user AS nombre," +
            "   lastname_app_user AS apellido " +
            "FROM " +
            "   app_user " +
            "WHERE " +
            "   email_app_user = ? AND " +
            "   password_app_user = ?";

    private String createQuery = "" +
            "INSERT INTO " +
            "   app_user (" +
            "   id_app_user," + 
            "   name_app_user, " +
            "   lastname_app_user," + 
            "   birthday_app_user, " +
            "   email_app_user, " +
            "   password_app_user" +
            ") " +
            "VALUES( " +
            "   ((SELECT COUNT(*) FROM app_user) + 1), " +    // Contar cuantos registros hay para saber el proximo id
            "   ?, " +  // FirstName
            "   ?, " +  // LastName
            "   ?, " +  // Birthday
            "   ?, " +  // Email
            "   ? " +   // Pass
            ")";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        out = response.getWriter();
        response.setHeader("Content-Type", "application/json");
        JSonG json = new JSonG();

        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        Date birthday = Date.valueOf(request.getParameter("birthday"));
        String email = request.getParameter("email");
        String pass = request.getParameter("pass");

        Object[][] table = jdbc.executeQuery(countQuery, email);


        long count = (long) table[1][0];

        // Verificamos que solo haya una coincidencia
        if ( count == 0) { // ninguna persona

            // Creamos al usuario
            jdbc.execute(createQuery, firstName, lastName, birthday, email, pass);

            // Y luego lo buscamos en la base de datos
            table = jdbc.executeQuery(authQuery, email, pass);

            int length = table.length - 1;

            // Verificamos que solo haya una coincidencia
            if (length == 1) { //una sola persona

                Object[] registro = table[1]; //datos de la persona

                HttpSession session = request.getSession();

                SessionUser user = new SessionUser(
                        session.getId(),        // token
                        registro[0],  // id
                        registro[1],   // name
                        registro[2]    // lastname
                );

                session.setAttribute("user", user);

                json = user.getJson();
            } else {
                json
                        .add("error", "Datos invalidos");
            }

        } else {
            json
                    .add("error", "El email ya esta en uso");
        }

        out.print(json.toString()); //getjson

    }

}
