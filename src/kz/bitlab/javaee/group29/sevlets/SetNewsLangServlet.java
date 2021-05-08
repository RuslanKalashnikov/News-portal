package kz.bitlab.javaee.group29.sevlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/setlangcookie")
public class SetNewsLangServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {




    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String langcode = request.getParameter("langcode");

        Cookie langcodeCookie = new Cookie("langcode", langcode);
        langcodeCookie.setMaxAge(3600 * 24 * 31);
        response.addCookie(langcodeCookie);


        response.sendRedirect("/worldnews");







    }
}
