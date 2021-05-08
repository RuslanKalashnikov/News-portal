package kz.bitlab.javaee.group29.sevlets;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(value = "/changesitename")
public class ChangeSiteNameServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {




    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String sitename = request.getParameter("sitename");

        Cookie sitenameCookie = new Cookie("sitename", sitename);
        sitenameCookie.setMaxAge(3600 * 24);
        response.addCookie(sitenameCookie);


        response.sendRedirect("/index");







    }
}
