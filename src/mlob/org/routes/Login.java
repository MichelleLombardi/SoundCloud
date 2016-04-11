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

@WebServlet("/login")
public class Login extends HttpServlet {
    private PrintWriter out = null;
    private JDBC jdbc = new JDBC(
            JDBC.JDBC_MYSQL,        // JDBC
            "db4free.org",          // Host
            "brutal",               // User
            "masterkey",            // Pass
            "soundcloudveinte",     // DataBase
            JDBC.PORT_MYSQL         // Port
    );
    private String authQuery = "" +
            "SELECT " +
            "id_app_user AS id," +
            "name_app_user AS nombre," +
            "lastname_app_user AS apellido " +
            "FROM app_user " +
            "WHERE " +
            "   email_app_user = ? AND " +
            "   password_app_user = ?"
    ;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        out = response.getWriter();
        response.setHeader("Content-Type", "application/json");
        JSonG json = new JSonG();

        String email = request.getParameter("email");
        String pass = request.getParameter("pass");

        /*
        * Hacemos una consulta para que nos cuenta cuantos registros hay
        * que tengan parUser como usuario y parPass como contrasenia,
        *
        * Como sabemos que esta consulta solo nos debe retornar un registro
        * entonces lo que hacemos es verificar la cantidad de filas que obtuvimos
        * y le restamos 1 para despreciar la fila de los labels, es decir, que solo
        * nos queden los registros, como sabemos que solo debe por haber una
        * coincidenia entonces evaluamos en un if el la variable length para
        * verificar que solo obtuvimos un solo registro
        */
        Object[][] table = jdbc.executeQuery(authQuery,email, pass);

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
        
        out.print(json.toString()); //getjson
    }

    // Verificar si la session sigue activa
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        out = response.getWriter();
        response.setHeader("Content-Type", "application/json");
        JSonG json;

        HttpSession session = request.getSession();
        String token = request.getHeader("Authorization");

        SessionUser user = (SessionUser) session.getAttribute("user");

        // Si el token es igual al id de la session y tenemos los datos en la session
        if(token.equals(session.getId()) && (user != null) ) {
            // obtenemos los datos
            json = user.getJson();

            // y los enviamos al cliente
        }
        else {
            this.logout(session);
            json = new JSonG();
            json.add("error", "La session ya no sigue activa");
        }
        response.setStatus(200);
        out.print(json.toString());

    }

    // Cuando queremos hacer logout
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        out = response.getWriter();
        response.setHeader("Content-Type", "application/json");

        // Obtenemos la session
        this.logout(request.getSession());

        response.setStatus(200);
        out.print("");
    }

    private HttpSession logout( HttpSession session ) {
        // Vaciamos la variable user de la session
        session.removeAttribute("user");

        // La invalidamos
        session.invalidate();

        return session;
    }
}