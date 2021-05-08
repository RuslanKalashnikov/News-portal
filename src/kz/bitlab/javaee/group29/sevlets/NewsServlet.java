package kz.bitlab.javaee.group29.sevlets;

import kz.bitlab.javaee.group29.db.Categories;
import kz.bitlab.javaee.group29.db.DBManager;
import kz.bitlab.javaee.group29.db.News;
import kz.bitlab.javaee.group29.db.Publications;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(value = "/worldnews")
public class NewsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie cookies[] = request.getCookies();


        String langcode = "ALL";
        String sitename = "NewsBlog";
        Long pub_id = 0L;
        Long category_id = 0L;
        boolean categories = false;

        if (cookies != null){
            for (Cookie c: cookies){
                if (c.getName().equals("langcode")){
                    langcode = c.getValue();
                }
                if (c.getName().equals("sitename")){
                    sitename = c.getValue();
                }
            }
        }
        request.setAttribute("langcode", langcode);
        request.setAttribute("sitename", sitename);

        try {
            pub_id = Long.parseLong(request.getParameter("pub"));
            category_id = Long.parseLong(request.getParameter("category"));

        } catch (Exception e){
            e.printStackTrace();
        }


        ArrayList<News> news = null;
        ArrayList<Categories> categors = null;
        Publications neededpub = null;

        if (pub_id == 0L) {
            news = DBManager.getNews();

        } else {
            if (category_id == 0L) {
                news = DBManager.getNewsByPub(pub_id);

            } else{
                news = DBManager.getNewsByPubByCategoty(pub_id, category_id);
            }
            neededpub = DBManager.getPublication(pub_id);
            categors = DBManager.getCategories();
            categories = true;
        }

        ArrayList<Publications> publications = DBManager.getPublications();

        request.setAttribute("neededpub", neededpub);
        request.setAttribute("categors", categors);
        request.setAttribute("categories", categories);
        request.setAttribute("pubs", publications);
        request.setAttribute("news", news);
        request.getRequestDispatcher("/worldnews.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
