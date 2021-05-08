package kz.bitlab.javaee.group29.sevlets;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(value = "/setsitename")
public class SetSiteNameServlet extends HttpServlet {
    @Override
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

        request.getRequestDispatcher("/setsitename.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
