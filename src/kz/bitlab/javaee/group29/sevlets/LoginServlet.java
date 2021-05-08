package kz.bitlab.javaee.group29.sevlets;

import kz.bitlab.javaee.group29.db.AuthUser;
import kz.bitlab.javaee.group29.db.DBManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(value = "/login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String redirect = "/login?error";

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        AuthUser user = DBManager.getUser(email);

        if(user!=null){

            if(user.getPassword().equals(password)){

                HttpSession session = request.getSession();
                session.setAttribute("CURRENT_USER", user);
                redirect = "/profile";

            }

        }

        response.sendRedirect(redirect);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie cookies[] = request.getCookies();
        String sitename = "NewsBlog";

        if (cookies != null){
            for (Cookie c: cookies){

                if (c.getName().equals("sitename")){
                    sitename = c.getValue();
                }
            }
        }

        request.setAttribute("sitename", sitename);
        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }
}
