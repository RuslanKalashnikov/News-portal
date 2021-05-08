package kz.bitlab.javaee.group29.sevlets;

import kz.bitlab.javaee.group29.db.AuthUser;
import kz.bitlab.javaee.group29.db.DBManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(value = "/register")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        String redirect = "/register?emailerror";

        String email = request.getParameter("email");
        String fullName = request.getParameter("full_name");
        String password = request.getParameter("password");
        String rePassword = request.getParameter("re_password");

        AuthUser user = DBManager.getUser(email);

        if(user==null) {

            redirect = "/register?passworderror";

            if(rePassword.equals(password)){

                AuthUser newUser = new AuthUser(null, email, password, fullName);
                DBManager.addUser(newUser);
                redirect = "/register?success";

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
        request.getRequestDispatcher("/newregister.jsp").forward(request, response);
    }
}
