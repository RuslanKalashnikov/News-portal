package kz.bitlab.javaee.group29.sevlets;


import kz.bitlab.javaee.group29.db.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/savenew")
public class SaveNewServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String redirect = "/adminnew";

        Long new_id = null;
        Long lang_id = null;
        Long pub_id = null;
        Long category_id = null;

        try {
            new_id = Long.parseLong(request.getParameter("new_id"));
            lang_id = Long.parseLong(request.getParameter("language"));
            pub_id = Long.parseLong(request.getParameter("publication"));
            category_id = Long.parseLong(request.getParameter("category"));

        } catch (Exception e){
            e.printStackTrace();
        }


        if (pub_id != null && new_id != null && lang_id != null && category_id != null){

            String title = request.getParameter("title");
            String short_content =  request.getParameter("short_content");
            String content =  request.getParameter("content");
            String url =  request.getParameter("url");


            News news = DBManager.getNew(new_id);
            Languages language = DBManager.getLanguage(lang_id);
            Publications publication = DBManager.getPublication(pub_id);
            Categories category = DBManager.getCategory(category_id);

            if (news != null){
                news.setTitle(title);
                news.setShort_content(short_content);
                news.setContent(content);
                news.setPicture_url(url);
                news.setLanguage(language);
                news.setPublication(publication);
                news.setCategory(category);



                if (DBManager.saveNew(news)){

                    redirect = "/newedit?id=" + new_id + "&success";

                }
            }
        }

        response.sendRedirect(redirect);

    }






    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }
}
