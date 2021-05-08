package kz.bitlab.javaee.group29.sevlets;

import kz.bitlab.javaee.group29.db.AuthUser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/profile")
public class ProfileServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        AuthUser authUser = (AuthUser)request.getSession().getAttribute("CURRENT_USER");

        if(authUser!=null) {

            String sitename = "NewsBlog";

            Cookie cookies[] = request.getCookies();
            if (cookies != null){
                for (Cookie c: cookies){

                    if (c.getName().equals("sitename")){
                        sitename = c.getValue();
                    }
                }
            }

            request.setAttribute("sitename", sitename);
            request.getRequestDispatcher("/profile.jsp").forward(request, response);
        }else{
            response.sendRedirect("/login");
        }
    }
}
