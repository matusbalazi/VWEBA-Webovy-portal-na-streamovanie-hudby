package sk.kmikt.webovy_portal_na_streamovanie_hudby.user;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.springframework.security.crypto.bcrypt.BCrypt;

import javax.naming.NamingException;
import java.io.IOException;
import java.util.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@WebServlet(name = "UserRegisterServlet", value = "/user-register")
public class UserRegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String passwordHash = BCrypt.hashpw(request.getParameter("password"), BCrypt.gensalt(12));

        try {
            String dateString = request.getParameter("dob");
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = dateFormat.parse(dateString);
            User user = new User(request.getParameter("name"), date, request.getParameter("email"), passwordHash);

            if ((new UserController()).getUserByEmail(user.getEmail()) == null)
            {
                if ((new UserController()).insertUser(user)) {
                    session.setAttribute("name", user.getName());
                    session.setAttribute("login", user.getEmail());
                    session.setAttribute("is_admin", user.isIs_admin());
                    response.sendRedirect("/index.jsp?success_register=true");
                }
            }
            else {
                response.sendRedirect("/login.jsp?success_register=false");
            }

        } catch (NumberFormatException var5) {
            var5.printStackTrace();
            response.sendRedirect("/login.jsp");
        } catch (SQLException var6) {
            throw new RuntimeException(var6);
        } catch (NamingException var7) {
            throw new RuntimeException(var7);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
