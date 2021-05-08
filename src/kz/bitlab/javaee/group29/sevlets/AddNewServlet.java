package kz.bitlab.javaee.group29.sevlets;


import kz.bitlab.javaee.group29.db.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/addnew")
public class AddNewServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String title = request.getParameter("title");
        String short_content = request.getParameter("short_content");
        String content = request.getParameter("content");
        String url = request.getParameter("url");


        Long lang_id = 0L;
        Long pub_id = 0L;
        Long category_id = 0L;

        Languages language = null;
        Publications publication = null;
        Categories category = null;


        try {
            lang_id = Long.parseLong(request.getParameter("language"));
            pub_id = Long.parseLong(request.getParameter("publication"));
            category_id = Long.parseLong(request.getParameter("category"));

        } catch (Exception e) {
            e.printStackTrace();
        }

        if (lang_id != null && pub_id != null && category_id != null) {
            language = DBManager.getLanguage(lang_id);
            publication = DBManager.getPublication(pub_id);
            category = DBManager.getCategory(category_id);
        }

        if (language != null && publication != null) {

            if (title != null && short_content != null && content != null && url != null && DBManager.addNewNew(new News(title, short_content, content, null, url, language, publication, category))) {
                response.sendRedirect("/adminnew?success");


            } else {

                response.sendRedirect("/adminnew?error");

            }
        } else {
            response.sendRedirect("/adminnew?error");
        }
    }



    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }

}



