package kz.bitlab.javaee.group29.sevlets;

import kz.bitlab.javaee.group29.db.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(value = "/adminnew")
public class AdminNewServlet extends HttpServlet {
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

        ArrayList<News> news = DBManager.getNews();
        ArrayList<Languages> languages = DBManager.getLanguages();
        ArrayList<Publications> publications = DBManager.getPublications();
        ArrayList<Categories> categories = DBManager.getCategories();

        request.setAttribute("categories", categories);
        request.setAttribute("news", news);
        request.setAttribute("languages", languages);
        request.setAttribute("publications", publications);

        request.getRequestDispatcher("/adminnews.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
