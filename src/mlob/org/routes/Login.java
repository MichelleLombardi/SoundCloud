package mlob.org.routes;

import mlob.org.objs.BrutalSon;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.ws.handler.Handler;
import java.io.IOException;
import java.io.PrintWriter;

//1458597514051 62B976BC98BEA1BD5907F3F3982B73B5
//1458597565544 975854E84CB86041169C613E05970EE8
//1458597695675 2D472A224E189D006A6ECD432EC8C65A
@WebServlet("/login")
public class Login extends HttpServlet {
    private PrintWriter out = null;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        out = response.getWriter();
        response.setHeader("Content-Type", "application/json");
        BrutalSon json = new BrutalSon();


        String parUser = request.getParameter("username");
        String parPass = request.getParameter("password");

        if( parUser.equals("admin") && parPass.equals("masterkey") ) {

            HttpSession session = request.getSession();

            System.out.println(session.getCreationTime());
            System.out.println(session.getId());

            //                      ms *   seg *  min
            long timeDuration = 1000   * 60    * 2;     // 2 min de duracion
            long expTime = session.getCreationTime() + timeDuration;

            session.setAttribute("id", 1);
            session.setAttribute("name", "Orlando");
            session.setAttribute("expTime", expTime);

            json
                .add("name", "Orlando")
                .add("token", session.getId());
        }
        else {
            json
                .add("error", "Datos invalidos")
                .add("token", "");
        }

        out.print(json.getJson());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        out = response.getWriter();
        response.setHeader("Content-Type", "application/json");
        BrutalSon json = new BrutalSon();

        HttpSession session = request.getSession();

        String sessionId = request.getHeader("Authorization");

        System.out.println(sessionId);
        System.out.println(session.getId());

        if( sessionId.equals(session.getId()) ) {
            long expTime = (long) session.getAttribute("expTime");
            long actTime = session.getLastAccessedTime();

            if( actTime >= expTime ) {
                session.invalidate();
                // 440 Login Timeout
                response.setStatus(440);
                out.print(json.getJson());
            }
            else {
                json
                    .add("id", session.getAttribute("id").toString())
                    .add("name", session.getAttribute("name").toString())
                    .add("expTime", session.getAttribute("expTime").toString());
                response.setStatus(200);
                out.print(json.getJson());
            }
        }
        else {
            session.invalidate();
            // 401 Unauthorized
            response.setStatus(401);
            out.print(json.getJson());
        }

    }
}
